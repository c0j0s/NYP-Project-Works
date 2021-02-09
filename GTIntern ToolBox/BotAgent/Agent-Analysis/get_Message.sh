#!/bin/bash
for filename in intentOnly-English/*.json; do
    {
    	cat "$filename" | {
    		jp -c .responses[].messages[].speech[] 2> /dev/null; 
    	}
    } || {
    	echo "No value"
    }
	echo "===="
done
