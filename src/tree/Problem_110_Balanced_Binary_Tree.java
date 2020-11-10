package tree;

/**
 * 给定一棵二叉树的头节点head，返回这颗二叉树是不是平衡二叉树
 * 平衡二叉树：
 * 它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树
 */
public class Problem_110_Balanced_Binary_Tree {

    private static class Info {
        int height;
        boolean isBalance;

        public Info(int height, boolean isBalance) {
            this.height = height;
            this.isBalance = isBalance;
        }
    }

    public static Info process(TreeNode root) {
        if (root == null) {
            return new Info(0, true);
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        int h = 1 + Math.max(leftInfo.height, rightInfo.height);
        boolean isBalance = false;
        if (leftInfo.isBalance && rightInfo.isBalance
                && Math.abs(leftInfo.height - rightInfo.height) < 2) {
            isBalance = true;
        }
        return new Info(h, isBalance);
    }

}
