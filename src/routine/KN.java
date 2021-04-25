package routine;

import java.util.HashMap;
import java.util.HashSet;

/*
 *
 * 已知数组中其他数都出现了N次，只有一种数出现了K次
 * 怎么找到出现了K次的数？做到时间复杂度O(N)，额外空间复杂度O(1)
 * 规定：N > 1，K > 0，K < N
 *
 * */

public class KN {

    public static int get(int[] arr, int k, int n) {
        int[] count = new int[32];
        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                if ((num & (1 << i)) != 0) {
                    count[i]++;
                }
            }
        }
        for (int i = 0; i < count.length; i++) {
            count[i] = (count[i] % n) / k;
        }
        int ans = 0;
        for (int i = 0; i < count.length; i++) {
            ans |= (count[i] << i);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr=new int[]{1,2,3,2,4,5,6,7,8,9};
        System.out.println(get(arr,2,1));
    }

}