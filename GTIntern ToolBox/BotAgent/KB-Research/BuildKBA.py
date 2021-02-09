#!/usr/bin/env python3
import json
import csv
file = "./sample_biz.csv"

lines = []

with open(file) as f:
    spamreader = csv.reader(f, delimiter=',', quotechar='"')
    print("Feeding file...................................")
    for subject in spamreader:
        lines.append(subject[2] + " is located at " + subject[0] + ", the country of " + subject[1] + ". The hotel rating is " + subject[4] + " stars. the hotel's offical website url link is " + subject[5] + "." )

print("Outputing file.................................")
with open('hotel.txt', 'w') as f:
    for line in lines:
        print(line)
        f.write(line + "\n\r\n")
    
print("Complete.......................................")
