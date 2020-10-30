package islands;

/**
 * Given an m x n 2d grid map of '1's (land) and '0's (water), return the number of islands.
 * <p>
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * Output: 1
 * Example 2:
 * <p>
 * Input: grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * Output: 3
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] is '0' or '1'.
 */
public class Problem_200_Number_of_Islands {
    public static int numIslands(int[][] grid) {
        int M = grid.length, N = grid[0].length;
        int ans = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1) {
                    ans++; //每次遇到1就计算一个岛，然后去递归的把其上下左右所有的1改为2
                    infect(i, j, grid);
                }
            }
        }
        return ans;
    }

    //感染函数
    private static void infect(int x, int y, int[][] grid) {
        int M = grid.length, N = grid[0].length;
        if (x < 0 || x >= M || y < 0 || y >= N || grid[x][y] != 1) {
            return;
        }
        grid[x][y] = 2;
        //去感染其上下左右
        infect(x - 1, y, grid);
        infect(x + 1, y, grid);
        infect(x, y - 1, grid);
        infect(x, y + 1, grid);
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                new int[]{1, 1, 0, 0, 0},
                new int[]{1, 1, 0, 0, 0},
                new int[]{0, 1, 1, 0, 0},
                new int[]{0, 0, 0, 1, 1}};
        System.out.println(numIslands(grid));
    }
}
