#!/usr/bin/env python3
import json
import sys
import os
from pprint import pprint

def recordMergeParameters(filename,parameters):
    fo = open("intents-complex/parameterMergeRecord.txt", "a")
    fo.write(filename+"\n")
    for parameter in parameters:
        fo.write(parameter["name"] + "\n")
    fo.write("\n=========\n")
    fo.close()

directory = os.fsencode("intents-complex/1 - resolvedMsg/")
for file in os.listdir(directory):
    filename = os.fsdecode(file)
    if filename.endswith(".json"):
        print(" [ " + filename + " ] ")

        bs = "/"
        bseq = ("intents-complex/1 - resolvedMsg/", filename)
        bpath = bs.join(bseq)
        with open(bpath) as f:
            base = json.load(f)

        for responses in base["responses"]:

            cs = "/"
            cseq = ("folder/intents", filename)
            cpath = cs.join(cseq)
            with open(cpath) as f:
                chinese = json.load(f)

            for cresponses in chinese["responses"]:
                if(len(cresponses["parameters"]) > 0):
                    bnames = []
                    for parameter in responses["parameters"]:
                        bnames.append(parameter["name"])

                    for cparameter in cresponses["parameters"]:
                        if(cparameter["name"] in bnames):
                            if ("prompts" in cparameter):
                                index = 0
                                for idx, val in enumerate(responses["parameters"]):
                                    if(val["name"]==cparameter["name"]):
                                        index = idx
                                for prompt in cparameter["prompts"]:
                                    base["responses"][0]["parameters"][index]["prompts"].append(prompt)
                                print(base["responses"][0]["parameters"][index])
                        else:
                            base["responses"][0]["parameters"].append(cparameter)
                            print(base["responses"][0]["parameters"])
                            recordMergeParameters(filename,base["responses"][0]["parameters"])
        
        with open('intents-complex/2 - resolvedParameterPrompts/'+filename, 'w',encoding='utf8') as f:
            json.dump(base, f, indent=2, ensure_ascii=False) 
        print("============END===========")
    else:
        print("END")
