package tree;

import java.util.ArrayList;
import java.util.List;

//112的升级版
//返回所有路径
public class Problem_0113_PathSumII {

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root==null) return ans;
        List<Integer> path = new ArrayList<>();
        dfs(root, path, ans, targetSum);
        return ans;
    }

    //path：x节点之前 沿途形成的路径
    public void dfs(TreeNode x, List<Integer> path, List<List<Integer>> ans, int targetSum) {
        if (x==null) return;
        path.add(x.val); //第一次到达 add
        if (x.left==null && x.right==null) { //叶子节点收集答案
            int sum = 0;
            for (int value : path) {
                sum += value;
            }
            if (sum == targetSum) ans.add(new ArrayList<>(path));
        }
        dfs(x.left, path, ans, targetSum);
        dfs(x.right, path, ans, targetSum);
        path.remove(path.size()-1); //最后一次到达 remove 清理现场
    }

}
