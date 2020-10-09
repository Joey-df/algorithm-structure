package array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
 *
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 *
 * Example:
 *
 * Input: 5
 * Output:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 */
public class Problem_118_Pascals_Triangle {

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        if (numRows < 1) {
            return ans;
        }
        process(numRows, ans);
        return ans;
    }

    private static void process(int n, List<List<Integer>> ans) {
        for (int i = 0; i < n; i++) {
            List<Integer> sub = new ArrayList<>(i + 1);
            if (i == 0) {
                sub.add(1);
                ans.add(sub);
                continue;
            }
            if (i == 1) {
                sub.add(1);
                sub.add(1);
                ans.add(sub);
                continue;
            }
            //i>=2
            sub.add(1);
            for (int j = 0; j < i - 1; j++) {
                List<Integer> temp = ans.get(i - 1);
                sub.add(temp.get(j) + temp.get(j + 1));
            }
            sub.add(1);
            ans.add(sub);
        }
    }

    public static void main(String[] args) {
        System.out.println(generate(1));
    }
}
