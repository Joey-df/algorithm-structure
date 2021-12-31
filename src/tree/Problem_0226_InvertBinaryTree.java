package tree;

//226. 翻转二叉树
/**
 * 翻转一棵二叉树。
 * 示例：
 * 输入：
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 */
public class Problem_0226_InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        if (root==null) return null;
        TreeNode l = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(l);
        return root;
    }

}
