
import tkinter as tk  # 使用Tkinter前需要先匯入
import tkinter.messagebox  
from evaluate import evaluate

# 第1步，例項化object，建立視窗window
window = tk.Tk()

# 第2步，給視窗的視覺化起名字
window.title('政治立場分析')

# 第3步，設定視窗的大小(長 * 寬)
window.geometry('500x300')  # 這裡的乘是小x

# 第4步，在圖形介面上設定輸入框控制元件entry框並放置


# 第5步，定義兩個觸發事件時的函式insert_point和insert_end（注意：因為Python的執行順序是從上往下，所以函式一定要放在按鈕的上面）
def judge(): # 在滑鼠焦點處插入輸入內容
    var=t.get("1.0",'end')
    x=evaluate(var,'1')
    tkinter.messagebox.showinfo(title='分析結果', message=x)  
def judge1(): # 在滑鼠焦點處插入輸入內容
    var=t.get("1.0",'end')
    x=evaluate(var,'2')
    tkinter.messagebox.showinfo(title='分析結果', message=x)  
def judge2(): # 在滑鼠焦點處插入輸入內容
    var=t.get("1.0",'end')
    x=evaluate(var,'3')
    tkinter.messagebox.showinfo(title='分析結果', message=x)  

# 第6步，建立並放置兩個按鈕分別觸發兩種情況
l = tk.Label(window, text='請輸入文字', bg='PeachPuff', font=('Arial', 12), width=30, height=2)
l.pack() 
t = tk.Text(window, height=15)
t.pack()
b1 = tk.Button(window, text='NB', bg='LightBlue',font=('Times'), width=17,
               height=2, command=judge)
b1.pack(side='left')
b2 = tk.Button(window, text='KNN', bg='PeachPuff',font=('Times'), width=19,
               height=2, command=judge1)
b2.pack(side='left')
b3 = tk.Button(window, text='SVM', bg='LightBlue',font=('Times'), width=17,
               height=2, command=judge2)
b3.pack(side='left')

# 第7步，建立並放置一個多行文字框text用以顯示，指定height=3為文字框是三個字元高度


# 第8步，主視窗迴圈顯示
window.mainloop()