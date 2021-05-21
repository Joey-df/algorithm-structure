package system_study.class12;

import tree.TreeNode;

//给定一棵二叉树的头节点head，返回这颗二叉树是不是平衡二叉树
//leetcode 110
//https://leetcode.com/problems/balanced-binary-tree
public class Code03_IsBalanced {

    public static class Info {
        int h; //height
        boolean is;//是否平衡

        public Info(int h, boolean is) {
            this.h = h;
            this.is = is;
        }
    }

    public static Info process(TreeNode x) {
        if (x == null) return new Info(0, true);
        Info l = process(x.left);
        Info r = process(x.right);
        int h = Math.max(l.h, r.h) + 1;
        boolean is = l.is && r.is && Math.abs(l.h - r.h) < 2;
        return new Info(h, is);
    }


    public static boolean isBalance(TreeNode root) {
        if (root == null) return true;
        return process(root).is;
    }
}
