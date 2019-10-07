# -*- coding: utf-8 -*-
"""
Created on Tue Mar 19 17:54:29 2019

@author: user
"""
from elasticsearch import Elasticsearch
import json 
import os


es = Elasticsearch()

path = "D:/all/1/data"
files = os.listdir(path)
for i in range(0,len(files)):
    file = files[i]
    with open(path+'/'+file,'r', encoding='UTF-8') as fp:
        lines = fp.readlines()
        for j in range(0,len(lines)):
            es.index(index='erica', doc_type='politics', body=lines[j], ignore=400)
            print(lines[j])