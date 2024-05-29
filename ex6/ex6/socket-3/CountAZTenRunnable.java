// Runnable インターフェースを実装することで、このクラスのインスタンスはスレッドとして実行可能になります。
public class CountAZTenRunnable implements Runnable {
    char name;
    // main メソッドはプログラムのエントリーポイントです。
    public static void main(String[] args){
        char [] cts = new char[26];
        CountAZTenRunnable [] ctens = new CountAZTenRunnable[26];
        Thread []ths = new Thread[26];
        for (int i = 0; i < 26; i++) {
            cts[i] = (char)(97 + i);
            // System.out.println(cts[i]);
        }

        for (int i = 0; i < ths.length; i++) {
            ctens[i] = new CountAZTenRunnable();
            ctens[i].setChar(cts[i]);
            ths[i] = new Thread(ctens[i],"th-"+String.valueOf(i));
        }

        for (Thread thread: ths) {
            thread.start();
        }
        // // 2つの文字を初期化します。
        // char c1 = 97; // ASCII値 97 は 'a' です
        // char c2 = (char)(c1 + 1); // c1 に 1 を足すと ASCII値 98 になり、'b' になります

        // // 初期化した文字をコンソールに出力します。
        // System.out.println(c1); // 出力: a
        // System.out.println(c2); // 出力: b

        // CountTenRunnable クラスのインスタンスを作成します。
        // CountTenRunnable ct = new CountTenRunnable();
        // ct を実行する新しいスレッドを作成します。
        // Thread th = new Thread(ct);

        // スレッドを開始します。これにより、CountTenRunnable の run メソッドが呼び出されます。
        // th.start();

        // この try-catch ブロックは、0 から 9 までの値を 500 ミリ秒間隔で出力するループを実行します。
        // try {
        //     for(int i = 0; i < 10; i++) {
        //         System.out.println("main:i=" + i);

        //         // メインスレッドを 500 ミリ秒間一時停止します。
        //         Thread.sleep(500);  // ミリ秒単位のスリープ時間
        //     }
        // }
        // catch(InterruptedException e) {
        //     // スレッドが中断された場合は、例外を出力します。
        //     System.err.println(e);
        // }
    }

    private void setChar(char c) {
        this.name=c;
    }

    // run メソッドは、新しいスレッドが実行するコードを含みます。
    public void run() {
        // この try-catch ブロックは、0 から 9 までの値を 1000 ミリ秒間隔で出力するループを実行します。
        try {
            for(int i = 0; i < 10; i++) {
                System.out.println(this.name+String.valueOf(i));

                // スレッドを 1000 ミリ秒間一時停止します。
                Thread.sleep(1000);  // ミリ秒単位のスリープ時間
            }
        }
        catch(InterruptedException e) {
            // スレッドが中断された場合は、例外を出力します。
            System.err.println(e);
        }
    }
}
