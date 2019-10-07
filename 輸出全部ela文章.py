

from elasticsearch import Elasticsearch
import json

es = Elasticsearch()
result = es.search(index='erica', doc_type='politics',size=3000)
print(result)
print(json.dumps(result, indent=2, ensure_ascii=False))
with open("result-new.txt","w+",encoding="utf-8") as f:
    f.write(json.dumps(result, indent=2, ensure_ascii=False))