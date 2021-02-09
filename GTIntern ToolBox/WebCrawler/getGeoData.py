#!/usr/bin/env python3
import json
import urllib.request as rq
from urllib.parse import urlparse
import requests
from time import sleep

file_food = "shop_sg_food.json"
file_shopping = "shop_sg_shopping.json"

def getGeoInfo(shopname,address):
    geo_arr = []
    lng,lat = callGMapApi(shopname,address)
    geo_arr.append(lng)
    geo_arr.append(lat)
    return geo_arr

def callGMapApi(shopname,address):
    shopname = formatString(shopname)
    address = formatString(address)
    if "singapore" not in address.lower():
        address = address + " singapore" 

    query = shopname + " " + address

    #print(api)
    try:
        # try:
        #     api = "https://maps.googleapis.com/maps/api/geocode/json?address="+ query + "&key=[ACCESS_KEY]"
        #     f = requests.get(api)
        #     j = f.json()
        #     geo = j['results'][0]['geometry']['location']
        # except:
            api = "https://maps.googleapis.com/maps/api/geocode/json?address="+ address+ "&key=[ACCESS_KEY]"
            f = requests.get(api)
            j = f.json()
            geo = j['results'][0]['geometry']['location']
    except:
        print(api)
        print(j)
        geo = {
            'lng':0,
            'lat':0
        }
    
    return geo['lng'],geo['lat']

def formatString(text):
    text = text.replace("("," ").replace(")","").replace(","," ").replace("#"," ").replace("/"," ")
    return text

def processFile(filepath,key):
    print("Processing " + filepath + " \n=======================================================================================")
    with open(filepath) as f:
        lines = f.readlines()
        with open(filepath.replace(".json","_wGeo.json"), 'a') as f:
            for item in lines:
                obj = json.loads(item)
                shopname = obj[key]

                address = obj['address']

                print(shopname + ":" + address)
                obj['geo_location'] = getGeoInfo(shopname,address)
                print(obj['geo_location'])

                json.dump(obj,f,ensure_ascii=False)
                f.write('\n')
                sleep(1)
            
    
            

def main():
    #processFile(file_food,'name')
    #processFile(file_shopping,'shopname')

if __name__ == '__main__':
    main()

    