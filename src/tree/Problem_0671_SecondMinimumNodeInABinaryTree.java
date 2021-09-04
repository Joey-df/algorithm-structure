package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 671. 二叉树中第二小的节点
 * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。
 * 如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。
 * <p>
 * 更正式地说，root.val = min(root.left.val, root.right.val) 总成立。
 * <p>
 * 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
 */
public class Problem_0671_SecondMinimumNodeInABinaryTree {

    public int findSecondMinimumValue(TreeNode root) {
        if (root == null || root.right == null || root.left == null) return -1;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        Integer secondMin = null;
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur.right != null) q.offer(cur.right);
            if (cur.left != null) q.offer(cur.left);
            if (cur.val > root.val) {
                if (secondMin == null) {
                    secondMin = cur.val;
                } else {
                    secondMin = Math.min(secondMin, cur.val);
                }
            }
        }
        return secondMin == null ? -1 : secondMin;
    }

}
