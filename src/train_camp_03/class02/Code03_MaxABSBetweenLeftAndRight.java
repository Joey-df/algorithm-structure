package train_camp_03.class02;

/**
 * 给定一个数组arr长度为N，你可以把任意长度大于0且小于N的前缀作为左部分，剩下的 作为右部分。
 * 但是每种划分下都有左部分的最大值和右部分的最大值，请返回最大的，左部分最大值减去右部分最大值的绝对值。
 * <p>
 * 单调性：范围变大，max只可能不变或变大
 */
public class Code03_MaxABSBetweenLeftAndRight {

    //分析：先找打全局最大值allMax， allMax-nums[0] 与 allMax-nums[N-1]的最大值
    //如果allMax在右边，那么必须包含nums[0]的范围变大，左边的最大值只能不变或变大
    //如果allMax在左边，那么必须包含nums[N-1]的范围变大，右边的最大值只能不变或变大
    public static int process(int[] nums) {
        int allMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            allMax = Math.max(allMax, nums[i]);
        }
        return allMax - Math.min(nums[0], nums[nums.length - 1]);
    }

    //on class
    public static int maxABS1(int[] arr) {
        int res = Integer.MIN_VALUE;
        int maxLeft = 0;
        int maxRight = 0;
        for (int i = 0; i != arr.length - 1; i++) {
            maxLeft = Integer.MIN_VALUE;
            for (int j = 0; j != i + 1; j++) {
                maxLeft = Math.max(arr[j], maxLeft);
            }
            maxRight = Integer.MIN_VALUE;
            for (int j = i + 1; j != arr.length; j++) {
                maxRight = Math.max(arr[j], maxRight);
            }
            res = Math.max(Math.abs(maxLeft - maxRight), res);
        }
        return res;
    }

    public static int[] generateArr(int maxLen, int maxValue) {
        int len = (int) (Math.random() * maxLen);
        int[] res = new int[len < 2 ? 10 : len];
        for (int i = 0; i < len; i++) {
            res[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue));
        }
        return res;
    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLen = 100;
        int maxValue = 1000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] nums = generateArr(maxLen, maxValue);
            int ans1 = process(nums);
            int ans2 = maxABS1(nums);
            if (ans1 != ans2) {
                System.out.println("fuck");
            }
        }
        System.out.println("test end");
    }
}
