package leetcode_top_interview_questions;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 */
public class Problem_0003_LongestSubstringWithoutRepeatingCharacters {
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || "".equals(s) || s.length() == 0) {
            return 0;
        }

        int[] map = new int[256];
        for (int i = 0; i < map.length; i++) {
            map[i] = -1;
        }
        char[] str = s.toCharArray();
        int ans = 1; //至少为1
        int pre = -1;//前一个位置往左推不动的位置
        for (int i = 0; i < str.length; i++) {
            int cur = Math.max(map[str[i]], pre);//当前位置往左推不到的位置
            ans = Math.max(ans, i - cur);
            pre = cur;
            map[str[i]] = i;
        }
        return ans;
    }

    //最长无重复子串长度
    public static int fun(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.length() == 1) return 1;
        int[] map = new int[256];//表示每个位置的字符上次出现的位置
        for (int i = 0; i < map.length; i++) {
            map[i] = -1;
        }
        //abcdabc
        char[] str = s.toCharArray();
        int ans = 1;
        int pre = 1;//i-1位置往左推的长度
        map[str[0]] = 0; //0位子字符上次出现在0位置
        for (int i = 1; i < str.length; i++) {
            int p1 = pre + 1;
            int p2 = i - map[str[i]];
            int cur = Math.min(p1, p2); //当前位置往左推的长度
            ans = Math.max(ans, cur);
            pre = cur;
            map[str[i]] = i;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(fun("abcdab"));
        System.out.println(lengthOfLongestSubstring("abcdab"));
    }
}
