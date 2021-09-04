package tree;

//669. 修剪二叉搜索树
public class Problem_0669_TrimABinarySearchTree {

    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root==null) return null;
        if (root.val < low) return trimBST(root.right, low, high);
        if (root.val > high) return trimBST(root.left, low, high);
        // low <= root.val <= high
        root.left = root.val==low ? null : trimBST(root.left, low, high);
        root.right = root.val==high ? null : trimBST(root.right, low, high);
        return root;
    }
}
