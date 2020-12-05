package train_camp_03.class05;


/**
 * 求完全二叉树节点的个数
 * <p>
 * 要求时间复杂度低于O(N)
 * https://leetcode.com/problems/count-complete-tree-nodes/
 */
public class Code03_CompleteTreeNodeNumber {

    private static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //如果node在第level层 (根节点level=1)
    //求 以node为头的完全二叉树的深度是多少 注意 深度不是高度
    public static int height(TreeNode node, int level) {
        while (node != null) {
            level++;
            node = node.left;
        }
        return level - 1;
    }

    //时间复杂度：O((logN)^2)
    public static int nodeNums(TreeNode root, int level) {
        if (root == null) {
            return 0;
        }
        int h = height(root, 1);
        System.out.println("h = " + h);
        return process(root, h, level);
    }

    //以root为根节点的完全二叉树的节点个数
    //h是整棵树的高，固定不变
    private static int process(TreeNode root, int h, int level) {
        if (level == h) { //叶子节点
            return 1;
        }
        if (height(root.right, level + 1) == h) { //右树可以到最后一层，左树是满的
            return (1 << (h - level)) + process(root.right, h, level + 1);
        } else { //右树是满的 height(root.right, level + 1) == h-1
            return (1 << (h - 1 - level)) + process(root.left, h, level + 1);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8);
        root.left.left.right = new TreeNode(9);
        System.out.println(nodeNums(root, 1));

    }
}
