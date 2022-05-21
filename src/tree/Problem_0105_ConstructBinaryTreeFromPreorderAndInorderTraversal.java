package tree;

import java.util.HashMap;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class Problem_0105_ConstructBinaryTreeFromPreorderAndInorderTraversal {

    // 先序：根 左 右
    // 中序：左 根 右
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        //<in_val，对应数组中的index>
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i=0;i<inorder.length;i++) {
            map.put(inorder[i], i);
        }
        return build(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1, map);
    }


    //递归含义
    //使用pre[l1,r1] in[l2,r2]建树，返回头节点
    //map：inorder数组中，每个值对应的位置 组成的map
    private static TreeNode build(int[] pre, int l1, int r1, int[] in, int l2, int r2, HashMap<Integer,Integer> map) {
        if (l1>r1 && l2>r2) return null; //注意是&&的关系
        TreeNode root = new TreeNode(pre[l1]);
        int rootPos = map.get(pre[l1]);
        int leftLen = rootPos-l2; //左子树节点数量
        root.left = build(pre, l1+1, l1+leftLen, in, l2, rootPos-1, map);
        root.right = build(pre, l1+leftLen+1, r1, in, rootPos+1, r2, map);
        return root;
    }
}
