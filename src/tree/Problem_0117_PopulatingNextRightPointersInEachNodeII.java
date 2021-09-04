package tree;

import java.util.LinkedList;
import java.util.Queue;

//117. Populating Next Right Pointers in Each Node II、
//代码与116一模一样
public class Problem_0117_PopulatingNextRightPointersInEachNodeII {
    // Definition for a Node.
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        if (root == null) return null;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i=0; i<size; i++) {
                Node node = q.poll();
                //i==size-1时表示当前层处理完了，当前层最后一个的next应该指向null
                node.next = i!=size-1 ? q.peek() : null;
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
        }
        return root;
    }
}
