package Week3.problem_3;

import java.util.Scanner;

//Реализация алгоритма Рабина-Карпа
public class Main {
    private static final int p = 1_000_000_007;  //простое число
    private static final int x = 263;  //основание хеш-фукнции

    //вычисляет (base^exp) % mod
    private static long pow(int base, int exp, int mod) {
        long x = 1;
        long y = base;

        while (exp > 0) {
            if(exp % 2 == 1) {
                x = ((x % mod) * (y % mod)) % mod;
            }
            y = ((y % mod) * (y % mod)) % mod;
            exp /= 2;
        }
        return x % mod;
    }

    //вычисление хеш-функции
    private static int hash(String s) {
        char[] chars = s.toCharArray();
        long sumP = 0;

        for (int i = 0; i < chars.length; i++) {
            int charI = (int) chars[i];

            long add = ((charI % p) * pow(x, i, p)) % p;

            sumP += add % p;
        }
        return (int) (sumP % p);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String pattern = sc.nextLine();
        String text = sc.nextLine();

        int P = pattern.length();
        int T = text.length();

        long[] x_pow = new long[P];

        //предвычисление массива степеней полинома
        for (int i = 0; i < P; i++) {
            x_pow[i] = pow(x, i, p);
        }
        int hashPattern = hash(pattern);

        int[] hashWindow = new int[T - P + 1];  //hashCode всех "окон" (слева направо!)

        String window = text.substring(T - P, T);

        hashWindow[T - P] = hash(window);

        //Идём по строке справа-налево и вычисляем hash
        for (int i = T - 2; i >= P - 1; i--) {
            int charIP = (int) text.charAt(i + 1);
            int charI = (int) text.charAt(i - P + 1);

            long hash_i = hashWindow[i - P + 2];
            hash_i -= ((charIP % p) * x_pow[P - 1]) % p;
            hash_i = ((hash_i % p) * (x % p)) % p;
            hash_i = ((hash_i % p) + (charI % p)) % p;

            hashWindow[i - P + 1] = (int) hash_i;
        }



    }
}
