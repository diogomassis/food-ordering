DROP SCHEMA IF EXISTS "order" CASCADE;

CREATE SCHEMA "order";

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Configure search_path to include customer schema
ALTER DATABASE "order" SET search_path TO "order", customer, public;


DROP TYPE IF EXISTS order_status;
CREATE TYPE order_status AS ENUM ('PENDING', 'PAID', 'APPROVED', 'CANCELLED', 'CANCELLING');

DROP TABLE IF EXISTS "order".orders CASCADE;

CREATE TABLE "order".orders
(
    id uuid NOT NULL,
    customer_id uuid NOT NULL,
    restaurant_id uuid NOT NULL,
    tracking_id uuid NOT NULL,
    price numeric(10,2) NOT NULL,
    order_status order_status NOT NULL,
    failure_messages character varying COLLATE pg_catalog."default",
    CONSTRAINT orders_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS "order".order_items CASCADE;

CREATE TABLE "order".order_items
(
    id bigint NOT NULL,
    order_id uuid NOT NULL,
    product_id uuid NOT NULL,
    price numeric(10,2) NOT NULL,
    quantity integer NOT NULL,
    sub_total numeric(10,2) NOT NULL,
    CONSTRAINT order_items_pkey PRIMARY KEY (id, order_id)
);

ALTER TABLE "order".order_items
    ADD CONSTRAINT "FK_ORDER_ID" FOREIGN KEY (order_id)
    REFERENCES "order".orders (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE
    NOT VALID;

DROP TABLE IF EXISTS "order".order_address CASCADE;

CREATE TABLE "order".order_address
(
    id uuid NOT NULL,
    order_id uuid UNIQUE NOT NULL,
    street character varying COLLATE pg_catalog."default" NOT NULL,
    postal_code character varying COLLATE pg_catalog."default" NOT NULL,
    city character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT order_address_pkey PRIMARY KEY (id, order_id)
);

ALTER TABLE "order".order_address
    ADD CONSTRAINT "FK_ORDER_ID" FOREIGN KEY (order_id)
    REFERENCES "order".orders (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE
    NOT VALID;

DROP TYPE IF EXISTS saga_status;
CREATE TYPE saga_status AS ENUM ('STARTED', 'FAILED', 'SUCCEEDED', 'PROCESSING', 'COMPENSATING', 'COMPENSATED');

DROP TYPE IF EXISTS outbox_status;
CREATE TYPE outbox_status AS ENUM ('STARTED', 'COMPLETED', 'FAILED');

DROP TABLE IF EXISTS "order".payment_outbox CASCADE;

CREATE TABLE "order".payment_outbox
(
    id uuid NOT NULL,
    saga_id uuid NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    processed_at TIMESTAMP WITH TIME ZONE,
    type character varying COLLATE pg_catalog."default" NOT NULL,
    payload jsonb NOT NULL,
    outbox_status outbox_status NOT NULL,
    saga_status saga_status NOT NULL,
    order_status order_status NOT NULL,
    version integer NOT NULL,
    CONSTRAINT payment_outbox_pkey PRIMARY KEY (id)
);

CREATE INDEX "payment_outbox_saga_status"
    ON "order".payment_outbox
    (type, outbox_status, saga_status);

--CREATE UNIQUE INDEX "payment_outbox_saga_id"
--    ON "order".payment_outbox
--    (type, saga_id, saga_status);

DROP TABLE IF EXISTS "order".restaurant_approval_outbox CASCADE;

CREATE TABLE "order".restaurant_approval_outbox
(
    id uuid NOT NULL,
    saga_id uuid NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    processed_at TIMESTAMP WITH TIME ZONE,
    type character varying COLLATE pg_catalog."default" NOT NULL,
    payload jsonb NOT NULL,
    outbox_status outbox_status NOT NULL,
    saga_status saga_status NOT NULL,
    order_status order_status NOT NULL,
    version integer NOT NULL,
    CONSTRAINT restaurant_approval_outbox_pkey PRIMARY KEY (id)
);

CREATE INDEX "restaurant_approval_outbox_saga_status"
    ON "order".restaurant_approval_outbox
    (type, outbox_status, saga_status);

--CREATE UNIQUE INDEX "restaurant_approval_outbox_saga_id"
--    ON "order".restaurant_approval_outbox
--    (type, saga_id, saga_status);

-- Criar uma view no schema order que referencia a materialized view do customer
-- Isso permite que o Hibernate acesse os dados do customer através do schema order
DROP VIEW IF EXISTS "order".order_customer_m_view;
CREATE VIEW "order".order_customer_m_view AS 
SELECT id, username, first_name, last_name 
FROM customer.order_customer_m_view;

-- Criar schema restaurant e materialized view para dados de mock
DROP SCHEMA IF EXISTS restaurant CASCADE;
CREATE SCHEMA restaurant;

-- Criar tabela de restaurantes mock
CREATE TABLE restaurant.restaurants
(
    id uuid NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    active boolean NOT NULL DEFAULT true,
    CONSTRAINT restaurants_pkey PRIMARY KEY (id)
);

-- Criar tabela de produtos mock
CREATE TABLE restaurant.products
(
    id uuid NOT NULL,
    restaurant_id uuid NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    price numeric(10,2) NOT NULL,
    CONSTRAINT products_pkey PRIMARY KEY (id),
    CONSTRAINT fk_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurant.restaurants (id)
);

-- Inserir dados mock de restaurante
INSERT INTO restaurant.restaurants(id, name, active)
VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb45', 'Test Restaurant', true);

-- Inserir dados mock de produtos
INSERT INTO restaurant.products(id, restaurant_id, name, price)
VALUES 
  ('d215b5f8-0249-4dc5-89a3-51fd148cfb48', 'd215b5f8-0249-4dc5-89a3-51fd148cfb45', 'Test Product 1', 50.00),
  ('d215b5f8-0249-4dc5-89a3-51fd148cfb49', 'd215b5f8-0249-4dc5-89a3-51fd148cfb45', 'Test Product 2', 25.00);

-- Criar materialized view restaurant
DROP MATERIALIZED VIEW IF EXISTS restaurant.order_restaurant_m_view;
CREATE MATERIALIZED VIEW restaurant.order_restaurant_m_view
TABLESPACE pg_default
AS
SELECT 
    p.id as product_id,
    r.id as restaurant_id,
    p.name as product_name,
    p.price as product_price,
    r.active as restaurant_active,
    r.name as restaurant_name
FROM restaurant.restaurants r
JOIN restaurant.products p ON r.id = p.restaurant_id
WITH DATA;

-- Refresh a materialized view
REFRESH MATERIALIZED VIEW restaurant.order_restaurant_m_view;

-- Conceder permissões no schema restaurant
GRANT USAGE ON SCHEMA restaurant TO postgres;
GRANT SELECT ON restaurant.order_restaurant_m_view TO postgres;
GRANT SELECT ON ALL TABLES IN SCHEMA restaurant TO postgres;
