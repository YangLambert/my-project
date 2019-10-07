# -*- coding: utf-8 -*-
"""
Created on Tue Mar 12 17:59:26 2019

@author: user
"""

# 引入 Beautiful Soup 模組
from bs4 import BeautifulSoup
import json
 
#with open('[F  B] teamKP FB- 讓改變，繼續發生！.txt','r') as fp:
import os


path = "D:/all/1/all"
files = os.listdir(path)
for i in range(0,len(files)):
    file = files[i]
    with open(path+'/'+file,'r', encoding='UTF-8') as fp:
    # 原始 HTML 程式碼
    
        html_doc = ''
        line = fp.readline()
    #用 while 逐行讀取檔案內容，直至檔案結尾
        while line:
            html_doc += line
            line = fp.readline()
    
    #print(html_doc)
    # 以 Beautiful Soup 解析 HTML 程式碼
        soup = BeautifulSoup(html_doc, 'html.parser')
        countt=1
        a_tags = soup.findAll('span', {"class":"article-meta-value"})
    
        topic = {}
        """
        topic["id"]=a_tags[0].string
        topic["kinds"]=a_tags[1].string
        topic["title"]=a_tags[2].string
        topic["time"]=a_tags[3].string
        print(topic)
        """
    #print("{")
        for tag in a_tags:
            if countt==1:
            #print("\"id\":\""+tag.string+"\"")
                topic["id"]=tag.string
            if countt==2:
            #print("\"kinds\":\""+tag.string+"\"")
                topic["kinds"]=tag.string
            if countt==3:
            #print("\"title\":\""+tag.string+"\"")
                topic["title"]=tag.string
            if countt==4:
            #print("\"time\":\""+tag.string+"\"")
                topic["time"]=tag.string
            countt=countt+1
        
    #內文
        a_tags =  soup.find('meta', {"name":"description"})
        
    #print("\"content\":\""+a_tags['content']+"\"")
        if a_tags:
            topic["content"]= soup.find(id="main-content").text
            
        counttt=1
    #留言
        a_tags = soup.findAll('span')
    #print("\"comment\":[")
        topic["comment"]=[]
        for i in range(13,len(a_tags)):
            if counttt==1:
            #print("{")
            #print("\"push\":\"",a_tags[i].string,"\"" )
                comment={}
                comment["push"]=a_tags[i].string
            elif counttt==2:
            #print("\"comment_name\":\"",a_tags[i].string,"\"" )
                comment["comment_name"]=a_tags[i].string
            elif counttt==3:
            #print("\"comment_content\":\"",a_tags[i].string,"\"" )
                comment["comment_content"]=a_tags[i].string
            elif counttt==4:
            #print("\"comment_time\":\"",a_tags[i].string,"\"" )
            #print("}")
                comment["comment_time"]=a_tags[i].string
                topic["comment"].append(comment)
                counttt=0
            
            counttt=counttt+1
    #print("]")
    #print("}")
        filename=file
        filename.rstrip()
        filename.rstrip()
        filename.rstrip()
        filename.rstrip()
        print(filename)
        with open("newdata529.txt","a", encoding='UTF-8') as f:
            json.dump(topic,f,ensure_ascii=False)
            f.write("\n")      
            

    #for tag in a_tags:
        # 輸出超連結的文字
    #    print(tag)