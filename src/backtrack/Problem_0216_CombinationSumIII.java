package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 216. Combination Sum III
 * Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
 * Only numbers 1 through 9 are used.
 * Each number is used at most once.
 * Return a list of all possible valid combinations.
 * The list must not contain the same combination twice, and the combinations may be returned in any order.
 *
 * Example 1:
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * Explanation:
 * 1 + 2 + 4 = 7
 * There are no other valid combinations.
 *
 * Example 2:
 * Input: k = 3, n = 9
 * Output: [[1,2,6],[1,3,5],[2,3,4]]
 * Explanation:
 * 1 + 2 + 6 = 9
 * 1 + 3 + 5 = 9
 * 2 + 3 + 4 = 9
 * There are no other valid combinations.
 *
 * Example 3:
 * Input: k = 4, n = 1
 * Output: []
 * Explanation: There are no valid combinations.
 * Using 4 different numbers in the range [1,9], the smallest sum we can get is 1+2+3+4 = 10 and since 10 > 1, there are no valid combination.
 *
 * Example 4:
 * Input: k = 3, n = 2
 * Output: []
 * Explanation: There are no valid combinations.
 *
 * Example 5:
 * Input: k = 9, n = 45
 * Output: [[1,2,3,4,5,6,7,8,9]]
 * Explanation:
 * 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 = 45
 * There are no other valid combinations.
 *
 * Constraints:
 * 2 <= k <= 9
 * 1 <= n <= 60
 */

//和77题类似

//k个数，累加和为n
//2 <= k <= 9
//1 <= n <= 60
//只能选择1～9之间的数，每个数最多只能用一次
public class Problem_0216_CombinationSumIII {

    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        if (k > 9 || k < 2 || k > n || n > 45) {
            return ans;
        }
        List<Integer> path = new ArrayList<>();
        process(k,n,1,path,ans);
        return ans;
    }

    // k, rest  固定输入参数
    // start及其往后自由选择k个数，搞定rest
    // start 当前来到start这个数做决定
    // [1...start-1]已经做好决定了，不用操心了
    // [1...start-1]已经做好决定，形成的路径 存在path里
    // ans收集答案
    private static void process(int k, int rest, int start, List<Integer> path, List<List<Integer>> ans) {
        if (rest < 0) {
            return;
        }
        if (rest==0 && path.size()==k) {
            ans.add(new ArrayList<>(path));
        } else { //rest > 0
            for (int cur = start; cur < 10; cur++) { //cur 当前选的数
                path.add(cur);
                process(k,rest-cur,cur+1,path,ans);
                path.remove(path.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(combinationSum3(3,7));
        System.out.println(combinationSum3(3,9));
        System.out.println(combinationSum3(4,1));
    }
}
