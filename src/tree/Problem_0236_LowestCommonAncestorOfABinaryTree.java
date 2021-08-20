package tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 给定一棵二叉树的头节点head，和另外两个节点a和b。
 * 返回a和b的最低公共祖先
 */
public class Problem_0236_LowestCommonAncestorOfABinaryTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        HashMap<TreeNode, TreeNode> map = new HashMap<>(); //key的父亲节点是value
        map.put(root, null);
        fillParentMap(root, map);

        Set<TreeNode> set = new HashSet<>();
        TreeNode cur = p;
        set.add(cur);
        while (map.get(cur) != null) { //cur来到根节点 停
            cur = map.get(cur);//来到父节点
            set.add(cur);
        }
        cur = q;
        while (!set.contains(cur)) {
            cur = map.get(cur);
        }
        return cur;
    }


    private void fillParentMap(TreeNode root, HashMap<TreeNode, TreeNode> map) {
        if (root.left != null) {
            map.put(root.left, root);
            fillParentMap(root.left, map);
        }
        if (root.right != null) {
            map.put(root.right, root);
            fillParentMap(root.right, map);
        }
    }
}
