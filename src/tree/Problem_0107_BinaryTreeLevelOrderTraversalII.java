package tree;

import java.util.*;

/**
 * 107. 二叉树的层序遍历 II
 * 给定一个二叉树，返回其节点值自底向上的层序遍历。
 * （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层序遍历为：
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 */
public class Problem_0107_BinaryTreeLevelOrderTraversalII {

    //方法1：使用LinkedList + 头插法
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            ans.add(0, level);
        }
        return ans;
    }

    // 方法2：ArrayList+反转
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root==null) return ans;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            List<Integer> sub = new ArrayList<>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                sub.add(node.val);
                if (node.left!=null) q.offer(node.left);
                if (node.right!=null) q.offer(node.right);
            }
            ans.add(sub);
        }
        //reverse
        Collections.reverse(ans); //反转list
        return ans;
    }

}
