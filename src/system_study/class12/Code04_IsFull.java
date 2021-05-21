package system_study.class12;

import tree.TreeNode;

//给定一棵二叉树的头节点head，返回这颗二叉树是不是满二叉树
public class Code04_IsFull {

    public static class Info {
        int h;
        int nodes;

        public Info(int h, int nodes) {
            this.h = h;
            this.nodes = nodes;
        }
    }

    public static Info process(TreeNode x) {
        if (x == null) return new Info(0, 0);
        Info l = process(x.left);
        Info r = process(x.right);
        int h = Math.max(l.h, r.h) + 1;
        int nodes = l.nodes + r.nodes + 1;
        return new Info(h, nodes);
    }

    //获取到整棵树的层数，判断节点数是否为 2^N - 1
    public static boolean isFull(TreeNode root) {
        if (root == null) return true;
        Info info = process(root);
        return (Math.pow(2, info.h) - 1) == info.nodes;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        System.out.println(isFull(root));
    }
}
