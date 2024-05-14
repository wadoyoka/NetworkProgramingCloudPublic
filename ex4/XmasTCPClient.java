package ex4;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.Socket; //ネットワーク関連のパッケージを利用する
import java.util.Scanner;

public class XmasTCPClient {

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
                System.out.println("プレゼントを送ります");
                oos = new ObjectOutputStream(socket.getOutputStream());
                System.out.println("メッセージを入力してください(例:メリークリスマス)(※qを入力でプログラム終了します) ↓");
                String message = new java.util.Scanner(System.in, "Shift-JIS").nextLine();
                if (message.equals("q")) {
                    System.out.println("プログラムを終了しました");
                    oos.writeObject(null);
                    oos.flush();
                    System.exit(0);
                    break;
                }
                int content=0;
                boolean dataflagment = false;
                while (!dataflagment) {
                    try {
                        System.out.println("データの内容を入力してください(例:お菓子)(※qを入力でプログラム終了します) ↓");
                        String data = new java.util.Scanner(System.in, "Shift-JIS").nextLine();
                        if (data.equals("q")) {
                            System.out.println("プログラムを終了します。");
                            System.out.println("プログラムを終了しました");
                            oos.writeObject(null);
                            oos.flush();
                            System.exit(0);
                        }
                        content = Integer.parseInt(data);
                        dataflagment = true;
                    } catch (Exception e) {
                        System.out.println("入力が違います。もう一度入力してください。");
                        continue;
                    }
                }
                DataPresent present = new DataPresent();
                present.setMessage(message);
                present.setData(content);

                oos.writeObject(present);
                oos.flush();

                ois = new ObjectInputStream(socket.getInputStream());

                DataPresent okaeshiPresent = (DataPresent) ois.readObject();

                String replayMsg = okaeshiPresent.getMessage();
                System.out.println("サーバからのメッセージは" + replayMsg);
                int replayContent = okaeshiPresent.getData();
                System.out.println(replayContent + "をもらいました！");
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
