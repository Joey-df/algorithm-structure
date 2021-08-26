package array;

/**
 * Given an integer array nums, you need to find one continuous subarray that if you only sort this subarray in ascending order,
 * then the whole array will be sorted in ascending order.
 * <p>
 * Return the shortest such subarray and output its length.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,6,4,8,10,9,15]
 * Output: 5
 * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 * Example 2:
 * <p>
 * Input: nums = [1,2,3,4]
 * Output: 0
 * Example 3:
 * <p>
 * Input: nums = [1]
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 104
 * -105 <= nums[i] <= 105
 */
//最短不需要排序子数组长度
public class Problem_0581_ShortestUnsortedContinuousSubarray {

    //[2,6,4,8,10,9,15]
    public static int minLen(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int N = arr.length;
        //找最右侧不需要排的位置
        int rightNosortIndex = 0;
        int maxLeft = arr[0];
        for (int i = 1; i < N; i++) {
            if (maxLeft <= arr[i]) {
                maxLeft = arr[i];
            } else {
                rightNosortIndex = i;
            }
        }

        //找最左侧不需要排的位置
        int leftNosortIndex = N - 1;
        int minRight = arr[N - 1];
        for (int i = N - 1; i >= 0; i--) {
            if (minRight >= arr[i]) {
                minRight = arr[i];
            } else {
                leftNosortIndex = i;
            }
        }
        if (rightNosortIndex == 0 && leftNosortIndex == N - 1) return 0;
        return rightNosortIndex - leftNosortIndex + 1;
    }

    public static void main(String[] args) {
        System.out.println(minLen(new int[]{1, 2, 3, 4, 5}));
    }
}
