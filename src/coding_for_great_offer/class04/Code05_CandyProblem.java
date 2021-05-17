package coding_for_great_offer.class04;

/**
 * 原问题：leetcode135题 分糖果
 * https://leetcode.com/problems/candy/
 * 给定分数数组arr，表示每个孩子的得分
 * 每个孩子至少分到1颗糖，相邻孩子得分高的必须得到更多的糖
 * <p>
 * 进阶问题：在原问题的基础上，增加一个原则：
 * 相邻的孩子间如果分数一样，分的糖果数必须一样
 * 返回至少需要分多少糖
 * <p>
 * 贪心 + 预处理结构
 */
public class Code05_CandyProblem {

    //给定分数数组arr，表示每个孩子的得分
    //每个孩子至少分到1颗糖，相邻孩子得分高的必须得到更多的糖
    //返回满足条件最少的糖数
    public static int candy(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        if (arr.length == 1) return 1;
        int N = arr.length;
        int[] left = new int[N];
        left[0] = 1;
        for (int i = 1; i < N; i++) {
            left[i] = arr[i] > arr[i - 1] ? left[i - 1] + 1 : 1;
        }

        int[] right = new int[N];
        right[N - 1] = 1;
        for (int j = N - 2; j >= 0; j--) {
            right[j] = arr[j] > arr[j + 1] ? right[j + 1] + 1 : 1;
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans += Math.max(left[i], right[i]);
        }
        return ans;
    }


    // 进阶问题：在原问题的基础上，增加一个原则：
    // 相邻的孩子间如果分数一样，分的糖果数必须一样
    // 返回至少需要分多少糖
    public static int followUp(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        if (arr.length == 1) return 1;
        int N = arr.length;
        int[] left = new int[N];
        left[0] = 1;
        for (int i = 1; i < N; i++) {
            left[i] = arr[i] >= arr[i - 1] ? left[i - 1] + 1 : 1;
        }

        int[] right = new int[N];
        right[N - 1] = 1;
        for (int j = N - 2; j >= 0; j--) {
            right[j] = arr[j] >= arr[j - 1] ? right[j + 1] + 1 : 1;
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans += Math.max(left[i], right[i]);
        }
        return ans;
    }

}
