package tree;

import java.util.HashMap;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class Problem_0106_ConstructBinaryTreeFromInorderAndPostorderTraversal {

    // 中序：左 根 右
    // 后序：左 右 根
    public TreeNode buildTree(int[] in, int[] post) {
        if (in==null || post==null || in.length!=post.length) return null;
        int n = in.length;
        //<in_val, pos>
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(in[i], i);
        }
        return build(in, 0, n-1, post, 0, n-1, map);
    }

    public TreeNode build(int[] in, int l1, int r1, int[] post, int l2, int r2, HashMap<Integer,Integer> map) {
        if (l1>r2 && l2>r2) return null;
        int rootVal = post[r2];
        TreeNode root = new TreeNode(rootVal);
        int rootPos = map.get(rootVal);
        int len = rootPos-l1; //左子树节点数量
        root.left = build(in, l1, rootPos-1, post, l2,l2+len-1, map);
        root.right = build(in, rootPos+1, r1, post, l2+len, r2-1, map);
        return root;
    }

}
