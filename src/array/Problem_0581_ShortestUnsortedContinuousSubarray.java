package array;

/**
 * 581. 最短无序连续子数组
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 * 请你找出符合题意的 最短 子数组，并输出它的长度。
 *
 * 示例 1：
 * 输入：nums = [2,6,4,8,10,9,15]
 * 输出：5
 * 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 * 示例 2：
 * 输入：nums = [1,2,3,4]
 * 输出：0
 * 示例 3：
 * 输入：nums = [1]
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
        System.out.println(rightNosortIndex);
        System.out.println(leftNosortIndex);
        if (rightNosortIndex == 0 && leftNosortIndex == N - 1) return 0;
        return rightNosortIndex - leftNosortIndex + 1;
    }

    public static void main(String[] args) {
        System.out.println(minLen(new int[]{2,6,4,8,10,9,15}));
    }
}
