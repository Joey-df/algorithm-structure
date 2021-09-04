package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    public List<Integer> postorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        if (root==null) return ans;
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            Node cur = stack1.pop();
            stack2.push(cur);
            int size = cur.children.size();
            for (int i = 0; i < size; i++) {
                stack1.push(cur.children.get(i));
            }
        }
        while (!stack2.isEmpty()) { //对应二叉树就是先左后右
            ans.add(stack2.pop().val);
        }
        return ans;
    }

}
