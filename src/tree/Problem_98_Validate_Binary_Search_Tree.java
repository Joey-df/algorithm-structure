package tree;

/**
 * 给定一棵二叉树的头节点head，返回这颗二叉树是不是搜索二叉树
 */
public class Problem_98_Validate_Binary_Search_Tree {
    private static class Info {
        private int min;
        private int max;
        private boolean isAllBst;//是否整体是bst

        public Info(int min, int max, boolean isAllBst) {
            this.min = min;
            this.max = max;
            this.isAllBst = isAllBst;
        }
    }

    //以root为根的二叉树是否为BST
    public static Info process(TreeNode root) {
        if (root == null) {
            return null;
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        int min = root.val;
        int max = root.val;
        if (leftInfo != null) { //只需要和左子树pk最小值即可
            min = Math.min(min, leftInfo.min);
        }
        if (rightInfo != null) { //只需要和右子树pk最大值即可
            max = Math.max(max, rightInfo.max);
        }
        boolean isAllBst = false;
        if ((leftInfo == null ? true : (leftInfo.isAllBst && leftInfo.max < root.val))
                && (rightInfo == null ? true : (rightInfo.isAllBst && rightInfo.min > root.val))) {
            isAllBst = true;
        }
        return new Info(min, max, isAllBst);
    }
}
