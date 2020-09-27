package backtrack;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 * Example:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 *
 *
 * Constraints:
 *
 * board and word consists only of lowercase and uppercase English letters.
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 */
public class Problem_79_Word_Search {

    public static boolean exist(char[][] board, String word) {
        if (null == board || board.length == 0) {
            return false;
        }
        if ("".equals(word) || word.length() == 0) {
            return false;
        }
        char[] w = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                //从每个位置出发搜索，搜索到了就返回true
                if(process(board, w, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    //递归含义：
    //word[0...index-1]已经搜索到了，不用操心了
    //从[row, col]出发，是否可以搜索到给定单词的部分串 word[index...N-1]
    public static boolean process(char[][] board, char[] word, int index, int row, int col) {
        int N = word.length;
        if (index == N) { //表示word[0...N-1]已经搜索到了，即已经找到整个单词
            return true;
        }

        //位置越界
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return false;
        }

        if (word[index] != board[row][col]) {
            return false;
        }
        // str[index] == board[row][col]
        char oldChar = board[row][col];
        board[row][col] = 0; //为了不走回头路
        boolean ans = process(board, word, index + 1, row - 1, col)//向上可以搜索到word[index+1...N-1]
                || process(board, word, index + 1, row + 1, col)//向下可以搜索到word[index+1...N-1]
                || process(board, word, index + 1, row, col - 1)//向左可以搜索到word[index+1...N-1]
                || process(board, word, index + 1, row, col + 1);//向右可以搜索到word[index+1...N-1]

        board[row][col] = oldChar;//恢复现场
        return ans;
    }
}
