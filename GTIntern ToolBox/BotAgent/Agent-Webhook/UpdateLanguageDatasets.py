#!/usr/bin/env python3
import re
import os

print('''

==============================================
    Extraction Script
----------------------------------------------
    CreateDate:   26/9/18
    LastUpdateOn: 28/9/18
    Usage: 
        1) enter an existing language code to 
        update the translation files.
        2) enter a new language code to create
        new translation files.
    COJOS
----------------------------------------------''')

defaultLangCode = "zh-cn"
langCode = input("Please enter the language code [" + defaultLangCode  + "]: ")

if langCode == "en":
    print("Translation is based on english language, thus no translation files is needed, bye.")
    exit()
if langCode is "":
    langCode = defaultLangCode


defalutfolder = "./PATH/multiLang/"
defaultoutput = "./PATH/language/translation_en_"+ langCode +".php"

folder = input("Please enter the webhook folder path [" + defalutfolder  + "]: ")
output = input("Please enter the tranlation file path [" + defaultoutput + "]: ")

if folder is "":
    folder = defalutfolder

if output is "":
    output = defaultoutput

print('''------------------------------------------
Step 1: Reading existing translation files''')

tl = {}
keys = []
values = []

if(not os.path.isfile(output)):
    open(output,"w+") 
else:
    patternTLkey = re.compile('\[".*?\"]')
    patternTLvalue = re.compile('\=.".*?\"')

    for i, line in enumerate(open(output)):
        for match in re.finditer(patternTLkey, line): 
            text = match.group(0).replace('["','').replace('"]','')
            keys.append(text)

        for match in re.finditer(patternTLvalue, line): 
            text = match.group(0).replace('= ','').replace('"','')
            values.append(text)

print('''------------------------------------------
Step 2: Reading all webhook files....''')

patternSQ = re.compile("lang..t.\(\'.*?\'")
patternDQ = re.compile('lang..t.\(\".*?\"')

transList = []

for file in os.listdir(folder):
    fileObj = {}
    senList = []
    file_path = os.fsdecode(file)
    print(file_path)
    fileObj['file'] = file_path
    for i, line in enumerate(open(folder + file_path)):
        for match in re.finditer(patternSQ, line):
            string = match.group(0).replace("lang::tl(","").replace("lang->to(","").replace("'","")
            senList.append(string)
        for match in re.finditer(patternDQ, line):
            string = match.group(0).replace("lang::tl(","").replace("lang->to(","").replace('"',"")
            senList.append(string)
    fileObj['items'] = senList
    transList.append(fileObj)

print('''------------------------------------------
Step 3: Removing duplicates''')

seenTable = []
filteredList = []
for obj in transList:
    itemList = []
    for item in obj['items']:
        if item not in seenTable:
            seenTable.append(item)
            itemList.append(item)

    obj['items'] = itemList
    filteredList.append(obj)

print('''------------------------------------------
Step 4: Writing to translation files''')
arrayKeyname = langCode.replace('-','_')
with open(output, "w") as tlfile:
    tlfile.write("""<?php
/**
 * Created by PhpStorm.
 * User: c0j0s
 * Date: 26/9/18
 * Time: 4:32 PM
 * 
 * DESCRIPTION:
 * this file is generated using scripts
 * by looking for patterns in all webhook files:
 *      lang::tl( | lang->to(
 *
 * TAKE NOTE:
 * This is not for direct translation but rather it is for mode tranlation as it may be use for condition checking.
 *      e.g. "chinese" is tranlated into "english" instead of "英文".
 * Keep special characters including spacing ot \\n after tanslation.
 *
 * KNOWN ISSUES:
 * using '[' in original text will cause error.
 * double qoutes might cause issues
 * cannot have duplicate keys.
 * ===========================   PLEASE DO NOT CHANGE THE FORMAT, EDITING OF VALUE IS FINE   ================================= */
 """)
    for obj in filteredList:
        name = obj['file']
        items = obj['items']
        if(len(items) > 0):
            tlfile.write("""

/*
 * Text found in """ + name +  """
 */
""")
            for index,item in enumerate(items):
                if(item in keys):
                    translated = values[keys.index(item)]
                    tlfile.write('$text_'+arrayKeyname+'["'+ item +'"] = "'+ translated +'";\n')
                else:
                    tlfile.write('$text_'+arrayKeyname+'["'+ item +'"] = "";\n')
    tlfile.close()

print('''------------------------------------------
Script Completed:
Remember to update the codes for 
new translation files.
==========================================
''')