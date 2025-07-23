package com.food.ordering.system.domain;

/**
 * Utility class containing common constants used across the domain layer.
 * This class provides centralized access to shared constant values that are
 * used throughout the food ordering system domain.
 */
public class DomainConstants {
    /**
     * Private constructor to prevent instantiation of this utility class.
     * This class is designed to be used statically and should not be instantiated.
     */
    private DomainConstants() {
    }

    /**
     * Standard UTC timezone identifier used for consistent timestamp handling
     * across the domain layer.
     */
    public static final String UTC = "UTC";
}
