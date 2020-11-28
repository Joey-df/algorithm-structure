package train_camp_03.class01;

/**
 * 给定一个N*N的矩阵matrix，只有0和1两种值，返回边框全是1的最大正方形的边长长度。
 * 例如:
 * 01111
 * 01001
 * 01001
 * 01111
 * 01011
 * 其中边框全是1的最大正方形的大小为4*4，所以返回4。
 * <p>
 * https://leetcode-cn.com/problems/largest-1-bordered-square/
 * 书上Page406: 边长为1的最大正方形大小
 * 最优解复杂度 O(N^3)
 * 使用预处理数组
 */
// 前置知识：
// 求一个正方形矩阵中的长方形个数O(N^4)
//      两个点(a,b)唯一确定一个长方形 点的个数O(N^2) 相乘 所以是 O(N^4)
// 求一个正方形矩阵中的正方形个数O(N^3)
//      左上角点唯一确定一个正方形 枚举左上角点的个数O(N^2) 正方形的边长O(N) 相乘 所以是O(N^3)
// 所以这道题的极限是O(N^3)
public class Code05_MaxOneBorderSize {

    //分析：每个点需要知道它右边1的个数，以及它下边1的个数
    //有了这个辅助结构之后，O(1)判断
    //求每一个点右边连续1的个数
    public static void getRightHelp(int[][] matrix, int[][] rightHelp) {
        //先生成最后一列
        //左<-右 依次生成
        int N = matrix.length;
        int M = matrix[0].length;
        for (int i = 0; i < N; i++) { //先生成最后一列
            rightHelp[i][M - 1] = matrix[i][M - 1] == 1 ? 1 : 0;
        }
        for (int i = 0; i < N; i++) { //控制行
            for (int j = M - 2; j >= 0; j--) { //控制列
                rightHelp[i][j] = matrix[i][j] == 0 ? 0 : rightHelp[i][j + 1] + 1;
            }
        }
    }

    //求每一个点下边连续1的个数
    public static void getDownHelp(int[][] matrix, int[][] downHelp) {
        //先生成最后一行
        //从左往右，再整体从下往上
        int N = matrix.length;
        int M = matrix[0].length;
        for (int i = 0; i < M; i++) {
            downHelp[N - 1][i] = matrix[N - 1][i] == 0 ? 0 : 1;
        }
        for (int i = N - 2; i >= 0; i--) {
            for (int j = 0; j < M; j++) {
                downHelp[i][j] = matrix[i][j] == 0 ? 0 : downHelp[i + 1][j] + 1;
            }
        }
    }


    public static int process(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int N = matrix.length;
        int M = matrix[0].length;
        int[][] rightHelp = new int[N][M];
        int[][] downHelp = new int[N][M];
        getDownHelp(matrix, downHelp);
        getRightHelp(matrix, rightHelp);

        for (int size = Math.min(N, M); size != 0; size--) {
            if (hasSizeOfBorder(size, rightHelp, downHelp)) {
                return size;
            }
        }
        return 0;
    }

    public static boolean hasSizeOfBorder(int size, int[][] right, int[][] down) {
        for (int i = 0; i != right.length - size + 1; i++) {
            for (int j = 0; j != right[0].length - size + 1; j++) {
                if (right[i][j] >= size && down[i][j] >= size
                        && right[i + size - 1][j] >= size
                        && down[i][j + size - 1] >= size) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int[][] generateRandom01Matrix(int rowSize, int colSize) {
        int[][] res = new int[rowSize][colSize];
        for (int i = 0; i != rowSize; i++) {
            for (int j = 0; j != colSize; j++) {
                res[i][j] = (int) (Math.random() * 2);
            }
        }
        return res;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = generateRandom01Matrix(5, 5);
        printMatrix(matrix);
        int N = matrix.length;
        int M = matrix[0].length;
        int[][] rightHelp = new int[N][M];
        int[][] downHelp = new int[N][M];
        getDownHelp(matrix, downHelp);
        getRightHelp(matrix, rightHelp);
        System.out.println("==============");
        printMatrix(downHelp);
        System.out.println("==============");
        printMatrix(rightHelp);
        System.out.println("==============");

        System.out.println(process(matrix));
    }
}
