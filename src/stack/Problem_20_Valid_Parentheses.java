package stack;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
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
public class Problem_20_Valid_Parentheses {
    public static boolean isValid(String s) {
        if ("".equals(s) || s.length() == 0) return false;
        char[] str = s.toCharArray();
        if (str[0] == ')' || str[0] == ']' || str[0] == '}') return false;

        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        Stack<Character> stack = new Stack<>();

        stack.push(str[0]);
        //遍历， 栈不空并且栈顶能配对就出栈，否则就进栈
        for (int i = 1; i < str.length; i++) {
            if (!stack.isEmpty() && map.get(str[i]) == stack.peek()) {
                stack.pop();
            } else {
                stack.push(str[i]);
            }
        }
        return stack.isEmpty();
    }

    public static boolean isValid2(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        char[] str = s.toCharArray();
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '{' || str[i] == '[' || str[i] == '(') {
                stack.addLast(str[i] == '{' ? '}' : (str[i] == '(' ? ')' : ']'));
            } else {
                if (stack.isEmpty()) {//如果栈为空时遇到了右括号
                    return false;
                }
                //栈非空
                if (stack.pollLast()!=str[i]) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println("ans = " + isValid("{}{}{}[][[]]"));
        System.out.println("ans = " + isValid("{}{{}{}[][[]]"));
        System.out.println("ans = " + isValid("{}[[]](){}{}"));
    }
}
