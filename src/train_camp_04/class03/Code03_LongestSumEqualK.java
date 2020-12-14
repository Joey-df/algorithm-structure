package train_camp_04.class03;

import tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一棵二叉树的头节点head，和一个数K
 * 路径的定义:
 * 可以从任何一个点开始，但是只能往下走，往下可以走到任何节点停止
 * 返回路径累加和为K的所有路径中，最长的路径最多有几个节点？
 */
public class Code03_LongestSumEqualK {

    //算法原型：
    //给定数组arr，值可正可负可0，求累加和为K的子数组的最大长度
    //把从头节点到任何一个叶子节点的路径看作一个数组arr

    public static int max = 0;

    public static int process(TreeNode root, int K) {
        if (root == null) {
            return 0;
        }
        max = 0;
        //root!=null
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);//累加和为0出现在-1层
        //第一层对应数组的0位置
        dfs(root, 0, 0, K, map);
        return max;
    }

    //dfs
    //递归含义：
    //之前已经形成了preSum的累加和
    //当前节点X位于第level层
    //K: 固定参数
    public static void dfs(TreeNode X, int preSum, int level, int K, Map<Integer, Integer> map) {
        if (X == null) {
            return;
        }
        //第一次到达节点做事
        int sum = preSum + X.val;
        if (map.containsKey(sum - K)) {
            max = Math.max(max, level - map.get(sum - K));
        }
        if (!map.containsKey(sum)) {
            map.put(sum, level);
        }

        dfs(X.left, sum, level + 1, K, map);
        dfs(X.right, sum, level + 1, K, map);
        //第三次到达节点，清理现场 如果sum是当前level添加进去的才清理
        if (map.get(sum) == level) {
            map.remove(sum);
        }
    }

    public static void main(String[] args) {
        //                   3
        //           -2             3
        //        1      4      5      -7
        //       3 5   2 -5  -5  -3   1   5
        int K = 0;
        TreeNode head = new TreeNode(3);
        head.left = new TreeNode(-2);
        head.left.left = new TreeNode(1);
        head.left.right = new TreeNode(4);
        head.left.left.left = new TreeNode(3);
        head.left.left.right = new TreeNode(5);
        head.left.right.left = new TreeNode(2);
        head.left.right.right = new TreeNode(5);

        head.right = new TreeNode(3);
        head.right.left = new TreeNode(5);
        head.right.right = new TreeNode(-7);
        head.right.left.left = new TreeNode(-5);
        head.right.left.right = new TreeNode(-3);
        head.right.right.left = new TreeNode(1);
        head.right.right.right = new TreeNode(5);

        System.out.println(process(head, K));

    }
}
