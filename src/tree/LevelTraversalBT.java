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
        if (root==null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val+" ");//出队就打印
            //看node的左右节点，先压左，后压右
            if (node.left!=null) {
                queue.add(node.left);
            }
            if (node.right!=null) {
                queue.add(node.right);
            }
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
