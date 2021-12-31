package tree;

/**
 * 96. 不同的二叉搜索树
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？
 * 返回满足题意的二叉搜索树的种数。
 *
 * Example:
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
 * 1 <= n <= 19
 */
public class Problem_0096_UniqueBinarySearchTrees {

    public int numTrees(int n) {
        if (n==0 || n==1) return 1;
        if (n==2) return 2;
        int[] dp = new int[n+1]; //dp[k]: the number of BST trees with k consecutive(连续) nodes.
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
