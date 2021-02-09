#!/bin/bash
for filename in entities/*.json; do
    filename=${filename##*/}
    basename=${filename%".json"}
    echo $basename
    enName=$basename"_entries_en.json"
    cnName=$basename"_entries_zh-cn.json"
    if [ -e "entityEntries-en/$enName" ] && [ -e "entityEntries-cn/$cnName" ]
    then
        echo "yes"
    else
        echo "no"
        if [ -e "entityEntries-en/$enName" ] 
        then
            rm "entityEntries-en/$enName" 
        elif [ -e "entityEntries-cn/$cnName" ] 
        then
            rm "entityEntries-cn/$cnName" 
        fi
    fi
done
