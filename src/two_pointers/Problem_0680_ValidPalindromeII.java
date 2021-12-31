package two_pointers;

/**
 * 680. 验证str最多删除一个字符能否变成回文串
 * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 *
 * 示例 1:
 * 输入: s = "aba"
 * 输出: true
 * 示例 2:
 * 输入: s = "abca"
 * 输出: true
 * 解释: 你可以删除c字符。
 * 示例 3:
 * 输入: s = "abc"
 * 输出: false
 */
public class Problem_0680_ValidPalindromeII {

    public static boolean validPalindrome(String s) {
        char[] str = s.toCharArray();
        int l = 0, r = str.length - 1;
        while (l < r) {
            if (str[l] != str[r]) {
                //如果str[l] != str[r]时，判断分别删掉str[l]、str[r]后是否为回文串
                return isPalindrome(str, l + 1, r) || isPalindrome(str, l, r - 1);
            }
            //str[l] == str[r]时，l++，r--
            l++;
            r--;
        }
        return true;
    }

    //验证sc[l,r]范围上是否为回文串
    public static boolean isPalindrome(char[] sc, int l, int r) {
        while (l < r) {
            if (sc[l] != sc[r]) return false;
            l++;
            r--;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "abbb";
        System.out.println(validPalindrome(s));
    }
}
