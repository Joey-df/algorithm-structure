package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes' values.
 * (ie, from left to right, then right to left for the next level and alternate between).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its zigzag level order traversal as:
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */
public class Problem_0103_BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans=new ArrayList<>();
        if (root==null) {
            return ans;
        }
        LinkedList<TreeNode> q=new LinkedList<>();
        q.offer(root); // addLast或者addFirst，这里无所谓
        boolean leftToRight=true;
        while (!q.isEmpty()) {
            List<Integer> sub = new ArrayList<>();
            int size = q.size();
            for (int i=0; i<size; i++) {
                if (leftToRight) {
                    TreeNode node = q.pollFirst();
                    sub.add(node.val);
                    if (node.left!=null) q.addLast(node.left);
                    if (node.right!=null) q.addLast(node.right);
                } else {
                    TreeNode node = q.pollLast();
                    sub.add(node.val);
                    if (node.right!=null) q.addFirst(node.right); //注意这里是，先右后左
                    if (node.left!=null) q.addFirst(node.left);
                }
            }
            ans.add(sub);
            leftToRight = !leftToRight;
        }
        return ans;
    }
}
