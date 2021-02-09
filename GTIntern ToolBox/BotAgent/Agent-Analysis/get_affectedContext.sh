#!/bin/bash
for filename in intentOnly-English/*.json; do
    {
    	cat "$filename" | {
    		jp -c .responses[].affectedContexts[] 2> /dev/null; 
    	}
    } || {
    	echo "No value"
    }
	echo "====="
done
