package tree;

//剑指offer---二叉树的下一个节点

/**
 * 题目描述：给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结 点并且返回。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 * 思路：
 * 若节点右孩子存在，则设置一个指针从该节点的右孩子出发，一直沿着指 向左子结点的指针找到的叶子节点即为下一个节点；
 * 若节点不是根节点。如果该节 点是其父节点的左孩子，则返回父节点；
 * 否则继续向上遍历其父节点的父节点，重 复之前的判断，返回结果。
 */
public class NextNodeInBST {

    //二叉树
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode parent;//指向上一个节点（父节点）
        public TreeNode(int x) {
            val = x;
        }
    }


    //首先中序遍历的一个结点的下一个节点不可能在左子树上。
    // 1.若有右子树则在右子树最左的结点；
    // 2.若没有右子树，下一个节点是父节点且必须满足父节点是爷爷结点的左孩子，
    // 若有父节点，但不满足父节点是爷爷结点的左孩子，则向上走（pNode=pNode.parent），判断爷爷节点是否满足条件，直到满足条件或者没有父节点了，没有父结点返回null
    // 任意一个节点pNode
    public static TreeNode getNext(TreeNode pNode) {
        if (pNode == null)
            return null;
        //如果有右子树，则找右子树的最左节点
        if (pNode.right != null) {
            pNode = pNode.right;
            //下一个结点为右子树的最左子树
            while (pNode.left != null) {
                pNode = pNode.left;
            }
            return pNode;
        }
        //没右子树，则找第一个当前节点是父节点左孩子的节点
        while (pNode.parent != null) {
            if (pNode.parent.left == pNode) {
                return pNode.parent;
            }
        }
        return null; //退到了根节点仍没找到，则返回null
    }
    public static void main(String[] args) {
        TreeNode p1=new TreeNode(1);
        TreeNode p2=new TreeNode(2);
        TreeNode p3=new TreeNode(3);
        TreeNode p4=new TreeNode(4);
        TreeNode p5=new TreeNode(5);
        TreeNode p6=new TreeNode(6);
        p1.left=p2; p2.parent = p1;
        p1.right=p3; p3.parent = p1;
        p2.left=p4; p4.parent = p2;
        p2.right=p5; p5.parent = p2;
        p3.right=p6; p6.parent = p3;
        TreeNode m=getNext(p3);
        System.out.println(m.val);
    }

    //     1
    //    / \
    //   2   3
    //  / \   \
    // 4  5     6
}
