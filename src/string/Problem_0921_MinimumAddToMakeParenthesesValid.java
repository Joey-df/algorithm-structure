package string;

/**
 * Given a string S of '(' and ')' parentheses,
 * we add the minimum number of parentheses ( '(' or ')', and in any positions )
 * so that the resulting parentheses string is valid.
 * 
 * Formally, a parentheses string is valid if and only if:
 * 
 * It is the empty string, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 * Given a parentheses string, return the minimum number of parentheses we must add to make the resulting string valid.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: "())"
 * Output: 1
 * Example 2:
 * 
 * Input: "((("
 * Output: 3
 * Example 3:
 * 
 * Input: "()"
 * Output: 0
 * Example 4:
 * 
 * Input: "()))(("
 * Output: 4
 * 
 * 
 * Note:
 * 
 * S.length <= 1000
 * S only consists of '(' and ')' characters.
 */
public class Problem_0921_MinimumAddToMakeParenthesesValid {

    public static int ways(String s) {
        if ("".equals(s) || s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int need = 0;
        int count = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '(') {
                count++;
            } else { //str[i]==')'
                count--;
            }
            if (count == -1) {
                need++;
                count = 0;
            }
        }
        return need + count;
    }

    public static void main(String[] args) {
        String s = "()))((";
        System.out.println(ways(s));
    }
}
