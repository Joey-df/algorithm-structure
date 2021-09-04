package tree;

import java.util.Stack;

/**
 * 99. 恢复二叉搜索树
 * 给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 *
 * 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用常数空间的解决方案吗？
 */
public class Problem_0099_RecoverBinarySearchTree {

    //思路
    //根据BST的性质，中序遍历严格递增，找出两个违规的节点，交换即可
    // The first element：第一个 大于 其后继节点 的节点
    // the second element：最后一个 小于 其前驱节点 的节点
    //        3 2 1
    //        1 2 3
    //
    //        1 3 2 4
    //        1 2 3 4
    public void recoverTree(TreeNode root) {
        if (root==null) return;
        TreeNode first=null, second=null;
        TreeNode pre = null;
        Stack<TreeNode> s = new Stack<>();
        TreeNode cur = root;
        while (cur!=null || !s.isEmpty()) {
            if (cur!=null) {
                s.push(cur);
                cur = cur.left;
            } else {
                TreeNode node = s.pop();
                if (pre==null) { //第一个节点
                    pre = node;
                } else {
                    if (pre.val > node.val) {
                        if (first==null) first = pre; //只设置一次
                        second = node; //注意这行每次都要设置
                    }
                    pre = node;
                }
                cur = node.right;
            }
        }
        //swap
        int t = first.val;
        first.val = second.val;
        second.val = t;
    }

}
