package routine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 在一个字符串中找到没有重复字符子串中最长的长度。
 * 例如:
 * abcabcbb没有重复字符的最长子串是abc，长度为3
 * bbbbb，答案是b，长度为1
 * pwwkew，答案是wke，长度是3
 * 要求:答案必须是子串，"pwke" 是一个子字符序列但不是一个子字符串。
 */

//每天一遍 最长无重复子串
public class Problem_3_Longest_Substring_Without_Repeating_Characters {

    public static int longestNoRepeat(String s) {
        if (s==null||s.length()==0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int[] map=new int[256];
        for (int i = 0; i < str.length; i++) {
            map[str[i]] = -1; //str每个字符上次出现的位置，初始化为-1
        }
        int pre=-1;//i-1位置往左推不动的位置在哪
        int ans = 0;
        for (int i = 0; i < str.length; i++) {
            int cur = Math.max(pre, map[str[i]]);//当前位置往左推不动的位置在哪
            ans = Math.max(ans, i - cur);
            pre = cur;
            map[str[i]] = i;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(longestNoRepeat("pwwkew"));
    }
}
