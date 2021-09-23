package two_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
 * 使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * <p>
 * https://leetcode-cn.com/problems/3sum/
 */
public class Problem_0015_3Sum {

    public static List<List<Integer>> threeSum(int[] arr) {
        List<List<Integer>> ans = new ArrayList<>();
        if (arr == null || arr.length == 0) return ans;
        return threeSum(arr, 0, 0);
    }

    //在arr[start...N-1]区间求和为target的不重复的二元组
    public static List<List<Integer>> twoSum(int[] arr, int start, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (arr == null || arr.length == 0) return ans;
        Arrays.sort(arr);
        int n = arr.length;
        int l = start, r = n - 1;
        while (l < r) {
            if (arr[l] + arr[r] < target) l++;
            else if (arr[l] + arr[r] > target) r--;
            else { //==
                if (l == start || arr[l] != arr[l - 1]) {
                    List<Integer> sub = new ArrayList<>();
                    sub.add(arr[l]);
                    sub.add(arr[r]);
                    ans.add(sub);
                }
                l++;
                r--;
            }
        }
        return ans;
    }

    public static List<List<Integer>> threeSum(int[] arr, int start, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (arr == null || arr.length == 0) return ans;
        Arrays.sort(arr);
        int n = arr.length;
        for (int i = start; i < n; i++) {
            if (i == start || arr[i] != arr[i - 1]) {
                List<List<Integer>> twoSum = twoSum(arr, i + 1, target - arr[i]);
                for (List<Integer> list : twoSum) {
                    list.add(0, arr[i]);
                    ans.add(new ArrayList<>(list));
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums));
    }

}
