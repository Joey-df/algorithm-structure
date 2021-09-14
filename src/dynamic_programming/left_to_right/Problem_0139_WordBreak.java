package dynamic_programming.left_to_right;

import java.util.*;

/**
 * 139. 单词拆分
 * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，
 * 判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 *
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 */
//前缀树
public class Problem_0139_WordBreak {

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


    private static class Node {
        int p;
        int e;
        Node[] nexts;

        public Node() {
            p = 0;
            e = 0;
            nexts = new Node[26];
        }
    }

    public static boolean ways3(String s, List<String> wordDict) {
        if ("".equals(s) || s == null || s.length() == 0) return false;
        Set<String> set = new HashSet<>();
        for (String word : wordDict) {
            set.add(word);
        }
        Node root = buildTrie(s, set);
        return dpWays3(s, set, root);
    }

    private static Node buildTrie(String s, Set<String> set) {
        Node root = new Node();
        Node cur;
        for (String word : set) {
            cur = root;//处理每一个word时，都从root开始往下
            root.p++;
            char[] str = word.toCharArray();
            for (int i = 0; i < str.length; i++) {
                int road = str[i] - 'a';
                if (cur.nexts[road] == null) {
                    cur.nexts[road] = new Node();
                }
                cur.nexts[road].p++;
                cur = cur.nexts[road];
            }
            //for循环结束，代表一个具体的单词加完前缀树了，此时e++；
            cur.e++;
        }
        return root;
    }

    //使用前缀树做优化
    private static boolean dpWays3(String s, Set<String> set, Node root) {
        int N = s.length();
        boolean[] dp = new boolean[N + 1];
        dp[N] = true;
        char[] str = s.toCharArray();
        for (int index = N - 1; index >= 0; index--) {
            Node cur = root;
            for (int end = index; end < s.length(); end++) {
                //枚举每一个结束位置
                //[index...end]
                int road = str[end] - 'a';
                cur = cur.nexts[road];
                if (cur == null) {
                    break;
                }
                if (cur.e == 1) {
                    dp[index] |= dp[end + 1];
                }
            }
        }
        return dp[0];
    }


    public static void main(String[] args) {
        String s = "leetcodeleetcodeleetcodeleet";
        List<String> list = new ArrayList<>();
        list.add("leet");
        list.add("code");
        System.out.println(ways1(s, list));
        System.out.println(ways2(s, list));
        System.out.println(ways3(s, list));
    }
}
