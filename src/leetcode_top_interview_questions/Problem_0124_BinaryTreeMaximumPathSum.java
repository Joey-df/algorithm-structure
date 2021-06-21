package leetcode_top_interview_questions;


import tree.TreeNode;

/**
 * 124. 二叉树中的最大路径和
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。
 * 同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 *
 * 路径和 是路径中各节点值的总和。
 *
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 */
public class Problem_0124_BinaryTreeMaximumPathSum {


    private static class Info {
        int allMaxPathSum; //整棵树上的最大路径和
        int maxPathSumFromHead; //必须从根节点出发往下扎的最大路径和

        public Info(int a, int b) {
            allMaxPathSum = a;
            maxPathSumFromHead = b;
        }
    }

    public static int maxPathSum(TreeNode root) {
        if (root==null) return 0;
        return func(root).allMaxPathSum;
    }

    //递归含义：
    //返回以x为头的二叉树的Info
    public static Info func(TreeNode x) {
        if(x==null) {
            return null;
        }
        Info l = func(x.left);
        Info r = func(x.right);
        int p1=Integer.MIN_VALUE;
        if (l!=null)
            p1 = l.allMaxPathSum;
        int p2 = Integer.MIN_VALUE;
        if (r!=null)
            p2=r.allMaxPathSum;
        int p3 = x.val;
        int p4=Integer.MIN_VALUE;
        if (l!=null)
            p4 = x.val + l.maxPathSumFromHead;
        int p5=Integer.MIN_VALUE;
        if (r!=null)
            p5 = x.val + r.maxPathSumFromHead;
        int p6=Integer.MIN_VALUE;
        if (l!=null && r!=null)
            p6 = x.val + l.maxPathSumFromHead + r.maxPathSumFromHead;
        int allMaxPathSum = Math.max(p1,Math.max(p2,Math.max(p3,Math.max(p4,Math.max(p5,p6)))));
        int maxPathSumFromHead = Math.max(p3,Math.max(p4,p5));
        return new Info(allMaxPathSum, maxPathSumFromHead);
    }
}
