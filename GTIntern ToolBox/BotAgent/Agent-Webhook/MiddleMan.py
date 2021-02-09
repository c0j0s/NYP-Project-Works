#!/usr/bin/env python
from flask import Flask,request
import requests as rq
from flask import Response 
import json

"""
This is a mini server that simulates wonder boy frontend function for webhook testing
        Webhook testing setup 3:
        ------------------------------------------------------------------------
        | DialogFlow -> ngrok tunnel -> MiddleMan Flask server -> PHP webhooks |
        ------------------------------------------------------------------------
"""

print(
"""
            ====================
                MiddleMan
            ====================
            Au       :COJOS
            createdOn:08/10/2018
            updatedOn:10/10/2018
            ====================
"""
)


LOCALHOST = "http://localhost:8000/webhook"
TEST_ID = "TEST_USER_ID"
SESSION_ID = ""
REMEMBER_ME = ""

print("[ Authenticating with stageapi ]")
data = {
    'email':'API_EMAIL',
    'password':'API_PASSWORD'
}
response = rq.post("API_LOGIN_URL", data=data)
result = json.loads(response.text)

if result["code"] == "ok":
    SESSION_ID = response.cookies["SESSION"]
    REMEMBER_ME = response.cookies["remember-me"]
    print("SESSION: \n" + SESSION_ID)
    print("REMEMBER-ME: \n" + REMEMBER_ME)
    print("[ Authentication Complete, Starting Flask ] \n")
    app = Flask(__name__)
else:
    print("Authentication Failed")
    print(response.text)
    exit()

@app.route("/webhook",methods=['POST'])
def main():
    gtdollar = {
        "name":"gtdollar",
        "parameters":{
            "session": SESSION_ID,
            "remember-me": REMEMBER_ME,
            "latitude":"latitude",
            "longitude":"longitude"
        }
    }

    content = request.get_json()
    content["session"] = "projects/project-name/agent/sessions/" + TEST_ID

    if("outputContexts" not in content["queryResult"]):
        content["queryResult"]["outputContexts"] = []

    content["queryResult"]["outputContexts"].append(gtdollar)

    response = rq.post(LOCALHOST, json=content)
    resp = Response(response.text, status=200, mimetype='application/json')
    return resp

@app.route("/",methods=['GET'])
def info():
    return "MiddleMan"