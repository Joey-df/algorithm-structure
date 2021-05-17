package coding_for_great_offer.class04;

/**
 * 返回一个二维数组中，子矩阵最大累加和
 */
// 本题测试链接 : https://leetcode-cn.com/problems/max-submatrix-lcci/
public class Code03_SubMatrixMaxSum {


    public static int subArrMaxSum(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int ans = arr[0];
        int pre = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int cur = arr[i] + Math.max(pre, 0);
            ans = Math.max(ans, cur);
            pre = cur;
        }
        return ans;
    }

    //子矩阵最大累加和
    public static int subMatrixMaxSum(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) return 0;
        int ans = 0;
        int M = m.length; //行数
        int N = m[0].length; //列数
        for (int i = 0; i < M; i++) {
            int[] sub = new int[N];
            for (int j = i; j < M; j++) {
                for (int k = 0; k < N; k++) {
                    sub[k] += m[j][k];
                }
                //printArr(sub);
                ans = Math.max(ans, subArrMaxSum(sub));
            }
        }
        return ans;
    }

    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, -1, 4};
        System.out.println(subArrMaxSum(arr));
        int[][] m = new int[][]{
                {1, 3, -1, 4},
                {1, 3, -20, 4},
                {1, 3, -100, 4}
        };
        System.out.println(subMatrixMaxSum(m));
        printArr(getMaxMatrix(m));
    }


    // 本题测试链接 : https://leetcode-cn.com/problems/max-submatrix-lcci/
    public static int[] getMaxMatrix(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) return null;
        int N = m.length;//行数
        int M = m[0].length;//列数
        int max = Integer.MIN_VALUE;
        int a = 0;
        int b = 0;//[a,b]左上角
        int c = 0;
        int d = 0;//[c,d]右下角
        for (int i = 0; i < N; i++) {
            int[] sub = new int[M];
            for (int j = i; j < N; j++) {
                int sumCur = 0;
                int beginCol = 0;
                for (int k = 0; k < M; k++) {
                    sub[k] += m[j][k];
                    sumCur += sub[k];
                    if (sumCur > max) {//更新max，同时抓出 左上角 和 右下角 坐标
                        max = sumCur;
                        a = i;
                        b = beginCol;
                        c = j;
                        d = k;
                    }
                    if (sumCur < 0) { //加到0一下，sumCur归0，beginCol重新开始
                        beginCol = k + 1;
                        sumCur = 0;
                    }
                }
            }
        }
        return new int[]{a, b, c, d};
    }
}
