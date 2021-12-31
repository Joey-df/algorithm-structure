package tree;

/**
 * 450. 删除二叉搜索树中的节点
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。
 * 返回二叉搜索树（有可能被更新）的根节点的引用。
 */
public class Problem_0450_DeleteNodeInABST {

    //在BST中删除值为V的节点，返回删除后搜索二叉树的根节点
    public TreeNode deleteNode(TreeNode root, int V) {
        if (root==null) return null;
        if (V > root.val) {
            //在右子树删
            root.right = deleteNode(root.right, V);
        } else if (V < root.val) {
            //在左子树删
            root.left = deleteNode(root.left, V);
        } else { // V == root.val
            if (root.left==null) {
                return root.right; //无左树，root又是要删的节点，所以就只剩下右树了
            } else if (root.right==null) {
                return root.left; //无右树，root又是要删的节点，所以就只剩下左树了
            } else {
                //左右都不空
                //找到待删节点的后继节点
                TreeNode successor = findLeftMost(root.right);
                root.val = successor.val; //被删节点的值改为后继节点的值
                // 然后在右树上删除后继节点，结果仍然是BST
                root.right = deleteNode(root.right,successor.val);
            }
        }
        return root;
    }

    public TreeNode findLeftMost(TreeNode node) {
        if (node==null) return null;
        while (node.left!=null) {
            node = node.left;
        }
        return node;
    }

}
