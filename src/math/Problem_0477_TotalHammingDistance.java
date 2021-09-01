package math;

/**
 * 477. 汉明距离总和
 * 两个整数的 汉明距离 指的是这两个数字的二进制数对应位不同的数量。
 * 给你一个整数数组 nums，请你计算并返回 nums 中任意两个数之间汉明距离的总和。
 * <p>
 * 示例 1：
 * 输入：nums = [4,14,2]
 * 输出：6
 * 解释：在二进制表示中，4 表示为 0100 ，14 表示为 1110 ，2表示为 0010 。（这样表示是为了体现后四位之间关系）
 * 所以答案为：
 * HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6
 * 示例 2：
 * 输入：nums = [4,14,4]
 * 输出：4
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 */
public class Problem_0477_TotalHammingDistance {

    //方法一：逐位统计
    //在计算汉明距离时，我们考虑的是同一比特位上的值是否不同，而不同比特位之间是互不影响的。
    //
    //对于数组 nums 中的某个元素 val，若其二进制的第 i 位为 1，
    // 我们只需统计 nums 中有多少元素的第 i 位为 0，即计算出了 val 与其他元素在第 i 位上的汉明距离之和。
    //
    //具体地，若长度为 nn 的数组 nums 的所有元素二进制的第 i 位共有 c 个 1，n−c 个 0，则些元素在二进制的第 i 位上的汉明距离之和为
    //
    //c⋅(n−c)
    //
    //我们可以从二进制的最低位到最高位，逐位统计汉明距离。将每一位上得到的汉明距离累加即为答案。
    //
    //具体实现时，对于整数 val 二进制的第 i 位，我们可以用代码 (val >> i) & 1 来取出其第 i 位的值。
    // 此外，由于 10^9<2^30，我们可以直接从二进制的第 0 位枚举到第 29 位。
    public static int totalHammingDistance(int[] arr) {
        int ans = 0;
        int n = arr.length;
        for (int i = 0; i < 30; i++) {
            int numOf1 = 0;
            for (int num : arr) {
                numOf1 += ((num >>> i) & 1) == 1 ? 1 : 0; //取出第i为的状态 1还是0
            }
            int numOf0 = n - numOf1;
            ans += numOf1 * numOf0;
        }
        return ans;
    }

    //O(n^2)超时 TLE
    public static int totalHammingDistance2(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int eor = arr[i] ^ arr[j];
                ans += numOf1Bits(eor);
            }
        }
        return ans;
    }

    public static int numOf1Bits(int n) {
        int ans = 0;
        while (n != 0) {
            int ro = n & -n;
            ans++;
            n = n ^ ro;
        }
        return ans;
    }

}
