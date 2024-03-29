package tree;

import java.util.Stack;

/**
 * 700. 二叉搜索树中的搜索
 * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。
 * 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
 * 例如，
 * 给定二叉搜索树:
 *         4
 *        / \
 *       2   7
 *      / \
 *     1   3
 * 和值: 2
 * 你应该返回如下子树:
 *       2
 *      / \
 *     1   3
 * 在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。
 */
public class Problem_0700_SearchInABinarySearchTree {

    //中序遍历在BST中找指定的节点
    public TreeNode searchBST(TreeNode root, int val) {
        if (root==null) return null;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode cur = root;
        while (cur!=null || !stack.isEmpty()) {
            if (cur!=null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode node = stack.pop();
                if (node.val == val) return node;
                cur = node.right;
            }
        }
        return null;
    }

}
