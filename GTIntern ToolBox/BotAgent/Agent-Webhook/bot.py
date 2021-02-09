#!/usr/bin/env python3
import json
import sys
import os
import dialogflow_v2 as dialogflow

feeds = []

def detect_intent_texts(project_id, session_id, texts, language_code):
    """Returns the result of detect intent with texts as inputs.

    Using the same `session_id` between requests allows continuation
    of the conversaion."""
    session_client = dialogflow.SessionsClient()

    session = session_client.session_path(project_id, session_id)

    text_input = dialogflow.types.TextInput(
        text=texts, language_code=language_code)

    query_input = dialogflow.types.QueryInput(text=text_input)

    response = session_client.detect_intent(
        session=session, query_input=query_input)

    with open('log.json', mode='w') as feedsjson:
        feeds.append(str(response))
        json.dump(feeds, feedsjson, indent=4, ensure_ascii=False)

    print('BOT: {}\n'.format(
        response.query_result.fulfillment_text))

    if response.query_result.fulfillment_text is "":
        print("---------------------------------------")
        print(" Issue ouputing text, thus raw output:")
        print(' status: {}'.format(
        response.webhook_status.message))
        print(" Ignore this issue")
        print("---------------------------------------")

print('''
=================================================================
BotBot by COJOS:
    A simple testing chat bot.
    createdOn:28/09/18
    updatedOn:09/10/18

Require:
    google_auth.json from DialogFlow agant google service account

Next configure the environment:
    bash $: export GOOGLE_APPLICATION_CREDENTIALS=./google_auth.json

Then restart the bot again
=================================================================
''')

lang = input('\nChoose a language[1:en[default], 2:zh]: ')

if lang is '':
    lang = 'en'
elif int(lang) == 1:
    lang = 'en'
else:
    lang = 'zh-cn'

with open('log.json', mode='w') as f:
    json.dump([], f)

while(True):
    line = str(input('\nMessage[exit to quit]: '))
    if(line=='exit'):
        exit(0)
    elif(line is ''):
        line = 'hi'

    project_id = 'gtwonderboy-test'
    session_id = 'TEST_USER_ID'
    texts = line
    language_code = lang
    
    detect_intent_texts(project_id,session_id,texts,language_code)
