package sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given a list of non-negative integers nums, arrange them such that they form the largest number.
 * <p>
 * Note: The result may be very large, so you need to return a string instead of an integer.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [10,2]
 * Output: "210"
 * Example 2:
 * <p>
 * Input: nums = [3,30,34,5,9]
 * Output: "9534330"
 * Example 3:
 * <p>
 * Input: nums = [1]
 * Output: "1"
 * Example 4:
 * <p>
 * Input: nums = [10]
 * Output: "10"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 109
 */
public class Problem_179_Largest_Number {

    public static String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) return "";
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2 + o1).compareTo(o1 + o2); // 3 30
            }
        });
        if (strs[0].startsWith("0")) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(s);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(largestNumber(new int[]{3, 30, 34, 5, 9}));
    }
}
