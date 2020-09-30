package tow_pointer;

/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * <p>
 * Note: For the purpose of this problem, we define empty string as valid palindrome.
 * <p>
 * Example 1:
 * <p>
 * Input: "A man, a plan, a canal: Panama"
 * Output: true
 * Example 2:
 * <p>
 * Input: "race a car"
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * s consists only of printable ASCII characters.
 */
public class Problem_125_Valid_Palindrome {

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
