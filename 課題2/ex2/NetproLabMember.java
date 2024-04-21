package 課題2.ex2;

import java.util.Random;

public class NetproLabMember {
    public static void main(String[] args) {
        final int lastYear = 15;
        final int basicStudents = 110;
        final int iwaiBasicCapacity = 10;
        final int basicWoman = 20;
        final int colums = 3;
        int [][] data = new int[lastYear][colums];
        Random random =new Random();
        double total =100;
        for (int i = 0; i < lastYear; i++) {
            //学生総数
            data[i][0] = basicStudents + random.nextInt(21)-10;
            //女子学生%
            data[i][1] = basicWoman +i;
            //岩井研配属人数
            data[i][2] = iwaiBasicCapacity + random.nextInt(4)-3;

            //男性数
            int man = data[i][0] - (int)(data[i][0]*((float)data[i][1]/100));
            //総数から岩井研へ配属する組み合わせ
            long iwai = Combination(data[i][0], data[i][2]);
            //男性のみ岩井研へ入るときの男性の組み合わせ
            long iwaiMan = Combination(man, data[i][2]);
            //男性のみ/総数 = 女性が岩井研究にその年に入らない確率
            total *=(double)iwaiMan/iwai;
        }
        System.out.println("15年間岩井研に女性の学生が来ない確率は"+total+"%");
    }

    public static long Combination(int total, int select){
        long combinationNum=0;
        long molecule =1;//分子
        for (int i = 0; i <select; i++) {
            molecule*=(total-i);
        }
        long denominator =1;//分母
        for (int i = 1; i <=select; i++) {
            denominator *=i; 
        }
        combinationNum = molecule/denominator;
        return combinationNum;
    }
}

