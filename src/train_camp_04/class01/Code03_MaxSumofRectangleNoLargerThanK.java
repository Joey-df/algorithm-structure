package train_camp_04.class01;

import java.util.TreeSet;

/**
 * 给定一个二维数组matrix，再给定一个k值
 * 返回累加和小于等于k，但是离k最近的子矩阵累加和
 */
public class Code03_MaxSumofRectangleNoLargerThanK {

    public static int process(int[][] matrix, int k) {

        int N = matrix.length;
        int M = matrix[0].length;
        //0...0
        //0...1
        //0...N-1
        //1...1
        //1...2
        //1...N-1
        //2...2
        //2...3
        //2...N-1
        int ans = Integer.MIN_VALUE;
        TreeSet<Integer> sumSet = new TreeSet<>();

        for (int s = 0; s < N; s++) {//枚举每个子矩阵的开始行s
            int[] colSum = new int[M];
            for (int e = s; e < N; e++) { // e结束行
                // 子矩阵必须包含s~e行的数，且只包含s~e行的数
                sumSet.add(0);
                int rowSum = 0;
                for (int c = 0; c < M; c++) {
                    colSum[c] += matrix[e][c];
                    rowSum += colSum[c];
                    Integer it = sumSet.ceiling(rowSum - k);
                    if (it != null) {
                        ans = Math.max(ans, rowSum - it);
                    }
                    sumSet.add(rowSum);
                }
                sumSet.clear();
            }
        }
        return ans;
    }
}
