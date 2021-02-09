#!/usr/bin/env python3
import json
import csv
file = "./sample_biz.csv"

name_address = []
name_website = []
name_country = []
name_ratings = []

address_name = []
address_website = []
address_country = []
address_ratings = []

with open(file) as f:
    spamreader = csv.reader(f, delimiter=',', quotechar='"')
    print("Feeding file...................................")
    for subject in spamreader:
        faq = {}
        faq["question"] = "What is the address of " + subject[2] + "?"
        faq["answer"] = subject[0]
        name_address.append(faq)
        faq = {}
        faq["question"] = "What is the website of " + subject[2] + "?"
        faq["answer"] = subject[5]
        name_website.append(faq)
        faq = {}
        faq["question"] = "Which country is " + subject[2] + " in?"
        faq["answer"] = subject[1]
        name_country.append(faq)
        faq = {}
        faq["question"] = "What is the rating of " + subject[2] + "?"
        faq["answer"] = subject[4]
        name_ratings.append(faq)
        faq = {}
        faq["question"] = "What is the name of the hotel at " + subject[0] + "?"
        faq["answer"] = subject[2]
        address_name.append(faq)
        faq = {}
        faq["question"] = "What is the website of the hotel at " + subject[0] + "?"
        faq["answer"] = subject[5]
        address_website.append(faq)
        faq = {}
        faq["question"] = "What is the country of the hotel at " + subject[0] + "?"
        faq["answer"] = subject[1]
        address_country.append(faq)
        faq = {}
        faq["question"] = "What is the rating of the hotel at " + subject[0] + "?"
        faq["answer"] = subject[4]
        address_ratings.append(faq)

print("Outputing file.................................")
with open('name_address.csv', 'w') as csvfile:
    fieldnames = ['question','answer']
    writer = csv.DictWriter(csvfile,fieldnames=fieldnames)
    for faq in name_address:
        writer.writerow(faq)
    
with open('name_website.csv', 'w') as csvfile:
    fieldnames = ['question','answer']
    writer = csv.DictWriter(csvfile,fieldnames=fieldnames)
    for faq in name_website:
        writer.writerow(faq)

with open('name_country.csv', 'w') as csvfile:
    fieldnames = ['question','answer']
    writer = csv.DictWriter(csvfile,fieldnames=fieldnames)
    for faq in name_country:
        writer.writerow(faq)

with open('name_ratings.csv', 'w') as csvfile:
    fieldnames = ['question','answer']
    writer = csv.DictWriter(csvfile,fieldnames=fieldnames)
    for faq in name_ratings:
         writer.writerow(faq)

with open('address_name.csv', 'w') as csvfile:
    fieldnames = ['question','answer']
    writer = csv.DictWriter(csvfile,fieldnames=fieldnames)
    for faq in address_name:
         writer.writerow(faq)

with open('address_website.csv', 'w') as csvfile:
    fieldnames = ['question','answer']
    writer = csv.DictWriter(csvfile,fieldnames=fieldnames)
    for faq in address_website:
         writer.writerow(faq)

with open('address_name.csv', 'w') as csvfile:
    fieldnames = ['question','answer']
    writer = csv.DictWriter(csvfile,fieldnames=fieldnames)
    for faq in address_name:
         writer.writerow(faq)

with open('address_country.csv', 'w') as csvfile:
    fieldnames = ['question','answer']
    writer = csv.DictWriter(csvfile,fieldnames=fieldnames)
    for faq in address_country:
         writer.writerow(faq)

with open('address_ratings.csv', 'w') as csvfile:
    fieldnames = ['question','answer']
    writer = csv.DictWriter(csvfile,fieldnames=fieldnames)
    for faq in address_ratings:
         writer.writerow(faq)

print("Complete.......................................")
