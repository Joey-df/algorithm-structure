package train_camp_03.class02;

/**
 * 给定一个数组arr,已知其中所有的值都是非负的,将这个数组看作一个容器, 请返回容器能装多少水
 * 比如,arr = {3,1,2,5,2,4},根据值画出的直方图就是容器形状,该容 器可以装下5格水
 * 再比如,arr = {4,5,1,3,2},该容器可以装下2格水
 * https://leetcode.com/problems/trapping-rain-water/
 */
public class Code04_TrappingRainWater {

    //方法一：使用预处理数组
    public static int ways(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        //{3,1,2,5,2,4}
        int[] lTor = new int[N]; //i位置之前的最大值 组成的数组
        lTor[0] = nums[0];
        for (int i = 1; i < N; i++) {
            lTor[i] = Math.max(lTor[i - 1], nums[i]);
        }

        int[] rTol = new int[N]; //i位置之后的最大值 组成的数组
        rTol[N - 1] = nums[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            rTol[i] = Math.max(rTol[i + 1], nums[i]);
        }
        int ans = 0;
        for (int i = 1; i < N - 1; i++) {
            ans += Math.max(0, Math.min(lTor[i], rTol[i]) - nums[i]);
        }
        return ans;
    }

    //方法二：完全不使用预处理数组
    public static int ways2(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        int N = nums.length;
        int lMax = nums[0];
        int rMax = nums[N - 1];
        int ans = 0;
        int l = 1;
        int r = N - 2;
        //{3,1,2,5,2,4}
        while (l <= r) { //谁小结算谁
            if (lMax <= rMax) { //结算左边
                ans += Math.max(0, Math.min(lMax, rMax) - nums[l]);
                lMax = Math.max(lMax, nums[l++]); //更新最大值之后 指针跳下一个
            } else {
                ans += Math.max(0, Math.min(lMax, rMax) - nums[r]);
                rMax = Math.max(rMax, nums[r--]);
            }
        }
        return ans;
    }

    public static int[] genArr(int maxLen, int maxVal) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = (int) (Math.random() * (maxVal + 1));
        }
        return ans;
    }

    public static void main(String[] args) {
        int testTimes = 10000000;
        int maxLen = 1000;
        int maxVal = 100;
        System.out.println("test start");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = genArr(maxLen, maxVal);
            int ans1 = ways(arr);
            int ans2 = ways2(arr);
            if (ans1 != ans2) {
                System.out.println("fuck!!!");
            }
        }
        System.out.println("test end");
    }
}
