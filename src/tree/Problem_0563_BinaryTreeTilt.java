package tree;

/**
 * 563. 二叉树的坡度
 * 给定一个二叉树，计算 整个树 的坡度 。
 * <p>
 * 一个树的 节点的坡度 定义即为，该节点左子树的节点之和和右子树节点之和的 差的绝对值 。
 * 如果没有左子树的话，左子树的节点之和为 0 ；没有右子树的话也是一样。空结点的坡度是 0 。
 * <p>
 * 整个树 的坡度就是其所有节点的坡度之和。
 */
public class Problem_0563_BinaryTreeTilt {

    private static class Info {
        int sum; //节点之和
        int tilt; //坡度

        public Info(int sum, int tilt) {
            this.sum = sum;
            this.tilt = tilt;
        }
    }

    public static int findTilt(TreeNode root) {
        if (root == null) return 0;
        return process(root).tilt;
    }

    public static Info process(TreeNode x) {
        if (x == null) {
            return new Info(0, 0);
        }
        Info l = process(x.left);
        Info r = process(x.right);
        int tilt = l.tilt + r.tilt + Math.abs(l.sum - r.sum);
        int sum = l.sum + r.sum + x.val;
        return new Info(sum, tilt);
    }
}
