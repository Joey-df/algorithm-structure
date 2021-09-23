package slide_window;

/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 *
 * Example:
 *
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * Note:
 *
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */

//时间复杂度 O(N)
public class Problem_76_Minimum_Window_Substring {
    public static String process(String S, String T) {
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

    public static void main(String[] args) {
        String S = "ADOBECODEBANC", T = "AB";
        System.out.println(process(S, T));
    }
}
