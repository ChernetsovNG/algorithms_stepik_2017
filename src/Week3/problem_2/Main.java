package Week3.problem_2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final int p = 1_000_000_007;  //простое число
    private static final int x = 263;  //основание хеш-фукнции
    public static int m;

    public static long[] powerX_i_modP = new long[15];

    static {
        for (int i = 0; i < 15; i++) {
            powerX_i_modP[i] = pow(x, i, p);  //предвычисление массива
        }
    }

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

            long add = ((charI % p) * powerX_i_modP[i]) % p;

            sumP += add % p;
        }
        sumP = sumP % p;
        sumP = sumP % m;

        return (int) sumP;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s1 = sc.nextLine();

        m = Integer.parseInt(s1);  //размер хеш-таблицы

        s1 = sc.nextLine();

        int n = Integer.parseInt(s1);  //количество запросов

        String[] s1Arr;

        //хэш таблица
        LinkedList<String>[] hashTable = (LinkedList<String>[]) new LinkedList[m];

        int h;
        String str;
        LinkedList<String> list;

        List<String> out = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            s1 = sc.nextLine();
            s1Arr = s1.split(" ");

            if (s1Arr[0].equals("add")) {
                str = s1Arr[1];
                h = hash(str);

                if (hashTable[h] == null) {
                    list = new LinkedList<>();
                    list.addFirst(str);
                    hashTable[h] = list;
                } else {
                    list = hashTable[h];
                    if (!list.contains(str)) {
                        list.addFirst(str);
                    }
                }
            }
            else if (s1Arr[0].equals("del")) {
                str = s1Arr[1];
                h = hash(str);

                if (hashTable[h] != null) {
                    list = hashTable[h];
                    if (list.contains(str)) {
                        list.remove(str);
                    }
                }
            }
            else if (s1Arr[0].equals("find")) {
                str = s1Arr[1];
                h = hash(str);

                if (hashTable[h] != null) {
                    list = hashTable[h];
                    if (list.contains(str)) {
                        out.add("yes");
                    } else {
                        out.add("no");
                    }
                } else {
                    out.add("no");
                }
            }
            else if (s1Arr[0].equals("check")) {
                int num_i = Integer.parseInt(s1Arr[1]);

                if (hashTable[num_i] != null) {
                    list = hashTable[num_i];
                    StringBuilder sb = new StringBuilder("");
                    for (int j = 0; j < list.size()-1; j++) {
                        sb.append(list.get(j));
                        sb.append(" ");
                    }
                    if (list.size() > 0) {
                        sb.append(list.get(list.size() - 1));
                    }
                    out.add(sb.toString());
                } else {
                    out.add("");
                }
            }
        }

        for (String anOut : out) {
            System.out.println(anOut);
        }

    }
}
