package dynamic_programming.range;

//486. 预测赢家
//https://leetcode-cn.com/problems/predict-the-winner/
/**
 * 486. 预测赢家
 * 给定一个表示分数的非负整数数组。
 * 玩家 1 从数组任意一端拿取一个分数，随后玩家 2 继续从剩余数组任意一端拿取分数，然后玩家 1 拿，…… 。
 * 每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。最终获得分数总和最多的玩家获胜。
 * 给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。
 *
 * 示例 1：
 * 输入：[1, 5, 2]
 * 输出：False
 * 解释：一开始，玩家1可以从1和2中进行选择。
 * 如果他选择 2（或者 1 ），那么玩家 2 可以从 1（或者 2 ）和 5 中进行选择。如果玩家 2 选择了 5 ，那么玩家 1 则只剩下 1（或者 2 ）可选。
 * 所以，玩家 1 的最终分数为 1 + 2 = 3，而玩家 2 为 5 。
 * 因此，玩家 1 永远不会成为赢家，返回 False 。
 *
 * 示例 2：
 * 输入：[1, 5, 233, 7]
 * 输出：True
 * 解释：玩家 1 一开始选择 1 。然后玩家 2 必须从 5 和 7 中进行选择。无论玩家 2 选择了哪个，玩家 1 都可以选择 233 。
 *      最终，玩家 1（234 分）比玩家 2（12 分）获得更多的分数，所以返回 True，表示玩家 1 可以成为赢家。
 *
 * 提示：
 * 1 <= 给定的数组长度 <= 20.
 * 数组里所有分数都为非负数且不会大于 10000000 。
 * 如果最终两个玩家的分数相等，那么玩家 1 仍为赢家。
 */
//https://leetcode-cn.com/problems/predict-the-winner/solution/bi-jiao-rong-yi-li-jie-de-fang-fa-by-ma-li-ge-bi-d/
//system_study.class18_23.Class18_Code02_CardsInLine
public class Problem_0486_PredictTheWinner {

    //暴力尝试
    public static boolean PredictTheWinner(int[] nums) {
        if (nums == null || nums.length < 1) {
            return false;
        }
        return first(nums, 0, nums.length - 1) >= last(nums, 0, nums.length - 1);
    }

    // 先手方法，在l到r上先手
    private static int first(int[] nums, int l, int r) {
        // 只有一张牌的时候，拿走该牌获得分数
        if (l == r) {
            return nums[l];
        }
        // 拿走最左边的牌(获得分数)，情况转变为在l+1到r上后手，或者拿走最右边的牌(获得分数)，情况转变为在l到r-1上后手，两个值求个最大值
        return Math.max(nums[l] + last(nums, l + 1, r), nums[r] + last(nums, l, r - 1));
    }

    // 后手方法，在l到r上后手
    private static int last(int[] nums, int l, int r) {
        // 只有一张牌的时候，由于是后手，无法获得分数
        if (l == r) {
            return 0;
        }
        // 因为是后手，无法获得分数，所以情况直接变为在l+1到r上转变为先手，或者在l到r-1上先手，由于是后手，分数大的会被对方拿走，所以两个值求个最小值
        return Math.min(first(nums, l + 1, r), first(nums, l, r - 1));
    }

    //观察得知，每个格子first中每个格子依赖last对应格子中，左边 和 下边
    //所以每列从下往上，整体从左往右填好两张表的右上半区 ******
    //因为是经典的范围尝试模型，所以左下半区没用
    public static boolean PredictTheWinner2(int[] nums) {
        if (nums == null || nums.length < 1) {
            return false;
        }
        int n = nums.length;
        int[][] first = new int[n][n];
        int[][] last = new int[n][n];
        for (int r = 0; r < n; r++) {
            first[r][r] = nums[r]; //对角线位置
            // last[r][r] = 0; //因为初始化就是0，所以不用再单独设置
            for (int l = r - 1; l >= 0; l--) {
                first[l][r] = Math.max(nums[l] + last[l + 1][r], nums[r] + last[l][r - 1]);
                last[l][r] = Math.min(first[l + 1][r], first[l][r - 1]);
            }
        }
        return first[0][n - 1] >= last[0][n - 1]; //写等于是为了leetcode上的 test case: [0]
    }

    //观察得知，每个格子first中每个格子依赖last对应格子中，左边 和 下边
    //因为是经典的范围尝试模型，所以左下半区没用
    //按照每条对角线，从左往右依次填好整张表
    public static boolean PredictTheWinner3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int N = arr.length;
        int[][] first = new int[N][N];
        int[][] last = new int[N][N];
        //先填好第一条对角线
        for (int i = 0; i < N; i++) {
            first[i][i] = arr[i];
            //last[i][i] = 0;
        }
        //按照对角线调度
        for (int startCol = 1; startCol < N; startCol++) {
            int L = 0;
            int R = startCol;
            while (R < N) {
                first[L][R] = Math.max(arr[L] + last[L + 1][R], arr[R] + last[L][R - 1]);
                last[L][R] = Math.min(first[L + 1][R], first[L][R - 1]);
                L++;
                R++;
            }
        }
        return first[0][N-1] >= last[0][N-1];
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 233, 7};
        System.out.println(PredictTheWinner(arr));
        System.out.println(PredictTheWinner2(arr));
        System.out.println(PredictTheWinner3(arr));
    }

}
