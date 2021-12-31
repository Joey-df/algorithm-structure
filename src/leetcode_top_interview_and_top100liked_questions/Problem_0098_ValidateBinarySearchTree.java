package leetcode_top_interview_and_top100liked_questions;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 */
public class Problem_0098_ValidateBinarySearchTree {

    private static class Info {
        int min;
        int max;
        boolean isAllBst;

        public Info(int min, int max, boolean is) {
            this.min = min;
            this.max = max;
            this.isAllBst = is;
        }
    }

    public static Info fun(TreeNode x) {
        if (x == null) {
            return null;
        }
        Info l = fun(x.left);
        Info r = fun(x.right);
        int min = x.val, max = x.val;
        boolean isAllBst = false;
        if (l != null) {
            min = Math.min(min, l.min);
            max = Math.max(max, l.max);
        }
        if (r != null) {
            min = Math.min(min, r.min);
            max = Math.max(max, r.max);
        }

        if (
                (l == null ? true : (l.isAllBst && l.max < x.val))
                        && (r == null ? true : (r.isAllBst && r.min > x.val))
        ) {
            isAllBst = true;
        }

        return new Info(min, max, isAllBst);
    }

    public static boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        return fun(root).isAllBst;
    }

    //方法二：中序遍历、是否违反严格递增
    public static boolean isValidBST2(TreeNode root) {
        if (root==null) return true;
        ArrayList<Integer> list = new ArrayList<>();
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        while(!stack.isEmpty() || cur!=null) {
            if (cur!=null) {
                stack.push(cur);
                cur = cur.left;//一路往左
            } else {
                TreeNode node = stack.pop();
                list.add(node.val);
                cur = node.right;
            }
        }
        boolean ans = true;
        int pre = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) <= pre) return false;
            pre = list.get(i);
        }
        return ans;
    }

    //方法二基础上优化（优化空间复杂度）
    public boolean isValidBST3(TreeNode root) {
        if (root==null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        Integer pre = null;
        while (cur!=null || !stack.isEmpty()) {
            if (cur!=null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode node = stack.pop();
                if (pre==null) {
                    pre = node.val;
                } else {
                    if (node.val <= pre) return false;
                    pre = node.val;
                }
                cur = node.right;
            }
        }
        return true;
    }
}
