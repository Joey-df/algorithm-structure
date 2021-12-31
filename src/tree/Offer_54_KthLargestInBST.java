package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 剑指 Offer 54. 二叉搜索树的第k大节点
 * 给定一棵二叉搜索树，请找出其中第 k 大的节点的值。
 */
public class Offer_54_KthLargestInBST {

    //方法1：迭代版中序遍历
    public int kthLargest(TreeNode root, int k) {
        if (root == null || k <= 0) return -1;
        List<TreeNode> help = new ArrayList<>();
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode node = stack.pop();
                help.add(node);
                cur = node.right;
            }
        }
        return help.get(help.size() - k).val;
    }


    //方法2：使用递归序（右根左）
    //根据BST的性质，刚好是单调递减序列，数够第k个就是答案
    public static int count = 0;
    public static TreeNode ans;

    public static int kthLargest2(TreeNode root, int k) {
        fun(root, k);
        return ans.val;
    }

    public static void fun(TreeNode root, int k) {
        if (root == null) return;
        fun(root.right, k); //右
//        count++;
//        if(count==k) {
        if (++count == k) {  //根  ++之后等于k，即表示的是从0开始第k-1个
            ans = root;
        }
        fun(root.left, k); //左
    }

}
