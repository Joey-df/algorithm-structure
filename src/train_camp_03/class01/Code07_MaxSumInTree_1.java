package train_camp_03.class01;

/**
 * 给定一个二叉树的头节点head：
 * <p>
 * 1）规定路径必须是头节点出发，到叶节点为止，返回最大路径和
 */
public class Code07_MaxSumInTree_1 {

    public static int maxPathSum = Integer.MIN_VALUE;

    public static int ways1(TreeNode root) {
        if (root == null) return 0;
        maxPathSum = Integer.MIN_VALUE;
        process(root, 0);
        return maxPathSum;
    }

    //递归含义：
    //之前已经形成pre的路径和
    //求根节点到叶子节点的最大路径和
    private static void process(TreeNode x, int pre) {
        if (x.left == null || x.right == null) {
            maxPathSum = Math.max(maxPathSum, pre + x.val);
        }
        if (x.left != null) {
            process(x.left, pre + x.val);
        }
        if (x.right != null) {
            process(x.right, pre + x.val);
        }
    }

    //二叉树递归套路
    public static int ways2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process2(root);
    }

    //返回x为根节点的二叉树的最大路径和
    public static int process2(TreeNode x) {
        if (x.left == null && x.right == null) { //只有一个节点
            return x.val;
        }
        int nextMaxPathSum = Integer.MIN_VALUE;
        if (x.left != null) {
            nextMaxPathSum = process2(x.left);
        }
        if (x.right != null) {
            nextMaxPathSum = Math.max(nextMaxPathSum, process2(x.right));
        }
        return x.val + nextMaxPathSum;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        System.out.println(ways1(root));
        System.out.println(ways2(root));
    }
}
