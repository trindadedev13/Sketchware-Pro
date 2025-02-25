#!/bin/bash

DIRECTORY="../" # use ../ because the script is inside the shellscript folder

# Text to be replaced
OLD_TEXT="extends BaseAppCompatActivity"
NEW_TEXT="extends BaseAppCompatActivity"

find "$DIRECTORY" -name "scripts" -o -name ".git" -prune -o -type f -print | while read -r file; do
    sed -i "s/$OLD_TEXT/$NEW_TEXT/g" "$file"
    echo "Text replaced in: $file"
done

echo "Replacement completed!"