package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 剑指 Offer 54. 二叉搜索树的第k大节点
 * 给定一棵二叉搜索树，请找出其中第 k 大的节点的值。
 *
 * 限制：
 * 1 ≤ k ≤ 二叉搜索树元素个数
 */
// 前提：树非空
// 否则讨论第k大无意义
public class Problem_Offer54_KthLargetElementInBST {

    int count = 0;
    TreeNode ans = null;

    // 方法1
    // 右  根  左，遍历刚好是降序，数到第k个就是 第k大
    public int kthLargest(TreeNode root, int k) {
        fun(root, k);
        return ans.val;
    }

    public void fun(TreeNode x, int k) {
        if (x==null) return;
        fun(x.right, k);
        if (++count == k) { // ++之后等于k，即表示的是从0开始第k-1个
            ans = x;
        }
        fun(x.left, k);
    }


    //方法2：迭代版中序遍历
    //1 ≤ k ≤ 二叉搜索树元素个数
    public int kthLargest2(TreeNode root, int k) {
        List<TreeNode> list = new ArrayList<>();
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode node = stack.pop();
                list.add(node);
                cur = node.right;
            }
        }
        return list.get(list.size() - k).val;
    }
}
