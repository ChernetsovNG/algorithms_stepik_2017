package Week4.problem_2;

import java.util.Scanner;

class BinaryTree {
    private int index;

    int key;

    int left;
    int right;

    BinaryTree(int index, int key, int left, int right) {
        this.index = index;
        this.key = key;
        this.left = left;
        this.right = right;
    }
}

public class Main {
    private static BinaryTree[] trees;

    private static boolean check(BinaryTree v, long min, long max) {
        if ((v.key < min) || (v.key >= max)) {
            return false;
        }
        else {
            if ((v.left == -1) && (v.right == -1)) {
                return true;
            }
            else if ((v.left != -1) && (v.right == -1)) {
                return check(trees[v.left], min, v.key);
            }
            else if (v.left == -1) {
                return check(trees[v.right], v.key, max);
            }
            else {
                return check(trees[v.left], min, v.key) &&
                        check(trees[v.right], v.key, max);
            }
        }
    }

    private static boolean isCorrectSearchTree(BinaryTree v) {
        return check(v, Integer.MIN_VALUE, ((long) Integer.MAX_VALUE)+1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        int n = Integer.parseInt(s);  //число вершин

        if (n == 0) {
            System.out.println("CORRECT");
        } else {
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

            if (isCorrectSearchTree(trees[0]))
                System.out.println("CORRECT");
            else
                System.out.println("INCORRECT");
        }

    }
}
