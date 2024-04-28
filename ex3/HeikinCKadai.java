package ex3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class HeikinCKadai {
	public static void main(String[] args) {
		Random random = new Random();
		ArrayList<Integer> datas = new ArrayList<>();
		int sum =0;
		for (int i = 0; i < 100; i++) {
			datas.add(random.nextInt(101));
			sum+=datas.get(i);
		}
		int avg = sum/100;
		System.out.println("*平均点は"+avg+"*");
		System.out.println("*以下は合格者の点数です。*");
		Collections.sort(datas);
		for(int data: datas){
			if (data>=80) {
				System.out.println("*"+data+"*");
			}
		}
	}
}
