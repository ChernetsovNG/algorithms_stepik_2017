package Week2.problem_3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s1 = sc.nextLine();
        String[] s1Arr = s1.split(" ");

        int n = Integer.parseInt(s1Arr[0]);  //число таблиц
        int m = Integer.parseInt(s1Arr[1]);  //число запросов

        String s2 = sc.nextLine();
        String[] s2Arr = s2.split(" ");

        int[] r = new int[n];  //n целых чисел - размеры таблиц

        for (int i = 0; i < n; i++) {
            r[i] = Integer.parseInt(s2Arr[i]);
        }

        int[] dest = new int[m];
        int[] src = new int[m];

        for (int i = 0; i < m; i++) {
            String s3 = sc.nextLine();
            String[] s3Arr = s3.split(" ");

            dest[i] = Integer.parseInt(s3Arr[0]);
            src[i] = Integer.parseInt(s3Arr[1]);
        }

        //Решение

    }
}
