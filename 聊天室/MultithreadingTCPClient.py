from socket import *
import threading
import time
import sys
from PyQt5 import QtWidgets, QtCore
from PyQt5.QtWidgets import QTextBrowser,QDialog
from PyQt5.QtCore import QThread

class Dialog(QtWidgets.QDialog):
    def __init__(self, parent=None):
        QtWidgets.QDialog.__init__(self, parent)
        self.resize(240, 200)
        # 表格布局，用来布局QLabel和QLineEdit及QSpinBox
        grid = QtWidgets.QGridLayout()
        grid.addWidget(QtWidgets.QLabel(u'暱稱', parent=self), 0, 0, 1, 1)
        self.leName = QtWidgets.QLineEdit(parent=self)
        grid.addWidget(self.leName, 0, 1, 1, 1)
        grid.addWidget(QtWidgets.QLabel(u'顏色', parent=self), 1, 0, 1, 1)
        self.leColor = QtWidgets.QLineEdit(parent=self)
        grid.addWidget(self.leColor, 1, 1, 1, 1)
        # 创建ButtonBox，用户确定和取消
        buttonBox = QtWidgets.QDialogButtonBox(parent=self)
        buttonBox.setOrientation(QtCore.Qt.Horizontal) # 设置为水平方向
        buttonBox.setStandardButtons(QtWidgets.QDialogButtonBox.Cancel|QtWidgets.QDialogButtonBox.Ok) # 确定和取消两个按钮
        # 连接信号和槽
        buttonBox.accepted.connect(self.accept) # 确定
        buttonBox.rejected.connect(self.reject) # 取消
        # 垂直布局，布局表格及按钮
        layout = QtWidgets.QVBoxLayout()
        # 加入前面创建的表格布局
        layout.addLayout(grid)
        # 放一个间隔对象美化布局
        spacerItem = QtWidgets.QSpacerItem(20, 48, QtWidgets.QSizePolicy.Minimum, QtWidgets.QSizePolicy.Expanding)
        layout.addItem(spacerItem)
        # ButtonBox
        layout.addWidget(buttonBox)
        self.setLayout(layout)
        
    def name(self):
        return self.leName.text()
    
    def color(self):
        return self.leColor.text()
    
class Thread(QThread):

    def __init__(self, nickname,name,port,color, parent=None):
        super().__init__(parent)
        
        self.serverName = name
        self.serverPort = port
        
        self.client_name = nickname
        self.color = color
        self.input_text = ''
        
        self.plain = QTextBrowser()
        self.plain.move(10,10)
        self.plain.resize(400,200)
        
        self.btn1 = QtWidgets.QPushButton("輸入")
        self.text = QtWidgets.QLineEdit()
        self.btn1.clicked.connect(self.get_input)
        
        self.btn2 = QtWidgets.QPushButton("Exit")
        
        self.disconnect = False

    def run(self):
        try:
            with socket(AF_INET, SOCK_STREAM) as clientSocket:
                print('Connecting to server', self.serverName, ':', self.serverPort)
                clientSocket.connect((self.serverName, self.serverPort))
                clientAddress, clientPort = clientSocket.getsockname()
                print('Client', clientAddress, ':', clientPort)
                print('Connect to server', self.serverName, ':', self.serverPort)
                thread = threading.Thread(target = self.__listening, args = (clientSocket,))
                thread.start()
                clientSocket.send(self.client_name.encode())
                clientSocket.send(self.color.encode())
                while not self.disconnect:
                    while self.input_text:
                        clientSocket.send(self.input_text.encode())
                        self.input_text = ''           
        except:
            pass
        finally:
            print('Connection shutdown')
            
    def get_input(self):
        self.input_text = self.text.text()
        self.text.clear()

    def __listening(self, clientSocket):
        try:
            while True:
                message = clientSocket.recv(1024)
                if len(message) is 0:
                    break
                sentence = message.decode()
                output = sentence.split(':')
                color = "<font color="+output[2]+">{}</font>"
                self.plain.append(color.format(output[0]) + ' : ' + output[1])        
        except:
            pass
            

        
class Input(QtWidgets.QWidget):

    def __init__(self, parent = None):
        
        super().__init__(parent)
        self.Thread_List = []      #初始化時指派一個線程
        #self.progress_bar = []
        #self.temp = ""
        #self.temp1 = 0
        #self.count = 0
        self.arrange = False   #檢查此次的輸入是否有排入線程中
        self.serverName = '127.0.0.1'
        self.serverPort = 12000
        
        self.layout = QtWidgets.QFormLayout()
        
        self.btn2 = QtWidgets.QPushButton('Connect')
        self.btn2.clicked.connect(self.get)
        self.layout.addRow(self.btn2)
        
        
        
        self.setLayout(self.layout)
        self.setWindowTitle("Practice")
        self.setGeometry(150, 150, 400, 300)
        
    def get(self):             #多線程處理
        print("Get process!")
        #self.temp = self.date.text()
        #self.temp1 = int(self.good.text())
        #self.arrange = False
        #self.count += 1
        self.check()


    def check(self):
        for Threads in self.Thread_List:
            if not Threads.isRunning():
                self.arrange = True
                #Threads.set(self.temp, self.temp1, self.count)
                Threads.start()
                break

        if not self.arrange:
            if len(self.Thread_List) <= 10:
                dialog = Dialog(parent=self)
                if dialog.exec_():
                   name = dialog.name()
                   color = dialog.color()
                dialog.destroy()
                self.Thread_List.append(Thread(name,self.serverName, self.serverPort,color,parent = self))                    #新增一個Thread到最後面
                self.layout.addRow(self.Thread_List[-1].plain)
                self.layout.addRow(self.Thread_List[-1].text,self.Thread_List[-1].btn1)
                #self.Thread_List[-1].set(self.temp, self.temp1, self.count)
                #self.layout.addRow(self.Thread_List[-1].progress_bar)
                self.Thread_List[-1].start()                         #將最後一個Thread指派程序
            else:
                print("Out of Process")

def main():
    app = QtWidgets.QApplication(sys.argv)
    example = Input()
    example.show()
    app.exec_()


if __name__ == '__main__':
    main()

