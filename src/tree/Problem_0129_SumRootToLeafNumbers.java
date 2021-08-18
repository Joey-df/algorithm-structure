package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 *
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 *
 * Find the total sum of all root-to-leaf numbers.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Input: [1,2,3]
 *     1
 *    / \
 *   2   3
 * Output: 25
 * Explanation:
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Therefore, sum = 12 + 13 = 25.
 * Example 2:
 *
 * Input: [4,9,0,5,1]
 *     4
 *    / \
 *   9   0
 *  / \
 * 5   1
 * Output: 1026
 * Explanation:
 * The root-to-leaf path 4->9->5 represents the number 495.
 * The root-to-leaf path 4->9->1 represents the number 491.
 * The root-to-leaf path 4->0 represents the number 40.
 * Therefore, sum = 495 + 491 + 40 = 1026.
 */
public class Problem_0129_SumRootToLeafNumbers {

    private static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    private static int sumVal = 0;
    private static List<Integer> list = new ArrayList<>();

    //计算root为根的二叉树的路径和
    private static void dfs(TreeNode root) {
        if (root == null) return;
        //第一次到root就添加
        list.add(root.val);
        //遇到叶子节点就搜集答案
        if (root.left == null && root.right == null) {
            int n = 0;
            for (int i = 0; i < list.size(); i++) { //4,8,9 ==> 489
                n = n * 10 + list.get(i);
            }
            sumVal += n;
        }
        dfs(root.left);
        dfs(root.right);
        //最后一次到达root就删除
        list.remove(list.size() - 1);
    }

    public static int sumNumbers(TreeNode root) {
        dfs(root);
        return sumVal;
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
