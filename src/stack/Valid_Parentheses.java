package stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 *
 * Example 2:
 * Input: s = "()[]{}"
 * Output: true
 */
public class Valid_Parentheses {
    public static boolean isValid(String s) {
        if ("".equals(s)|| s.length()==0) return false;
        char[] str = s.toCharArray();
        if (str[0]==')'||str[0]==']'||str[0]=='}') return false;

        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        Stack<Character> stack = new Stack<>();

        stack.push(str[0]);
        //遍历， 栈不空并且栈顶能配对就出栈，否则就进栈
        for (int i = 1; i < str.length; i++) {
            if (!stack.isEmpty() && map.get(str[i])==stack.peek()) {
                stack.pop();
            } else {
                stack.push(str[i]);
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println("ans = " + isValid("{}{}{}[][[]]"));
        System.out.println("ans = " + isValid("{}{{}{}[][[]]"));
        System.out.println("ans = " + isValid("{}[[]](){}"));
    }
}
