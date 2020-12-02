package train_camp_03.class03;

/**
 * 背包容量为w
 * 一共有n袋零食, 第i袋零食体积为v[i]>0
 * 总体积不超过背包容量的情况下，
 * 一共有多少种零食放法？(总体积为0也算一种放法)。
 */
public class Code02_SnacksWays {

    //经典的从左往右的尝试模型
    //递归含义：
    //nums表示零食体积组成的数组 nums[i]:第i袋零食的体积
    //nums[index...N-1]自由选择，填充rest容量有多少种方法
    //nums[0...index-1]已经搞定了，不用操心了
    public static int process(int[] nums, int index, int rest) {
        if (rest < 0) {//没容量了
            return -1;
        }
        //rest>=0
        if (index == nums.length) {//rest>=0 && 无零食可选了
            return 1;
        }
        //rest>0 && index<nums.length
        //不要index号零食
        int p1 = process(nums, index + 1, rest);
        //要index号零食
        int p2 = process(nums, index + 1, rest - nums[index]);
        return p1 + (p2 != -1 ? p2 : 0);
    }

    //dp[i][j]含义：
    //nums[i...N-1]自由选择，填充j容量的方法数
    public static int dpWay(int[] nums, int w) {
        int N = nums.length;
        int[][] dp = new int[N + 1][w + 1];
        for (int i = 0; i <= w; i++) {  //对应rest>=0
            dp[N][i] = 1;
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int j = w; j >= 0; j--) {
                dp[i][j] = (j - nums[i] >= 0 ? dp[i + 1][j - nums[i]] : 0) + dp[i + 1][j];
            }
        }
        return dp[0][w];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5, 4, 3, 2, 1, 2, 3, 4};
        int w = 13;
        int ans1 = process(nums, 0, w);
        System.out.println(ans1);
        int ans2 = dpWay(nums, w);
        System.out.println(ans2);
    }
}
