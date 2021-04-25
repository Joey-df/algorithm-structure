package train_camp_03.class01;

import java.util.Arrays;

/**
 * 给定一个有序数组arr，从左到右依次表示X轴上从左往右点的位置
 * 给定一个正整数K，返回如果有一根长度为K的绳子，最多能盖住几个点
 * 绳子的边缘点碰到X轴上的点，也算盖住
 */
// 两种方法：
// 1、每个位置结尾求 二分 O(NlogN)
// 2、滑动窗口O(N)
public class Code01_CordCoverMaxPoint {
    //[-5,-3,1,3,4,7,9,100]  k=5
    //二分找>=val最左侧的位置
    //时间复杂度：O(N*logN)
    //思路：在nums中枚举绳子的每个结尾的位置i，在nums[0,i]范围上找>=nums[i]-k最左的位置
    //求出每个结尾的答案，求整体max
    public static int ways1(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        int ans = Integer.MIN_VALUE;
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            int l = 0;
            int r = i;
            while (l <= r) {
                int m = l + ((r - l) >> 1);
                if (nums[m] >= nums[i] - k) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            //while跳出来时，如果找到了，l就是>=nums[i]-k最左的位置；如果没找到，l会来到r+1的位置
            ans = Math.max(ans, i - l + 1);
        }
        return ans;
    }

    //滑动窗口：时间复杂度O(N)
    //枚举绳子每一个开头的位置i能盖住几个点，求整体max
    //双指针l、r只前进不后退，所以时间复杂度是O(N)
    //[-5,-3,1,3,4,7,9,10]  k=5
    public static int ways2(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        int N = nums.length;
        int r = 0;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i <= N - 1; i++) { //i就是l
            while (r < N && nums[r] - nums[i] <= k) { //nums[r]-nums[l]>k时跳出
                r++;//以i位置开头，r能往右扩就往右扩，扩不动了停
            }
            ans = Math.max(ans, r - i);
        }
        return ans;
    }

    //on class
    public static int maxPoint2(int[] arr, int L) {
        int left = 0;
        int right = 0;
        int N = arr.length;
        int max = 0;
        while (left < N) {
            while (right < N && arr[right] - arr[left] <= L) {
                right++;
            }
            max = Math.max(max, right - (left++));
        }
        return max;
    }

    //给定数组arr，绳子长度为K，求在arr中绳子最多能盖住几个点
    //[1,3,4,6,7,10]
    //K=4
    public static int process(int[] arr, int K) {
        //使用滑动窗口
        //枚举每个i开头的答案，整体求max
        /*if(arr==null || arr.length==0) return 0;
        if (K<0) return 0;
        int ans = 0;
        int l=0;
        int r=0;
        int N = arr.length;
        while (l<N) { //枚举开头：从0开始，越界停
            while (r<N && arr[r]-arr[l]<=K) {
                r++;//r不越界，并且数组l~r之间的差值没有超过K，r往右动
            }
            ans = Math.max(ans, r-l);
            l++;
        }
        return ans;*/
        //二分法
        //枚举每个结尾的位置i，在[0,i]上找>=arr[i]-K最左的位置
        if (arr == null || arr.length == 0 || K < 0) return 0;
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {//枚举每个结尾的位置i;结尾的位置包括数组中每个位置
            int r = i;
            int l = 0;
            int target = arr[i] - K;
            while (l <= r) {
                int m = (l + r) >> 1;
                if (arr[m] >= target) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            //while结束，找到了的话，l就是>=arr[i]-K最左的位置，没找到的话l会来到r+1的位置
            ans = Math.max(ans, i - l + 1);
        }
        return ans;
    }

    //for test
    public static int[] generateArr(int maxLen, int maxValue) {
        int[] arr = new int[(int) (Math.random() * (maxLen + 1))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue));
        }
        Arrays.sort(arr);
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxLen = 1000;
        int maxVlaue = 100;
        System.out.println("test start");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateArr(maxLen, maxVlaue);
            int k = (int) (Math.random() * maxLen);
            int ans1 = ways1(arr, k);
            int ans2 = ways2(arr, k);
            int ans3 = maxPoint2(arr, k);
            int ans4 = process(arr, k);
            if (ans1 != ans2 || ans2 != ans3 || ans3 != ans4) {
                System.out.println("funk!");
                break;
            }
        }
        System.out.println("pass!");
    }
}
