#!/bin/bash
for filename in intentOnly-English/*.json; do
    {
    	cat "$filename" | {
    		jp -c '.responses[].parameters[] | "\(.dataType); \(.name); \(.value); \(.isList)"' 2> /dev/null; 
    	}
    } || {
    	echo "No value"
    }
	echo "====="
done

