package tree;

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


    /**
     * 先序遍历
     * @param root
     */
    private static void preOrder(TreeNode root) {
        if (root==null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.val+" ");//出栈就打印
            //看node的左右节点，先压右，后压左
            if (node.right!=null) {
                stack.push(node.right);
            }
            if (node.left!=null) {
                stack.push(node.left);
            }
        }
        System.out.println();
    }

    /**
     * 后序遍历
     * @param root
     */
    public static void postOrder(TreeNode root) {
        if (root==null) return;
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node); //出栈就给stack2中压
            //看node的左右节点，先压左，后压右
            if (node.left!=null) {
                stack1.push(node.left);
            }
            if (node.right!=null) {
                stack1.push(node.right);
            }
        }
        //将stack2全部出栈，打印，就是后序遍历
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().val+" ");
        }
        System.out.println();
    }

    /**
     * 中序遍历
     * @param root
     */
    public static void inOrder(TreeNode root) {
        if (root==null) return;
        Stack<TreeNode> stack = new Stack<>();
        while(!stack.isEmpty()||root!=null) {
            if (root!=null) {
                stack.push(root);
                root = root.left; //一路往左窜
            } else {
                TreeNode node = stack.pop();
                System.out.print(node.val + "  ");
                root = node.right;
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
