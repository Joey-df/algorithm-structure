package slide_window;

/**
 * 395. 至少有 K 个重复字符的最长子串
 * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。
 * 返回这一子串的长度。
 * 示例 1：
 * 输入：s = "aaabb", k = 3
 * 输出：3
 * 解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
 * 示例 2：
 * 输入：s = "ababbc", k = 2
 * 输出：5
 * 解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 */
public class Problem_395_Longest_Substring_with_At_Least_K_Repeating_Characters {

    //给定字符串只包含小写字母，所以用一个固定次数为26的for循环
    public int longestSubstring(String s, int k) {
        char[] str = s.toCharArray();
        int N = str.length;
        int ans = 0;
        //前提：每种字符出现的次数不少于k次
        //1种的时候，收集答案
        //2种的时候，收集答案
        //3种的时候，收集答案
        //...
        //26种的时候，收集答案
        //答案必在其中
        for (int require = 1; require <= 26; require++) {
            int collect = 0;//窗口内已经收集到的种类数
            int satisfy = 0;//窗口内已经收集到的字符，满足>=k的有几种
            int[] count = new int[26];
            int R = -1;//窗口右边界
            for (int L = 0; L < N; L++) {//L要尝试每个窗口的最左位置
                while (R + 1 < N && !(collect == require && count[str[R + 1] - 'a'] == 0)) { //R右扩
                    R++;
                    if (count[str[R] - 'a'] == 0) {
                        collect++;
                    }
                    if (count[str[R] - 'a'] == k - 1) {
                        satisfy++;
                    }
                    count[str[R] - 'a']++;
                }
                if (satisfy == require) {
                    ans = Math.max(ans, R - L + 1);
                }
                if (count[str[L] - 'a'] == 1) {
                    collect--;
                }
                if (count[str[L] - 'a'] == k) {
                    satisfy--;
                }
                count[str[L] - 'a']--;
            }
        }
        return ans;
    }
}
