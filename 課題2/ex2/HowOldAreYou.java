package 課題2.ex2;

// C言語では、#include に相当する
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Calendar;

public class HowOldAreYou {

	public static void main(String[] args) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		// BufferedReader というのは、データ読み込みのクラス(型)
		// クラスの変数を作るには、new を使う。

		// readLine() は、入出力エラーの可能性がある。エラー処理がないとコンパイルできない。
		// Java では、 try{ XXXXXXXX } catch(エラーの型 変数) { XXXXXXXXXXXXXXXXXX} と書く
		while (true) {
			int age = 0;
			boolean flag = false;
			while (!flag) {
				try {
					System.out.println("何歳ですか? (qあるいはeを入力するとプログラムを終了します。)");
					String line = reader.readLine();
					if (line.equals("e") || line.equals("q")) {
						System.out.println("プログラムを終了します。");
						System.exit(0);
					}
					age = Integer.parseInt(line);
					if (age >= 120) {
						System.out.println("値が大きすぎます。再入力して下さい。");
						continue;
					} else if (age < 0) {
						System.out.println("値が負になっています。再入力して下さい。");
						continue;
					}
					flag = true;
				} catch (Exception e) {
					System.out.println(e);
					System.out.println("入力の型が違います。半角数字で入力してください。");
				}
			}
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int birthYear = year - age;
			int birthYearJapanese = 0;
			String birthYearJapanese_String = "";
			if (birthYear >= 2019) {
				birthYearJapanese_String = "令和";
				birthYearJapanese = birthYear - 2019;
			} else if (birthYear >= 1989) {
				birthYearJapanese_String = "平成";
				birthYearJapanese = birthYear - 1989;
			} else if (birthYear >= 1926) {
				birthYearJapanese_String = "昭和";
				birthYearJapanese = birthYear - 1926;
			} else if (birthYear >= 1912) {
				birthYearJapanese_String = "大正";
				birthYearJapanese = birthYear - 1912;
			} else if (birthYear >= 1868) {
				birthYearJapanese_String = "明治";
				birthYearJapanese = birthYear - 1868;
			}
			System.out.println("あなたは" + age + "歳ですね。");
			System.out.println("あなたは10年後、" + (age + 10) + "歳ですね。");
			System.out.println("あなたは" + birthYearJapanese_String + birthYearJapanese + "年に生まれました。");
		}

	}
}

// 課題 キーボードから数字を打ち込む
// その結果、 あなたは、???歳ですね、と画面に表示させる。
// その後、あなたは10年後、????歳ですね、と画面に表示させる。
