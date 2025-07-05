# ---
# PowerShell Script to find and remove all 'target' directories from the current location downwards.
# This is useful for cleaning up Maven or Gradle projects, such as Spring Boot applications.
# ---

Write-Host "🚀 Starting cleanup..."

# Get-ChildItem: Gets the items and child items in one or more specified locations.
# -Path .: Specifies the path to the current directory.
# -Recurse: Gets the items in the specified locations and in all child items of the locations.
# -Directory: Specifies that we are looking for directories.
# -Name "target": Specifies the name of the directory to find.
# | (Pipe): Sends the output of the Get-ChildItem command to the Remove-Item command.
# Remove-Item: Deletes the specified items.
# -Recurse: Deletes the items in the specified locations and in all child items of the locations.
# -Force: Forces the command to run without asking for user confirmation.

Get-ChildItem -Path . -Recurse -Directory -Name "target" | Remove-Item -Recurse -Force

Write-Host "✅ Cleanup complete. All 'target' directories have been removed."
