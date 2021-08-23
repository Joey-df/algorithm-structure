package two_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * <p>
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 * <p>
 * The order of output does not matter.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * s: "cbaebabacd" p: "abc"
 * <p>
 * Output:
 * [0, 6]
 * <p>
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 * <p>
 * Input:
 * s: "abab" p: "ab"
 * <p>
 * Output:
 * [0, 1, 2]
 * <p>
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 */
public class Problem_0438_FindAllAnagramsInAString {
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
