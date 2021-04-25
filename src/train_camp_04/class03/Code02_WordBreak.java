package train_camp_04.class03;

import java.util.HashSet;
import java.util.Set;

/**
 * 假设所有字符都是小写字母.   长字符串是str
 * words是去重的单词表, 每个单词都不是空字符串且可以使用任意次
 * 使用words中的单词有多少种拼接str的方式，返回方法数.
 * <p>
 * leetcode 139 140 同一个问题，区别是返回的结果不一样
 * 好题 //TODO 改动态规划
 */
public class Code02_WordBreak {

    //递归含义：
    //words单词表任意使用，拼出str[index....]的方法数是多少，返回
    //假设str[0...index-1]已经搞定了不需要操心了
    public static int process(String str, Set<String> set, int index) {
        if (index == str.length()) { //表示str[0...N-1]已经搞定了，此时是一种正确的方案
            return 1;
        }

        int ans = 0;
        // 枚举每一个结尾end
        for (int end = index; end < str.length(); end++) {
            if (set.contains(str.substring(index, end + 1))) {
                ans += process(str, set, end + 1);
            }
        }
        return ans;
    }

    public static int ways1(String str, String[] words) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        Set<String> set = new HashSet<>();
        for (String word : words) {
            set.add(word);
        }
        return process(str, set, 0);
    }

    public static class Node {
        int pass;
        int end;
        Node[] nexts;

        public Node() {
            this.nexts = new Node[26];
            pass = 0;
            end = 0;
        }
    }

    //使用前缀树
    public static int ways2(String str, String[] words) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        Set<String> set = new HashSet<>();
        for (String word : words) {
            set.add(word);
        }

        Node root = buildTrie(set);
        return process2(str.toCharArray(), root, 0);
    }

    //递归含义：
    //str[0...index-1]已经搞定了不用操心了
    //求str[index...N-1]，被单词表分解的方法数是多少
    private static int process2(char[] str, Node root, int index) {
        if (index == str.length) {
            //表示str[0...N-1]已经搞定了，此时代表一种方法
            return 1;
        }
        int ans = 0;
        Node cur = root;
        for (int end = index; end < str.length; end++) {
            //枚举每一个结束位置
            //index...index
            //index...index+1
            //index...index+2
            //...
            int path = str[end] - 'a';
            if (cur.nexts[path] == null) {
                break;
            }
            cur = cur.nexts[path];
            if (cur.end == 1) {
                ans += process2(str, root, end + 1);
            }
        }
        return ans;
    }

    private static Node buildTrie(Set<String> set) {
        Node root = new Node();
        for (String s : set) {
            Node cur = root; //每add一个单词，都从root开始，root.pass都要+1
            cur.pass++;
            char[] str = s.toCharArray();
            for (int i = 0; i < str.length; i++) {
                int path = str[i] - 'a';
                if (cur.nexts[path] == null) {
                    cur.nexts[path] = new Node();
                }
                cur.nexts[path].pass++;
                cur = cur.nexts[path];
            }
            cur.end++; //描黑
        }
        return root;
    }


    public static void main(String[] args) {
        String s = "aaaaaaaaaaaaaaaaaa";
        String[] list = new String[]{
                "a",
                "aa",
                "aaa",
                "aaaa",
                "aaaaa",
                "aaaaaa"
        };
        System.out.println(ways1(s, list));
        System.out.println(ways2(s, list));
    }
}
