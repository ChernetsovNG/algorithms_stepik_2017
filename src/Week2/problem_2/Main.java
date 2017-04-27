package Week2.problem_2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Ввод исходных данных
        Scanner sc = new Scanner(System.in);

        String s1 = sc.nextLine();
        String[] s1Arr = s1.split(" ");

        int n = Integer.parseInt(s1Arr[0]);
        int m = Integer.parseInt(s1Arr[1]);

        String s2 = sc.nextLine();

        String[] s2Arr = s2.split(" ");

        int[] t = new int[m];

        for (int i = 0; i < m; i++) {
            t[i] = Integer.parseInt(s2Arr[i]);
        }

        System.out.println(n);
        System.out.println(m);
        for (int i = 0; i < m; i++) {
            System.out.println(t[i]);
        }
    }

}
