# -*- coding: utf-8 -*-
"""
Created on Tue Mar 19 13:25:57 2019

@author: user
"""
from elasticsearch import Elasticsearch

import json
dsl = {
    'query': {
       'bool':{ 'must':[{'term': {   'id': ('btm978952')      }} ]
               
              }
    },'size': 3000
}
es = Elasticsearch()
result = es.search(index='erica', doc_type='politics',body=dsl,filter_path=["hits.hits._source.content"])
print(json.dumps(result, indent=2, ensure_ascii=False))
with open("btm978952-new.txt","w+",encoding="utf-8") as f:
    f.write(json.dumps(result, indent=2, ensure_ascii=False))
    