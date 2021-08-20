package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. 组合
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 你可以按 任何顺序 返回答案。
 *
 * 示例 1：
 * 输入：n = 4, k = 2
 * 输出：
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 * 示例 2：
 * 输入：n = 1, k = 1
 * 输出：[[1]]
 *
 * 提示：
 * 1 <= n <= 20
 * 1 <= k <= n
 */

//和216题类似

// 题意：
// 给定 n ，k ，表示从 { 1, 2, 3 ... n } 中选 k 个数，输出所有可能，并且选出数字从小到大排列，每个数字只能用一次
// 学习链接：https://leetcode.wang/leetCode-77-Combinations.html
public class Problem_0077_Combinations {

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        process(n,k,1,path,ans);
        return ans;
    }

    //当前来到index数字做决定
    //index-1及其之前 已经做好决定了，不用操心了
    //index-1及其之前 所做的决定，形成的结果存在path里
    //n，k固定参数
    //ans 收集答案
    private static void process(int n, int k, int index, List<Integer> path, List<List<Integer>> ans) {
        if (path.size()==k) {
            //index-1及其之前 所做的决定，形成的path长度正好等于k，此时应该收集答案
            ans.add(new ArrayList<>(path));
        } else { //path.size < k
            for (int i = index; i <= n; i++) {
                path.add(i);
                process(n,k,i+1,path,ans);
                path.remove(path.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(combine(4,2));
    }
}
