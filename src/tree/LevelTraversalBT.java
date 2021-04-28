package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 层序遍历二叉树
 * 只能使用迭代，没有递归版本
 */
public class LevelTraversalBT {

    private static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void levelTraversal(TreeNode root) {
        if (root == null) return;
        //root不为空
        LinkedList<TreeNode> q = new LinkedList<>();
        q.offerLast(root);
        while (!q.isEmpty()) {
            TreeNode cur = q.pollFirst();
            System.out.print(cur.val + " ");
            if (cur.left != null) q.offerLast(cur.left);
            if (cur.right != null) q.offerLast(cur.right);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        levelTraversal(root);
    }
}
