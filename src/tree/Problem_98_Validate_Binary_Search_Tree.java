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

    //中序遍历违反严格递增就返回false
    public static boolean morrisIn(TreeNode root) {
        if (root == null) return true;
        TreeNode cur = root;
        TreeNode mostRight;
        Integer pre = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right; //找到左树最右节点
                }
                //while出来时：mostRight.right==null 或者 mostRight.right==cur
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { //mostRight.right==cur
                    //此处是第二次到达节点
                    mostRight.right = null;
                }
            }
            if (pre != null && pre >= cur.val) {
                return false;
            }
            pre = cur.val;
            cur = cur.right;
        }
        return true;
    }
}
