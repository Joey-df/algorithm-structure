package tree;


import java.util.ArrayList;

/**
 * 给定一棵二叉树的头节点head，
 * 返回这颗二叉树中，最大的二叉搜索子树的头节点
 * 注意:
 * 子树必须包含其所有后代。
 */
public class Problem_0333_LargestBSTSubTreeHead {

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

    public static TreeNode maxSubBSTHead1(TreeNode head) {
        if (head == null) {
            return null;
        }
        if (getBSTSize(head) != 0) {
            return head;
        }
        TreeNode leftAns = maxSubBSTHead1(head.left);
        TreeNode rightAns = maxSubBSTHead1(head.right);
        return getBSTSize(leftAns) >= getBSTSize(rightAns) ? leftAns : rightAns;
    }

    
    public static class Info{
        TreeNode maxBSTSubHead; // 整棵树上最大BST的头
        int maxBSTSubSize; // 整棵树上最大BST的大小(节点数)
        int max; // 整棵树最大值
        int min; // 整棵树最小值

        public Info(TreeNode maxBSTSubHead, int maxBSTSubSize, int max, int min) {
            this.maxBSTSubHead = maxBSTSubHead;
            this.maxBSTSubSize = maxBSTSubSize;
            this.max = max;
            this.min = min;
        }
    }

    public static TreeNode largestBSTSubTreeHead(TreeNode root) {
        if (root==null) return null;
        return fun(root).maxBSTSubHead;
    }

    public static Info fun(TreeNode x) {
        if (x==null) return null;
        Info lInfo = fun(x.left);
        Info rInfo = fun(x.right);

        int max = x.val;
        int min = x.val;
        TreeNode maxBSTSubHead = null;
        int maxBSTSubSize = 0;
        if (lInfo!=null) {
            max = Math.max(max, lInfo.max);
            min = Math.min(min, lInfo.min);
            maxBSTSubSize = lInfo.maxBSTSubSize;
            maxBSTSubHead = lInfo.maxBSTSubHead;
        }
        if (rInfo!=null) {
            max = Math.max(max, rInfo.max);
            min = Math.min(min, rInfo.min);
            if (rInfo.maxBSTSubSize > maxBSTSubSize) {
                maxBSTSubSize = rInfo.maxBSTSubSize;
                maxBSTSubHead = rInfo.maxBSTSubHead;
            }
        }
        boolean leftIsBST = lInfo==null || lInfo.maxBSTSubHead==x.left;
        boolean rightIsBST = rInfo==null || rInfo.maxBSTSubHead==x.right;
        
        //如果左子树整体是BST && 右子树整体是BST && 左max < x.val && 右min > x.val
        if (leftIsBST && rightIsBST) {
            boolean leftMaxLessX = lInfo==null || lInfo.max<x.val;
            boolean rightMimMoreX = rInfo==null || rInfo.min>x.val;
            if (leftMaxLessX && rightMimMoreX) {
                maxBSTSubHead = x;
                maxBSTSubSize = (lInfo==null ? 0 : lInfo.maxBSTSubSize) +
                        (rInfo==null ? 0 : rInfo.maxBSTSubSize) + 1;
            }
        }
        return new Info(maxBSTSubHead, maxBSTSubSize, max, min);
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
            if (maxSubBSTHead1(head) != largestBSTSubTreeHead(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
    
}
