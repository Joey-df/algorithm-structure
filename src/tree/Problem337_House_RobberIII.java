package tree;

/**
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.
 *
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 *
 * Example 1:
 *
 * Input: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * Output: 7
 * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * Example 2:
 *
 * Input: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * Output: 9
 * Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 */
public class Problem337_House_RobberIII {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){}
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static class Info {
        private int allTreeMax;//整棵树的最大值
        private int maxWithoutRoot; //不带root的最大值

        public Info(int a, int b) {
            allTreeMax = a;
            maxWithoutRoot = b;
        }
    }

    //二叉树递归套路
    public static Info process(TreeNode x) {
        if (x==null) return new Info(0,0);
        //x != null
        Info lInfo = process(x.left);
        Info rInfo = process(x.right);
        //不要x时
        int maxWithoutRoot = lInfo.allTreeMax + rInfo.allTreeMax;
        //要x时
        int withX = x.val + lInfo.maxWithoutRoot + rInfo.maxWithoutRoot;
        int allTreeMax = Math.max(maxWithoutRoot, withX);
        return new Info(allTreeMax, maxWithoutRoot);
    }

    public static int rob(TreeNode root) {
        if (root==null) return 0;
        return process(root).allTreeMax;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        System.out.println("rob(root) = " + rob(root));
    }
}
