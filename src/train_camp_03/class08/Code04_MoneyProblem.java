package train_camp_03.class08;

/**
 * int[] d，d[i]：i号怪兽的能力
 * int[] p，p[i]：i号怪兽要求的钱
 * 开始时你的能力是0，你的目标是从0号怪兽开始，通过所有的怪兽。
 * 如果你当前的能力，小于i号怪兽的能力，你必须付出p[i]的钱，贿赂这个怪兽，然后怪兽就会加入你，他的能力直接累加到你的能力上；
 * 如果你当前的能力，大于等于i号怪兽的能力，你可以选择直接通过，你的能力并不会下降，
 * 你也可以选择贿赂这个怪兽，然后怪兽就会加入你，他的能力直接累加到你的能力上。
 * 返回通过所有的怪兽，需要花的最小钱数。
 *
 * 好题 多复习
 */
public class Code04_MoneyProblem {

    //int[] d，d[i]：i号怪兽的能力
    //int[] p，p[i]：i号怪兽要求的钱
    //ability: 当前已经具有的能力值
    //index: 当前已经来到index位置
    //递归含义：从index开始到结尾需要花的最小钱数？
    public static int process(int[] d, int[] p, int ability, int index) {
        if (index == d.length) {
            return 0;
        }
        //没有到终止位置
        if (ability < d[index]) {
            // 如果你当前的能力，小于i号怪兽的能力，你必须付出p[i]的钱，贿赂这个怪兽，
            // 然后怪兽就会加入你，他的能力直接累加到你的能力上
            return p[index] + process(d, p, ability + d[index], index + 1);
        }
        //如果你当前的能力，大于等于i号怪兽的能力，你可以贿赂 也可以不贿赂
        int p1 = p[index] + process(d, p, ability + d[index], index + 1);//贿赂
        int p2 = process(d, p, ability, index + 1);//不贿赂
        return Math.min(p1, p2);
    }

    //int[] d，d[i]：i号怪兽的能力
    //int[] p，p[i]：i号怪兽要求的钱
    //改动态规划
    //dp[i][j]的含义:
    //当前的能力值为j，从i开始到结尾 花的最小钱数
    //适用与能力值数组值较小的情况
    public static long func2(int[] d, int[] p) {
        //从暴力递归改动态规划
        //通过观察，index依赖index+1 即i行依赖下面的行
        //从右往左，整体从下往上，最后返回左上角的值
        int sum = 0;
        for (int i : d) {
            sum += i;
        }
        int N = d.length;
        int[][] dp = new int[N + 1][sum + 1];
        for (int j = 0; j < sum + 1; j++) {
            dp[N][j] = 0; //根据base case
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int j = sum; j >= 0; j--) {
                if (j + d[i] > sum) {
                    continue;
                }
                if (j < d[i]) {
                    dp[i][j] = p[i] + dp[i + 1][j + d[i]];
                } else {
                    dp[i][j] = Math.min(p[i] + dp[i + 1][j + d[i]], dp[i + 1][j]);
                }
            }
        }
        return dp[0][0];
    }

    //适用于p[i]：i号怪兽要求的钱 值比较小的情况
    //dp[i][j]的含义：
    //从0号一直通过到i号怪兽，严格花够j元，获得的最大能力值是多少？
    //返回最后一行，第一个不为-1的下标j，即为最终结果
    public static int func3(int[] d, int[] p) {
        int sum = 0; //钱数和
        for (int i : p) {
            sum += i;
        }
        int N = d.length;
        int[][] dp = new int[N][sum + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < sum + 1; j++) {
                dp[i][j] = -1;
            }
        }
        //第一行：仅通过0号怪兽，严格花够j元获得最大能力
        for (int j = 0; j < sum + 1; j++) {
            // 经过0～i的怪兽，花钱数一定为p[0]，达到武力值d[0]的地步。其他第0行的状态一律是无效的
            dp[0][j] = (p[0] == j) ? d[0] : -1;
        }
        //第一列：通过[0,i]怪兽，严格花够0元获得的最大能力
        for (int i = 1; i < N; i++) {
            dp[i][0] = -1;
        }
        //普遍位置 从左往右，再依次从上往下
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < sum + 1; j++) {
                // 可能性一，为当前怪兽花钱
                // 存在条件：
                // j - p[i]要不越界，并且在钱数为j - p[i]时，要能通过0～i-1的怪兽，并且钱数组合是有效的。
                if (j - p[i] >= 0 && dp[i - 1][j - p[i]] != -1) {
                    dp[i][j] = dp[i - 1][j - p[i]] + d[i];
                }
                // 可能性二，不为当前怪兽花钱
                // 存在条件：
                // 0~i-1怪兽在花钱为j的情况下，能保证通过当前i位置的怪兽
                if (dp[i - 1][j] >= d[i]) {
                    // 两种可能性中，选武力值最大的
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }
            }
        }
        int ans = -1;
        for (int i = 0; i < sum + 1; i++) {
            if (dp[N - 1][i] != -1) {
                ans = i;
                break;
            }
        }
        return ans;
    }

    public static int[][] generateTwoRandomArray(int len, int value) {
        int size = (int) (Math.random() * len) + 1;
        int[][] arrs = new int[2][size];
        for (int i = 0; i < size; i++) {
            arrs[0][i] = (int) (Math.random() * value) + 1;
            arrs[1][i] = (int) (Math.random() * value) + 1;
        }
        return arrs;
    }

    public static void main(String[] args) {
        int len = 10;
        int value = 20;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int[][] arrs = generateTwoRandomArray(len, value);
            int[] d = arrs[0];
            int[] p = arrs[1];
            long ans1 = process(d, p,0,0);
            long ans2 = func2(d, p);
            long ans3 = func3(d, p);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("oops!");
            }
        }

    }

}
