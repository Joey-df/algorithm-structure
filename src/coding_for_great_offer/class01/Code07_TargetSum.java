package coding_for_great_offer.class01;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个数组arr，你可以在每个数字之前决定+或者-
 * 但是必须所有数字都参与
 * 再给定一个数target，请问最后算出target的方法数是多少？
 * leetcode 494
 * 1、暴力尝试
 * 2、记忆化搜索
 * 3、背包优化
 */
public class Code07_TargetSum {

    //暴力尝试
    //从左往右的尝试模型
    //递归含义：
    //[0,index-1]已经搞定了 不用操心了
    //在arr[index....]每个元素前任意添加+或-，组成target的方法数返回
    public static int process(int[] arr, int index, int target) {
        if (arr == null || arr.length == 0) return 0;
        int ans = 0;
        if (index == arr.length) {
            //表示已经没有元素可以选了,组成0有一种方法(什么也不选)，否则有0中方法
            ans = target == 0 ? 1 : 0;
        } else {
            //还有元素可选
            int p1 = process(arr, index + 1, target - arr[index]);//arr[index]前添加+
            int p2 = process(arr, index + 1, target + arr[index]);//arr[index]前添加-
            ans = p1 + p2;
        }
        return ans;
    }

    //主函数
    public static int findTargetSumWays1(int[] arr, int target) {
        if (arr == null || arr.length == 0) return 0;
        return process(arr, 0, target);
    }

    //改动态规划：记忆话搜索
    public static int findTargetSumWays2(int[] arr, int target) {
        if (arr == null || arr.length == 0) return 0;
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        return dp(arr, 0, target, map);
    }

    public static int dp(int[] arr, int index, int target, Map<Integer, Map<Integer, Integer>> map) {
        if (map.get(index) != null && map.get(index).get(target) != null) {
            return map.get(index).get(target);
        }
        int ans = 0;
        if (index == arr.length) {
            //表示已经没有元素可以选了,组成0有一种方法(什么也不选)，否则有0中方法
            ans = target == 0 ? 1 : 0;
        } else {
            //还有元素可选
            int p1 = dp(arr, index + 1, target - arr[index], map);//arr[index]前添加+
            int p2 = dp(arr, index + 1, target + arr[index], map);//arr[index]前添加-
            ans = p1 + p2;
            if (map.get(index) == null) {
                map.put(index, new HashMap<>());
            } else {
                map.get(index).put(target, ans);
            }
        }
        return ans;
    }

    // 优化点一 :
    // 你可以认为arr中都是非负数
    // 因为即便是arr中有负数，比如[3,-4,2]
    // 因为你能在每个数前面用+或者-号
    // 所以[3,-4,2]其实和[3,4,2]达成一样的效果
    // 那么我们就全把arr变成非负数，不会影响结果的
    // 优化点二 :
    // 如果arr都是非负数，并且所有数的累加和是sum
    // 那么如果target<sum，很明显没有任何方法可以达到target，可以直接返回0
    // 优化点三 :
    // 因为题目要求一定要使用所有数字去拼target，
    // 所以不管这些数字怎么用+和-折腾，最终的结果都一定不会改变奇偶性
    // 所以，如果所有数的累加和是sum，
    // 并且与target的奇偶性不一样，没有任何方法可以达到target，可以直接返回0
    // 优化点四 :
    // 比如说给定一个数组, arr = [1, 2, 3, 4, 5] 并且 target = 3
    // 其中一个方案是 : +1 -2 +3 -4 +5 = 3
    // 该方案中取了正的集合为P = {1，3，5}
    // 该方案中取了负的集合为N = {2，4}
    // 所以任何一种方案，都一定有 sum(P) - sum(N) = target
    // 现在我们来处理一下这个等式，把左右两边都加上sum(P) + sum(N)，那么就会变成如下：
    // sum(P) - sum(N) + sum(P) + sum(N) = target + sum(P) + sum(N)
    // 2 * sum(P) = target + 数组所有数的累加和
    // sum(P) = (target + 数组所有数的累加和) / 2
    // 也就是说，任何一个集合，只要累加和是(target + 数组所有数的累加和) / 2
    // 那么就一定对应一种target的方式
    // 也就是说，比如非负数组arr，target = 7, 而所有数累加和是11
    // 求使用所有数字的情况下，多少方法最后形成7？
    // 其实就是求有多少个子集的累加和是9 -> (7 + 11) / 2
    // 优化点五 :
    // 二维动态规划的空间压缩技巧
    public static int fromTeacherZuo(int[] arr, int target) {
        int sum = 0;
        for (int n : arr) {
            sum += n;
        }
        return sum < target || ((target & 1) ^ (sum & 1)) != 0
                ? 0
                : sub2(arr, (target + sum) >> 1);
    }


    // 求nums有多少个子集，累加和是s
    // 二维动态规划
    // 不用空间压缩
    public static int sub1(int[] nums, int s) {
        int n = nums.length;
        // dp[i][j] : nums前缀长度为i的所有子集，有多少子集的累加和是j？
        int[][] dp = new int[n + 1][s + 1];
        // nums前缀长度为0的所有子集，有多少累加和是0？一个：空集
        dp[0][0] = 1;
        //第一行
        for (int i = 1; i < s + 1; i++) {
            dp[0][i] = 0; //nums前缀长度为0的所有子集，有多少累加和是i(i>=1)？0个
        }

        for (int i = 1; i <= n; i++) { //i表示前缀长度
            for (int j = 0; j <= s; j++) {
                dp[i][j] = dp[i - 1][j]; //不要i-1位置的数，nums前缀长度为i，最后一个元素的下标为i-1
                if (j - nums[i - 1] >= 0) { //要i-1位置的数
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][s];
    }

    // 求nums有多少个子集，累加和是s
    // 二维动态规划
    // 用空间压缩:
    // 核心就是for循环里面的：for (int i = s; i >= n; i--) {
    // 为啥不枚举所有可能的累加和？只枚举 n...s 这些累加和？
    // 因为如果 i - n < 0，dp[i]怎么更新？和上一步的dp[i]一样！所以不用更新
    // 如果 i - n >= 0，dp[i]怎么更新？上一步的dp[i] + 上一步dp[i - n]的值，这才需要更新
    public static int sub2(int[] nums, int s) {
        int[] dp = new int[s + 1];
        dp[0] = 1; //nums前缀长度为0的所有子集，有多少累加和是0？一个：空集
        for (int n : nums) {
            for (int i = s; i >= n; i--) {
                dp[i] += dp[i - n];
            }
        }
        return dp[s];
    }

    public static int sub3(int[] nums, int s) {
        int[] dp = new int[s + 1];
        dp[0] = 1; //nums前缀长度为0的所有子集，有多少累加和是0？一个：空集
        for (int i = 0; i < nums.length; i++) {
            for (int j = s; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[s];
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    //nums中不包含0是对的，包含0就不对了
    //此方法只针对nums为正数数组有效
    public static int subset1(int[] nums, int target) {
        int N = nums.length;
        //dp[i][j]:
        //nums[0,i]自由选择,组成j的方法数，返回
        int[][] dp = new int[N][target + 1];
        dp[0][0] = 1; //nums[0,0]范围上组成0有一种方法：什么数也不选
        //第一行
        for (int i = 1; i < target + 1; i++) {
            //nums[0,0]范围上组成i有几种方法
            dp[0][i] = nums[0] == i ? 1 : 0;
        }
        //第一列
        for (int i = 1; i < N; i++) {
            //nums[0,i]范围上组成0有1种方法:什么数也不选
            dp[i][0] = 1;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < target + 1; j++) {
                dp[i][j] = dp[i - 1][j]; //不要nums[i]
                if (j >= nums[i]) {
                    dp[i][j] += dp[i - 1][j - nums[i]];
                }
            }
        }

        return dp[N - 1][target];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
//        int[] nums = new int[]{1,2,13,4,5,6,7,8,9,10};
        int t = 1;
        System.out.println(subset1(nums, t));
        System.out.println(sub1(nums, t));
        System.out.println(sub2(nums, t));
        System.out.println(sub3(nums, t));
    }
}
