package tree;

import java.util.List;

//590. N-ary Tree Postorder Traversal
public class Problem_0590_NaryTreePostorderTraversal {

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

//    public List<Integer> postorder(Node root) {
//
//    }
}
