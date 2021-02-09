#!/usr/bin/env python3
import json
import sys
import os
from pprint import pprint

directory = os.fsencode("folder/intents")
for file in os.listdir(directory):
    filename = os.fsdecode(file)
    if filename.endswith(".json"):
        print(filename)
        bs = "/"
        bseq = ("folder/intents", filename)
        bpath = bs.join(bseq)
        with open(bpath) as f:
            base = json.load(f)

        cs = "/"
        cseq = ("folder/intents", filename)
        cpath = cs.join(cseq)
        with open(cpath) as f:
            chinese = json.load(f)
        
        for cresponses in chinese["responses"][0]["messages"]:
            if "speech" in cresponses:
                if len(cresponses["speech"]) > 0:
                    base["responses"][0]["messages"].append(cresponses)

            
            if "payload" in cresponses:
                base["responses"][0]["messages"].append(cresponses)

        for responses in base["responses"][0]["messages"]:
            pprint(responses)
        
        with open('intents-resolvedMsg/'+filename, 'w',encoding='utf8') as f:
            json.dump(base, f, indent=2, ensure_ascii=False)
        print("==================")
    else:
        print("END")
