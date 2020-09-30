package tow_pointer;

/**
 * Given a non-empty string s, you may delete at most one character.
 * Judge whether you can make it a palindrome.
 * <p>
 * Example 1:
 * Input: "aba"
 * Output: True
 * Example 2:
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 * Note:
 * The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 */
public class Problem_680_Valid_Palindrome_II {
    public static boolean leftPlus(String s) {
        char[] str = s.toCharArray();
        int L = 0;
        int R = str.length - 1;
        boolean isFirstFind = false;
        while (L < R) {
            if (str[L] == str[R]) {
                R--;
            } else {
                if (!isFirstFind) {
                    isFirstFind = true;
                } else {
                    return false;
                }
            }
            L++;
        }
        return true;
    }

    public static boolean rightSub(String s) {
        char[] str = s.toCharArray();
        int L = 0;
        int R = str.length - 1;
        boolean isFirstFind = false;
        while (L < R) {
            if (str[L] == str[R]) {
                L++;
            } else {
                if (!isFirstFind) {
                    isFirstFind = true;
                } else {
                    return false;
                }
            }
            R--;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "deeee";
        System.out.println(leftPlus(s) || rightSub(s));
    }
}
