package ex5;

public interface ITask {
    public void setExecNumber(int x); // クライアントで最初に計算させる数字を入力しておく関数
    public void exec(); // サーバで計算を実行をさせる関数...計算アルゴリズムが記載される。
    public int getResult(); // クライアントで結果を取り出す関数
}
