package tree;

/**
 * 给定一棵二叉树的头节点head，
 * 返回这颗二叉树是不是满二叉树
 */
public class Problem_ValidateIsFullBinaryTree {

    public static class Info {
        int height;
        int nodes;
        public Info(int h, int n) {
            height = h;
            nodes = n;
        }
    }

    public static boolean isFull(TreeNode root) {
        if (root==null) return true;
        Info info = fun(root);
        return (1 << info.height) - 1 == info.nodes;
    }

    public static Info fun(TreeNode x) {
        if (x==null) return new Info(0,0);
        Info l = fun(x.left);
        Info r = fun(x.right);
        int height = Math.max(l.height, r.height) + 1;
        int nodes = l.nodes + r.nodes + 1;
        return new Info(height, nodes);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        System.out.println(isFull(root));
    }

}
