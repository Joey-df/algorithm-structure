package system_study.class12;

import tree.TreeNode;

import java.util.LinkedList;

/**
 * 判断二叉树是否是完全二叉树
 * 常规解法
 */
//leetcode 958 https://leetcode.com/problems/check-completeness-of-a-binary-tree
public class Code01_IsCBT {

    //常规解法思路：
    //完全二叉树的特点：除了最后一层，其他层节点都是满的，最后一层如果不满的话也是从左到右依次变满的状态
    //使用层序遍历
    //遍历的过程中，如果遇到有右无左，直接返回false
    //如果发现了左右孩子不双全的节点，那么以后遇到的节点必须都是叶子节点
    public static boolean isComplete(TreeNode root) {
        if (root == null) return true;
        LinkedList<TreeNode> q = new LinkedList<>();
        q.offerLast(root);
        boolean find = false;
        while (!q.isEmpty()) {
            TreeNode cur = q.pollFirst();
            if (cur.right != null && cur.left == null) { //有右无左直接false
                return false;
            }
            if (find && (cur.left != null || cur.right != null)) {
                return false;
            }
            if (cur.left != null) {
                q.offerLast(cur.left);
            }
            if (cur.right != null) {
                q.offerLast(cur.right);
            }
            if (cur.left == null || cur.right == null) { //发现左右孩子不双全
                find = true;
            }
        }
        return true;
    }
}
