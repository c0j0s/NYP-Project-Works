#!/bin/bash
# move all singular files to intent folder
for filename in folder/*.json; do
    filename=${filename##*/}
    if [ -e "folder/$filename" ]
    then
        echo "$filename exist"
    else
        echo "$filename not exist"
        rm "folder/$filename" "intents"
    fi
done

for filename in folder/*.json; do
    filename=${filename##*/}
    if [ -e "folder/$filename" ]
    then
        echo "$filename exist"
    else
        echo "$filename not exist"
        rm "folder/$filename" "intents"
    fi
done