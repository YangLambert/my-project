from socket import *
import threading
import sys

class MultithreadingTCPServer:
    def __init__(self, name, port):
        self.serverName = name
        self.serverPort = port
        self.client = []

    def start(self):
        try:
            with socket(AF_INET, SOCK_STREAM) as serverSocket:
                print('Bind server socket to', self.serverName, ':', self.serverPort)
                serverSocket.bind((self.serverName, self.serverPort))
                serverSocket.listen(1)
                print('Multithreading server binding success')
                while True:
                    clientSocket, address = serverSocket.accept()
                    self.client.append(clientSocket)
                    thread = threading.Thread(target = self.__handleClient, args = (clientSocket,))
                    thread.start()
        except:
            pass
        finally:
            print('Server shutdown.')
            
    def __handleClient(self, clientSocket):
        clientName, clientPort = clientSocket.getpeername()
        print('connected!')
        try:
            message = clientSocket.recv(1024)
            name = message.decode()
            message = clientSocket.recv(1024)
            color = message.decode()
            while True:
                message = clientSocket.recv(1024)
                if len(message) is 0:
                    break
                sentence = message.decode()
                sentence = name + ':' + sentence + ':' + color
                for clientSocket in self.client:
                    clientSocket.send(sentence.encode())
        except:
            clientSocket.close()
        finally:
            print('Disconnecting to', clientName, ':', clientPort)
        

if len(sys.argv) < 3:
    serverName = '127.0.0.1'
    serverPort = 12000
else:
    serverName = sys.argv[1]
    serverPort = int(sys.argv[2])

server = MultithreadingTCPServer(serverName, serverPort)
server.start()

