#!/usr/bin/env python3
import json
import sys
import os
from pprint import pprint

def extractFile(path):
    sentenceArr = []
    for line in base:
        sentence = ""
        if line["isTemplate"] is False:
            for item in line["data"]:
                for word in item["text"]:
                    sentence += word
            sentenceArr.append(sentence)
    return sentenceArr


directory = os.fsencode("en_tf")

for file in os.listdir(directory):
    filename = os.fsdecode(file)
    if filename.endswith(".json"):
        bs = "/"
        bseq = ("../usersays", filename)
        bpath = bs.join(bseq)

        with open(path) as f:
            base = json.load(f)

        print(base)
        print("==================")
    else:
        print("END")

