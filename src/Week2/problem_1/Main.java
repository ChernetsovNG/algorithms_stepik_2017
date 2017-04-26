package Week2.problem_1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Ввод исходных данных
        Scanner sc = new Scanner(System.in);

        String s1 = sc.nextLine();

        int n = Integer.parseInt(s1);

        String s2 = sc.nextLine();
        String[] s2Arr = s2.split(" ");

        int[] A = new int[n];

        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(s2Arr[i]);
        }

        //Решение

    }
}
