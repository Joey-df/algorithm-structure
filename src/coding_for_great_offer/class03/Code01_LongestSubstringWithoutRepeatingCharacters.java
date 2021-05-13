package coding_for_great_offer.class03;

// 本题测试链接 : https://leetcode.com/problems/longest-substring-without-repeating-characters/
// 求一个字符串中，最长无重复字符子串长度
public class Code01_LongestSubstringWithoutRepeatingCharacters {

    //思路：
    //遇到子数组/子串：考虑开头 or 结尾
    //这道题，考虑子串必须以每一个位置i结尾，求最大无重复字符的长度，求整体max
    public static int lengthOfLongestSubstring1(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        char[] str = s.toCharArray();
        int[] map = new int[256]; //ascii码为i的字符上次出现在v位置
        for (int i = 0; i < map.length; i++) {
            map[i] = -1;
        }
        int ans = 0;
        int pre = 0;//i-1位置往左推的长度
        for (int i = 0; i < str.length; i++) {
            int p1 = i - map[str[i]]; //情况1: str[i]上次出现的位置
            int p2 = pre + 1;//情况2: i-1位置往左推的长度
            int cur = Math.min(p1, p2);//当前i位置往左推的长度
            pre = cur;
            ans = Math.max(ans, cur);
            map[str[i]] = i;
        }
        return ans;
    }


    public static int lengthOfLongestSubstring2(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        char[] str = s.toCharArray();
        int[] map = new int[256]; //ascii码为i的字符上次出现在v位置
        for (int i = 0; i < map.length; i++) {
            map[i] = -1;
        }
        int ans = 0;
        int pre = -1;//i-1位置往左推不动的位置在哪
        for (int i = 0; i < str.length; i++) {
            int cur = Math.max(map[str[i]], pre);//当前位置i往左推不动的位置
            ans = Math.max(ans, i - cur);
            pre = cur;
            map[str[i]] = i;
        }
        return ans;
    }

}

