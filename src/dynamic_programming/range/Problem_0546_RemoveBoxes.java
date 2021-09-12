package dynamic_programming.range;

/**
 * 546. 移除盒子
 * 给出一些不同颜色的盒子，盒子的颜色由数字表示，即不同的数字表示不同的颜色。
 * 你将经过若干轮操作去去掉盒子，直到所有的盒子都去掉为止。
 * 每一轮你可以移除具有相同颜色的连续 k 个盒子（k >= 1），这样一轮之后你将得到 k * k 个积分。
 * 当你将所有盒子都去掉之后，求你能获得的最大积分和。
 *
 * 示例 1：
 * 输入：boxes = [1,3,2,2,2,3,4,3,1]
 * 输出：23
 * 解释：
 * [1, 3, 2, 2, 2, 3, 4, 3, 1]
 * ----> [1, 3, 3, 4, 3, 1] (3*3=9 分)
 * ----> [1, 3, 3, 3, 1] (1*1=1 分)
 * ----> [1, 1] (3*3=9 分)
 * ----> [] (2*2=4 分)
 *
 * 示例 2：
 * 输入：boxes = [1,1,1]
 * 输出：9
 *
 * 示例 3：
 * 输入：boxes = [1]
 * 输出：1
 *
 * 提示：
 * 1 <= boxes.length <= 100
 * 1 <= boxes[i] <= 100
 */
//system_study.class46.Code02_RemoveBoxes
public class Problem_0546_RemoveBoxes {

    public static int removeBoxes(int[] boxes) {
        if (boxes==null || boxes.length==0) return 0;
        int n = boxes.length;
        int[][][] dp = new int[n][n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    dp[i][j][k]=-1;
                }
            }
        }
        return func(boxes, 0, n-1, 0, dp);
    }


    //需要补外部信息才能完成的尝试
    //前提：arr[l,r]范围上前面跟着k个arr[l]的数
    //返回消除[l,r]所有的数，获得的最大得分
    public static int func(int[] arr, int l, int r, int k, int[][][] dp) {
        if (dp[l][r][k]!=-1) return dp[l][r][k];
        int ans;
        if (l>r) {
            ans = 0;
        } else if (l==r) {
            ans = (k+1)*(k+1);
        } else {
            //l<r
            ans = (k + 1) * (k + 1) + func(arr, l + 1, r, 0, dp); //前面k个和arr[l]一起消
            for (int i = l + 1; i <= r; i++) {
                if (arr[i] == arr[l]) {
                    //前面的k个X，和arr[l]的数，合在一起了，现在有k+1个arr[l]的数
                    //k+1表示，i位置之前有k+1个arr[i]（k个+1个arr[l]）
                    int cur = func(arr, l + 1, i - 1, 0, dp) + func(arr, i, r, k + 1, dp);
                    ans = Math.max(ans, cur);
                }
            }
        }
        dp[l][r][k] = ans;
        return ans;
    }

    //最优解
    public static int removeBoxes2(int[] boxes) {
        int N = boxes.length;
        int[][][] dp = new int[N][N][N];
        int ans = process2(boxes, 0, N - 1, 0, dp);
        return ans;
    }

    public static int process2(int[] boxes, int L, int R, int K, int[][][] dp) {
        if (L > R) {
            return 0;
        }
        if (dp[L][R][K] > 0) {
            return dp[L][R][K];
        }
        // 找到一片1，最后1的位置，
        // 1,1,1,1,1,5
        // 3 4 5 6 7 8
        //         !
        int last = L;
        while (last + 1 <= R && boxes[last + 1] == boxes[L]) {
            last++;
        }
        // K个1     (K + last - L) last
        int pre = K + last - L;
        int ans = (pre + 1) * (pre + 1) + process2(boxes, last + 1, R, 0, dp);
        for (int i = last + 2; i <= R; i++) {
            if (boxes[i] == boxes[L] && boxes[i - 1] != boxes[L]) {
                ans = Math.max(ans, process2(boxes, last + 1, i - 1, 0, dp) + process2(boxes, i, R, pre + 1, dp));
            }
        }
        dp[L][R][K] = ans;
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1,3,2,2,2,3,4,3,1};
        System.out.println(removeBoxes(arr));
    }
}
