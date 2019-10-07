cou=1
i=0
with open('andy199113.txt','r',encoding='UTF-8') as f:
 temp=""
 temp=f.read()
 print(temp)
 while temp.find("content",i)!=-1:
     head=temp.find("content",i)
     tail=temp.find("發信站",head)
     i=tail
     with open("andy199113-"+str(cou)+".txt","a",encoding="utf-8") as fa:
         fa.write(temp[head:tail])
     cou=cou+1
     print(temp[head:tail])