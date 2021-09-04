package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//173. 二叉搜索树迭代器
public class Problem_0173_BinarySearchTreeIterator {

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

}
