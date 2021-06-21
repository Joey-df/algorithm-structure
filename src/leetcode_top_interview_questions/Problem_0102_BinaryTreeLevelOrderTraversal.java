package leetcode_top_interview_questions;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 102. 二叉树的层序遍历
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层序遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */
public class Problem_0102_BinaryTreeLevelOrderTraversal {

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root==null) return ans;
        LinkedList<TreeNode> q = new LinkedList<>();
        q.offerFirst(root);
        while(!q.isEmpty()) {
            int sz = q.size();
            ArrayList<Integer> levelAns = new ArrayList<>();
            while (sz-- > 0) {
                TreeNode cur = q.pollLast();
                levelAns.add(cur.val);
                if (cur.left!=null) {
                    q.offerFirst(cur.left);
                }
                if (cur.right!=null) {
                    q.offerFirst(cur.right);
                }
            }
            ans.add(levelAns);
        }
        return ans;
    }
}
