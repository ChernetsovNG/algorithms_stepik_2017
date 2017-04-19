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

class QueueStackMax {  //очередь на основе стеков с поддержкой максимумаg
    private StackMax stackLeft;
    private StackMax stackRight;

    public QueueStackMax() {
        stackLeft = new StackMax();
        stackRight = new StackMax();
    }

    public void pushBack(Integer i) {
        stackLeft.push(i);
    }

    //переписываем все элементы из левого стека в правый
    private void rewriteFromLeftStackToRight() {
        while (!stackLeft.isEmpty()) {
            stackRight.push(stackLeft.pop());
        }
    }

    public Integer popFront() {
        if (!stackRight.isEmpty()) {
            return stackRight.pop();
        } else {
            rewriteFromLeftStackToRight();
            return stackRight.pop();
        }
    }

    public boolean isEmpty() {
        return (stackLeft.isEmpty() && stackRight.isEmpty());
    }

    public int size() {
        return (stackLeft.size() + stackRight.size());
    }

    public int max() {
        if (stackLeft.isEmpty())
            return stackRight.max();
        else if (stackRight.isEmpty())
            return stackLeft.max();
        else {
            return max(stackLeft.max(), stackRight.max());
        }
    }

    private int max(int a, int b) {
        return a >= b ? a : b;
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

        int[] maxArr = new int[n - m + 1];

        QueueStackMax queue = new QueueStackMax();

        for (int i = 0; i < m; i++) {
            queue.pushBack(A[i]);
        }
        maxArr[0] = queue.max();

        for (int i = m; i < n; i++) {
            queue.popFront();
            queue.pushBack(A[i]);
            maxArr[i - m + 1] = queue.max();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n - m; i++) {
            sb.append(maxArr[i]).append(" ");
        }
        sb.append(maxArr[n - m]);

        System.out.println(sb.toString());
    }
}
