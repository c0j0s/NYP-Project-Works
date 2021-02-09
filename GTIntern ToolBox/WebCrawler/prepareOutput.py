#!/usr/bin/env python3
import json

output = "output.json"
validate = "validate.json"

lang = "zh-cn"

with open(output) as f:
    collection = json.load(f)

with open(validate) as f:
    validateCollection = json.load(f)

for count, elem in enumerate(validateCollection):
    oldGeo = collection[count]['geo_location']
    if elem['geo_location'] is not oldGeo:
        collection[count]['geo_location'] = elem['geo_location']

with open("attractions_"+ lang +"_wGeo.json", "a") as myfile:
    for item in collection:
        newItem = item

        newItem['name'] = item[lang]['name']
        newItem['opening_hours'] = item[lang]['opening_hours']
        newItem['recommended_duration'] = item[lang]['recommended_duration']
        newItem['summary'] = item[lang]['summary']
        newItem['ticket_info'] = item[lang]['ticket_info']
        newItem['transportation'] = item[lang]['transportation']
        geo_arr = [item["geo_location"]["lng"],item["geo_location"]["lat"]]
        newItem["geo_location"] = geo_arr
        newItem['contact'] = newItem['telephone']

        del newItem['telephone'] 
        del newItem['en'] 
        del newItem['zh-cn']

        json.dump(newItem,myfile,ensure_ascii=False)
        myfile.write('\n')
