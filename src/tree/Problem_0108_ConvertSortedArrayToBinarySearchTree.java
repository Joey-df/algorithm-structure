package tree;

/**
 * 108. 将有序数组转换为二叉搜索树
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 *
 * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
 */
public class Problem_0108_ConvertSortedArrayToBinarySearchTree {

    public static TreeNode sortedArrayToBST(int[] nums) {
        if(nums==null || nums.length==0) {
            return null;
        }
        return build(nums, 0, nums.length-1);
    }

    private static TreeNode build(int[] arr, int l, int r) {
        if (l>r) return null;
        int m = l+ ((r-l) >>1);
        TreeNode root = new TreeNode(arr[m]);
        root.left = build(arr, l, m-1);
        root.right = build(arr, m+1, r);
        return root;
    }

}