package greedy;

/**
 * 409. 字符串中所有字符能组成的最长回文串
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 * <p>
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 * <p>
 * 注意:
 * 假设字符串的长度不会超过 1010。
 * <p>
 * 示例 1:
 * 输入:
 * "abccccdd"
 * 输出:
 * 7
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 */
public class Problem_0409_LongestPalindrome {

    public static int longestPalindrome(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] str = s.toCharArray();
        int[] map = new int[128];
        for (char c: str) {
            map[c]++;
        }
        int max = 0;
        for (int freq: map) {
            max += freq>1 ? (freq>>1)<<1 : 0;
        }
        return max==str.length ? max : max+1;
    }

    public static void main(String[] args) {
        int ans = longestPalindrome("abcccccdd");
        System.out.println(ans);
    }

}

