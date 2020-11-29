package train_camp_03.class02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个有序数组arr，给定一个正数aim
 * 1）返回累加和为aim的，所有不同二元组
 * 2）返回累加和为aim的，所有不同三元组
 * https://leetcode.com/problems/3sum/
 */
public class Code06_PrintUniquePairAndTriad {

    // 给定数组nums[begin,N-1]范围上
    // 生成累加和为aim的最有二元组
    // [-1,0,1,2,-1,-4], aim=-2
    // [-4,-1,-1,0,1,2]
    // [-1,-1],[2,-4]
    public static List<List<Integer>> getUniquePair(int[] nums, int begin, int aim) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 2) {
            return ans;
        }
        Arrays.sort(nums);
        int l = begin;
        int r = nums.length - 1;
        while (l < r) {
            if (nums[l] + nums[r] < aim) {
                l++;
            } else if (nums[l] + nums[r] > aim) {
                r--;
            } else { //nums[l]+nums[r] == aim
                if (l == begin || nums[l] != nums[l - 1]) {
                    List<Integer> sub = new ArrayList<>();
                    sub.add(nums[l]);
                    sub.add(nums[r]);
                    ans.add(sub);
                }
                l++;
                r--;
            }
        }
        return ans;
    }


    public static List<List<Integer>> getAllTrid(int[] nums, int aim) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return ans;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                //add
                List<List<Integer>> pairs = getUniquePair(nums, i + 1, aim - nums[i]);
                for (int j = 0; j < pairs.size(); j++) {
                    List<Integer> sub = pairs.get(j);
                    sub.add(nums[i]);
                    ans.add(sub);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 0, 0, 2, 2};
        int aim = 0;
        System.out.println(getAllTrid(nums, aim));
    }
}
