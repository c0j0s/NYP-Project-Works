# About this
This is a documentation regarding webhook translation and testing.

# Webhook Translation Info
## About UpdateLanguageDatasets.py
This is an extraction script that read and extract strings with translation codes from all webhooks to update or generate translation files for translation.  
___Environment: python3___  

Example:
```PHP
use PATH\language\lang;

//In webhook
lang::tl("Translate",$detectedLanguage);
//or OOP style
$lang = new Lang($detectedLanguage);
$lang->to("Translate")
```

```PHP
//In translation files
$text_lang["Translate"] = "translation here";
```
Usage:
```sh
#Startup
./UpdateLanguageDatasets.py

#Follow instruction to input following parameter
    #language code
    #webhook directory
    #translation file path
```

## About error_messages.php
This is a special translation file that contains error messages for translation. All error messages should exist in all languages.  

Example:
```PHP
$er['en'] = [
    'DEFAULT_ERROR' => "Error occurred, please try again later."
    .
    .
];

$er['zh-cn'] = [
    'DEFAULT_ERROR' => "发生异常，请稍后重试。"
    .
    .
];
```
Usage:
```PHP
//In webhook
lang::er("DEFAULT_ERROR",$detectedLanguage);
//or OOP style
$lang->ec("DEFAULT_ERROR")
```
# Webhook Testing Setup
From DialogFlow:  
>---  
>DialogFlow <-> ngrok tunnel <-> MiddleMan <-> Webhook
>___
From PostMan:
>---  
>PostMan <-> MiddleMan <-> PHP Webhook
>___

## About ngrok:
ngrok tunnel allow external request to be forwarded to localhost server.  
More info: https://ngrok.com/docs
```sh
#Setup command:
#./ngrok authtoken ACCESS_TOKEN
./ngrok authtoken ACCESS_TOKEN

#Startup command:
./ngrok http -region eu 5000

#Copy generated link to DialogFlow
https://example.ngrok.io -> 127.0.0.1:5000
```

## About MiddleMan:
MiddleMan will authenticate with stage api automatically on start, then it will handle all request from DialogFlow and injects additional data to simulate request sent by a robot frontend before forwarding to PHP webhook.  
___Environment: python3, flask___

It Injects following data:
- robot context:
    - name : "robot_context"
    - session : [from stageapi]
    - remember-me : [from stageapi]
    - latitude : ""
    - longtitude : ""
- session
    - session id replaced with test user id

```sh
#Startup command:
export FLASK_APP=MiddleMan.py
flask run
```

## Additional Environment Setup for Testing
### Redis -> Redis-cli
```sh
#Setup local test data
set hotel.TEST_USER_ID "{\"currency\":\"SGD\",\"displayPrice\":1000,\"name\":\"Tester\",\"userId\":\"TEST_USER_ID\"}"
```
### Local MySQL Database
Setup a local mysql server and load homestead.sql.
```sh
DB_USERNAME=username
DB_PASSWORD=password
```

## Additional Tools
### About bot.py and google_auth.json
>---  
>Bot <-> DialogFlow <-> ngrok tunnel <-> MiddleMan <-> PHP Webhook
>___
Bot is a simple chatbot style testing tool to interact and validate the dialogs and webhooks.  
It supports both languages  
___Environment: python3, dialogflow___

It requires google authentication files, before starting, configure the following variable:
```sh
export GOOGLE_APPLICATION_CREDENTIALS=./google_auth.json
./bot.py
```

google_auth.json can be download by creating a service account for dialogflow agent.