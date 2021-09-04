package tree;

/**
 * 235. 二叉搜索树的最近公共祖先
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：
 * “对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，
 * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 */
public class Problem_0235_LowestCommonAncestorOfABinarySearchTree {

    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root==null) return null;
        if (p.val>root.val && q.val>root.val) {
            return lowestCommonAncestor1(root.right,p,q);
        } else if (p.val<root.val && q.val<root.val) {
            return lowestCommonAncestor1(root.left,p,q);
        }
        //p q位于root左右两侧，或者p q其中之一是root
        return root;
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root==null) return null;
        if ((root.val-p.val) * (root.val-q.val) > 0) { //同时大于root 或者 同时小于root
            return lowestCommonAncestor2(
                    root.val>p.val ? root.left : root.right,
                    p,
                    q
            );
        }
        return root; //p q位于root两侧
    }

    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        if (root==null) return null;
        TreeNode cur = root;
        while (cur!=null) {
            if (p.val>cur.val && q.val>cur.val) {
                cur = cur.right;
            } else if (p.val<cur.val && q.val<cur.val) {
                cur = cur.left;
            } else {
                return cur;
            }
        }
        return null;
    }


}
