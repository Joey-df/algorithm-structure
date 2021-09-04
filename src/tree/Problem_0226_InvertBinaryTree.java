package tree;

//226. 翻转二叉树
public class Problem_0226_InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        if (root==null) return null;
        TreeNode l = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(l);
        return root;
    }

}
