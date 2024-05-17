package ex5;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.Socket; //ネットワーク関連のパッケージを利用する
import java.util.Scanner;

public class TaskClientWhile {

    public static void main(String arg[]) {
        try {
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
            System.out.println("localhostの" + port + "番ポートに接続を要求します");
            Socket socket = new Socket("localhost", port);
            System.out.println("接続されました");

            ObjectInputStream ois = null;
            ObjectOutputStream oos = null;
            while (true) {
                System.out.println("入力x以下の最大素数を求めます。");
                oos = new ObjectOutputStream(socket.getOutputStream());
                System.out.println("xを入力してください");
                flagment = false;
                int x = 0;
                while (!flagment) {
                    try {
                        x = scanner.nextInt();
                        flagment = true;
                    } catch (Exception e) {
                        System.out.println("入力が違います。もう一度入力してください。");
                    }
                }
                TaskObject task = new TaskObject();
                task.setExecNumber(x);
                oos.writeObject(task);
                oos.flush();
                if (x <= 1) {
                    break;
                }

                ois = new ObjectInputStream(socket.getInputStream());
                TaskObject response = (TaskObject) ois.readObject();
                System.out.println("入力x=" + x + "以下の最大素数:" + response.getResult());
            }
            scanner.close();
            ois.close();
            oos.close();
            socket.close();

        } // エラーが発生したらエラーメッセージを表示してプログラムを終了する
        catch (BindException be) {
            be.printStackTrace();
            System.err.println("ポート番号が不正か、サーバが起動していません");
            System.err.println("サーバが起動しているか確認してください");
            System.err.println("別のポート番号を指定してください(6000など)");
        } catch (Exception e) {
            System.err.println("エラーが発生したのでプログラムを終了します");
            throw new RuntimeException(e);
        }
    }
}
