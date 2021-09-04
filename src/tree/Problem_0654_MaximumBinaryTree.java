package tree;

/**
 * 654. 最大二叉树
 * 给定一个不含重复元素的整数数组 nums 。一个以此数组直接递归构建的 最大二叉树 定义如下：
 *
 * 二叉树的根是数组 nums 中的最大元素。
 * 左子树是通过数组中 最大值左边部分 递归构造出的最大二叉树。
 * 右子树是通过数组中 最大值右边部分 递归构造出的最大二叉树。
 * 返回有给定数组 nums 构建的 最大二叉树 。
 */
public class Problem_0654_MaximumBinaryTree {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums==null||nums.length==0) return null;
        return build(nums, 0, nums.length-1);
    }

    public TreeNode build(int[] nums, int l, int r) {
        if (l > r) return null;
        //l<=r
        int mid = maxValIndex(nums,l,r);
        TreeNode root = new TreeNode(nums[mid]);
        root.left = build(nums, l, mid-1);
        root.right = build(nums, mid+1, r);
        return root;
    }

    //在nums[l,r]范围上找最大值的下标
    //调用前提 保证l<=r
    private int maxValIndex(int[] nums ,int l, int r) {
        int max = nums[l];
        int index = l;
        for (int i = l+1; i <= r; i++) {
            if (nums[i] > max) {
                max = nums[i];
                index = i;
            }
        }
        return index;
    }

}
