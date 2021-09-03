package system_study.class11;

/**
 * 二叉树结构如下定义：
 * Class Node {
 * V value;
 * Node left;
 * Node right;
 * Node parent;
 * }
 * 给你二叉树中的某个节点，返回该节点的后继节点 / 前驱节点
 */
//二叉树查找前驱节点与后继节点

/***
 * 前驱节点：对一棵二叉树进行中序遍历，遍历后的顺序，当前节点的前一个节点为该节点的前驱节点；
 * 后继节点：对一棵二叉树进行中序遍历，遍历后的顺序，当前节点的后一个节点为该节点的后继节点；
 * 例如一颗完全二叉树（1,2,3,4,5,6,7），按照中序遍历后的顺序为:（4,2,5,1,6,3,7），1节点的前驱节点为：5，后继节点为6.
 *
 * 若每次遍历二叉树进行查找前驱节点或后继节点，复杂度太高，需要O(n)的时间复杂度。
 * 例如查找5节点的后继节点，则需要对整棵树进行遍历，而真正可以做到的是，只需要经过两者之间的距离就可以找到其后继节点：521。
 *
 * 因此可以断定：
 * 前驱节点：若有左子树，当前节点左子树的最右节点（例如1节点的前驱节点为5）；若无左子树，则：当前节点是其父节点的右子树（5节点的前驱节点为2）；
 * 后继节点：若有右子树，当前节点右子树的最左节点（1节点的后继节点为6），若无右子树，则：当前节点为其父节点的左子树（6节点的后继节点为3）。
 */
public class Code06_SuccessorNode {

    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data) {
            this.value = data;
        }
    }

    //返回node的后继节点
    public static Node getSuccessorNode(Node node) {
        if (node == null) {
            return node;
        }
        if (node.right != null) {
            return getLeftMost(node.right);
        } else { // 无右子树
            Node parent = node.parent;
            while (parent != null && parent.right == node) { // 当前节点是其父亲节点的左孩子 或者 parent走到null 跳出
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    public static Node getLeftMost(Node node) {
        if (node == null) {
            return node;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    //返回node的前驱节点
    public static Node getSuccessPreNode(Node node) {
        if (node == null) {
            return node;
        }
        if (node.left != null) {
            return getRightMost(node.left);
        } else { //无左子树
            Node parent = node.parent;
            while (parent != null && parent.left == node) { //node是parent的右孩子 或者 parent走到null 跳出
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    private static Node getRightMost(Node node) {
        if (node == null) {
            return node;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        System.out.println("=====================测试后继节点===========================");
        Node test = head.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + getSuccessorNode(test));

        System.out.println("=====================测试前驱节点===========================");
        test = head.left.left; // 1's pre is null
        System.out.println(test.value + " pre: " + getSuccessPreNode(test));
        test = head.left.left.right;
        System.out.println(test.value + " pre: " + getSuccessPreNode(test).value);
        test = head.left;
        System.out.println(test.value + " pre: " + getSuccessPreNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " pre: " + getSuccessPreNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " pre: " + getSuccessPreNode(test).value);
        test = head;
        System.out.println(test.value + " pre: " + getSuccessPreNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " pre: " + getSuccessPreNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " pre: " + getSuccessPreNode(test).value);
        test = head.right;
        System.out.println(test.value + " pre: " + getSuccessPreNode(test).value);
        test = head.right.right;
        System.out.println(test.value + " pre: " + getSuccessPreNode(test).value);
    }

}