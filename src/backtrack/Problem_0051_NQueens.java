package backtrack;

import java.util.ArrayList;
import java.util.List;

//51. N-Queens
public class Problem_0051_NQueens {

    //主方法
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        if (n < 1) return ans;
        int[] record = new int[n];
        process(0, record, n, ans);
        return ans;
    }

    // 递归含义：
    // 使用一维数组int[] record表示n*n的棋盘
    // record[x] = y 之前的第x行的皇后，放在了y列上
    // 当前来到i行，一共是0~n-1行
    // 潜台词：record[0..i-1]的皇后，任何两个皇后一定都不共行、不共列，不共斜线
    // 在i行上放皇后，所有列都尝试
    // 必须要保证跟之前所有的皇后不打架
    // ans:收集所有摆放方法的答案
    // 返回值是：不关心i以上发生了什么，i.... 后续合理的摆法有多少种
    public static int process(int i, int[] record, int n, List<List<String>> ans) {
        if (i == n) { //i到终止行，此时是一种正确的摆法
            List<String> cur = new ArrayList<>();
            for (int j = 0; j < n; j++) { //收集所有答案
                //cur.add(j + "," + record[j]); //直接收集 第j行的皇后摆在第 record[j] 列
                StringBuilder sb = new StringBuilder();
                for (int k = 0; k < n; k++) {
                    sb.append(record[j] == k ? "Q" : ".");
                }
                cur.add(sb.toString());
            }
            ans.add(cur);
            return 1;
        }
        //i没到终止行，还需要摆皇后
        int res = 0;
        // i行的皇后，放哪一列呢？j列，0～n-1列都尝试
        for (int j = 0; j < n; j++) {
            if (isValid(record, i, j)) {
                record[i] = j;
                res += process(i + 1, record, n, ans);
            }
        }
        return res;
    }

    //判断当前i行的皇后摆在j列，是否和0～i-1行的皇后打架
    //需要判断的是record[0..i-1]，这些行的皇后
    public static boolean isValid(int[] record, int i, int j) {
        // 判断的行范围：0..i-1，
        for (int k = 0; k < i; k++) { // (i,j) 与 (k,record[k])
            if (i == k // 共行判断，这个条件其实不用判断，因为不在同一行
                    || j == record[k] //表示共列
                    || Math.abs(record[k] - j) == Math.abs(i - k) //表示共斜线
            ) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 4;
        List<List<String>> list = solveNQueens(n);
        System.out.println(list);

    }

}
