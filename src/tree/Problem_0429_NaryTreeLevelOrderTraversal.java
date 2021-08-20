package tree;

import java.util.List;

//429. N 叉树的层序遍历
public class Problem_0429_NaryTreeLevelOrderTraversal {
    // Definition for a Node.
    static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

//    public List<List<Integer>> levelOrder(Node root) {
//
//    }
}
