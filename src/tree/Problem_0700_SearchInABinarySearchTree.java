package tree;

import java.util.Stack;

/**
 * 700. Search in a Binary Search Tree
 * You are given the root of a binary search tree (BST) and an integer val.
 *
 * Find the node in the BST that the node's value equals val and return the subtree rooted with that node.
 * If such a node does not exist, return null.
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
