package train_camp_03.class06;

/**
 * 给定一个只由 0(假)、1(真)、&(逻辑与)、|(逻辑或)和^(异或)五种字符组成 的字符串express，再给定一个布尔值 desired。
 * 返回express能有多少种组合 方式，可以达到desired的结果。
 * 【举例】
 * express="1^0|0|1"，desired=false
 * 只有 1^((0|0)|1)和 1^(0|(0|1))的组合可以得到 false，返回 2。 express="1"，desired=false
 * 无组合则可以得到false，返回0
 * <p>
 * 范围上的尝试模型
 */
public class Code02_ExpressionNumber {

    //express串有效性检查
    private static boolean isValid(String express) {
        //长度如果为偶数，无效
        if (express == null || express.length() == 0 || (express.length() & 1) == 0) {
            return false;
        }
        for (int i = 0; i < express.length(); i++) {
            char c = express.charAt(i);
            if ((i & 1) == 0) {//偶数位必须为0 1
                if (c != '0' && c != '1') {
                    return false;
                }
            } else {
                if (c != '|' && c != '^' && c != '&') {
                    return false;
                }
            }
        }
        return true;
    }

    //暴力递归
    public static int expressNumsWays1(String express, boolean desired) {
        if (!isValid(express)) {
            return 0;
        }

        return process(express.toCharArray(), desired, 0, express.length() - 1);
    }

    //递归含义：
    //express[l,r]范围上 想要得到desired的结果 的方法数？
    //潜台词：l、r必压中0或1(偶数位置)
    //1^0|0|1
    //0123456
    private static int process(char[] exp, boolean desired, int l, int r) {
        //base case: [l,r]范围上只有一个字符了
        if (l == r) {
            if (exp[l] == '1') {
                return desired ? 1 : 0;
            } else { //'0'
                return desired ? 0 : 1;
            }
        }
        //l<r
        int ways = 0; //方法数
        for (int i = l + 1; i < r; i += 2) { //枚举每一个 与或非 的位置(最后结合的位置)
            if (desired) {
                switch (exp[i]) {
                    case '|':
                        ways += process(exp, true, l, i - 1) * process(exp, false, i + 1, r)
                                + process(exp, false, l, i - 1) * process(exp, true, i + 1, r)
                                + process(exp, true, l, i - 1) * process(exp, true, i + 1, r);
                        break;
                    case '&':
                        ways += process(exp, true, l, i - 1) * process(exp, true, i + 1, r);
                        break;
                    case '^':
                        ways += process(exp, true, l, i - 1) * process(exp, false, i + 1, r)
                                + process(exp, false, l, i - 1) * process(exp, true, i + 1, r);
                        break;
                }
            } else { //false
                switch (exp[i]) {
                    case '|':
                        ways += process(exp, false, l, i - 1) * process(exp, false, i + 1, r);
                        break;
                    case '&':
                        ways += process(exp, false, l, i - 1) * process(exp, false, i + 1, r)
                                + process(exp, false, l, i - 1) * process(exp, true, i + 1, r)
                                + process(exp, true, l, i - 1) * process(exp, false, i + 1, r);
                        break;
                    case '^':
                        ways += process(exp, true, l, i - 1) * process(exp, true, i + 1, r)
                                + process(exp, false, l, i - 1) * process(exp, false, i + 1, r);
                        break;
                }
            }
        }
        return ways;
    }

    // 改动态规划
    public static int dpWay(String express, boolean desired) {
        if (!isValid(express)) {
            return 0;
        }

        char[] str = express.toCharArray();
        int N = str.length;
        int[][] tMap = new int[N][N];
        int[][] fMap = new int[N][N];

        //范围上尝试模型，N*N方阵，左下半区没用
        //先填对角线 根据base case
        for (int i = 0; i < N; i += 2) { //只有偶数行偶数列才有效
            tMap[i][i] = str[i] == '1' ? 1 : 0;
            fMap[i][i] = str[i] == '0' ? 1 : 0;
        }

        //填普遍位置
        //从左往右，再依次从下往上 返回右上角的格子
        for (int row = N - 3; row >= 0; row -= 2) { //N-1行填过了，N-2行是逻辑运算符，所以从N-3开始，每次-2
            for (int col = row + 2; col < N; col += 2) {
                for (int i = row + 1; i < col; i += 2) { //[row,col]范围
                    switch (str[i]) {
                        case '|':
                            tMap[row][col] += tMap[row][i - 1] * fMap[i + 1][col]
                                    + fMap[row][i - 1] * tMap[i + 1][col]
                                    + tMap[row][i - 1] * tMap[i + 1][col];
                            break;
                        case '&':
                            tMap[row][col] += tMap[row][i - 1] * tMap[i + 1][col];
                            break;
                        case '^':
                            tMap[row][col] += tMap[row][i - 1] * fMap[i + 1][col]
                                    + fMap[row][i - 1] * tMap[i + 1][col];
                            break;
                    }

                    switch (str[i]) {
                        case '|':
                            fMap[row][col] += fMap[row][i - 1] * fMap[i + 1][col];
                            break;
                        case '&':
                            fMap[row][col] += fMap[row][i - 1] * fMap[i + 1][col]
                                    + fMap[row][i - 1] * tMap[i + 1][col]
                                    + tMap[row][i - 1] * fMap[i + 1][col];
                            break;
                        case '^':
                            fMap[row][col] += tMap[row][i - 1] * tMap[i + 1][col]
                                    + fMap[row][i - 1] * fMap[i + 1][col];
                            break;
                    }
                }
            }
        }

        return desired ? tMap[0][N - 1] : fMap[0][N - 1];
    }


    public static void main(String[] args) {
        String exp = "1^0&0|1&1^0&0^1|0|1&1";
        System.out.println(expressNumsWays1(exp, true));
        System.out.println(dpWay(exp, true));
    }

}
