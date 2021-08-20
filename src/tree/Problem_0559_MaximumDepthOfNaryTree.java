package tree;

import java.util.List;

//559. N 叉树的最大深度
public class Problem_0559_MaximumDepthOfNaryTree {
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

//    public int maxDepth(Node root) {
//
//    }
}
