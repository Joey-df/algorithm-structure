package dynamic_programming.left_to_right;

/**
 * 413. 等差数列划分
 * 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 * 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
 * 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
 * 子数组 是数组中的一个连续序列。
 *
 * 示例 1：
 * 输入：nums = [1,2,3,4]
 * 输出：3
 * 解释：nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。
 *
 * 示例 2：
 * 输入：nums = [1]
 * 输出：0
 *
 * 提示：
 * 1 <= nums.length <= 5000
 * -1000 <= nums[i] <= 1000
 */
public class Problem_0413_ArithmeticSlices {

    //"cur" the number of different "Arithmetic Slices" ends at index = i
    //a small example :
    //we have :[1, 2, 3, 4]
    //index = 2, we have curr = 1, sum = 1, which is [1,2,3];
    //index = 3, curr = 2, sum = 2 + 1 = 3; because we look back from index = 3 which is 4 ,
    //we will have two "Arithmetic Slices", which is [2,3,4] and [1,2,3,4]...
    public int numberOfArithmeticSlices(int[] arr) {
        if (arr==null || arr.length<3) return 0;
        int cur = 0;
        int sum = 0;
        int n = arr.length;
        for (int i = 2; i < n; i++) {
            if (arr[i]-arr[i-1] == arr[i-1]-arr[i-2]) {
                cur += 1;
                sum += cur;
            } else {
                cur = 0;
            }
        }
        return sum;
    }


    public int numberOfArithmeticSlices2(int[] arr) {
        if (arr==null || arr.length<3) return 0;
        int n = arr.length;
        int ans = 0;
        int pre = 0; //必须以i-1位置结尾，等差子数组的个数
        //等差子数组只能从2位置开始（至少3个元素）
        for (int l=1, r=2; r<n; l++, r++) {
            int cur = (arr[r]-arr[l] == arr[r-1]-arr[l-1]) ? pre+1 : 0;
            pre = cur;
            ans += cur;
        }
        return ans;
    }


    public int numberOfArithmeticSlices3(int[] arr) {
        if (arr==null || arr.length<3) return 0;
        int n = arr.length;
        int ans = 0;
        int[] dp = new int[n];//dp[i]的含义：必须以i位置结尾，等差子数组的个数是多少
        // dp[0] = 0, dp[1] = 0
        for (int i = 2; i < n; i++) {
            if (arr[i]-arr[i-1] == arr[i-1]-arr[i-2]) {
                dp[i] = dp[i-1] + 1;
            } else {
                dp[i] = 0;
            }
            ans += dp[i];
        }
        return ans;
    }

}
