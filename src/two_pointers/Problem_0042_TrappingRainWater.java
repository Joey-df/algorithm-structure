package two_pointers;

/**
 * 42. 接雨水
 * 给定一个数组arr，已知其中所有的值都是非负的，将这个数组看作一个容器， 请返回容器能装多少水
 * 比如，arr = {3，1，2，5，2，4}，根据值画出的直方图就是容器形状，该容 器可以装下5格水
 * 再比如，arr = {4，5，1，3，2}，该容器可以装下2格水
 */
public class Problem_0042_TrappingRainWater {

    //平凡解：使用辅助数组
    public static int trap(int[] arr) {
        if (arr == null || arr.length < 3) return 0;
        int n = arr.length;
        //从左往右，求左侧的最大值
        int[] left = new int[n];
        left[0] = arr[0];
        for (int i = 1; i < n; i++) {
            left[i] = Math.max(arr[i], left[i - 1]);
        }
        //从右往左，求右侧的最大值
        int[] right = new int[n];
        right[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(arr[i], right[i + 1]);
        }
        int ans = 0;
        for (int i = 1; i < n - 1; i++) {
            ans += Math.max(0, Math.min(left[i], right[i]) - arr[i]);
        }
        return ans;
    }


    //最优解，不用辅助数组
    public static int trap2(int[] nums) {
        if (nums == null || nums.length < 3) return 0;
        int leftMax = nums[0]; //l左边的最大值
        int rightMax = nums[nums.length - 1]; //r右边的最大值
        int l = 1, r = nums.length - 2;//0、N-1两个位置没有水量产生
        int ans = 0;
        while (l <= r) {//l>r停
            //谁小结算谁 水量取决于左右的短板
            if (leftMax <= rightMax) {
                ans += Math.max(0, (leftMax - nums[l]));
                leftMax = Math.max(leftMax, nums[l]);
                l++;
            } else {
                ans += Math.max(0, (rightMax - nums[r]));
                rightMax = Math.max(rightMax, nums[r]);
                r--;
            }
        }
        return ans;
    }

}
