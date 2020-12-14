package train_camp_02.class02;

/**
 * 给定一个正整数组成的无序数组arr，给定一个正整数值K
 * 找到arr的所有子数组里，哪个子数组的累加和等于K，并且是长度最大的
 * 返回其长度
 * <p>
 * 滑动窗口
 */
public class Code01_LongestSumSubArrayLengthInPositiveArray {

    //如[2,3,1,4,4,2,6,1,1,1,1,1,1,2]，累加和等于6的子数组最长的是6
    //分析：因为是正数数组，所以累加和与子数组长度有严格的单调性
    //子数组长度增大，累加和必变大
    //所以使用滑动窗口，求出每一个i开头的答案，求全局max即可
    public static int process(int[] arr, int K) {
        if (arr == null || arr.length == 0 || K <= 0) {
            return 0;
        }
        int l = 0;
        int r = 0;//[l,r]
        int windowSum = arr[0];
        int ans = 0;
        while (r < arr.length) {
            if (windowSum == K) {
                ans = Math.max(ans, r - l + 1);
                windowSum -= arr[l++];
            } else if (windowSum < K) {
                r++;
                if (r == arr.length) {
                    break;
                }
                windowSum += arr[r];
            } else { //windowSum > K
                windowSum -= arr[l++];
            }
        }
        return ans;
    }

    public static int right(int[] arr, int K) {
        if (arr == null || arr.length == 0 || K <= 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) { //考察每一个以i开始以j结尾的子数组
                if (isValid(arr, i, j, K)) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }

    public static boolean isValid(int[] arr, int l, int r, int K) {
        int sum = 0;
        for (int i = l; i <= r; i++) {
            sum += arr[i];
        }
        return sum == K;
    }

    public static int[] generatePositiveArr(int maxLen, int maxVal) {
        int len = (int) (Math.random() * (maxLen));
        len = Math.max(3, len);
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = (int) (Math.random() * (maxVal)) + 1;
        }
        return res;
    }

    public static void print(int[] arr) {
        if (arr == null || arr.length == 0) return;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int testTimes = 100000;
        int maxLen = 50;
        int maxVal = 20;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generatePositiveArr(maxLen, maxVal);
            int K = (int) (Math.random() * maxVal);
            int ans1 = right(arr, K);
            int ans2 = process(arr, K);
            if (ans1 != ans2) {
                print(arr);
                System.out.println("fuck");
                break;
            }
        }
        System.out.println("test end.");
    }

}
