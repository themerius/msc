#!/bin/bash

for file in *.svg; do 
  echo "Diese Datei: $file"
  /Applications/Inkscape.app/Contents/Resources/bin/inkscape -f "$file" -A "$file.pdf"
done