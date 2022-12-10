import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
//https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
public class Main {
    public static void main(String[] args) {
        System.out.println(returnValidString("))(("));
        System.out.println("Hello world!");
    }

    private static String returnValidString(String str) {
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack.push(i);
            } else if (str.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    indexes.add(i);
                } else {
                    stack.pop();
                }
            }
        }
        while (!stack.isEmpty()) {
            indexes.add(stack.pop());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (!indexes.contains(i)) {
                sb.append(str.charAt(i));
            }
        }

        return sb.toString();
    }

}