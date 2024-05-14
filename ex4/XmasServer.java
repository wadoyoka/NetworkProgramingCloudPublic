package ex4;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class XmasServer {
    private static int serverProcess(int content) {
        return (int)(Math.pow(content, 2));
    }

    public static void main(String arg[]) {
        try {
            /* 通信の準備をする */
            Scanner scanner = new Scanner(System.in);
            int port = 0;
            boolean flagment = false;
            while (!flagment) {
                System.out.print("ポートを入力してください(5000など) (※qを入力でプログラム終了します)→ ");
                try {
                    String bufIn = scanner.nextLine();
                    if (bufIn.equals("q")) {
                        System.out.println("プログラムを終了します。");
                        System.exit(0);
                    }
                    port = Integer.parseInt(bufIn);
                    flagment = true;
                } catch (Exception e) {
                    System.out.println("入力が違います。もう一度入力してください。");
                    continue;
                }
            }
            scanner.close();
            System.out.println("localhostの" + port + "番ポートで待機します");
            ServerSocket server = new ServerSocket(port); // ポート番号を指定し、クライアントとの接続の準備を行う

            Socket socket = server.accept(); // クライアントからの接続要求を待ち、
            // 要求があればソケットを取得し接続を行う
            System.out.println("接続しました。相手の入力を待っています......");
            ObjectInputStream ois = null;
            ObjectOutputStream oos = null;
            while (true) {
                ois = new ObjectInputStream(socket.getInputStream());
                DataPresent present = (DataPresent) ois.readObject();// Integerクラスでキャスト。

                String msgPresent = present.getMessage();
                if (msgPresent.equals("q")) {
                    System.exit(0);
                    break;
                }
                System.out.println("メッセージは" + msgPresent);
                int presentFromClient = present.getData();
                System.out.println("データの内容は" + presentFromClient);

                oos = new ObjectOutputStream(socket.getOutputStream());

                DataPresent response = new DataPresent();
                response.setMessage(
                        "サーバーです！\n" + presentFromClient + "貰いました！。\nデータのお返しは二乗です");
                response.setData(serverProcess(present.getData()));

                oos.writeObject(response);
                oos.flush();
            }
            // close処理

            ois.close();
            oos.close();
            // socketの終了。
            socket.close();
            server.close();

        } // エラーが発生したらエラーメッセージを表示してプログラムを終了する
        catch (BindException be) {
            be.printStackTrace();
            System.out.println("ポート番号が不正、ポートが使用中です");
            System.err.println("別のポート番号を指定してください(6000など)");
        } catch (Exception e) {
            System.err.println("エラーが発生したのでプログラムを終了します");
            throw new RuntimeException(e);
        }
    }
}
