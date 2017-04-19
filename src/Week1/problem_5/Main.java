package Week1.problem_5;

import java.util.Scanner;
import java.util.Stack;

class StackMax {
    private Stack<Integer> stack;     //стек значений
    private Stack<Integer> stackMax;  //стек максимумов

    public StackMax() {
        stack = new Stack<>();
        stackMax = new Stack<>();
    }

    public void push(Integer v) {
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

    public Integer pop() {
        stackMax.pop();
        return stack.pop();
    }

    public Integer max() {
        return stackMax.peek();
    }

    public boolean isEmpty() {
        return (stack.size() == 0);
    }

    public int size() {
        return stack.size();
    }
}

class QueueStackMax {  //очередь на основе стеков с поддержкой максимума
    private StackMax stackLeft;
    private StackMax stackRight;

    public QueueStackMax() {
        stackLeft = new StackMax();
        stackRight = new StackMax();
    }

    public void pushBack(Integer i) {

    }

    public Integer popFront() {
        return 0;
    }

    public boolean isEmpty() {
        return false;
    }

    public int size() {
        return 0;
    }
}

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
