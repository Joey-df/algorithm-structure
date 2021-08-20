package tree;

import java.util.List;

//589. N-ary Tree Preorder Traversal
public class Problem_0589_NaryTreePreorderTraversal {

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

//    public void iterativeTraversal(Node root) {
//
//    }
}
