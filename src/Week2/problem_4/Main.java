package Week2.problem_4;

import java.util.Scanner;

class DisjointSet {
    public int[] parent;
    public int[] rank;

    public DisjointSet(int size) {
        parent = new int[size];
        rank = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int i) {
        if (i != parent[i]) {
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }

    public void union(int i, int j) {
        int i_id = find(i);
        int j_id = find(j);

        if (i_id == j_id) {
            return;
        }

        if (rank[i_id] > rank[j_id]) {
            parent[j_id] = i_id;
        } else {
            parent[i_id] = j_id;

            if (rank[i_id] == rank[j_id]) {
                rank[j_id] += 1;
            }
        }
    }

}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s1 = sc.nextLine();
        String[] s1Arr = s1.split(" ");

        int n = Integer.parseInt(s1Arr[0]);
        int e = Integer.parseInt(s1Arr[1]);
        int d = Integer.parseInt(s1Arr[2]);

        String s2;
        String[] s2Arr;

        DisjointSet set = new DisjointSet(n);

        for (int k = 0; k < e; k++) {
            s2 = sc.nextLine();
            s2Arr = s2.split(" ");

            int i = Integer.parseInt(s2Arr[0]);
            int j = Integer.parseInt(s2Arr[1]);

            set.union(i-1,j-1);  //т.к. в set нумерация от нуля
        }

        boolean result = true;

        for (int k = 0; k < d; k++) {
            s2 = sc.nextLine();
            s2Arr = s2.split(" ");

            int i = Integer.parseInt(s2Arr[0]);
            int j = Integer.parseInt(s2Arr[1]);

            if (set.find(i-1) == set.find(j-1)) {
                result = false;
            }
        }

        if (result) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }

    }
}
