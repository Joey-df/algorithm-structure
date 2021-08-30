package stack;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * <p>
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * <p>
 * Note:
 * <p>
 * Division between two integers should truncate toward zero.
 * The given RPN expression is always valid.
 * That means the expression would always evaluate to a result and there won't be any divide by zero operation.
 * Example 1:
 * <p>
 * Input: ["2", "1", "+", "3", "*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 * Example 2:
 * <p>
 * Input: ["4", "13", "5", "/", "+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 * Example 3:
 * <p>
 * Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 * Output: 22
 * Explanation:
 * ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 * <p>
 */
//逆波兰式
public class Problem_0150_EvaluateReversePolishNotation {

    public static int evalRPN(String[] tokens) {
        Set<String> set = new HashSet<>();
        set.add("*");
        set.add("/");
        set.add("+");
        set.add("-");
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < tokens.length; i++) {
            String cur = tokens[i];
            if (!set.contains(cur)) {
                stack.addFirst(Integer.parseInt(cur));
            } else {
                int first = stack.pollFirst();
                int second = stack.pollFirst();
                int val = compute(first, second, cur);
                stack.addFirst(val);
            }
        }
        return stack.pollFirst();
    }

    private static int compute(int first, int second, String cur) {
        int res;
        switch (cur) {
            case "+":
                res = second + first;
                break;
            case "-":
                res = second - first;
                break;
            case "*":
                res = second * first;
                break;
            case "/":
                res = second / first;
                break;
            default:
                res = 0;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
    }
}
