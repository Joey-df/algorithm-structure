package train_camp_03.class01;

/**
 * 给定一个二叉树的头节点head：
 * <p>
 * 2）路径可以从任何节点出发，但 必须 往下 走到达任何节点，返回最大路径和
 */
public class Code07_MaxSumInTree_2 {

    //分析：
    //与x无关：1、左树的整体最大路径和  2、右树的整体最大路径和
    //与x有关：3、仅x自己 4、x+左树从头出发的最大路径和  5、x+右树从头出发的最大路径和
    private static class Info {
        int allMax;
        int fromHeadMax;

        public Info(int allMax, int fromHeadMax) {
            this.allMax = allMax;
            this.fromHeadMax = fromHeadMax;
        }
    }

    public static Info process(TreeNode x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        int p1 = Integer.MIN_VALUE;
        if (leftInfo != null) {
            p1 = leftInfo.allMax;
        }

        int p2 = Integer.MIN_VALUE;
        if (rightInfo != null) {
            p2 = rightInfo.allMax;
        }

        int p3 = x.val;
        int p4 = Integer.MIN_VALUE;
        if (leftInfo != null) {
            p4 = leftInfo.fromHeadMax + x.val;
        }

        int p5 = Integer.MIN_VALUE;
        if (rightInfo != null) {
            p5 = rightInfo.fromHeadMax + x.val;
        }

        int allMax = Math.max(Math.max(p1, Math.max(p2, Math.max(p3, p4))), p5);
        int fromHeadMax = Math.max(Math.max(p3, p4), p5);
        return new Info(allMax, fromHeadMax);
    }

    public static int ways(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root).allMax;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-1);
        root.left = new TreeNode(200);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(-4);
        root.left.right = new TreeNode(-5);
        root.right.left = new TreeNode(-6);
        root.right.right = new TreeNode(-7);
        System.out.println(ways(root));
    }
}
