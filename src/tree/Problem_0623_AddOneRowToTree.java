package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 623. 在二叉树中增加一行
 * 给定一个二叉树，根节点为第1层，深度为 1。在其第 d 层追加一行值为 v 的节点。
 * 添加规则：给定一个深度值 d （正整数），针对深度为 d-1 层的每一非空节点 N，为 N 创建两个值为 v 的左子树和右子树。
 * 将 N 原先的左子树，连接为新节点 v 的左子树；将 N 原先的右子树，连接为新节点 v 的右子树。
 * 如果 d 的值为 1，深度 d - 1 不存在，则创建一个新的根节点 v，原先的整棵树将作为 v 的左子树。
 * 示例 1:
 * 输入:
 * 二叉树如下所示:
 *        4
 *      /   \
 *     2     6
 *    / \   /
 *   3   1 5
 * v = 1
 * d = 2
 * 输出:
 *        4
 *       / \
 *      1   1
 *     /     \
 *    2       6
 *   / \     /
 *  3   1   5
 * 示例 2:
 * 输入:
 * 二叉树如下所示:
 *       4
 *      /
 *     2
 *    / \
 *   3   1
 * v = 1
 * d = 3
 * 输出:
 *       4
 *      /
 *     2
 *    / \
 *   1   1
 *  /     \
 * 3       1
 */
//好题
public class Problem_0623_AddOneRowToTree {

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode newRoot = new TreeNode(val);
            newRoot.left = root;
            return newRoot;
        }
        //depth > 1
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        for (int i = 0; i < depth-2; i++) {
            int size = q.size();
            for (int j = 0; j < size; j++) {
                TreeNode cur = q.poll();
                if (cur.left!=null) q.offer(cur.left);
                if (cur.right!=null) q.offer(cur.right);
            }
        }
        //到此为止，q中保存的是第 depth-1 层 的 所有节点
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            TreeNode l = cur.left;
            TreeNode r = cur.right;
            TreeNode newL = new TreeNode(val);
            cur.left = newL;
            newL.left = l;
            TreeNode newR = new TreeNode(val);
            cur.right = newR;
            newR.right = r;
        }
        return root;
    }

}
