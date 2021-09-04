package tree;

/**
 * 111. 二叉树的最小深度
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明：叶子节点是指没有子节点的节点。
 */
public class Problem_0111_MinimumDepthOfBinaryTree {

    public int minDepth(TreeNode root) {
        if (root==null) return 0;
        return fun(root);
    }

    public int fun(TreeNode x) {
        if (x==null) return 0;
        int l = fun(x.left);
        int r = fun(x.right);
        int h = 0;
        if (x.left==null && x.right==null) {
            h = 1;
        } else if (x.left==null ^ x.right==null) {
            h = (x.left!=null ? l : r ) + 1;
        } else {
            h = Math.min(l, r) + 1;
        }
        return h;
    }
}
