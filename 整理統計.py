from collections import Counter  
def counter(arr):
    return Counter(arr).most_common(100)

result=[]
with open('allnew-art.txt','r',encoding='UTF-8') as f:
 for line in f:
  result.append(line.strip('\n'))

a = counter(result)
print(a)