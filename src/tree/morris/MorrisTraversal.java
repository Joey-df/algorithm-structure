package tree.morris;

import tree.TreeNode;

/**
 * 流程
 * cur一开始来到整棵树的头
 * cur无左树，cur=cur.right
 * cur有左树，找到左树的最右节点mostRight
 * 1）mostRight.right==null(mostRight无右树), mostRight.right=cur, cur=cur.left;
 * 2）mostRight.right==cur，mostRight=null，cur=cur.right。
 * cur来到null，停
 */
public class MorrisTraversal {

    public static void morris(TreeNode root) {
        if (root == null) return;
        TreeNode cur = root;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right; //找到左树最右节点
                }
                //while出来时：mostRight.right==null 或者 mostRight.right==cur
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { //mostRight.right==cur
                    //此处是第二次到达节点
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
    }


    public static void morrisPre(TreeNode root) {
        if (root == null) return;
        TreeNode cur = root;
        TreeNode mostRight;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right; //找到左树最右节点
                }
                //while出来时：mostRight.right==null 或者 mostRight.right==cur
                if (mostRight.right == null) {
                    System.out.print(cur.val + " ");
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { //mostRight.right==cur
                    //此处是第二次到达节点
                    mostRight.right = null;
                }
            } else {
                System.out.print(cur.val + " ");
            }
            cur = cur.right;
        }
    }

    public static void morrisIn(TreeNode root) {
        if (root == null) return;
        TreeNode cur = root;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right; //找到左树最右节点
                }
                //while出来时：mostRight.right==null 或者 mostRight.right==cur
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { //mostRight.right==cur
                    //此处是第二次到达节点
                    mostRight.right = null;
                }
            }
            System.out.print(cur.val+" ");
            cur = cur.right;
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
        morrisPre(root);
        System.out.println();
        morrisIn(root);
    }
}
