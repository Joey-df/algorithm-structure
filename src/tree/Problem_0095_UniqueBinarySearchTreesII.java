package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 95. 不同的二叉搜索树 II
 * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。
 * 可以按 任意顺序 返回答案。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：[[1]]
 *
 * 提示：
 * 1 <= n <= 8
 */
//好题，孪生题 96题
public class Problem_0095_UniqueBinarySearchTreesII {

    public List<TreeNode> generateTrees(int n) {
        if (n==0) return new ArrayList<>();
        return fun(1, n);
    }

    //递归含义
    //[start,end]闭区间上 生成所有的 BST 返回
    public List<TreeNode> fun(int start, int end) {
        List<TreeNode> ans = new ArrayList<>();
        if (start > end) {
            ans.add(null);
        } else if (start==end) {
            ans.add(new TreeNode(start));
        } else { // start < end
            for (int i = start; i <= end; i++) {
                //枚举每一个i作为root的情况
                List<TreeNode> l = fun(start, i - 1); //i作为root情况下，左子树的所有可能性
                List<TreeNode> r = fun(i + 1, end); //i作为root情况下，右子树的所有可能性
                //求所有组合
                for (TreeNode left : l) {
                    for (TreeNode right: r) {
                        TreeNode root = new TreeNode(i); //注意：这句必须放在这里
                        root.left = left;
                        root.right = right;
                        ans.add(root);
                    }
                }
            }
        }
        return ans;
    }

}
