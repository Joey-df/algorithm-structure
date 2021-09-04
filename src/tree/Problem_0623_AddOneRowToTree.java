package tree;

import java.util.LinkedList;
import java.util.Queue;

//623. 在二叉树中增加一行
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
