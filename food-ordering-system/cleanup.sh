#!/bin/bash

# ---
# Script to find and remove all 'target' directories from the current location downwards.
# This is useful for cleaning up Maven or Gradle projects, such as Spring Boot applications.
# ---

echo "🚀 Starting cleanup..."

# Find all directories named 'target' in the current directory and its subdirectories,
# then execute 'rm -rf' on them.
# -type d: Specifies that we are looking for directories.
# -name "target": Specifies the name of the directory to find.
# -exec rm -rf {}: Executes the remove command.
#   - rm: The remove command.
#   - -r: Recursive, to remove directories and their contents.
#   - -f: Force, to ignore nonexistent files and never prompt for confirmation.
#   {} +: A placeholder for the found directory paths, and the '+' makes the command more efficient
#          by grouping multiple paths into a single 'rm' command.
find . -type d -name "target" -exec rm -rf {} +

echo "✅ Cleanup complete. All 'target' directories have been removed."