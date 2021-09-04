package tree;

import coding_for_great_offer.class05.Hash;

import java.util.*;

/**
 * 653. 两数之和 IV - 输入 BST
 * 给定一个二叉搜索树 root 和一个目标结果 k，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
 */
public class Problem_0653_TwoSumIVInputIsABST {

    //利用中序遍历
    public boolean findTarget1(TreeNode root, int k) {
        if (root==null) return false;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        HashMap<Integer, Boolean> map = new HashMap<>();
        while (cur!=null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode node = stack.pop();
                if (map.containsKey(k-node.val)) {
                    return true;
                }
                map.put(node.val, true);
                cur = node.right;
            }
        }
        return false;
    }

    //利用层序遍历
    public boolean findTarget2(TreeNode root, int k) {
        if (root==null) return false;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        HashSet<Integer> set = new HashSet<>();
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if (set.contains(k-cur.val)) return true;
                set.add(cur.val);
                if (cur.left!=null) q.offer(cur.left);
                if (cur.right!=null) q.offer(cur.right);
            }
        }
        return false;
    }

}
