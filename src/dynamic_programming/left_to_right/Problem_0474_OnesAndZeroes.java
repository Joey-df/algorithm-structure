package dynamic_programming.left_to_right;

/**
 * 474. 一和零
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 * 请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 *
 * 示例 1：
 * 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 * 输出：4
 * 解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
 * 其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
 * 示例 2：
 * 输入：strs = ["10", "0", "1"], m = 1, n = 1
 * 输出：2
 * 解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
 *
 * 提示：
 * 1 <= strs.length <= 600
 * 1 <= strs[i].length <= 100
 * strs[i] 仅由 '0' 和 '1' 组成
 * 1 <= m, n <= 100
 */
//0-1背包问题
//从左往右的尝试模型
public class Problem_0474_OnesAndZeroes {

    //1 <= m, n <= 100
    //1 <= strs.length <= 600
    //1 <= strs[i].length <= 100
    //strs[i] consists only of digits '0' and '1'.
    public static int ways1(String[] strs, int m, int n) {
        return process(strs, 0, m, n);
    }

    //递归含义:
    //strs[index...]自由选择，搞定m个0、n个1的字符串的最大数量是多少？
    //strs[0...index-1]不用操心了
    //m、n是背包容量
    //strs是货物
    public static int process(String[] strs, int index, int m, int n) {
        //m、n均取不到负数
        if (m == 0 && n == 0) {//0个0、0个1可以搞定0个字符串
            return 0;
        }

        if (index == strs.length) {
            //没有字符串可以用,使用m个0、n个1可以搞定0个字符串
            return 0;
        }
        //index < strs.length && m、n>0
        //不要strs[index]的字符串
        int ans = process(strs, index + 1, m, n);
        int[] nums = help(strs[index]);
        int nums_0 = nums[0];//0的个数
        int nums_1 = nums[1];//1的个数
        //要strs[index]的字符串
        if (m - nums_0 >= 0 && n - nums_1 >= 0) //当背包容量还能装下index位置的货物时
            ans = Math.max(ans, process(strs, index + 1, m - nums_0, n - nums_1) + 1);
        return ans;
    }


    //dp[i][j][k]的含义：
    //strs[i...]上自由选择，搞定j个0、k个1，能得到的最多的字符串数量是多少？
    public static int dpWays(String[] strs, int m, int n) {
        int N = strs.length;
        int[][][] dp = new int[N + 1][m + 1][n + 1];
        //dp[N][...][...] = 0; //最后一行全是0
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    int ans = dp[i + 1][j][k];
                    int[] nums = help(strs[i]);
                    int nums_0 = nums[0];//0的个数
                    int nums_1 = nums[1];//1的个数
                    //要strs[index]的字符串
                    if (j - nums_0 >= 0 && k - nums_1 >= 0) //当背包容量还能装下index位置的货物时
                        ans = Math.max(ans, dp[i + 1][j - nums_0][k - nums_1] + 1);
                    dp[i][j][k] = ans;
                }
            }
        }
        return dp[0][m][n];
    }

    /**
     * @param s 0和1组成的字符串
     * @return 返回0、1各自的个数
     */
    public static int[] help(String s) {
        int[] ans = new int[2];
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++) {
            ans[str[i] - '0']++;
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] strs = {"0","11","1000","01","0","101","1","1","1","0","0","0","0","1","0","0110101","0","11","01","00","01111","0011","1","1000","0","11101","1","0","10","0111"};
        int m = 9, n = 80;
        System.out.println(ways1(strs, m, n));
        System.out.println(dpWays(strs, m, n));
    }
}
