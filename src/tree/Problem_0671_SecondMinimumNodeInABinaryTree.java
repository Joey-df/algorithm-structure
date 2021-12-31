package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 671. 二叉树中第二小的节点
 * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。
 * 如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。
 *
 * 更正式地说，root.val = min(root.left.val, root.right.val) 总成立。
 *
 * 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
 */
public class Problem_0671_SecondMinimumNodeInABinaryTree {

    //根据题目中的描述「如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个」，
    //我们可以知道，对于二叉树中的任意节点 xx，xx 的值不大于其所有子节点的值，因此：
    //对于二叉树中的任意节点 xx，xx 的值不大于以 xx 为根的子树中所有节点的值。
    //令 x 为二叉树的根节点，此时我们可以得出结论：
    //二叉树根节点的值即为所有节点中的最小值。
    //因此，我们可以对整棵二叉树进行一次遍历。设根节点的值为 root.value，
    //我们只需要通过遍历，找出严格大于root.value 的最小值，即为「所有节点中的第二小的值」。
    public static int findSecondMinimumValue2(TreeNode root) {
        if (root == null || root.right == null || root.left == null) return -1;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        Integer secondMin = null;
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur.left != null) q.offer(cur.left);
            if (cur.right != null) q.offer(cur.right);
            if (cur.val > root.val) { //找严格大于root.value的最小值，即为答案
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
