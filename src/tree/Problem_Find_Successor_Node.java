package tree;

/**
 * 二叉树结构如下定义：
 * Class Node {
 * 	V value;
 * 	Node left;
 * 	Node right;
 * 	Node parent;
 * }
 * 给你二叉树中的某个节点，返回该节点的后继节点
 */
public class Problem_Find_Successor_Node {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data) {
            this.value = data;
        }
    }

}
