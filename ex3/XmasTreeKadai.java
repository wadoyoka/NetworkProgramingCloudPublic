package ex3;

public class XmasTreeKadai {
    public static void main(String[] args) {
        String yuki = "+";
        String ha = "*";
        int Yoko = 19;
        int miki_yoko = 3;
        int miki_tate = 7;
        for (int i = 0; i < Yoko+1; i++) {
            for (int j = 0; j < (Yoko+1 - i) / 2; j++) {
                if (i % 2 == 0) {
                    System.out.print(yuki + " ");
                } else {
                    System.out.print(" " + yuki);
                    if (j == (20 - i) / 2 - 1) {
                        System.out.print(" ");
                    }
                }
            }
            if (i==Yoko) {
                System.out.print(" ");
            }
            if (i > 0) {
                for (int j = 0; j < i; j++) {
                    System.out.print(ha + ha);
                }
            }
            System.out.println();
        }

        for (int i = 0; i < miki_tate; i++) {
            for (int j = 0; j < Yoko-1; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < miki_yoko; j++) {
                System.out.print(ha);
            }
            if (i==miki_tate-1) {
                break;
            }
            System.out.println();
        }
    }
}
