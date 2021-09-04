package tree;

/**
 * 572. 另一棵树的子树
 * 给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。
 * 如果存在，返回 true ；否则，返回 false 。
 *
 * 二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。
 */
public class Problem_0572_SubtreeOfAnotherTree {

    //三种情况
    //1、两者完全一样
    //2、不一样 && 是左子树的子树
    //3、不一样 && 是右子树的子树
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        return isSame(root, subRoot) ||
                (root!=null && isSubtree(root.left, subRoot)) ||
                (root!=null && isSubtree(root.right, subRoot));
    }

    public boolean isSame(TreeNode a, TreeNode b) {
        if (a==null && b==null) return true;
        if (a==null ^ b==null) return false;
        return a.val==b.val &&
                isSame(a.left, b.left) &&
                isSame(a.right, b.right);
    }
}
