package coding_for_great_offer.class08;

/**
 * 给定一个char[][] matrix，也就是char类型的二维数组，再给定一个字符串word，
 * 可以从任何一个某个位置出发，可以走上下左右，能不能找到word？
 * char[][] m = {  { 'a', 'b', 'z' }, 
 * { 'c', 'd', 'o' }, 
 * { 'f', 'e', 'o' }  }
 * 设定1：可以走重复路的情况下，返回能不能找到
 * 比如，word = "zoooz"，是可以找到的，z -> o -> o -> o -> z，因为允许走一条路径中已经走过的字符
 * 设定2：不可以走重复路的情况下，返回能不能找到
 * 比如，word = "zoooz"，是不可以找到的，因为允许走一条路径中已经走过的字符不能重复走 
 */
//leetcode 79题
//https://leetcode.com/problems/word-search
public class Code03_FindWordInMatrix {

    //递归含义：
    //word中0～k-1已经搞定了，不用操心了！
    //当前来到word[k]
    //从m[i][j]出发，能否走出word[k...],返回
    public static boolean work(char[][] m, int i, int j, char[] word, int k) {
        if (k == word.length) {
            //说明word中0～N-1已经搞定了
            return true;
        }
        if (i < 0 || i > m.length - 1 || j < 0 || j > m[0].length - 1) {
            return false;
        }
        //[i][j]不越界
        if (m[i][j] != word[k]) {
            return false;
        }
        return work(m, i - 1, j, word, k + 1) ||
                work(m, i + 1, j, word, k + 1) ||
                work(m, i, j + 1, word, k + 1) ||
                work(m, i, j - 1, word, k + 1);
    }


    // 在矩阵中每一个位置开始搜索
    // 上下左右走
    // 看能否走出word
    // 可以走回头路
    public static boolean loop(char[][] m, char[] word) {
        if (m == null || m.length == 0 || word.length == 0) {
            return false;
        }
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (work(m, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    //不能改动态规划，因为m会变
    //暴力尝试就是最优解
    public static boolean work2(char[][] m, int i, int j, char[] word, int k) {
        if (k == word.length) {
            return true;
        }
        if (i == -1 || i == m.length || j == -1 || j == m[0].length) {
            return false;
        }
        if (m[i][j] != word[k]) {
            return false;
        }
        char c = m[i][j];
        m[i][j] = 0;
        boolean ans = (work2(m, i - 1, j, word, k + 1) ||
                work2(m, i + 1, j, word, k + 1) ||
                work2(m, i, j + 1, word, k + 1) ||
                work2(m, i, j - 1, word, k + 1));
        m[i][j] = c; //dfs恢复现场
        return ans;
    }


    // 在矩阵中每一个位置开始搜索
    // 上下左右走
    // 看能否走出word
    // 不能走回头路
    public static boolean noLoop(char[][] m, char[] word) {
        if (m == null || m.length == 0 || word.length == 0) {
            return false;
        }
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (work2(m, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        char[][] m = {
                new char[]{'a', 'b', 'z'},
                new char[]{'c', 'd', 'o'},
                new char[]{'f', 'e', 'o'}
        };
        String word = "abzooefca";
        System.out.println(loop(m, word.toCharArray()));
        System.out.println(noLoop(m, word.toCharArray()));
    }

}
