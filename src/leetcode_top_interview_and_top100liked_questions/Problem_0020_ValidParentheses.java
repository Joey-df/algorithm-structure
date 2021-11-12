package leetcode_top_interview_and_top100liked_questions;

import java.util.Stack;

/**
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 */
public class Problem_0020_ValidParentheses {

    public static boolean isValid(String s) {
        if (s == null || s.length() < 2) return false;
        Stack<Character> stack = new Stack<>();
        char[] str = s.toCharArray();
        for (char c : str) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c == '(' ? ')' : (c == '[' ? ']' : '}'));
            } else { //右括号
                if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("[(])()()()[]{}]"));
    }
}
