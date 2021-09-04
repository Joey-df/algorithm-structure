package tree;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 *
 * Example 1:
 *
 * Input: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * Output: 1
 * Example 2:
 *
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * Output: 3
 * Follow up:
 * What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
 *
 * Constraints:
 *
 * The number of elements of the BST is between 1 to 10^4.
 * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 */
public class Problem_0230_KthSmallestElementInABST {
    //中序遍历的结果，数到第k个返回
    public int kthSmallest(TreeNode root, int k) {
        LinkedList<Integer> q = new LinkedList<>();
        inOrder(root, q);
        return q.get(k-1); //第k个下标是k-1
    }


    private void inOrder(TreeNode root, LinkedList<Integer> ans) {
        if (root==null) {
            return;
        }
        inOrder(root.left, ans);
        ans.addLast(root.val);
        inOrder(root.right, ans);
    }

    //迭代版本的中序遍历
    private void in(TreeNode root, LinkedList<Integer> ans) {
        if (root==null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode node = stack.pop();
                ans.addLast(node.val);
                cur = node.right;
            }
        }
    }
}
