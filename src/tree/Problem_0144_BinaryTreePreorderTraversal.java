package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 144. Binary Tree Preorder Traversal
 * Given the root of a binary tree, return the preorder traversal of its nodes' values.
 */
public class Problem_0144_BinaryTreePreorderTraversal {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root==null) return ans;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            ans.add(cur.val);
            if (cur.right!=null) stack.push(cur.right);
            if (cur.left!=null) stack.push(cur.left);
        }
        return ans;
    }

    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root==null) return ans;
        dfs(root, ans);
        return ans;
    }

    public void dfs(TreeNode x, List<Integer> ans) {
        if (x==null) return;
        ans.add(x.val);
        dfs(x.left, ans);
        dfs(x.right, ans);
    }

}
