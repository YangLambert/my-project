# -*- coding: utf-8 -*-
"""
Created on Wed May 22 10:03:18 2019

@author: gabe
"""

#!/usr/bin/env python
# -*- coding: UTF-8 -*-
import os
import jieba
jieba.add_word('1450')
jieba.add_word('柯文哲')
jieba.add_word('柯P')
jieba.add_word('柯p')
jieba.add_word('資進黨')
jieba.add_word('冥進黨')
jieba.add_word('youtube')
jieba.add_word('國冥黨')
jieba.add_word('kmt')
jieba.add_word('dpp')
jieba.add_word('綠白合作')
jieba.add_word('民進黨')
jieba.add_word('蔡英文')
jieba.add_word('中國')
jieba.add_word('吱吱')
jieba.add_word('國民黨')
jieba.add_word('魯蛇')
jieba.add_word('馬英九')
jieba.add_word('KMT')
jieba.add_word('陳其邁')
jieba.add_word('其邁')
jieba.add_word('邁邁')
jieba.add_word('78')
jieba.add_word('陳78')
jieba.add_word('DPP')
jieba.add_word('丁丁')
jieba.add_word('花媽')
jieba.add_word('菜販')
jieba.add_word('綠吱')
jieba.add_word('藍蛆')
jieba.add_word('睡菊')
jieba.add_word('白海豚')
jieba.add_word('豬母')
jieba.add_word('肥菊')
jieba.add_word('垃圾不分藍綠')
jieba.add_word('9.2')
jieba.add_word('台灣價值')
jieba.add_word('韓導')
jieba.add_word('喜韓兒')
jieba.add_word('貪婪老人')
jieba.add_word('擊潰丁守中')
jieba.add_word('滅東廠')
jieba.add_word('1124滅東廠')
jieba.add_word('東廠')
jieba.add_word('韓總')
jieba.add_word('發大財')
jieba.add_word('又老又窮')
jieba.add_word('關三天')
jieba.add_word('千杯千杯再千杯')
jieba.add_word('反觀')
jieba.add_word('反指標')
jieba.add_word('反串')
jieba.add_word('支那')
jieba.add_word('領五百')
jieba.add_word('低能卡')
jieba.add_word('五樓')
jieba.add_word('韓市長')
jieba.add_word('郭董')
jieba.add_word('館長')




def savefile(savepath, content):
    with open(savepath, "wt",  encoding = 'utf-8') as fp:
        
        fp.write(content)

def readfile(path):
    with open(path, "rt", encoding = 'utf-8') as fp:
        #rt會將\r\n轉\n
        content = fp.read()
    return content
 
def tokenlize(train_data, token_path):
    classList = os.listdir(train_data)  
    

    for cla in classList:
        class_path = train_data + cla + "/"  
        token_dir = token_path + cla + "/"  
        #在token資料夾建跟train_data一樣的類別
        if not os.path.exists(token_dir):
            os.makedirs(token_dir)
        files = os.listdir(class_path)  
        for file in files: 
            fullname = class_path + file  
            content = readfile(fullname)
            content = content.replace(" ", "")
            content_token = jieba.cut(content)
            savefile(token_dir + file, " ".join(content_token))
 
    print ("Fininsh tokenlizing")


train_data_path = "./train_data/"  
token_path = "./train_token_data/"
tokenlize(train_data_path,token_path)
 
#測試集也要tokenlize
train_data_path = "./test_data/" 
token_path = "./test_token_data/"  
tokenlize(train_data_path,token_path)
