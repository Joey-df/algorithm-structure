package two_pointers;

import java.util.Arrays;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 */
//https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
public class Problem_0003_LongestSubstringWithoutRepeatingCharacters {

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] str = s.toCharArray();
        int[] map = new int[256]; //每个字符上次出现的位置
        Arrays.fill(map, -1);
        int n = str.length;
        int ans = 1;
        int pre = 1; //i-1位置往左推的长度
        map[str[0]] = 0; //str[0]上次出现在0位置
        for (int i = 1; i < n; i++) { //枚举每个结尾位置i往左推的长度
            int p1 = pre + 1;
            int p2 = i - map[str[i]];
            map[str[i]] = i;
            int cur = Math.min(p1, p2);
            ans = Math.max(ans, cur);
            pre = cur;
        }
        return ans;
    }

}
