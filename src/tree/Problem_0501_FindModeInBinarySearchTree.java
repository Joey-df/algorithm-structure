package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 501. 二叉搜索树中的众数
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 * 假定 BST 有如下定义：
 * 结点左子树中所含结点的值小于等于当前结点的值
 * 结点右子树中所含结点的值大于等于当前结点的值
 * 左子树和右子树都是二叉搜索树
 * 例如：
 * 给定 BST [1,null,2,2],
 *
 *    1
 *     \
 *      2
 *     /
 *    2
 * 返回[2].
 *
 * 提示：如果众数超过1个，不需考虑输出顺序
 *
 * 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
 */
// 主要是利用中序遍历为非递减序列的特点
public class Problem_0501_FindModeInBinarySearchTree {

    private int maxCount = -1;
    private int count = 0;
    private TreeNode prev = null;
    private List<Integer> res = new ArrayList<>();

    public int[] findMode(TreeNode root) {
        dfs(root);
        int i = 0;
        int[] arr = new int[res.size()];
        for (int e : res) arr[i++] = e;
        return arr;
    }

    //inorder
    private void dfs(TreeNode x) {
        if (x == null) return;
        dfs(x.left);
        // 有前驱节点，并且，当前节点的值 = 前驱节点的值 -> count = count+1
        // 前驱节点为空（第一个节点），或者当前节点的值，不等于前驱节点的值，count归1，重新计数
        count = (prev != null && prev.val == x.val) ? count + 1 : 1;
        prev = x;
        if (count == maxCount) {
            res.add(x.val);
        } else if (count > maxCount) {
            res.clear(); // 发现了更高词频的节点，需要清空之前的结果
            res.add(x.val);
            maxCount = count; // 更新maxCount
        }
        dfs(x.right);
    }

    // todo
    // morris遍历 空间复杂度O(1)

}
