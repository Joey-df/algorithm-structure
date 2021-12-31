package coding_for_great_offer.class10;

/**
 * 布尔运算
 * 给定一个只由 0(假)、1(真)、&(逻辑与)、|(逻辑或)和^(异或)五种字符组成 的字符串express，再给定一个布尔值 desired。
 * 返回express能有多少种组合 方式，可以达到desired的结果。
 * 【举例】
 * express="1^0|0|1"，desired=false
 * 只有 1^((0|0)|1)和 1^(0|(0|1))的组合可以得到 false，返回 2。 express="1"，desired=false
 * 无组合则可以得到false，返回0
 */
//Leetcode原题：
//https://leetcode-cn.com/problems/boolean-evaluation-lcci/
//范围上的尝试模型
public class Code05_BooleanEvaluation {

    public static class Info {
        int t;
        int f;

        public Info(int t, int f) {
            this.t = t;
            this.f = f;
        }
    }

    //递归含义
    //str[L,R]范围上计算结果是 true / false的方法数分别是多少,存在Info中
    //潜台词：L、R必须压中奇数位置的0或1
    public static Info process(char[] str, int L, int R, Info[][] dp) {
        if (dp[L][R] != null) return dp[L][R];
        if (L == R) {
            int t = str[L] == '1' ? 1 : 0;
            int f = str[L] == '0' ? 1 : 0;
            dp[L][R] = new Info(t, f);
            return dp[L][R];
        }
        //L<R && [L,R]>=3
        //举例：1^0|0|1
        int t = 0;
        int f = 0;
        for (int i = L + 1; i < R; i += 2) { //枚举所有逻辑符号的位置(作为最后结合的位置)
            Info lInfo = process(str, L, i - 1, dp);
            Info rInfo = process(str, i + 1, R, dp);
            int a = lInfo.t;
            int b = lInfo.f;
            int c = rInfo.t;
            int d = rInfo.f;
            switch (str[i]) {
                case '|':
                    t += a * c + a * d + b * c;
                    f += b * d;
                    break;
                case '^':
                    t += a * d + b * c;
                    f += a * c + b * d;
                    break;
                case '&':
                    t += a * c;
                    f += b * c + b * d + a * d;
                    break;
            }
        }
        dp[L][R] = new Info(t, f);
        return dp[L][R];
    }

    public static int countEval(String s, int result) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        Info[][] dp = new Info[N][N];
        Info info = process(str, 0, N - 1, dp);
        return result == 1 ? info.t : info.f;
    }

    public static void main(String[] args) {
        String s = "0&0&0&1^1|0";
        System.out.println(countEval(s, 0));
    }

}
