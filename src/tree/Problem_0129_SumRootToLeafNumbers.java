package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 129. 求根节点到叶节点数字之和
 * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 *
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 *
 * 叶节点 是指没有子节点的节点。
 *
 * 示例 1：
 * 输入：root = [1,2,3]
 *     1
 *    / \
 *   2   3
 * 输出：25
 * 解释：
 * 从根到叶子节点路径 1->2 代表数字 12
 * 从根到叶子节点路径 1->3 代表数字 13
 * 因此，数字总和 = 12 + 13 = 25
 *
 * 示例 2：
 * 输入：root = [4,9,0,5,1]
 *     4
 *    / \
 *   9   0
 *  / \
 * 5   1
 * 输出：1026
 * 解释：
 * 从根到叶子节点路径 4->9->5 代表数字 495
 * 从根到叶子节点路径 4->9->1 代表数字 491
 * 从根到叶子节点路径 4->0 代表数字 40
 * 因此，数字总和 = 495 + 491 + 40 = 1026
 */
public class Problem_0129_SumRootToLeafNumbers {

    //计算root为根的二叉树的路径和
    //path 沿途形成的路径
    private static void dfs(TreeNode root, List<Integer> path, List<Integer> ans) {
        if (root == null) return;
        //第一次到达 add
        path.add(root.val);
        //遇到叶子节点就收集答案
        if (root.left == null && root.right == null) {
            int pathSum = 0;
            for (int num : path) { //4,8,9 ==> 489
                pathSum = pathSum * 10 + num;
            }
            ans.add(pathSum);
        }
        dfs(root.left, path, ans);
        dfs(root.right, path, ans);
        //最后一次到达 remove 清理现场
        path.remove(path.size() - 1);
    }

    public static int sumNumbers(TreeNode root) {
        if (root==null) return 0;
        List<Integer> path = new ArrayList<>();
        List<Integer> pathSums = new ArrayList<>();
        dfs(root, path, pathSums);
        int ans = 0;
        for (int sum: pathSums) ans += sum;
        return ans;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(9);
        root.right = new TreeNode(0);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(1);
        System.out.println(sumNumbers(root));
    }
}
