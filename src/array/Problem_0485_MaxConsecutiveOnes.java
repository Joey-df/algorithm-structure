package array;

/**
 * 485. 最大连续 1 的个数
 * 给定一个二进制数组， 计算其中最大连续 1 的个数。
 *
 * 示例：
 * 输入：[1,1,0,1,1,1]
 * 输出：3
 * 解释：开头的两位和最后的三位都是连续 1 ，所以最大连续 1 的个数是 3.
 *
 * 提示：
 * 输入的数组只包含 0 和 1 。
 * 输入数组的长度是正整数，且不超过 10,000。
 */
public class Problem_0485_MaxConsecutiveOnes {

    public static int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int max = 0;
        for (int num : nums) {
            if (num == 0) {
                count = 0;
            } else {
                count++;
            }
            max = Math.max(max, count);
        }
        return max;
    }

    public static int findMaxConsecutiveOnes2(int[] arr) {
        if (arr==null || arr.length==0) return 0;
        int pre = arr[0]==1 ? 1: 0;
        int ans=pre;
        for (int i=1;i<arr.length;i++) {
            int cur = arr[i]==1 ? pre+1 : 0;
            ans = Math.max(ans, cur);
            pre = cur;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1,1,0,1,1,1};
        System.out.println(findMaxConsecutiveOnes2(arr));
    }
}
