# -*- coding: utf-8 -*-
"""
Created on Mon Aug 26 14:40:31 2019

@author: asus
"""

# -*- coding: utf-8 -*-
"""
Created on Mon Aug 26 13:45:04 2019

@author: asus
"""

# -*- coding: utf-8 -*-
"""
Created on Fri Jun 21 14:34:54 2019

@author: asus
"""

from sklearn.datasets import load_files 
from sklearn.feature_extraction.text import CountVectorizer, TfidfTransformer, TfidfVectorizer, HashingVectorizer
from sklearn.model_selection import train_test_split
import numpy as np
from sklearn.naive_bayes import MultinomialNB
from sklearn.svm import SVC
from sklearn.neighbors import KNeighborsClassifier
from sklearn.externals import joblib
import csv
import jieba
from sklearn.cross_validation import cross_val_score, KFold
from scipy.stats import sem
import numpy as np
from sklearn import metrics

def readfile(path):
    with open(path, "rt", encoding = 'utf-8') as fp:
        #rt會將\r\n轉\n
        content = fp.read()
    return content

def metrics_result(real, predict):
        x = 'Accuracy:{0:.3f}'.format(metrics.precision_score(real, predict,average='weighted'))
        print(x)
        return x

#分割8:2
def evaluate(word,choose):
    stw_list = readfile('stop_words2.txt').splitlines()
    data = load_files('./train_token_data', encoding='utf-8', shuffle  = False)
    split_rate = 0.8
    split_size = int(len(data.data) * split_rate)
    X_train = data.data[:split_size]
    y_train = data.target[:split_size]
    X_test  = data.data[split_size:]
    y_test  = data.target[split_size:]


    count_vect = CountVectorizer(stop_words = stw_list)
    X_train_counts = count_vect.fit_transform(X_train)
# Tf

# Tf_idf
    tfidf_transformer = TfidfTransformer(norm='l2', smooth_idf=True, sublinear_tf=False, use_idf=True)
    X_train_tfidf = tfidf_transformer.fit_transform(X_train_counts)


# create classifier

    

    if(choose == '1'):
        clf = MultinomialNB(alpha=1).fit(X_train_tfidf, y_train)
    elif choose == '2':
        clf = KNeighborsClassifier(n_neighbors = 7).fit(X_train_tfidf, y_train)
    elif choose == '3':
        clf = SVC(C= 10, gamma=0.1, kernel = 'rbf').fit(X_train_tfidf, y_train)

    test_data = load_files('./test_token_data', encoding='utf-8',  shuffle  = False)
    X_test_count = count_vect.transform(test_data.data)  #trainsform能把測試集特徵維度轉成跟訓練集一樣
    X_test_count = tfidf_transformer.transform(X_test_count)
# using classifier to predict
    predicted = clf.predict(X_test_count)
    #print(predicted)

   

    pre = metrics_result(test_data.target, predicted)

    sen = word
    
    sen_count = count_vect.transform([" ".join(jieba.cut(sen))])
    #print(sen_count)
    sen_tfidf = tfidf_transformer.transform(sen_count)
    #print(sen_tfidf)
    predicted = clf.predict(sen_tfidf)
    #print(predicted)
    if(predicted == 0):
        return('DPP(',pre,')')
    if(predicted == 1):
        return('KMT(',pre,')')
    















