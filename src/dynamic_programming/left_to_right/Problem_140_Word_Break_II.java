package dynamic_programming.left_to_right;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * add spaces in s to construct a sentence where each word is a valid dictionary word.
 * Return all such possible sentences.
 * 
 * Note:
 * 
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 * 
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 * "cats and dog",
 * "cat sand dog"
 * ]
 * Example 2:
 * 
 * Input:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output:
 * [
 * "pine apple pen apple",
 * "pineapple pen apple",
 * "pine applepen apple"
 * ]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * Example 3:
 * 
 * Input:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output:
 * []
 * related topics:
 * dfs
 * 多叉树遍历
 * 从左往右的尝试模型
 * 动态规划作为子过程
 * 前缀树
 */
public class Problem_140_Word_Break_II {

    private static class Node {
        int pass;
        int end;
        Node[] nexts;

        public Node() {
            pass = 0;
            end = 0;
            nexts = new Node[26];
        }
    }

    public static List<String> ways(String s, List<String> wordDict) {
        List<String> ans = new ArrayList<>();
        if ("".equals(s) || s == null || s.length() == 0) {
            ans.add("");
            return ans;
        }
        Set<String> set = new HashSet<>();
        for (String word : wordDict) {
            set.add(word);
        }
        Node root = buildTrie(set);
        boolean[] dp = getDp(s, root);
        process(s, 0, new ArrayList<>(), root, ans, dp);
        return ans;
    }

    //使用set建前缀树
    private static Node buildTrie(Set<String> set) {
        Node root = new Node();

        for (String s : set) {
            root.pass++;
            char[] str = s.toCharArray();
            Node node = root;
            int path;
            for (int i = 0; i < str.length; i++) {
                path = str[i] - 'a';
                if (node.nexts[path] == null) {
                    node.nexts[path] = new Node();
                }
                node = node.nexts[path];
                node.pass++;
            }
            node.end++;
        }
        return root;
    }

    //给定字符串s、字典组成的前缀树
    //返回s每个位置是否为有效分割点
    //dp[i]的含义：i是否为有效分割点
    private static boolean[] getDp(String s, Node root) {
        int N = s.length();
        boolean[] dp = new boolean[N + 1];
        dp[N] = true;
        char[] str = s.toCharArray();
        //对于每一个位置index，判断它是否为有效分割点
        for (int index = N - 1; index >= 0; index--) {
            Node cur = root;//每次都从root开始
            //str[index...end]
            for (int end = index; end < N; end++) {
                int path = str[end] - 'a';
                if (cur.nexts[path] == null) {
                    break;
                }
                cur = cur.nexts[path];
                if (cur.end == 1 && dp[end + 1]) {
                    dp[index] = true;
                    break;//一旦发现index是有效分割位置，就进入下一个index的判断了
                }
            }
        }
        return dp;
    }

    //递归含义：
    //对于给定非空s,[0...index-1]范围上已经完美分解了，不需要操心了
    //path: [0...index-1]范围上完美分解所形成的路径
    //从index位置开始到s结尾，能分解的总方案 返回
    public static void process(String s, int index, List<String> path, Node root, List<String> ans, boolean[] dp) {
        if (index == s.length()) {
            //表示[0...s.length()-1]已经被完美分解掉了
            //此时应该收集答案
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size(); i++) {
                sb.append(path.get(i));
                if (i < path.size() - 1) {
                    sb.append(" ");
                }
            }
            ans.add(sb.toString());
        }

        //index后面还有字符
        Node cur = root;
        for (int end = index; end < s.length(); end++) {
            // index...end
            int road = s.charAt(end) - 'a';
            cur = cur.nexts[road];
            if (cur == null) break;
            if (cur.end == 1 && dp[end + 1]) {
                String word = s.substring(index, end + 1);
                path.add(word);
                process(s, end + 1, path, root, ans, dp);
                path.remove(path.size() - 1);//清理现场
            }
        }
    }


    public static void main(String[] args) {
        String s = "aaaaaaaaaaaaaaaaaaa";
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("aa");
        list.add("aaa");
        list.add("aaaa");
        list.add("aaaaa");
        list.add("aaaaaa");
        list.add("aaaaaaa");
        list.add("aaaaaaaa");
        list.add("aaaaaaaaa");
        list.add("aaaaaaaaaa");
        System.out.println(ways(s, list));
    }


}
