package Week2.problem_3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class DisjointSet {
    public int[] parent;
    public int[] rank;
    public int[] tablesSize;
    public int maxTableSize = 0;

    public DisjointSet(int size, int[] talsesSize) {
        parent = new int[size];
        rank = new int[size];
        this.tablesSize = talsesSize;

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
            tablesSize[i] = tablesSize[i] + tablesSize[j];
            tablesSize[j] = 0;
        } else {
            parent[i_id] = j_id;
            tablesSize[j] = tablesSize[i] + tablesSize[j];
            tablesSize[i] = 0;
            if (rank[i_id] == rank[j_id]) {
                int k = rank[j_id];
                rank[j_id] = k+1;
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

        int[] dest = new int[m];
        int[] src = new int[m];

        int[] maxTableSize = new int[m];

        for (int i = 0; i < m; i++) {
            String s3 = sc.nextLine();
            String[] s3Arr = s3.split(" ");

            dest[i] = Integer.parseInt(s3Arr[0])-1;
            src[i] = Integer.parseInt(s3Arr[1])-1;
        }

        //Решение
        DisjointSet set = new DisjointSet(n, r);

        for (int i = 0; i < m; i++) {
            set.union(src[i], dest[i]);
            maxTableSize[i] = 0;
        }

        for (int i = 0; i < n; i++) {
            System.out.println(set.tablesSize[i]);
        }

    }
}
