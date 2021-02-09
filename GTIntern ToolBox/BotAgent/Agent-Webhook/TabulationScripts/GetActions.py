#!/usr/bin/env python3
import json
import os

folder = '../bilingual/intents'

outputList = []

for file in os.listdir(folder):
    if 'usersay' not in file:
        with open(folder + '/' + file) as f:
            base = json.load(f)
            try:
                action = base['responses'][0]['action']
            except:
                action = 'null'

            try:
                wh = base['webhookUsed']
            except:
                wh = 'false'
            
            if wh is True:
                print(file + "," + str(wh) + "," + action)
