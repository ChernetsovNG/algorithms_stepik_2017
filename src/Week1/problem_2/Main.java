package Week1.problem_2;

import java.util.*;

public class Main {
    public static Map<Integer, List<Integer>> tree = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String s1 = scanner.nextLine();
        String s2 = scanner.nextLine();
        String[] s2_array = s2.split(" ");

        int n = Integer.parseInt(s1);
        int[] parents = new int[s2_array.length];
        int n_root = -1;  //номер корневой вершины

        int j;
        for (int i = 0; i < n; i++) {
            j = Integer.parseInt(s2_array[i]);
            parents[i] = j;
            if (j == -1)
                n_root = i;
        }

        for (int i = 0; i < n; i++) {
            tree.put(i, new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            if (i == n_root)
                continue;
            tree.get(parents[i]).add(i);
        }

        System.out.println(height(n_root));
    }

    public static int height(int n) {
        int h = 1;
        for (Integer child : tree.get(n)) {
            h = max(h, 1 + height(child));
        }
        return h;
    }

    public static int max(int a, int b) {
        return (a >= b) ? a : b;
    }

}
