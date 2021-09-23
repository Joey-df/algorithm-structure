package two_pointers;

/**
 * 680. Valid Palindrome II
 * <p>
 * Given a string s, return true if the s can be palindrome after deleting at most one character from it.
 * <p>
 * Example 1:
 * Input: s = "aba"
 * Output: true
 * <p>
 * Example 2:
 * Input: s = "abca"
 * Output: true
 * Explanation: You could delete the character 'c'.
 * <p>
 * Example 3:
 * Input: s = "abc"
 * Output: false
 * <p>
 * Constraints:
 * 1 <= s.length <= 10^5
 * s consists of lowercase English letters.
 */
public class Problem_0680_ValidPalindromeII {

    public static boolean validPalindrome(String s) {
        char[] str = s.toCharArray();
        int l = 0, r = str.length - 1;
        while (l < r) {
            if (str[l] != str[r]) {
                return isPalindrome(str, l + 1, r) || isPalindrome(str, l, r - 1);
            }
            l++;
            r--;
        }
        return true;
    }

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
