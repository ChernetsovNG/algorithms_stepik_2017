package Week1.problem_4;

import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s1 = sc.nextLine();
        int n = Integer.parseInt(s1);

        Stack<Integer> stack = new Stack<>();
        Stack<Integer> stackMax = new Stack<>();

        String s2;
        String[] s2Array;
        int v;  //push v

        for (int i = 0; i < n; i++) {
            s2 = sc.nextLine();
            if (s2.contains("push")) {
                s2Array = s2.split(" ");
                v = Integer.parseInt(s2Array[1]);
                stack.push(v);
                if (stackMax.size() > 0) {
                    if (v >= stackMax.peek()) {
                        stackMax.push(v);
                    } else {
                        stackMax.push(stackMax.peek());
                    }
                } else {
                    stackMax.push(v);
                }
            }
            else if (s2.equals("pop")) {
                stack.pop();
                stackMax.pop();
            }
            else if (s2.equals("max")) {
                System.out.println(stackMax.peek());
            }
        }
    }

}
