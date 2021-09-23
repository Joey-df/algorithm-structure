package two_pointers;

/**
 * 76. 最小覆盖子串
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。
 * 如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *
 * 示例 1：
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 *
 * 示例 2：
 * 输入：s = "a", t = "a"
 * 输出："a"
 */
public class Problem_0076_MinimumWindowSubstring {

    public String minWindow(String S, String T) {
        if (S.length() < T.length()) {
            return "";
        }
        char[] str = S.toCharArray();
        char[] target = T.toCharArray();
        int[] map = new int[256];//欠账表
        for (char c : target) {
            map[c]++;
        }
        int all = target.length;//欠账总数
        int L = 0, R = 0;
        int minLen = -1, ansL = -1, ansR = -1;

        while (R < str.length) {
            map[str[R]]--;
            if (map[str[R]] >= 0) {
                all--; //map[str[R]]减1之后>=0时，all--
            }
            if (all == 0) {
                while (map[str[L]] < 0) {
                    map[str[L]]++;
                    L++;
                }
                //收集答案
                if (minLen == -1 || minLen > R - L + 1) {
                    minLen = R - L + 1;
                    ansL = L;
                    ansR = R;
                }
                all++;
                //尝试下一个位置，重新开始欠账
                map[str[L]]++;
                L++;
            }
            R++;
        }
        return minLen == -1 ? "" : S.substring(ansL, ansR + 1);
    }

}
