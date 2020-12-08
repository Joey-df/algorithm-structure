package train_camp_03.class06;

/**
 * 一个字符串str，至少切几刀，让切出来的部分全是回文串。
 * 如："12321abakfk"，至少切2刀变成12321、aba、kfk三个回文串。
 * leetcode 132
 * <p>
 * http://chenxii.cn/2020/02/23/D-DataStructureAndAlgorithm/E-ZuoInterview/82-minCut/
 */
public class Code07_PMinParts {

    //将str切分成全是回文串，最少得到几个回文串，减1之后的结果就是刀数
    //递归含义：
    //将str[index...N-1]切分，得到的回文串最小个数
    public static int process(char[] str, int index) {
        if (index == str.length) { //str[N,N-1]范围上切分，得到0个回文串
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int N = str.length;
        for (int cut = index; cut < N; cut++) { //枚举第一刀切的位置
            if (isP(str, index, cut)) {
                ans = Math.min(ans, 1 + process(str, cut + 1));
            }
        }
        return ans;
    }

    public static boolean isP(char[] arr, int l, int r) {
        while (l < r) {
            if (arr[l++] != arr[r--]) {
                return false;
            }
        }
        return true;
    }

    //返回最小分割数
    public static int ways1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        char[] str = s.toCharArray();
        int ans = process(str, 0);
        return ans - 1;
    }


    //dp[i]的含义：
    //str[i...N-1]分割出全是回文的 最少的回文个数
    //返回最小分割数
    public static int dpWays(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        boolean[][] isP = new boolean[N][N]; //用于快速判断str[l,r]范围上是否是回文
        for (int i = 0; i < N; i++) {
            isP[i][i] = true; //第一条对角线
        }
        for (int i = 0; i < N - 1; i++) {
            isP[i][i + 1] = str[i] == str[i + 1];  //第2条对角线
        }
        for (int row = N - 3; row >= 0; row--) {
            for (int col = row + 2; col < N; col++) {
                isP[row][col] = str[row] == str[col] && isP[row + 1][col - 1];
            }
        }

        int[] dp = new int[N + 1];
        dp[N] = 0;//根据base case
        for (int i = N - 1; i >= 0; i--) {
            dp[i] = Integer.MAX_VALUE; //因为每一个位置求最小值，所以初始给系统最大
            for (int end = i; end < N; end++) { //枚举第一刀切的位置
                if (isP[i][end]) {
                    dp[i] = Math.min(dp[i], 1 + dp[end + 1]);
                }
            }
        }
        return dp[0] - 1;
    }

    public static void main(String[] args) {
        String s = "12321TKFKaba";
        System.out.println(ways1(s));
        System.out.println(dpWays(s));
    }
}
