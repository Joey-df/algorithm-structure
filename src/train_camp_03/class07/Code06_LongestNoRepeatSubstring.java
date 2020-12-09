package train_camp_03.class07;

import java.util.Arrays;

/**
 * 在一个字符串中找到没有重复字符子串中最长的长度。
 * 例如:
 * abcabcbb没有重复字符的最长子串是abc，长度为3
 * bbbbb，答案是b，长度为1
 * pwwkew，答案是wke，长度是3
 * 要求:答案必须是子串，"pwke" 是一个子字符序列但不是一个子字符串。
 * <p>
 * http://chenxii.cn/2020/02/22/D-DataStructureAndAlgorithm/E-ZuoInterview/78-lowestString/
 */
public class Code06_LongestNoRepeatSubstring {

    public static int process(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] map = new int[256];//每个位置字符出现的位置
        Arrays.fill(map, -1);
        char[] str = s.toCharArray();
        int pre = -1;//表示i-1位置往左推不动的位置
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < str.length; i++) {
            int cur = Math.max(pre, map[str[i]]);//当前位置往左推不动的位置
            ans = Math.max(ans, i - cur);
            pre = cur; //当前位置i往左推不动的位置 变成下一步的pre
            map[str[i]] = i;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(process("pwwkew"));
    }
}
