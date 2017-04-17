package Week1.problem_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        char[] inputCharArray = input.toCharArray();
        int sizeInput = inputCharArray.length;

        Stack<Character> stack = new Stack<>();

        for (Character ch : inputCharArray) {
            stack.push(ch);
        }


        for (int i = 0; i < sizeInput; i++) {
            System.out.println(stack.pop());
        }
    }
}
