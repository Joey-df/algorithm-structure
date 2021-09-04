package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 515. 在每个树行中找最大值
 * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
 */
public class Problem_0515_FindLargestValueInEachTreeRow {

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root==null) return ans;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            int curMax = q.peek().val;
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                curMax = Math.max(curMax, cur.val);
                if (cur.left!=null) q.offer(cur.left);
                if (cur.right!=null) q.offer(cur.right);
            }
            ans.add(curMax);
        }
        return ans;
    }

}
