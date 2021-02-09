#!/bin/bash
for filename in intentOnly-English/*.json; do
    {
    	cat "$filename" | {
    		jp -c '. | "\(.name);\(.contexts);\(.priority);\(.webhookUsed)"' 2> /dev/null; 
    	}
    } || {
    	echo "No value"
    }
done
