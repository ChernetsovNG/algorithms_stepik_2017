package Week3.problem_1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s1 = sc.nextLine();
        int n = Integer.parseInt(s1);

        List<String> result = new ArrayList<>();

        Map<String, String> telBook = new HashMap<>();

        String[] s1Arr;
        String name = "";
        String number = "";

        for (int i = 0; i < n; i++) {
            s1 = sc.nextLine();
            s1Arr = s1.split(" ");

            if (s1.contains("add")) {
                number = s1Arr[1];
                name = s1Arr[2];
                telBook.put(number, name);
            }
            else if (s1.contains("del")) {
                number = s1Arr[1];
                telBook.remove(number);
            }
            else if (s1.contains("find")) {
                number = s1Arr[1];
                result.add(telBook.get(number));
            }
        }

        for (int i = 0; i < result.size(); i++) {
            if (result.get(i) != null) {
                System.out.println(result.get(i));
            } else {
                System.out.println("not found");
            }
        }
    }
}
