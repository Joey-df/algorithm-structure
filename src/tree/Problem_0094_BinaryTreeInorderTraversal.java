package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//94. 二叉树的中序遍历
public class Problem_0094_BinaryTreeInorderTraversal {

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode node = stack.pop();
                ans.add(node.val);
                cur = node.right;
            }
        }
        return ans;
    }
}
