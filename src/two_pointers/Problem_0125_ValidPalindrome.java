package two_pointers;

/**
 * 125. 验证回文串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * 示例 1:
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 解释："amanaplanacanalpanama" 是回文串
 * 示例 2:
 * 输入: "race a car"
 * 输出: false
 * 解释："raceacar" 不是回文串
 */
public class Problem_0125_ValidPalindrome {

    private static boolean help(char s) {
        return ((s >= '0' && s <= '9') || (s >= 'a' && s <= 'z'));
    }

    public static boolean isPalindrome(String s) {
        if ("".equals(s) || s == null || s.length() == 0) return true;
        char[] str = s.toLowerCase().toCharArray();
        int L = 0;
        int R = str.length - 1;
        while (L < R) {
            if (help(str[L]) && help(str[R])) {
                if (str[L] != str[R]) {
                    return false;
                }
                L++;
                R--;
            } else if (!help(str[L])) {
                L++;
            } else {
                R--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "race a car";
        System.out.println(isPalindrome(s));
    }

}
