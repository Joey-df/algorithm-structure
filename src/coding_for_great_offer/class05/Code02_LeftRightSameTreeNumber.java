package coding_for_great_offer.class05;

import tree.TreeNode;

/**
 * 如果一个节点X，它左树结构和右树结构完全一样
 * 那么我们说以X为头的子树是相等子树
 * 给定一棵二叉树的头节点head
 * 返回head整棵树上有多少棵相等子树
 */
public class Code02_LeftRightSameTreeNumber {

    //随机生成restLevel层，value在[0,maxValue)的二叉树
    public static TreeNode randomBinaryTree(int restLevel, int maxValue) {
        if (restLevel == 0) {
            return null;
        }
        TreeNode head = Math.random() < 0.2 ? null : new TreeNode((int) (Math.random() * maxValue));
        if (head != null) {
            head.left = randomBinaryTree(restLevel - 1, maxValue);
            head.right = randomBinaryTree(restLevel - 1, maxValue);
        }
        return head;
    }

    //判断a、b为头的两棵树结构是否相等
    public static boolean isSameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null ^ b == null) return false;//有一个为空
        //两个都不为空
        if (a.val != b.val) return false;
        //a.val == b.val
        return isSameTree(a.left, b.left) && isSameTree(a.right, b.right);
    }

    public static int sameTreeNum(TreeNode head) {
        if (head == null) return 0;
        int l = sameTreeNum(head.left); //左树上相等子树数量
        int r = sameTreeNum(head.right); //右树上相等子树数量
        return l + r + (isSameTree(head.left, head.right) ? 1 : 0);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(3);
        System.out.println(sameTreeNum(root));
    }


}

