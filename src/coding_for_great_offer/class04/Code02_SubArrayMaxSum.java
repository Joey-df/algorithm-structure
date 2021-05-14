package coding_for_great_offer.class04;

/**
 * 返回一个数组中，子数组最大累加和
 * leetcode 53题
 */
public class Code02_SubArrayMaxSum {

    //遇到子数组
    //思考问题方式：每个位置结尾如何如何
    public static int maxSum(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int ans = arr[0];
        int pre = arr[0];//i-1位置结尾的最大子数组累加和存在pre中
        for (int i = 1; i < arr.length; i++) {
            int cur = arr[i] + (Math.max(pre, 0));
            ans = Math.max(ans, cur);
            pre = cur;
        }
        return ans;
    }
}
