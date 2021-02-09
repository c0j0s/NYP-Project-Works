#!/usr/bin/env python3
import json
import sys
import os
from pprint import pprint
import requests

try:
    import apiai
except ImportError:
    sys.path.append(
        os.path.join(os.path.dirname(os.path.realpath(__file__)), os.pardir)
    )
    import apiai

CLIENT_ACCESS_TOKEN = 'ACCESS_TOKEN'

def sentRequest(line,langCode):
    ai = apiai.ApiAI(CLIENT_ACCESS_TOKEN)
    request = ai.text_request()
    request.session_id = "TEST_SESSION_BI"
    request.lang = langCode
    request.query = line
    response = request.getresponse()
    data = response.read()
    json_data = json.loads(data)
    print(json_data["status"]["errorType"] +  " -- "+ langCode +" -- " + line)
    return json_data


for file in os.listdir("bi_tf/"):
    print(file + ": ")
    engResArr = []
    chiResArr = []
    if file.endswith(".json"):
        bs = "/"
        bseq = ("bi_tf", file)
        bpath = bs.join(bseq)

        with open(bpath) as f:
            base = json.load(f)
            for line in base["en"]:
                engResArr.append(sentRequest(line,"en"))
            for line in base["zh-cn"]:
                chiResArr.append(sentRequest(line,"zh-cn"))
                
        responseObj = {}
        responseObj['en'] = engResArr
        responseObj['zh-cn'] = chiResArr
        with open('bi_response_no_wh/'+ file.replace("_tf.json","_rep.json"),'w',encoding='utf8') as f:
            json.dump(responseObj,f,indent=2,ensure_ascii=False)
        print("File exported")
        print("===================")
    else:
        print("NOT JSON FILE")

