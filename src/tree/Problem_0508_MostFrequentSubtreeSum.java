package tree;

//508. 出现次数最多的子树元素和

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 508. 出现次数最多的子树元素和
 * 给你一个二叉树的根结点，请你找出出现次数最多的子树元素和。
 * 一个结点的「子树元素和」定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。
 * 你需要返回出现次数最多的子树元素和。如果有多个元素出现的次数相同，返回所有出现次数最多的子树元素和（不限顺序）。
 *
 * 示例 1：
 * 输入:
 *    5
 *  /  \
 * 2   -3
 * 返回 [2, -3, 4]，所有的值均只出现一次，以任意顺序返回所有值。
 *
 * 示例 2：
 * 输入：
 *   5
 *  /  \
 * 2   -5
 * 返回 [2]，只有 2 出现两次，-5 只出现 1 次。
 *
 * 提示： 假设任意子树元素和均可以用 32 位有符号整数表示。
 */
public class Problem_0508_MostFrequentSubtreeSum {
    private int maxCount=0; //出现最多的次数

    public int[] findFrequentTreeSum(TreeNode root) {
        HashMap<Integer,Integer> map = new HashMap<>();
        fun(root,map);
        List<Integer> list = new ArrayList<>();
        for (int key: map.keySet()) {
            if (map.get(key)==maxCount) {
                list.add(key);
            }
        }
        //System.out.println(list);
        //List转整型数组
        return list.stream().mapToInt(a->a).toArray();
    }

    //递归含义
    //返回以x为头的数的子树和
    //map:用来收集每个节点的子树和 <子树和，出现的次数>
    public int fun(TreeNode x, HashMap<Integer, Integer> map) {
        if (x==null) return 0;
        int l = fun(x.left,map);
        int r = fun(x.right,map);
        int sum = l + r + x.val;
        map.put(sum, map.getOrDefault(sum,0)+1);
        maxCount = Math.max(maxCount, map.get(sum));
        return sum;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left=new TreeNode(2);
        root.right=new TreeNode(-3);
        new Problem_0508_MostFrequentSubtreeSum().findFrequentTreeSum(root);
    }

}
