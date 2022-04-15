package hash_map;

/**
 * 1399. 统计最大组的数目
 * 给你一个整数 n 。请你先求出从 1 到 n 的每个整数 10 进制表示下的数位和（每一位上的数字相加），然后把数位和相等的数字放到同一个组中。
 * 请你统计每个组中的数字数目，并返回数字数目并列最多的组有多少个。
 * 示例 1：
 * 输入：n = 13
 * 输出：4
 * 解释：总共有 9 个组，将 1 到 13 按数位求和后这些组分别是：
 * [1,10]，[2,11]，[3,12]，[4,13]，[5]，[6]，[7]，[8]，[9]。总共有 4 个组拥有的数字并列最多。
 * 示例 2：
 * 输入：n = 2
 * 输出：2
 * 解释：总共有 2 个大小为 1 的组 [1]，[2]。
 * 示例 3：
 * 输入：n = 15
 * 输出：6
 * 示例 4：
 * 输入：n = 24
 * 输出：5
 * 提示：
 * 1 <= n <= 10^4
 */
public class Problem_1399_CountLargestGroup {

    // 1 <= n <= 10^4，数位和最大的数是9999，和为36，所以申请长度为37的数组，能装下所有数位和（1～36）
    // 接下来就是hash表操作了
    // 1. 遍历1～n，求出每一个数num的数位和，对应桶的count++
    // 2. 遍历过程中，抓出全局最大的词频max
    // 3. 遍历hash表，返回长度为max的个数
    public static int countLargestGroup(int n) {
        int[] help = new int[37];
        int maxFreq = 0;
        for (int num = 1; num <= n; num++) {
            int sum = bitSum(num);
            maxFreq = Math.max(maxFreq, ++help[sum]);
        }
        int ans = 0;
        for (int i = 0; i < help.length; i++) {
            ans += help[i] == maxFreq ? 1 : 0;
        }
        return ans;
    }

    public static int bitSum(int num) {
        int ans = 0;
        while (num != 0) {
            ans += num % 10;
            num /= 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(countLargestGroup(13));
    }

}
