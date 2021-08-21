package coding_for_great_offer.class22;

// 本题测试链接 : https://leetcode.com/problems/trapping-rain-water/
public class Code02_TrappingRainWater {

    public static int trap(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int N = arr.length;
        int L = 1;
        int leftMax = arr[0];
        int R = N - 2;
        int rightMax = arr[N - 1];
        int water = 0;
        while (L <= R) {
            if (leftMax <= rightMax) {
                water += Math.max(0, leftMax - arr[L]);
                leftMax = Math.max(leftMax, arr[L++]);
            } else {
                water += Math.max(0, rightMax - arr[R]);
                rightMax = Math.max(rightMax, arr[R--]);
            }
        }
        return water;
    }

    //平凡解
    //使用预处理数组
    public static int trap2(int[] arr) {

        if (arr == null || arr.length < 3) {
            return 0;
        }
        int N = arr.length;
        int[] leftMax = new int[N];
        int lm = arr[0];
        for (int i = 0; i < N; i++) {
            lm = Math.max(lm, arr[i]);
            leftMax[i] = lm;
        }
        int[] rightMax = new int[N];
        int rm = arr[N - 1];
        for (int i = N - 1; i >= 0; i--) {
            rm = Math.max(rm, arr[i]);
            rightMax[i] = rm;
        }
        //print(leftMax);
        //print(rightMax);
        int ans = 0;
        for (int i = 1; i < N - 1; i++) {
            ans += Math.max((Math.min(leftMax[i], rightMax[i]) - arr[i]), 0);
        }
        return ans;
    }

}
