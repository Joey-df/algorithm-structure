package dynamic_programming.left_to_right;

import java.util.*;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * 
 * Note:
 * 
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 * 
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 * 
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * Example 3:
 * 
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 */
public class Problem_139_Word_Break {

    //递归含义：
    //s[0...index-1]已经被分解掉了，不用操心了
    //s[index...N-1]，即从index位置开始到字符串结束，能否被完全分解掉
    //set：wordDict
    public static boolean process(String s, int index, Set<String> set) {
        if (index == s.length()) {//表示[0...s.length()-1]已经被完美分解掉了
            return true;
        }
        //index<s.length() 即 index后面还有字符
        for (int end = index; end < s.length(); end++) {
            //枚举每一个结束位置
            //index...index
            //index...index+1
            //index...index+2
            //...
            if (set.contains(s.substring(index, end + 1))) {
                return process(s, end + 1, set);
            }
        }
        return false;
    }

    public static boolean ways1(String s, List<String> wordDict) {
        if ("".equals(s) || s == null || s.length() == 0) return false;
        Set<String> set = new HashSet<>();
        for (String word : wordDict) {
            set.add(word);
        }
        return process(s, 0, set);
    }

    //改动态规划
    //时间复杂度：O(N^3) 2阶调度+1阶求子串并且查hash表
    public static boolean dpWays(String s, Set<String> set) {
        int N = s.length();
        boolean[] dp = new boolean[N + 1];
        dp[N] = true;
        for (int index = N - 1; index >= 0; index--) {
            for (int end = index; end < s.length(); end++) {
                //枚举每一个结束位置
                if (set.contains(s.substring(index, end + 1))) {
                    dp[index] |= dp[end + 1];
                }
            }
        }
        return dp[0];
    }

    public static boolean ways2(String s, List<String> wordDict) {
        if ("".equals(s) || s == null || s.length() == 0) return false;
        Set<String> set = new HashSet<>();
        for (String word : wordDict) {
            set.add(word);
        }
        return dpWays(s, set);
    }

    public static void main(String[] args) {
        String s = "leetcodeleetcodeleetcodeu";
        List<String> list = new ArrayList<>();
        list.add("leet");
        list.add("code");
        System.out.println(ways1(s, list));
        System.out.println(ways2(s, list));
    }

    //TODO 使用前缀树优化
}
