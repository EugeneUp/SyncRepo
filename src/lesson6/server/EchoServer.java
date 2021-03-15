package lesson6.server;

import org.w3c.dom.ls.LSOutput;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private static final int  SERVER_PORT = 8888;

    public static void main(String[] args) {

        try(ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {

            System.out.println("Ожидание подключения...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Соединение установлено!");

                DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

                try {
                    while (true) {
                        String message = in.readUTF();

                        if (message.equals("/server-stop")) {
                            break;
                        }

                        System.out.println("Сообщение пользователя: " + message);

                        out.writeUTF(message.toUpperCase());

                    }

                    System.out.println("Сервер остановлен");
                } catch (IOException e) {
                    clientSocket.close();
                    System.out.println("Клиент отключился");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
