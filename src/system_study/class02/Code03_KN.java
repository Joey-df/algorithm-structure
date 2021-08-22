package system_study.class02;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 一个数组中有一种数出现K次，其他数都出现了N次，
 * N > 1,  K < N
 * 找到，出现了K次的数，
 * 要求，额外空间复杂度O(1)，时间复杂度O(N)
 */
//leetcode137
//只有一种数出现了1次，其余数都出现了3次，请找出出现一次的数
//routine.KN
public class Code03_KN {

    //用于验证的方法
    public static int get1(int[] arr, int k, int n) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }
        for (Integer key : map.keySet()) {
            if (map.get(key) == k) {
                return key;
            }
        }
        return -1;
    }

    public static int get2(int[] arr, int k, int n) {
        int[] count = new int[32];
        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                // 第二种写法：
                //if (((num >> i) & 1) != 0) {
                if ((num & (1 << i)) != 0) { //取出num第i位的状态是0还是1
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

    //随机生成数组
    //总共type种数
    //只有一种数出现了k次，其他数都出现了n次 (保证k<n)
    public static int[] randomArray(int k, int n, int type) {
        HashSet<Integer> set = new HashSet<>();
        //生成type-1种数
        while (set.size() != type - 1) {
            set.add((int) (Math.random() * 10000) - (int) (Math.random() * 10000));
        }
        int knum = 0;
        do {
            knum = (int) (Math.random() * 10000) - (int) (Math.random() * 10000);
        } while (set.contains(knum));
        int[] arr = new int[(type - 1) * n + k];//一种数出现k次，其他数均出现n次，总共type种数 (输入保证k<n)
        int index = 0;
        for (int num : set) {
            for (int i = 0; i < n; i++) {
                arr[index++] = num;
            }
        }
        for (int i = 0; i < k; i++) {
            arr[index++] = knum;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return arr;
    }

    public static boolean check(int[] arr, int k, int n, int type) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }
        int kcount = 0;
        int ncount = 0;
        for (Integer key : map.keySet()) {
            if (map.get(key) == n) {
                ncount++;
            } else if (map.get(key) == k) {
                kcount++;
            } else {
                return false;
            }
        }
        return map.size() == type && kcount == 1 && ncount == type - 1;
    }

    public static void main(String[] args) {
        int knMax = 20;
        int typeMax = 50;
        int testTimes = 100000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int k = 0;
            int n = 0;
            do {
                k = (int) (Math.random() * knMax) + 1;
                n = (int) (Math.random() * knMax) + 2;
            } while (k >= n); //保证k<n
            int type = (int) (Math.random() * typeMax) + 5;
            int[] arr = randomArray(k, n, type);
            if (!check(arr, k, n, type)) {
                System.out.println("random arr error!");
            }
            int ans1 = get1(arr, k, n);
            int ans2 = get2(arr, k, n);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
    }

}