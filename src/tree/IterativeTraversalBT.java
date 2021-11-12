package tree;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 迭代遍历二叉树
 * traversal binary tree iteratively.
 */
public class IterativeTraversalBT {

    private static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }


    //先序遍历打印二叉树
    //根 左 右
    public static void preOrder(TreeNode root) {
        if (root == null) return;
        //root不为空
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.offerLast(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pollLast();
            System.out.print(cur.val + " ");
            if (cur.right != null) stack.offerLast(cur.right);
            if (cur.left != null) stack.offerLast(cur.left);
        }
        System.out.println();
    }

    //后序遍历打印二叉树
    //左 右 根
    //刚好是 根 右 左 的反序，所以通过先序加工
    public static void postOrder(TreeNode root) {
        if (root == null) return;
        //root不为空
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<TreeNode> stack2 = new LinkedList<>();
        stack.offerFirst(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pollFirst();
            stack2.offerFirst(cur);
            if (cur.left != null) stack.offerFirst(cur.left);
            if (cur.right != null) stack.offerFirst(cur.right);
        }
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pollFirst().val + " ");
        }
        System.out.println();
    }

    //中序遍历二叉树
    //左 根 右
    public static void inOrder(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode node = stack.pop();
                System.out.print(node.val + " ");
                cur = node.right;
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
        System.out.println("======pre===========");
        preOrder(root);
        System.out.println("======in order===========");
        inOrder(root);
        System.out.println("======post===========");
        postOrder(root);
    }
}
