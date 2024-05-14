# NetworkProgramingCloudPublic
ネットワークプログラミングとクラウド開発のワークスペース（提出用）


課題4-2動作結果
サーバー側
ポートを入力してください(5000など) (※qを入力でプログラム終了します)→ 5050
localhostの5050番ポートで待機します
接続しました。相手の入力を待っています......
メッセージはこんにちは
データの内容は4
メッセージはＨｅｌｌｏ
データの内容は10
エラーが発生したのでプログラムを終了します
Exception in thread "main" java.lang.RuntimeException: java.lang.NullPointerException: Cannot invoke "ex4.DataPresent.getMessage()" because "present" is null
        at ex4.NijyouServer.main(NijyouServer.java:83)
Caused by: java.lang.NullPointerException: Cannot invoke "ex4.DataPresent.getMessage()" because "present" is null
        at ex4.NijyouServer.main(NijyouServer.java:49)


クライアント側
ポートを入力してください(5000など) (※qを入力でプログラム終了します)→ 5050
localhostの5050番ポートに接続を要求します
接続されました
プレゼントを送ります
メッセージを入力してください(例:こんにちは)(※qを入力でプログラム終了します) ↓
こんにちは
数値を入力してください(例:3)(※qを入力でプログラム終了します) ↓
ｄｄ
入力が違います。もう一度入力してください。
数値を入力してください(例:3)(※qを入力でプログラム終了します) ↓
4 
サーバからのメッセージはサーバーです！
4貰いました！。
データのお返しは二乗です
16をもらいました！
プレゼントを送ります
メッセージを入力してください(例:こんにちは)(※qを入力でプログラム終了します) ↓
Ｈｅｌｌｏ
数値を入力してください(例:3)(※qを入力でプログラム終了します) ↓
ww
入力が違います。もう一度入力してください。
数値を入力してください(例:3)(※qを入力でプログラム終了します) ↓
10
サーバからのメッセージはサーバーです！
10貰いました！。
データのお返しは二乗です
100をもらいました！
プレゼントを送ります
メッセージを入力してください(例:こんにちは)(※qを入力でプログラム終了します) ↓
q
プログラムを終了しました