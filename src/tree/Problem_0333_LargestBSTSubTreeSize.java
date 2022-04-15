package tree;

import java.util.ArrayList;

/**
 * 333.最大BST子树大小
 * 给定一棵二叉树的头节点head，
 * 返回这颗二叉树中，最大的二叉搜索子树的大小
 * 注意:
 * 子树必须包含其所有后代。
 */
public class Problem_0333_LargestBSTSubTreeSize {

    public static int getBSTSize(TreeNode head) {
        if (head == null) {
            return 0;
        }
        ArrayList<TreeNode> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).val <= arr.get(i - 1).val) {
                return 0;
            }
        }
        return arr.size();
    }

    public static void in(TreeNode head, ArrayList<TreeNode> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    // 为了验证的方法
    public static int maxSubBSTSize1(TreeNode head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(maxSubBSTSize1(head.left), maxSubBSTSize1(head.right));
    }


    public static class Info {
        int maxBSTSubTreeSize;
        boolean isAllBST;
        int max;
        int min;

        public Info(int maxBSTSubTreeSize, boolean isAllBST, int max, int min) {
            this.maxBSTSubTreeSize = maxBSTSubTreeSize;
            this.isAllBST = isAllBST;
            this.max = max;
            this.min = min;
        }
    }

    //主函数
    public static int largestBSTSubTreeSize(TreeNode root) {
        if (root==null) return 0;
        return fun(root).maxBSTSubTreeSize;
    }

    // 二叉树递归套路
    public static Info fun(TreeNode x) {
        if (x==null) return null;
        Info lInfo = fun(x.left);
        Info rInfo = fun(x.right);

        int max = x.val;
        int min = x.val;
        int maxBSTSubTreeSize = 0;

        if(lInfo != null) {
            max = Math.max(max, lInfo.max);
            min = Math.min(min, lInfo.min);
            maxBSTSubTreeSize = lInfo.maxBSTSubTreeSize;
        }
        if(rInfo != null) {
            max = Math.max(max, rInfo.max);
            min = Math.min(min, rInfo.min);
            maxBSTSubTreeSize = Math.max(maxBSTSubTreeSize, rInfo.maxBSTSubTreeSize);
        }

        boolean isAllBST = false;
        boolean leftBST = lInfo==null || lInfo.isAllBST;
        boolean rightBST = rInfo==null || rInfo.isAllBST;
        if (leftBST && rightBST) {
            boolean leftMaxLessX = lInfo==null || lInfo.max < x.val;
            boolean rightMinMoreX = rInfo==null || rInfo.min > x.val;
            if (leftMaxLessX && rightMinMoreX) {
                maxBSTSubTreeSize = (lInfo==null ? 0 : lInfo.maxBSTSubTreeSize)
                                + (rInfo==null ? 0 : rInfo.maxBSTSubTreeSize)
                                + 1;
                isAllBST = true;
            }
        }
        return new Info(maxBSTSubTreeSize, isAllBST, max, min);
    }

    // for test
    public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static TreeNode generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        TreeNode head = new TreeNode((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = generateRandomBST(maxLevel, maxValue);
            if (maxSubBSTSize1(head) != largestBSTSubTreeSize(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
