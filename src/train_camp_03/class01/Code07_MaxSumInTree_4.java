package train_camp_03.class01;

/**
 * 给定一个二叉树的头节点head：
 * <p>
 * 4）规定路径为从任何节点出发，到叶节点止，求最大路径和。
 */
public class Code07_MaxSumInTree_4 {

    private static int maxPathSum = Integer.MIN_VALUE;

    //分析，从每一个节点x出发到达叶子节点算路径和，求整体max
    public static int process(TreeNode x) {
        if (x.left == null && x.right == null) { // 表示从叶子节点开始到叶子节点
            maxPathSum = Math.max(maxPathSum, x.val);
            return x.val;
        }
        //x不是叶子
        int next = Integer.MIN_VALUE;
        //第一次到x
        if (x.left != null) {
            next = process(x.left);
        }
        //第二次到x
        if (x.right != null) {
            next = Math.max(next, process(x.right));
        }
        //第三次到x
        int ans = x.val + next; //从x出发到叶子节点的最大累加和
        maxPathSum = Math.max(maxPathSum, ans);
        return ans;
    }

}
