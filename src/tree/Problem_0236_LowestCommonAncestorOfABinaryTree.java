package tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 236. 二叉树的最近公共祖先
 * 给定一棵二叉树的头节点head，和另外两个节点a和b。
 * 返回a和b的最低公共祖先
 */
public class Problem_0236_LowestCommonAncestorOfABinaryTree {

    //基于hashmap的平凡解
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        HashMap<TreeNode, TreeNode> map = new HashMap<>(); //key的父亲节点是value
        map.put(root, null); //表示root没有parent
        fillParentMap(root, map);

        Set<TreeNode> set = new HashSet<>();
        set.add(p);
        TreeNode cur = p; //从p开始往上走到root，沿途节点放入set
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


    //递归求每个节点的父节点
    private void fillParentMap(TreeNode root, HashMap<TreeNode, TreeNode> map) {
        if (root == null) return;
        if (root.left != null) {
            map.put(root.left, root);
            fillParentMap(root.left, map);
        }
        if (root.right != null) {
            map.put(root.right, root);
            fillParentMap(root.right, map);
        }
    }

    public static class Info {
        boolean findA;
        boolean findB;
        TreeNode ans; //a b 的交汇点

        public Info(boolean findA, boolean findB, TreeNode ans) {
            this.findA = findA;
            this.findB = findB;
            this.ans = ans;
        }
    }

    //二叉树递归套路
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        return fun(root, p, q).ans;
    }

    public Info fun(TreeNode x, TreeNode a, TreeNode b) {
        if (x == null) {
            return new Info(false, false, null);
        }
        Info l = fun(x.left, a, b);
        Info r = fun(x.right, a, b);
        boolean findA = x == a || l.findA || r.findA;
        boolean findB = x == b || l.findB || r.findB;
        TreeNode ans = null;
        if (l.ans != null) {
            ans = l.ans;
        } else if (r.ans != null) {
            ans = r.ans;
        } else {
            if (findA && findB) {
                ans = x;
            }
        }
        return new Info(findA, findB, ans);
    }

}