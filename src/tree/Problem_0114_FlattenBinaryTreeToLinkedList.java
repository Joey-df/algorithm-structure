package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 114. 二叉树展开为链表
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 *
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 */
//大厂第10节Code04:二叉树转双向链表
public class Problem_0114_FlattenBinaryTreeToLinkedList {

    //平凡解
    public void flatten(TreeNode root) {
        List<TreeNode> pre = pre(root);
        if (pre.size()==0) return;
        root = pre.get(0);
        TreeNode cur = root;
        for (int i = 1; i < pre.size(); i++) {
            cur.right = pre.get(i);
            cur.left = null;
            cur = cur.right;
        }
    }

    public List<TreeNode> pre(TreeNode root) {
        List<TreeNode> ans = new ArrayList<>();
        if (root==null) return ans;
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        while (!s.isEmpty()) {
            TreeNode cur = s.pop();
            ans.add(cur);
            if (cur.right!=null) s.push(cur.right);
            if (cur.left!=null) s.push(cur.left);
        }
        return ans;
    }


    // 直接利用先序遍历 调整左右指针
    // 妙！
    public void flatten2(TreeNode root) {
        if (root==null) return;
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        while (!s.isEmpty()) {
            TreeNode cur = s.pop();
            if (cur.right!=null) s.push(cur.right);
            if (cur.left!=null) s.push(cur.left);
            cur.right = !s.isEmpty() ? s.peek() : null;
            cur.left = null; //题目其实也有这个要求的
        }
    }
}
