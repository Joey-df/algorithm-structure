package two_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 438. 找到字符串中所有字母异位词
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 异位词 指字母相同，但排列不同的字符串。
 * <p>
 * 示例 1:
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 * <p>
 * 示例 2:
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 * <p>
 * 提示:
 * 1 <= s.length, p.length <= 3 * 10^4
 * s 和 p 仅包含小写字母
 */

//和567题几乎一样
public class Problem_0438_FindAllAnagramsInAString {
    // s: "cbaebabacd" p: "abc"
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        if (s == null || p == null || s.length() < p.length()) {
            return ans;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        char[] pst = p.toCharArray();
        int M = pst.length;
        int[] map = new int[26]; //欠帐表
        for (char c : pst) map[c - 'a']++;
        int all = M; //总欠帐数量
        for (int end = 0; end < M - 1; end++) { //窗口先涨到M-1的长度，使得初始start=0，end=M-1
            if (map[str[end] - 'a'] > 0) {
                all--;
            }
            map[str[end] - 'a']--;
        }
        //枚举每个窗口
        for (int end = M - 1, start = 0; end < N; end++, start++) {
            //end位置字符进窗口（当前[start,end]窗口形成）
            if (map[str[end] - 'a'] > 0) all--;
            map[str[end] - 'a']--;

            if (all == 0) { //结算当前[start,end]的窗口
                ans.add(start);
            }

            //start位置字符出窗口，进入下一个窗口
            if (map[str[start] - 'a'] >= 0) {
                all++; //重新欠账
            }
            map[str[start] - 'a']++;
        }
        return ans;
    }
}
