package leetcode_top_interview_and_top100liked_questions;

import tree.TreeNode;

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
}
