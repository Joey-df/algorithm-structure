package tree;

import java.util.Stack;

/**
 * 530. 二叉搜索树的最小绝对差
 * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 *
 * 示例：
 * 输入：
 *
 *    1
 *     \
 *      3
 *     /
 *    2
 *
 * 输出：
 * 1
 * 解释：
 * 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
 * 提示：
 * 树中至少有 2 个节点。
 */
//本题与 783 https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/ 相同
public class Problem_0530_MinimumAbsoluteDifferenceInBST {

    public int getMinimumDifference(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        Integer pre = null;
        int diff = Integer.MAX_VALUE;
        TreeNode cur = root;
        while (cur!=null || !stack.isEmpty()) {
            if (cur!=null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode node = stack.pop();
                if (pre==null) {
                    pre = node.val;
                } else { //从第二个节点才能开始算diff
                    diff = Math.min(diff, node.val-pre); //因为是BST，所以中序遍历一定是升序
                    pre = node.val; //当前值变pre
                }
                cur = node.right;
            }
        }
        return diff;
    }

}
