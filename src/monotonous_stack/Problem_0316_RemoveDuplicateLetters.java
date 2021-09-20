package monotonous_stack;

import java.util.Stack;

/**
 * 316. 去除重复字母
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。
 * 需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 *
 * 示例 1：
 * 输入：s = "bcabc"
 * 输出："abc"
 *
 * 示例 2：
 * 输入：s = "cbacdcbc"
 * 输出："acdb"
 *
 * 提示：
 * 1 <= s.length <= 10^4
 * s 由小写英文字母组成
 */
// 注意：
// 该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同
public class Problem_0316_RemoveDuplicateLetters {


    public static String removeDuplicateLetters(String s) {
        char[] str = s.toCharArray();
        int[] count = new int[256];
        for (char c : str) {
            count[c]++;
        }
        boolean[] visited = new boolean[256];
        Stack<Character> stack = new Stack<>(); //从底向上单调递增的栈
        for (char cur : str) {
            count[cur]--;
            if (!visited[cur]) {
                while (!stack.isEmpty() && stack.peek() > cur && count[stack.peek()] > 0) {
                    visited[stack.peek()] = false;
                    stack.pop();
                }
                stack.push(cur);
                visited[cur] = true;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : stack) { //从栈底到栈顶的顺序遍历，不是pop的顺序
            sb.append(c);
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        String s = "cbacdcbc";
        String s1 = removeDuplicateLetters(s);
        System.out.println(s1);
    }

}

