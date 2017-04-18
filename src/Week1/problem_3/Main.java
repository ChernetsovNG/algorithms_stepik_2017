package Week1.problem_3;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s1 = sc.nextLine();
        String[] s1Array = s1.split(" ");

        int size = Integer.parseInt(s1Array[0]);
        int n = Integer.parseInt(s1Array[1]);

        int[] arrival = new int[n];
        int[] duration = new int[n];

        String s2;
        String[] s2Array;

        for (int i = 0; i < n; i++) {
            s2 = sc.nextLine();
            s2Array = s2.split(" ");
            arrival[i] = Integer.parseInt(s2Array[0]);
            duration[i] = Integer.parseInt(s2Array[1]);
        }

    }
}
