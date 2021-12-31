package tree;

import java.util.LinkedList;

/**
 * 662. 二叉树最大宽度
 * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。
 * 这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
 * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
 * 示例 1:
 * 输入:
 *            1
 *          /   \
 *         3     2
 *        / \     \
 *       5   3     9
 * 输出: 4
 * 解释: 最大值出现在树的第 3 层，宽度为 4 (5,3,null,9)。
 *
 * 示例 2:
 * 输入:
 *           1
 *          / \
 *         3   2
 *        /     \
 *       5       9
 *      /         \
 *     6           7
 * 输出: 8
 * 解释: 最大值出现在树的第 4 层，宽度为 8 (6,null,null,null,null,null,null,7)。
 */
// change the val of node to be the index to save space. The value is useless. All we need is just the index.
public class Problem_0662_MaximumWidthOfBinaryTree {

    //bfs宽度优先遍历
    //补充知识：
    //假设父节点在第 n 层的索引为 i，
    //（1）当索引从0开始时，该父节点在第 n + 1 层的左孩子节点的索引为 2* i + 1，右孩子节点的索引为 2*i + 2；
    //（2）当索引从1开始时，该父节点在第 n + 1 层的左孩子节点的索引为 2 *i，右孩子节点的索引为 2 * i + 1。
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> q = new LinkedList<>();
        q.offerLast(root);
        root.val = 1; //人为规定根节点index=1，左右子节点的索引分别为index*2，index*2+1
        int ans = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            //拿到每一层的宽度
            ans = Math.max(ans, q.peekLast().val - q.peekFirst().val + 1);
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.pollFirst();
                if (cur.left != null) {
                    cur.left.val = cur.val << 1;
                    q.offerLast(cur.left);
                }
                if (cur.right != null) {
                    cur.right.val = cur.val << 1 | 1;
                    q.offerLast(cur.right);
                }
            }
        }
        return ans;
    }

}
