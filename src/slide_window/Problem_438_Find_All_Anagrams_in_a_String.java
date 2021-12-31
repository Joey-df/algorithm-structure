package slide_window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 438. 找到字符串中所有字母异位词
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 * 示例 1:
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 * 示例 2:
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 */
public class Problem_438_Find_All_Anagrams_in_a_String {
    // s: "cbaebabacd" p: "abc"
    public static List<Integer> process(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        if (s.length() < p.length()) return ans;
        int L = 0, R = 0;
        int[] map = new int[256]; // 欠账表
        char[] str = s.toCharArray();
        char[] ptr = p.toCharArray();
        for (char c : ptr) {
            map[c]++;
        }
        int all = ptr.length;
        int N = str.length;
        int M = ptr.length;

        //单独处理长度相等的情况
        if (s.length() == p.length()) {
            for (int i = 0; i < N; i++) {
                if (--map[str[i]] >= 0) { //--之后>=0
                    all--;
                }
            }
            if (all == 0) {
                ans.add(0);
            }
            return ans;
        }
        // N > M
        boolean flag = false;
        while (R < N) {
            while (N > M && !flag) {//窗口先走M个长度，以后L、R同步++
                map[str[R]]--;
                if (map[str[R]] >= 0) {
                    all--;
                }
                R++;
                if (R - L == M) flag = true;
            }

            if (all == 0) {
                ans.add(L);
            }
            //R要++ R位置的元素要进窗口
//            if (--map[str[R]] >= 0) {
//                all--;
//            }
//            R++;
            all -= (--map[str[R++]] >= 0) ? 1 :0;
            //L要++ L位置的元素要出窗口
//            if (map[str[L]]++ == 0) {
//                all++;
//            }
//            L++;
            all += (map[str[L++]]++ == 0) ? 1 : 0;
        }
        //单独处理str的最后M个字符
        if (all == 0) {
            ans.add(L);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(process("ababa", "ab"));
    }
}
