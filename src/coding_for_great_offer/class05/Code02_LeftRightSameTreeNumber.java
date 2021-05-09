package coding_for_great_offer.class05;

/**
 * 如果一个节点X，它左树结构和右树结构完全一样
 * 那么我们说以X为头的子树是相等子树
 * 给定一棵二叉树的头节点head
 * 返回head整棵树上有多少棵相等子树
 */
public class Code02_LeftRightSameTreeNumber {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    //随机生成restLevel层，value在[0,maxValue)的二叉树
    public static Node randomBinaryTree(int restLevel, int maxValue) {
        if (restLevel == 0) {
            return null;
        }
        Node head = Math.random() < 0.2 ? null : new Node((int) (Math.random() * maxValue));
        if (head != null) {
            head.left = randomBinaryTree(restLevel - 1, maxValue);
            head.right = randomBinaryTree(restLevel - 1, maxValue);
        }
        return head;
    }
}

