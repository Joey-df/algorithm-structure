package dynamic_programming.left_to_right;

/**
 * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
 *
 * Example:
 *
 * Input: 3
 * Output: 5
 * Explanation:
 * Given n = 3, there are a total of 5 unique BST's:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 * Constraints:
 *
 * 1 <= n <= 19
 */
//https://leetcode.com/problems/unique-binary-search-trees/discuss/31707/Fantastic-Clean-Java-DP-Solution-with-Detail-Explaination
public class Problem_0096_UniqueBinarySearchTrees {

    //First note that dp[k] represents the number of BST trees built from 1....k;
    //
    //Then assume we have the number of the first 4 trees: dp[1] = 1 ,dp[2] =2 ,dp[3] = 5, dp[4] =14 , how do we get dp[5] based on these four numbers is the core problem here.
    //
    //The essential process is:
    // to build a tree, we need to pick a root node, then we need to know how many possible left sub trees and right sub trees can be held under that node, finally multiply them.
    //
    //To build a tree contains {1,2,3,4,5}. First we pick 1 as root, for the left sub tree, there are none;
    // for the right sub tree, we need count how many possible trees are there constructed from {2,3,4,5},
    // apparently it's the same number as {1,2,3,4}. So the total number of trees under "1" picked as root is dp[0] * dp[4] = 14. (assume dp[0] =1).
    // Similarly, root 2 has dp[1]*dp[3] = 5 trees. root 3 has dp[2]*dp[2] = 4, root 4 has dp[3]*dp[1]= 5 and root 5 has dp[0]*dp[4] = 14.
    // Finally sum the up and it's done.
    //
    //Now, we may have a better understanding of the dp[k], which essentially represents the number of BST trees with k consecutive nodes.
    // It is used as database when we need to know how many left sub trees are possible for k nodes when picking (k+1) as root.

    //the complete version of this induction rule :f(n) = f(0) * f(n - 1) + f(1) * f(n - 2) + ...+ f(n - 1) * f(0),
    //while the n in f(n) means the number of BST trees with n nodes.

    public int numTrees(int n) {
        if (n==0 || n==1) return 1;
        if (n==2) return 2;
        int[] dp = new int[n+1]; //dp[k]: the number of BST trees with k consecutive nodes.
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int k = 3; k <= n; k++) {
            //枚举1～k每个值作为root
            //1做root时，左子树个数0（1-1），右子树个数k-1
            //2做root时，左子树个数1（2-1），右子树个数k-2
            //3做root时，左子树个数1（3-1），右子树个数k-3
            for (int root = 1; root <= k; root++) {
                dp[k] += dp[root-1] * dp[k-root]; //左子树数量 * 右子树数量
            }
        }
        return dp[n];
    }

}
