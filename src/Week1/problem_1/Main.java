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
        int index = 0;
        boolean success = true;

        Stack<Character> stack = new Stack<>();
        Stack<Integer> stackIndexOpen = new Stack<>();

        for (char ch : inputCharArray) {
            index++;
            if ((ch != '(') && (ch != ')') &&
                (ch != '[') && (ch != ']') &&
                (ch != '{') && ch != '}')
                continue;

            if ((ch == '(') || (ch == '[') || (ch == '{')) {
                stack.push(ch);
                stackIndexOpen.push(index);
            }
            else {
                if (stack.isEmpty()) {
                    System.out.println(index);
                    success = false;
                }

                if (success) {
                    char top = stack.pop();
                    if (((top == '(') && (ch != ')')) ||
                            ((top == '[') && (ch != ']')) ||
                            ((top == '{') && (ch != '}')))
                    {
                        System.out.println(index);
                        success = false;
                    }
                    else {
                        stackIndexOpen.pop();
                    }
                }

            }
        }

        if (success) {
            if (stack.isEmpty())
                System.out.println("Success");
            else
                System.out.println(stackIndexOpen.pop());
        }
    }
}
