package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    //迭代方式
    public List<Integer> preorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        if (root==null) return ans;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            ans.add(cur.val);
            int size = cur.children.size();
            for (int i=size-1; i>=0; i--) { //对应二叉树就是先右后左
                stack.push(cur.children.get(i));
            }
        }
        return ans;
    }
}
