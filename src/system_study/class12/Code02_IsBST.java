package system_study.class12;

import tree.TreeNode;

//判断二叉树是否是搜索二叉树
//leetcode 98
//https://leetcode.com/problems/validate-binary-search-tree
//方法一：二叉树递归套路
//方法二：中序遍历，看是否严格递增
public class Code02_IsBST {

    public static class Info {
        int min;
        int max;
        boolean is;//是否整体是BST

        public Info(int min, int max, boolean is) {
            this.min = min;
            this.max = max;
            this.is = is;
        }
    }

    //递归含义：
    //返回以x为头的整棵树的信息
    public static Info process(TreeNode x) {
        if (x == null) return null;
        Info l = process(x.left);
        Info r = process(x.right);
        int min = x.val;
        int max = x.val;
        boolean is = false;
        if (l != null) {
            min = Math.min(min, l.min);
            max = Math.max(max, l.max);
        }
        if (r != null) {
            min = Math.min(min, r.min);
            max = Math.max(max, r.max);
        }
        if ((l == null ? true : (l.is && l.max < x.val))
                &&
                ((r == null) ? true : (r.is && r.min > x.val))) {
            is = true;
        }
        return new Info(min, max, is);
    }


    public static boolean validate(TreeNode root) {
        if (root == null) return true;
        return process(root).is;
    }
}
