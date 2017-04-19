package Week1.problem_5;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s1 = sc.nextLine();

        int n = Integer.parseInt(s1);  //число элементов массива
        int[] A = new int[n];

        String[] s2 = sc.nextLine().split(" ");

        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(s2[i]);
        }

        String s3 = sc.nextLine();
        int m = Integer.parseInt(s3);


    }
}
