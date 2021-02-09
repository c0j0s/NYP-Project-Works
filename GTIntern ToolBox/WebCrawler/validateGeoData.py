#!/usr/bin/env python3
import json
import urllib.request as rq
from urllib.parse import urlparse
import requests
from time import sleep

inputfile = "output.json"

def processFile(filepath,key):
    print("Processing " + filepath + " \n=======================================================================================")
    with open(filepath) as f:
        items = json.load(f)
        for obj in items:
            address = obj['address']

            lng = float(obj['geo_location']['lng'])
            lat = float(obj['geo_location']['lat'])
            if(lng > 104 or lng < 103 ) or (lat > 1.5 or lat < 1.2):
                print(address + ":" + str(obj['geo_location']))
                    
    
def main():
    processFile(inputfile,'name')

if __name__ == '__main__':
    main()

    