package string;

/**
 * 541. 反转字符串 II
 * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每 2k 个字符反转前 k 个字符。
 * <p>
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 * <p>
 * 示例 1：
 * 输入：s = "abcdefg", k = 2
 * 输出："bacdfeg"
 * 示例 2：
 * 输入：s = "abcd", k = 2
 * 输出："bacd"
 * <p>
 * 提示：
 * 1 <= s.length <= 104
 * s 仅由小写英文组成
 * 1 <= k <= 104
 */
public class Problem_0541_ReverseStringII {

    public static String reverseStr(String s, int k) {
        char[] str = s.toCharArray();
        for (int start = 0; start < str.length; start += 2 * k)
            reverse(str, start, Math.min(start + k - 1, str.length - 1));
        return new String(str);
    }

    public static void reverse(char[] s, int l, int r) {
        if (s.length == 0 || l >= r) return;
        while (l < r) {
            char t = s[l];
            s[l++] = s[r];
            s[r--] = t;
        }
    }
}
