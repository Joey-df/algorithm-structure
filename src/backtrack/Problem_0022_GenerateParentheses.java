package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: ["()"]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 8
 */
public class Problem_0022_GenerateParentheses {

    public static List<String> generateParenthesis(int n) {
        char[] path = new char[n << 1];
        List<String> ans = new ArrayList<>();
        process(0, path, ans);
        return ans;
    }

    // 递归含义：path[index,n-1]上()自由选择，组成的有效括号串填充在ans中
    // path[0,index-1]已经搞定了，不用再操心了
    // path:[0,index-1]沿途形成的路径
    public static void process(int index, char[] path, List<String> ans) {
        if (index == path.length) {//path[0,n-1]已经搞定了.收集答案
            if (isValid(path)) {
                ans.add(String.valueOf(path));
            }
        } else {
            path[index] = '(';
            process(index + 1, path, ans);
            path[index] = ')';
            process(index + 1, path, ans);
        }
    }

    private static boolean isValid(char[] path) {
        int count = 0;
        for (int i = 0; i < path.length; i++) {
            if (path[i] == '(') {
                count++;
            } else {
                count--;
            }
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }
}
