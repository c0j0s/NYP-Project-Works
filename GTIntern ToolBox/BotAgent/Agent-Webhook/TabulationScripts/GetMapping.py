#!/usr/bin/env python3
import json
import os
import re

mappingFile = 'PATH/webhook.php'
pattern = re.compile("\'.*\'")

mappingList = []
actionList = {}

for i, line in enumerate(open(mappingFile)):
    for match in re.finditer(pattern, line): 
        text = match.group(0).replace("'",'').split(',')
        obj = {}
        obj['action'] = text[0]
        function = text[1].replace('PATH_STRING','').split('@')
        obj['webhook'] = function[0]
        obj['function'] = function[1]
        mappingList.append(obj)
        actionList[obj['action']] = i

intentObjs = []
for i, lines in enumerate(open('out.csv')):
    obj = lines.replace('\n','').split(',')
    intentObjs.append(obj)

for intent in intentObjs:
    try:
        index = actionList[intent[2]]
        mapping = mappingList[index]
        intent.append(mapping['webhook'])
        intent.append(mapping['function'])
        print(intent[0] + ',' + intent[1] + ','+ intent[2] + ','+ mapping['webhook'] + ','+ mapping['function'])
    except:
        print(intent[0] + ',' + intent[1] + ','+ intent[2] + ',NULL,NULL')


