package train_camp_03.class04;

/**
 * 给定一个整型矩阵，返回子矩阵的最大累加和。
 */
public class Code07_SubMatrixMaxSum {

    public static int maxSum(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            throw new RuntimeException("matrix is null");
        }
        //0...0
        //0...1
        //0...2
        //0...N-1
        //1...1
        //1...2
        //1...N-1
        //...
        //N-1...N-1
        int cur = 0;
        int max = Integer.MIN_VALUE;
        int[] arr = null;
        for (int i = 0; i < matrix.length; i++) { //开始行的行号i
            arr = new int[matrix[0].length];
            for (int j = i; j < matrix.length; j++) { //结束行的的行号i,i~j是我们的讨论范围
                cur = 0; //这句很重要
                for (int k = 0; k < matrix[0].length; k++) {
                    arr[k] += matrix[j][k];
                    cur += arr[k];
                    max = Math.max(max, cur);
                    cur = Math.max(cur, 0);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {-90, 48, 78},
                {64, -40, 64},
                {-81, -7, 66}};
        System.out.println(maxSum(matrix));
    }
}
