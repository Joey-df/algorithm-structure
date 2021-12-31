package tree;

import java.util.*;

/**
 * 173. 二叉搜索树迭代器
 * 实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器：
 * BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对象。BST 的根节点 root 会作为构造函数的一部分给出。
 * 指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。
 * boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false 。
 * int next()将指针向右移动，然后返回指针处的数字。
 * 注意，指针初始化为一个不存在于 BST 中的数字，所以对 next() 的首次调用将返回 BST 中的最小元素。
 * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 的中序遍历中至少存在一个下一个数字。
 */
public class Problem_0173_BinarySearchTreeIterator {

    //平凡解
    class BSTIterator {

        List<TreeNode> inorder;
        int curIndex = 0;
        //构造方法，直接生成中序遍历的序列
        public BSTIterator(TreeNode root) {
            inorder = new ArrayList<>();
            if (root!=null) {
                Stack<TreeNode> s = new Stack<>();
                TreeNode cur = root;
                while (cur!=null || !s.isEmpty()) {
                    if (cur!=null) {
                        s.push(cur);
                        cur = cur.left;
                    } else {
                        TreeNode node = s.pop();
                        inorder.add(node);
                        cur = node.right;
                    }
                }
            }
        }

        public int next() {
            if (hasNext()) {
                return inorder.get(curIndex++).val;
            }
            return -1; //没有了
        }

        public boolean hasNext() {
            return curIndex < inorder.size();
        }
    }



    //最优解
    class BSTIterator2 {
        private TreeNode cur;
        private Stack<TreeNode> stack;

        public BSTIterator2(TreeNode root) {
            cur = root;
            stack = new Stack<>();
        }

        public int next() {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            int ans = cur.val;
            cur = cur.right;
            return ans;
        }

        public boolean hasNext() {
            return cur != null || !stack.isEmpty();
        }
    }


}
