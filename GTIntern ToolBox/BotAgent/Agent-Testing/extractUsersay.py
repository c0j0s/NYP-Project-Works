#!/usr/bin/env python3
import json
import sys
import os
from pprint import pprint

directory = os.fsencode("../folder/intents")

for file in os.listdir(directory):
    filename = os.fsdecode(file)
    if filename.endswith(".json"):

        sentenceArr = []
        filename = filename.replace(".json", "_usersays_en.json")
        bs = "/"
        bseq = ("../usersays", filename)
        bpath = bs.join(bseq)
        print(bpath)
        if(os.path.isfile(bpath)):
	        with open(bpath) as f:
	            base = json.load(f)

	            for line in base:
	            	sentence = ""
	            	if line["isTemplate"] is False:
		            	for item in line["data"]:
		            		for word in item["text"]:
		            			sentence += word
		            	sentenceArr.append(sentence)

        with open('en_tf/'+filename.replace("_usersays_en.json","_tf.json"), 'w',encoding='utf8') as f:
             json.dump(sentenceArr, f, indent=2, ensure_ascii=False)
        print("==================")
    else:
        print("END")
