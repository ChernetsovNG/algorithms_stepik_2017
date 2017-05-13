package Week4.problem_1;

import java.util.Scanner;

class BinaryTree {
    int index;

    int key;

    int left;
    int right;

    public BinaryTree(int index, int key, int left, int right) {
        this.index = index;
        this.key = key;
        this.left = left;
        this.right = right;
    }
}

public class Main {
    public static BinaryTree[] trees;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        int n = Integer.parseInt(s);  //число вершин

        trees = new BinaryTree[n];

        String[] sArr;
        int key;
        int left;
        int right;

        for (int i = 0; i < n; i++) {
            s = sc.nextLine();
            sArr = s.split(" ");

            key = Integer.parseInt(sArr[0]);
            left = Integer.parseInt(sArr[1]);
            right = Integer.parseInt(sArr[2]);

            trees[i] = new BinaryTree(i, key, left, right);
        }

        StringBuilder sb = new StringBuilder();
        String result = "";
        //1. Обход в порядке in-order
        inOrder(trees[0], sb);
        result = sb.toString().trim();
        System.out.println(result);
        sb.setLength(0);
        //2. Обход в порядке pre-order
        preOrder(trees[0], sb);
        result = sb.toString().trim();
        System.out.println(result);
        sb.setLength(0);
        //3. Обход в порядке post-order
        postOrder(trees[0], sb);
        result = sb.toString().trim();
        System.out.println(result);
        sb.setLength(0);
    }

    public static void inOrder(BinaryTree v, StringBuilder sb) {
        if (v.left != -1) {
            inOrder(trees[v.left], sb);
        }
        sb.append(v.key).append(" ");
        if (v.right != -1) {
            inOrder(trees[v.right], sb);
        }
    }

    public static void preOrder(BinaryTree v, StringBuilder sb) {
        sb.append(v.key).append(" ");
        if (v.left != -1) {
            preOrder(trees[v.left], sb);
        }
        if (v.right != -1) {
            preOrder(trees[v.right], sb);
        }
    }

    public static void postOrder(BinaryTree v, StringBuilder sb) {
        if (v.left != -1) {
            postOrder(trees[v.left], sb);
        }
        if (v.right != -1) {
            postOrder(trees[v.right], sb);
        }
        sb.append(v.key).append(" ");
    }

}
