#!/usr/bin/env python3
import json
import sys
import os
from pprint import pprint



directory = os.fsencode("../intents-complex/2 - resolvedParameterPrompts/")

for file in os.listdir(directory):
    filename = os.fsdecode(file)
    if filename.endswith(".json"):
        engArr = []
        chiArr = []
        tfFile = filename.replace(".json", "_tf.json")

        bs = "/"
        bseq = ("en_tf", tfFile)
        bpath = bs.join(bseq)
        print(bpath)

        cs = "/"
        cseq = ("cn_tf", tfFile)
        cpath = cs.join(cseq)
        print(cpath)

        if(os.path.isfile(bpath) and os.path.isfile(cpath)):
            with open(bpath) as f:
                base = json.load(f)
                engArr += base

            with open(cpath) as f:
                base = json.load(f)
                chiArr += base
            sentenceArr = {}
            sentenceArr['en'] = engArr
            sentenceArr['zh-cn'] = chiArr        
            print(sentenceArr)
            with open('bi_tf/' + tfFile , 'w',encoding='utf8') as f:
                json.dump(sentenceArr, f, indent=2, ensure_ascii=False)
        else:
            sentenceArr = {}
            if(os.path.isfile(bpath)):
                with open(bpath) as f:
                    base = json.load(f)
                    engArr += base
                    sentenceArr['en'] = engArr  
            if(os.path.isfile(cpath)):
                with open(cpath) as f:
                    base = json.load(f)
                    chiArr += base
                    sentenceArr['zh-cn'] = chiArr
     
            print(sentenceArr)
            with open('bi_tf/' + tfFile , 'w',encoding='utf8') as f:
                json.dump(sentenceArr, f, indent=2, ensure_ascii=False)

        print("==================")
    else:
        print("END")

