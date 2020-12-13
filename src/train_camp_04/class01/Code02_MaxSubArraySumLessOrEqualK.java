package train_camp_04.class01;

import java.util.TreeSet;

/**
 * 给定一个数组arr，再给定一个k值
 * 返回累加和小于等于k，但是离k最近的子数组累加和
 */
public class Code02_MaxSubArraySumLessOrEqualK {

    //如果[0...i]的前缀和为sum
    //那么: 以i结尾的子数组累加和<=k离k最近的累加和，等价于在[0...j]，j<i，找前缀和>=sum-k的离sum-k最近的前缀和
    public static int maxSubArrSumLEk(int[] arr, int K) {

        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(0); // -1位置的累加和 设为0
        int sum = 0;
        int ans = Integer.MIN_VALUE;
        // 每一步的i，都求子数组必须以i结尾的情况下，求个子数组的累加和，是<=K的，并且是最大的
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            Integer ceiling = treeSet.ceiling(sum - K);
            if (ceiling != null) {
                ans = Math.max(ans, sum - ceiling);
            }
            treeSet.add(sum);
        }
        return ans;
    }

}
