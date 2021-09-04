package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//429. N 叉树的层序遍历
//和leetcode102一样
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

    //与二叉树层序遍历过程一样
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root==null) return ans;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> sub = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node cur = q.poll();
                sub.add(cur.val);
                for (Node node: cur.children) {
                    q.offer(node);
                }
            }
            ans.add(sub);
        }
        return ans;
    }
}
