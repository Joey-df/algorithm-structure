package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 404. 左叶子之和
 * 计算给定二叉树的所有左叶子之和。
 *
 * 示例：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 */
public class Problem_0404_SumOfLeftLeaves {

    public int sumOfLeftLeaves(TreeNode root) {
        if (root==null) return 0;
        if (root.left==null && root.right==null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int ans = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if (cur.left!=null) {
                    q.offer(cur.left);
                    if (cur.left.left==null && cur.left.right==null) {
                        ans += cur.left.val;
                    }
                }
                if (cur.right!=null) q.offer(cur.right);
            }
        }
        return ans;
    }

}
