package train_camp_03.class01;

/**
 * 括号有效配对是指：
 * 1）任何一个左括号都能找到和其正确配对的右括号
 * 2）任何一个右括号都能找到和其正确配对的左括号
 * 有效的：(())  ()()   (()())  等
 * 无效的：(()   )(     等
 * 问题一：怎么判断一个括号字符串有效？
 * 问题二：如果一个括号字符串无效，返回至少填几个字符能让其整体有效
 */
 //leetcode 921
 //https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
public class Code02_NeedParentheses {

    //问题一：怎么判断一个小括号组成的字符串的有效性？
    //思路：只用一个变量即可
    //时间复杂度O(N)
    //额外空间复杂度O(1)
    public static boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return false; //人为规定空串非法
        }
        char[] str = s.toCharArray();
        int count = 0;
        for (int i = 0; i < str.length; i++) {
            count += str[i] == '(' ? 1 : -1;//遇到左括号就+1，遇到有扩后就-1
            if (count < 0) return false;
        }
        return count == 0;
    }

    //问题二：如果一个括号字符串无效，返回至少填几个字符能让其整体有效
    //例如 ))(
    public static int minAdd(String s) {
        if (s == null || s.length() == 0 || isValid(s)) return 0;
        int need = 0;
        int count = 0;
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++) {
            count += str[i] == '(' ? 1 : -1;
            if (count == -1) {
                need++;
                count = 0;
            }
        }
        return need + count;
    }

    public static void main(String[] args) {
        System.out.println(isValid("(())()"));
        System.out.println(minAdd("(())()"));
    }
}
