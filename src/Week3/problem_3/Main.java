package Week3.problem_3;

public class Main {
    private static final int p = 1_000_000_007;  //простое число
    private static final int x = 263;  //основание хеш-фукнции
    private static int m = 5;

    public static long pow(int base, int exp, int mod) {
        long a = base;
        long b = exp;
        long n = mod;

        long x = 1;
        long y = a;

        while (b > 0) {
            if(b % 2 == 1) {
                x = ((x % n) * (y % n)) % n;
            }
            y = ((y % n) * (y % n)) % n;
            b /= 2;
        }
        return x % n;
    }

    //вычисление хеш-функции
    public static int hash(String s) {
        char[] chars = s.toCharArray();
        long sumP = 0;

        for (int i = 0; i < chars.length; i++) {
            int charI = (int) chars[i];

            long add = ((charI % p) * pow(x, i, p)) % p;

            sumP += add % p;
        }
        sumP = sumP % p;
        sumP = sumP % m;

        return (int) sumP;
    }


    public static void main(String[] args) {
        System.out.println(hash("HellO"));
    }
}
