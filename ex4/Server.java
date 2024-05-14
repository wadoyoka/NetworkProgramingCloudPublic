package ex4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            // 1. サーバーソケットをポート番号で作成　//例えば5050
            ServerSocket serverSocket = new ServerSocket(5050);

            // 2. クライアントからの接続を待機
            System.out.println("Waiting for client connection...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            // 3. クライアントからのデータを受信するためのInputStreamを取得
            InputStream inputStream = clientSocket.getInputStream();
            // データを受信するためのラッパーを作成
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // 4. クライアントにデータを送信するためのOutputStreamを取得
            OutputStream outputStream = clientSocket.getOutputStream();
            // データを送信するためのラッパーを作成
            PrintWriter writer = new PrintWriter(outputStream, true);

            // 5. クライアントからのデータを受信して表示
            String clientMessage = reader.readLine();
            System.out.println("Message from client: " + clientMessage);

            // 6. クライアントに応答を送信
            writer.println("Hello, client!");

            // 7. 接続を閉じる
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


