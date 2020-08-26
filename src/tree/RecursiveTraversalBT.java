package tree;

/**
 * 递归遍历二叉树
 * recursive traversal binary tree
 */
public class RecursiveTraversalBT {

    private static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    private static void preOrder(TreeNode root) {
        if (root==null) return;
        System.out.print(root.val+ " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    private static void inOrder(TreeNode root) {
        if (root==null) return;
        inOrder(root.left);
        System.out.print(root.val+ " ");
        inOrder(root.right);
    }

    private static void postOrder(TreeNode root) {
        if (root==null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val+ " ");
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println("======pre order=========");
        preOrder(root);
        System.out.println();
        System.out.println("======in order=========");
        inOrder(root);
        System.out.println();
        System.out.println("=======post order========");
        postOrder(root);
    }
}
