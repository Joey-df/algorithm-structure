package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 104. 二叉树的最大深度
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 */
public class Problem_0104_MaximumDepthOfBinaryTree {

    //二叉树递归套路
    public static int maxDepth(TreeNode root) {
        if (root==null) return 0;
        int l = maxDepth(root.left);
        int r = maxDepth(root.right);
        return Math.max(l,r) + 1;
    }

    //层序遍历解法
    public static int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int height = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            height++; // 只要发现新层就+1
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if (cur.left!=null) q.offer(cur.left);
                if (cur.right!=null) q.offer(cur.right);
            }
        }
        return height;
    }
}
