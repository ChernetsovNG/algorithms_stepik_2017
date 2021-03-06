package Week2.problem_3;

import java.util.Scanner;

class DisjointSet {
    public int[] parent;
    public int[] rank;
    public int[] tablesSize;
    public int maxTableSize = 0;

    public DisjointSet(int size, int[] tablesSize) {
        parent = new int[size];
        rank = new int[size];
        this.tablesSize = tablesSize;

        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 0;
            if (tablesSize[i] > maxTableSize) {
                maxTableSize = tablesSize[i];
            }
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

            if (tablesSize[j_id] > 0) {

                tablesSize[i_id] += tablesSize[j_id];
                tablesSize[j_id] = 0;

                if (tablesSize[i_id] > maxTableSize) {
                    maxTableSize = tablesSize[i_id];
                }
            }
        } else {
            parent[i_id] = j_id;

            if (tablesSize[i_id] > 0) {

                tablesSize[j_id] += tablesSize[i_id];
                tablesSize[i_id] = 0;

                if (tablesSize[j_id] > maxTableSize) {
                    maxTableSize = tablesSize[j_id];
                }
            }

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

        int n = Integer.parseInt(s1Arr[0]);  //число таблиц
        int m = Integer.parseInt(s1Arr[1]);  //число запросов

        String s2 = sc.nextLine();
        String[] s2Arr = s2.split(" ");

        int[] r = new int[n];  //n целых чисел - размеры таблиц

        for (int i = 0; i < n; i++) {
            r[i] = Integer.parseInt(s2Arr[i]);
        }

        DisjointSet set = new DisjointSet(n, r);

        int dest;
        int src;
        String s3;
        String[] s3Arr;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            s3 = sc.nextLine();
            s3Arr = s3.split(" ");

            dest = Integer.parseInt(s3Arr[0])-1;
            src = Integer.parseInt(s3Arr[1])-1;

            set.union(dest, src);
            sb.append(set.maxTableSize).append("\n");
        }

        System.out.println(sb.toString());

    }
}
