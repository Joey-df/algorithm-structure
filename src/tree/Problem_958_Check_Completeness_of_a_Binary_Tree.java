package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一棵二叉树的头节点head，返回这颗二叉树中是不是完全二叉树
 */
public class Problem_958_Check_Completeness_of_a_Binary_Tree {

    //宽度优先遍历
    //遇到有右无左，直接返回false
    //否则，如果遇到左右孩子不双全的节点，那么以后遇到的必须是叶子节点
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean found = false; //遇到 左右孩子不双全的 标记
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node.left == null && node.right != null) { //有右无左
                return false;
            }
            //found && 遇到非叶子节点，直接false
            if (found &&
                    (node.left != null || node.right != null)
            ) {
                return false;
            }
            if (node.left != null) q.offer(node.left);
            if (node.right != null) q.offer(node.right);
            if (node.left == null || node.right == null) {//遇到左右孩子不双全
                found = true;
            }
        }
        return true;
    }
}
