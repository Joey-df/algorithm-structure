package tree;

import java.util.Stack;

/**
 * 230. 二叉搜索树中第K小的元素
 * 给定一个二叉搜索树的根节点 root，和一个整数 k，
 * 请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 *
 * 树中的节点数为 n 。
 * 1 <= k <= n <= 10^4
 * 0 <= Node.val <= 10^4
 */
public class Problem_0230_KthSmallestElementInABST {

    int count = 0;
    TreeNode ans = null;
    // 方法1：使用递归中序遍历
    // 数到第k个，收集答案
    public int kthSmallest(TreeNode root, int k) {
        inOrder(root, k);
        return ans.val;
    }


    private void inOrder(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        inOrder(root.left, k);
        if (++count==k) {
            ans = root;
        }
        inOrder(root.right, k);
    }


    // 方法2
    // 使用迭代版本的中序遍历
    // 数到第k个，直接返回
    public static int kthSmallest2(TreeNode root, int k) {
        TreeNode node = in(root, k);
        return node.val;
    }

    private static TreeNode in(TreeNode root, int k) {
        TreeNode res = null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode node = stack.pop();
                if (--k == 0) {
                    res = node;
                    break;
                }
                cur = node.right;
            }
        }
        return res;
    }
}
