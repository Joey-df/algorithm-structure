package train_camp_04.class03;

/**
 * 已知数组中其他数都出现了N次，只有一种数出现了K次
 * 怎么找到出现了K次的数？做到时间复杂度O(N)，额外空间复杂度O(1)
 * 规定：N > 1，K > 0，K < N
 * <p>
 * https://github.com/algorithmzuo/leetcode-top-interview-questions/blob/master/src/followup/KN.java
 */
public class Code05_FollowUp_KTimeNTimes {

    public static int findKTimesNum(int[] arr, int N, int K) {
        int[] help = new int[32]; //arr[0]位是最低位，arr[31]位是符号位
        for (int curNum : arr) {
            for (int j = 0; j < 32; j++) { //符号位也考虑在内
                if ((curNum & (1 << j)) != 0) { //提取出curNum每一位的状态 0 or 1
                    help[j]++;
                }
            }
        }
        for (int i = 0; i < help.length; i++) {
            help[i] = (help[i] % N) / K;
        }

        int ans = 0;
        for (int i = 0; i < help.length; i++) {
            ans |= (help[i] << i);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 1, 1, 5, -2, -2, 5, -2, -2, 3, 3, 3, 3, 4, 4, 4, 4};
        System.out.println(findKTimesNum(arr, 4, 2));
    }
}
