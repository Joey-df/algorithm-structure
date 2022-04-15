package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 112. 路径总和
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，
 * 判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
 *
 * 叶子节点 是指没有子节点的节点。
 */
public class Problem_0112_PathSum {

    //方法一
    public boolean hasPathSum1(TreeNode root, int targetSum) {
        if (root==null) return false;
        if (root.left==null && root.right==null) return root.val==targetSum;
        if (root.left==null ^ root.right==null) {
            return root.left!=null ?
                    hasPathSum1(root.left, targetSum-root.val) :
                    hasPathSum1(root.right, targetSum-root.val);
        }
        return hasPathSum1(root.left, targetSum-root.val) ||
                hasPathSum1(root.right, targetSum-root.val);
    }

    //方法二
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root==null) return false;
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(root, path, ans, targetSum);
        return ans.size() > 0;
    }

    //path：x节点之前 沿途形成的路径
    public void dfs(TreeNode x, List<Integer> path, List<List<Integer>> ans, int targetSum) {
        if (x==null) return;
        path.add(x.val); //第一次到达 add
        if (x.left==null && x.right==null) { //叶子节点收集答案
            int sum = 0;
            for (int value : path) {
                sum += value;
            }
            if (sum == targetSum) ans.add(new ArrayList<>(path));
        }
        dfs(x.left, path, ans, targetSum);
        dfs(x.right, path, ans, targetSum);
        path.remove(path.size()-1); //最后一次到达 remove 清理现场
    }

}
