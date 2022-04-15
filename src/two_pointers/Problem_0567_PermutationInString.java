package two_pointers;

/**
 * 567. 字符串的排列
 * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。
 * <p>
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 * <p>
 * 示例 1：
 * 输入：s1 = "ab" s2 = "eidbaooo"
 * 输出：true
 * 解释：s2 包含 s1 的排列之一 ("ba").
 * <p>
 * 示例 2：
 * 输入：s1= "ab" s2 = "eidboaoo"
 * 输出：false
 * <p>
 * 提示：
 * 1 <= s1.length, s2.length <= 10^4
 * s1 和 s2 仅包含小写字母
 */

//和438题几乎一样
public class Problem_0567_PermutationInString {

    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int n = str1.length;
        int m = str2.length;
        int all = n;//总欠帐数量
        int[] map = new int[26]; //欠帐表
        for (char c : str1) map[c - 'a']++;
        //在str2中，先让窗口成长到n-1的长度
        for (int end = 0; end < n - 1; end++) {
            if (map[str2[end] - 'a'] > 0) {
                all--; //还款
            }
            map[str2[end] - 'a']--;
        }

        //枚举每个i位置开始的窗口（窗口大小固定为n）
        for (int start = 0, end = n - 1; end < m; start++, end++) {
            //end位置字符进窗口（当前[start,end]窗口形成）
            if (map[str2[end] - 'a'] > 0) {
                all--;
            }
            map[str2[end] - 'a']--;

            if (all == 0) return true;

            //start位置字符出窗口，进入下一个窗口
            if (map[str2[start] - 'a'] >= 0) {
                all++; //重新欠账
            }
            map[str2[start] - 'a']++;
        }
        return all == 0;
    }


    public boolean checkInclusion2(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int n = str1.length;
        int m = str2.length;
        int all = n;//总欠帐数量
        int[] map = new int[26]; //欠帐表
        for (char c : str1) map[c - 'a']++;
        //在str2中，先让窗口成长到n的长度
        for (int end = 0; end < n; end++) {
            if (map[str2[end] - 'a'] > 0) {
                all--; //还款
            }
            map[str2[end] - 'a']--;
        }

        //枚举每个i位置开始的窗口（窗口大小固定为n）
        for (int start = 0, end = n; end < m; start++, end++) {
            //每个位置开始的窗口，都判断一次
            if (all == 0) return true;
            //右边进一个
            if (map[str2[end] - 'a'] > 0) {
                all--;
            }
            map[str2[end] - 'a']--;

            //左边出一个
            if (map[str2[start] - 'a'] >= 0) {
                all++; //重新欠账
            }
            map[str2[start] - 'a']++;
        }
        return all == 0;
    }
}
