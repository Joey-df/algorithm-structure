package backtrack;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 257. 二叉树的所有路径
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 输入:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 * 输出: ["1->2->5", "1->3"]
 *
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 */
public class Problem_0257_BinaryTreePaths {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        List<TreeNode> path = new ArrayList<>();
        dfs(root, ans, path);
        return ans;
    }

    //递归含义：
    //当前到达x节点，x之前的节点已经走过了，不用操心了
    //path 到达x节点形成的路径
    //ans 收集答案 遇到叶子结点时收集
    public void dfs(TreeNode x, List<String> ans, List<TreeNode> path) {
        if (x==null) {
            return;
        }
        path.add(x); //第一次到达add
        if (x.left==null && x.right==null) { //遇到叶子节点时收集答案
            ans.add(collect(path));
        }
        dfs(x.left, ans, path);
        dfs(x.right, ans, path);
        path.remove(path.size()-1); //第三次到达remove，清理现场
    }

    public String collect(List<TreeNode> path) {
        StringBuilder sb = new StringBuilder();
        int size = path.size();
        for (int i = 0; i < size; i++) {
            sb.append(path.get(i).val);
            if (i < size-1) sb.append("->");
        }
        return sb.toString();
    }
}
