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
        if (a == null ^ b == null) return false;//一个为空 一个非空
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

    // 时间复杂度O(N)
    public static int sameNumber2(TreeNode head) {
        String algorithm = "SHA-256";
        Hash hash = new Hash(algorithm);
        return process(head, hash).ans;
    }

    public static class Info {
        public int ans;
        public String str;

        public Info(int a, String s) {
            ans = a;
            str = s;
        }
    }

    public static Info process(TreeNode head, Hash hash) {
        if (head == null) {
            return new Info(0, hash.hashCode("#,"));
        }
        Info l = process(head.left, hash);
        Info r = process(head.right, hash);
        int ans = (l.str.equals(r.str) ? 1 : 0) + l.ans + r.ans;
        String str = hash.hashCode(String.valueOf(head.val) + "," + l.str + r.str);
        return new Info(ans, str);
    }

    public static void main(String[] args) {
        int maxLevel = 8;
        int maxValue = 4;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            TreeNode head = randomBinaryTree(maxLevel, maxValue);
            int ans1 = sameTreeNum(head);
            int ans2 = sameNumber2(head);
            if (ans1 != ans2) {
                System.out.println("出错了！");
                System.out.println(ans1);
                System.out.println(ans2);
            }
        }
        System.out.println("测试结束");

    }

}

