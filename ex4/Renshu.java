package ex4;

public class Renshu {
    public static void main(String[] args) {
        new Renshu();
    }

    public Renshu() {// コンストラクタ
        // printHello();
    }

    // xを2倍にして返す関数
    public int doubleValue(int x) {
        return x * 2;
    }

    public int sumUpToN(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        return sum;
    }

    public int sumFromPtoQ(int p, int q) {
        int sum = 0;
        try {
            if (p>q) {
                return -1;
            }
            for (int i = p; i <= q; i++) {
                sum += i;
            }
            return sum;
        } catch (Exception e) {
            return -1;
        }
    }

    public int sumFromArrayIndex(int[] a, int index) {
        int sum = 0;
        try {
            if (index>=a.length) {
                return -1;
            }
            for (int i = index; i < a.length; i++) {
                sum += a[i];
            }
        } catch (Exception e) {
            return -1;
        }
        return sum;
    }

    public int selectMaxValue(int[] a) {
        int max = a[0];
        for (int i = 0; i < a.length; i++) {
            if (max < a[i]) {
                max = a[i];
            }
        }
        return max;
    }

    public int selectMaxIndex(int[] a) {
        int max = a[0];
        int index = 0;
        for (int i = 0; i < a.length; i++) {
            if (max < a[i]) {
                max = a[i];
                index = i;
            }
        }
        return index;
    }

    public int selectMinValue(int[] a) {
        int min = a[0];
        for (int i = 0; i < a.length; i++) {
            if (min > a[i]) {
                min = a[i];
            }
        }
        return min;
    }

    public int selectMinIndex(int[] a) {
        int min = a[0];
        int index = 0;
        for (int i = 0; i < a.length; i++) {
            if (min > a[i]) {
                min = a[i];
                index = i;
            }
        }
        return index;
    }

    public void swapArrayElements(int[] p, int i, int j) {
        int flagInt = p[i];
        p[i] = p[j];
        p[j] = flagInt;
    }

    public boolean swapTwoArrays(int[] a, int[] b) {
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            int flagInt = a[i];
            a[i] = b[i];
            b[i] = flagInt;
        }
        return true;
    }
}