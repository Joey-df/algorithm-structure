package stack;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/**
 * 20. 有效的括号
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 * <p>
 * An input string is valid if:
 * <p>
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * <p>
 * Example 2:
 * Input: s = "()[]{}"
 * Output: true
 */
public class Problem_0020_ValidParentheses {
    public static boolean isValid(String s) {
        if (s == null || s.length() == 0) return true;
        char[] arr = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : arr) {
            if (c=='(' || c=='{' ||c=='[') {
                stack.push(c == '(' ? ')' : (c == '{' ? '}' : ']'));
            } else {
                //遇到右括号时栈时空的，直接false
                if (stack.isEmpty() || stack.pop() != c) return false;
            }
        }
        return stack.isEmpty();
    }


    public static void main(String[] args) {
        System.out.println("ans = " + isValid("{}{}{()}[][[]]"));
        System.out.println("ans = " + isValid("{}{{}{}[][[]]"));
        System.out.println("ans = " + isValid("{}[[]]]](){}{}"));
    }
}
