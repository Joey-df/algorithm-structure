package train_camp_02.class02;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数组成的无序数组arr，值可能正、可能负、可能0
 * 给定一个整数值K
 * 找到arr的所有子数组里，哪个子数组的累加和等于K，并且是长度最大的
 * 返回其长度
 */
//附加题（使用该题的原型）
//给定数组arr，有正有负有0，包含1和2数量一样多称为子数组达标，求达标的子数组的最大长度是多少？
//解法：预处理，1变1，2变-1，其余全变成0，得到预处理之后的数组arr'，基于arr'求累加和为0的最长子数组，就是答案。
public class Code02_LongestSumSubArrayLength {
    //分析：
    //无序数组arr，值可能正、可能负、可能0
    //没有范围增大累加和也增大的单调性
    //使用前缀和，求出每一个i结尾的子数组的答案，求整体max

    //如果arr[0,i]的整体累加和为sum
    //以i结尾的子数组累加和=K，等价于求sum-K累加和首次出现的位置j，[j+1,i]就是以i结尾的子数组累加和等于K，并且是长度最大的
    public static int process(int[] arr, int K) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>(); //<累加和sum，首次出现的位置>
        map.put(0, -1); //累加和为0出现在-1位置
        int sum = 0;
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum - K)) { // k 20 sum 100  找80首次出现的位置
                ans = Math.max(ans, i - map.get(sum - K)); //以i位置结尾的答案
            }
            if (!map.containsKey(sum)) {//只记录sum最早出现的位置即可，不更新
                map.put(sum, i);
            }
        }
        return ans;
    }


    public static int right(int[] arr, int K) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (isValid(arr, i, j, K)) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }

    private static boolean isValid(int[] arr, int l, int r, int K) {
        int sum = 0;
        for (int i = l; i <= r; i++) {
            sum += arr[i];
        }
        return sum == K;
    }

    public static int[] generateArr(int maxLen, int maxVal) {
        int len = (int) (Math.random() * maxLen);
        len = Math.max(len, 10);
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = (int) (Math.random() * maxVal) - (int) (Math.random() * maxVal);
        }
        return res;
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLen = 100;
        int maxVal = 50;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateArr(maxLen, maxVal);
            print(arr);
            int ans1 = right(arr, maxVal);
            int ans2 = process(arr, maxVal);
            if (ans1 != ans2) {
                print(arr);
                System.out.println("fuck");
                break;
            }
        }
        System.out.println("test end");
    }
}
