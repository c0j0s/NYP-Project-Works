#!/usr/bin/env python3
import fileinput
import os
from tempfile import mkstemp
from shutil import move
from os import fdopen, remove


def replace(file_path, pattern, subst):
    #Create temp file
    fh, abs_path = mkstemp()
    with fdopen(fh,'w') as new_file:
        with open(file_path) as old_file:
            for line in old_file:
                new_file.write(line.replace(pattern, subst))
    #Remove original file
    remove(file_path)
    #Move new file
    move(abs_path, file_path)


folder = "../folder/multiLang/"
directory = os.fsencode(folder)
for file in os.listdir(directory):
    file_path = os.fsdecode(file)
    print(file_path)
    replace(folder + file_path,"namespace PATH\ZhCn;","namespace PATH\multiLang; \nuse PATH\language\lang;")
    print("======")