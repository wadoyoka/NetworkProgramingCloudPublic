package ex3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MyExceptionHoliday {
	public static void main(String[] args) {

		MyExceptionHoliday myE = new MyExceptionHoliday();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			boolean flag = false;
			while (!flag) {
				System.out.println("2024年5月の休日の日付をいずれか入力してください。休日、土日以外はエラーになります。");
				System.out.println(" (qあるいはeを入力するとプログラムを終了します。)");
				int day = 0;
				try {
					String line = reader.readLine();
					if (line.equals("e") || line.equals("q")) {
						System.out.println("プログラムを終了します。");
						System.exit(0);
					}
					day = Integer.parseInt(line);
					if (day==3 || day==4 || day==5 || day==6 || day==11 || day==12 || day==18 || day==19 || day==25 || day==26) {
						System.out.println(day+"は休日です！");
					}else{
						try {
							myE.test(day);
						} catch (NoHolidayException e) {
							e.printStackTrace();
						}
					}
				} catch (Exception e) {
					System.out.println(e);
					System.out.println("入力の型が違います。半角数字で入力してください。");
				}
			}
		}

	}

	void test(int day) throws NoHolidayException {
		if (true) {
			throw new NoHolidayException(day);
		}
	}
}
