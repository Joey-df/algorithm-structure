package train_camp_04.class02;

/**
 * 给定一个矩阵matrix，先从左上角开始，每一步只能往右或者往下走，走到右下角。
 * 然后从右下角出发，每一步只能往上或者往左走，再回到左上角。任何一个位置的数字，只能获得一遍。返回最大路径和。
 */
public class Code06_CherryPickup {

    //A,B两个人共同从[Ar,Ac]、 [Br,Ar+Ac-Br]同步走
    //每一步只能往右或者往下走
    //走到相同的位置时，上面的数字只收集一次
    //走到右下角的最大路径和
    //A、B一定会同走到右下角
    public static int process(int[][] matrix, int Ar, int Ac, int Br, int[][][] dp) {
        int N = matrix.length;
        int M = matrix[0].length;
        if (dp[Ar][Ac][Br] != 0) {
            return dp[Ar][Ac][Br];
        }
        //base case: A B同时到了右下角
        if (Ar == N - 1 && Ac == M - 1 && Ar == Br) {
            dp[Ar][Ac][Br] = matrix[N - 1][M - 1];
            return matrix[N - 1][M - 1];//相同位置只收集一份数据
        }
        int Bc = Ar + Ac - Br;
        //没有同步走到右下角
        //A 下 B 下
        int ADownBDown = Integer.MIN_VALUE;
        if (Ar + 1 < N && Br + 1 < N && matrix[Ar + 1][Ac] != -1 && matrix[Br + 1][Bc] != -1) {
            ADownBDown = process(matrix, Ar + 1, Ac, Br + 1, dp);
        }
        //A 下 B 右
        int ADownBRight = Integer.MIN_VALUE;
        if (Ar + 1 < N && Bc + 1 < M && matrix[Ar + 1][Ac] != -1 && matrix[Br][Bc + 1] != -1) {
            ADownBRight = process(matrix, Ar + 1, Ac, Br, dp);
        }
        //A 右 B 下
        int ARightBDown = Integer.MIN_VALUE;
        if (Ac + 1 < M && Br + 1 < N && matrix[Ar][Ac + 1] != -1 && matrix[Br + 1][Bc] != -1) {
            ARightBDown = process(matrix, Ar, Ac + 1, Br + 1, dp);
        }
        //A 右 B 右
        int ARightBRight = Integer.MIN_VALUE;
        if (Ac + 1 < M && Bc + 1 < M && matrix[Ar][Ac + 1] != -1 && matrix[Br][Bc + 1] != -1) {
            ARightBRight = process(matrix, Ar, Ac + 1, Br, dp);
        }
        int next = Math.max(Math.max(ADownBDown, ADownBRight), Math.max(ARightBDown, ARightBRight));
        if (Ar == Br) { //A、B共位置的情况（非右下角）
            dp[Ar][Ac][Br] = next + matrix[Ar][Ac];
            return next + matrix[Ar][Ac];
        }
        //Ar!=Br
        dp[Ar][Ac][Br] = next + matrix[Ar][Ac] + matrix[Br][Bc];
        return next + matrix[Ar][Ac] + matrix[Br][Bc];
    }

    public static int ways(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int N = matrix.length;
        int M = matrix[0].length;
        int[][][] dp = new int[N][M][N];
        int ans = process(matrix, 0, 0, 0, dp);
        return Math.max(0, ans);
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 1, 1, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 1, 1},
        };
        System.out.println(ways(matrix));
    }
}
