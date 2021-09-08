package dynamic_programming.left_to_right;

import java.util.HashMap;

/**
 * 给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文
 * arr每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来
 * 返回需要至少多少张贴纸可以完成这个任务。
 * 例子：str= "babac"，arr = {"ba","c","abcd"}
 * ba + ba + c  3张，abcd + abcd 2  abcd+ba 2
 * 所以返回2
 * 注意：每一张贴纸的数量都是无限的。
 */
//691. 贴纸拼词
//https://leetcode-cn.com/problems/stickers-to-spell-word/
//system_study.class18_23.Class19_Code03_StickersToSpellWord
//与377题的递归有相似之处
public class Problem_0691_StickersToSpellWord {

    public int minStickers(String[] stickers, String target) {
        if (stickers == null || stickers.length == 0) {
            return -1;
        }
        HashMap<String, Integer> dp = new HashMap<>();
        int ans = process(stickers, target, dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    //递归含义
    //任意使用arr,拼出target，使用贴纸的最少张数
    public int process(String[] arr, String target, HashMap<String, Integer> dp) {
        if (dp.containsKey(target)) {
            return dp.get(target);
        } else {
            int ans;
            if (target == null || target.length() == 0) { //拼出空串需要0张贴纸
                dp.put("", 0);
                ans = 0;
            } else {
                int min = Integer.MAX_VALUE;
                for (String first : arr) { //枚举第一张贴纸
                    String rest = minus(target, first);
                    if (rest.length() != target.length()) { //first中包含有target中的字符，才尝试
                        min = Math.min(min, process(arr, rest, dp));
                    }
                }
                ans = (min == Integer.MAX_VALUE) ? min : min + 1;
                dp.put(target, ans);
            }
            return ans;
        }
    }

    public String minus(String a, String b) {
        char[] str1 = a.toCharArray();
        char[] str2 = b.toCharArray();
        int[] map = new int[26];
        for (char c : str1) {
            map[c - 'a']++;
        }
        for (char c : str2) {
            if (map[c - 'a'] > 0) {
                map[c - 'a']--;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < map.length; i++) {
            if (map[i] > 0) {
                for (int j = 0; j < map[i]; j++) {
                    sb.append((char) (i + 'a'));
                }
            }
        }
        return sb.toString();
    }
}
