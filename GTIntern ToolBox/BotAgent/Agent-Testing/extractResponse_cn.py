#!/usr/bin/env python3
import json
import sys
import os
from pprint import pprint


for file in os.listdir("cn_com_response/"):
    if file.endswith(".json"):
        bs = "/"
        bseq = ("cn_com_response", file)
        bpath = bs.join(bseq)
        with open(bpath) as f:
            base = json.load(f)
            for response in base:
                line = file.replace('_rep.json','') + "/t" + str(response["result"]["resolvedQuery"]) + "/t" + str(response["result"]["metadata"]["intentName"])  + "/t" + str(response["result"]["fulfillment"]["speech"]) + "/t" + str(response["result"]["parameters"]) + "/t" + str(response["result"]["action"])
                print(line)
        
        #with open('en_tf/'+filename.replace("_usersays_en.json","_tf.json"), 'w',encoding='utf8') as f:
             #json.dump(sentenceArr, f, indent=2, ensure_ascii=False)
        #print("==================")
    else:
        print("END")
