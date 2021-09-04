package tree;

import java.util.*;
/**
 * 513. 找树左下角的值
 * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
 *
 * 假设二叉树中至少有一个节点。
 */
public class Problem_0513_FindBottomLeftTreeValue {


    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        Stack<Integer> stack = new Stack<>();
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (i==0) stack.push(node.val); //每一层最左的节点
                if (node.left!=null) q.offer(node.left);
                if (node.right!=null) q.offer(node.right);
            }
        }
        return stack.peek();
    }

}
