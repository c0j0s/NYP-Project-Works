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

def sentRequest(line):
    headers = {'content-type': 'application/json','Authorization':'Bearer ' + CLIENT_ACCESS_TOKEN}
    url = "https://api.dialogflow.com/v1/contexts?sessionId=TEST_SESSION_CN"
    response = requests.delete(url, headers=headers)

    print("------------------")
    print(response)

    ai = apiai.ApiAI(CLIENT_ACCESS_TOKEN)
    request = ai.text_request()
    request.lang = 'zh-CN' 
    request.session_id = "TEST_SESSION_CN"
    request.query = line
    response = request.getresponse()
    data = response.read()
    json_data = json.loads(data)
    print(json_data["status"]["errorType"] +  " ---- " + line)
    return json_data


for file in os.listdir("cn_tf/"):
    print(file + ": ")
    responseArr = []
    if file.endswith(".json"):
        bs = "/"
        bseq = ("cn_tf", file)
        bpath = bs.join(bseq)

        with open(bpath) as f:
            base = json.load(f)
            for line in base:
                responseArr.append(sentRequest(line))

        with open('cn_response_no_wh/'+ file.replace("_tf.json","_rep.json"),'w',encoding='utf8') as f:
            json.dump(responseArr,f,indent=2,ensure_ascii=False)
        print("File exported")
        print("===================")
    else:
        print("NOT JSON FILE")

