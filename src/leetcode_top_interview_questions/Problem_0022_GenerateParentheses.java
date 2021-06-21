package leetcode_top_interview_questions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：["()"]
 */
public class Problem_0022_GenerateParentheses {

    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        ArrayList<Character> path = new ArrayList<>();
        Set<Character> set = new HashSet<>();
        set.add('(');
        set.add(')');
        fun(n, 0, path, ans, set);
        return ans;
    }

    private static boolean isValid(String s) {
        int count = 0;
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++) {
            count += str[i] == '(' ? 1 : -1;
            if (count == -1) return false;
        }
        return count == 0;
    }

    //递归含义
    //n: 总共几对括号
    //当前来打index位置做决定：[0...index-1]已经做好决定了，不用操心了！
    //[0...index-1]做的决定形成的路径，存在path里
    //ans：收集答案，当index来到终止位置时收集答案
    private static void fun(int n, int index, ArrayList<Character> path, List<String> ans, Set<Character> set) {
        if (index == (n << 1)) {
            //收集答案
            StringBuilder sb = new StringBuilder();
            for (char c : path) {
                sb.append(c);
            }
            if (isValid(sb.toString())) {
                ans.add(sb.toString());
            }
            return;
        }
        for (char c : set) { //当前index位置的选择：'(' 或 ')'
            path.add(c);
            fun(n, index + 1, path, ans, set);
            path.remove(path.size() - 1);//清除现场
        }
    }


    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }
}
