#!/usr/bin/env python3
import json
import sys
import os
from pprint import pprint

try:
    import apiai
except ImportError:
    sys.path.append(
        os.path.join(os.path.dirname(os.path.realpath(__file__)), os.pardir)
    )
    import apiai

CLIENT_ACCESS_TOKEN = 'ACCESS_TOKEN'

def sentRequest(line):
    ai = apiai.ApiAI(CLIENT_ACCESS_TOKEN)
    request = ai.text_request()
    request.lang = 'en'  
    request.session_id = "TEST_SESSION_EN"
    request.query = line
    response = request.getresponse()
    data = response.read()
    json_data = json.loads(data)
    print(json_data["status"]["errorType"] +  " ---- " + line)
    return json_data


for file in os.listdir("en_tf/"):
    print(file + ": ")
    responseArr = []
    if file.endswith(".json"):
        bs = "/"
        bseq = ("en_tf", file)
        bpath = bs.join(bseq)

        with open(bpath) as f:
            base = json.load(f)
            for line in base:
                responseArr.append(sentRequest(line))

        with open('en_response/'+ file.replace("_tf.json","_rep.json"),'w',encoding='utf8') as f:
            json.dump(responseArr,f,indent=2,ensure_ascii=False)
        print("File exported")
        print("===================")
    else:
        print("NOT JSON FILE")

