package islands;

/**
 * You are given row x col grid representing a map where grid[i][j] = 1 represents land and grid[i][j] = 0 represents water.
 * <p>
 * Grid cells are connected horizontally/vertically (not diagonally).
 * The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).
 * <p>
 * The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island. One cell is a square with side length 1.
 * The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: grid =
 * [
 * [0,1,0,0],
 * [1,1,1,0],
 * [0,1,0,0],
 * [1,1,0,0]
 * ]
 * Output: 16
 * Explanation: The perimeter is the 16 yellow stripes in the image above.
 * Example 2:
 * <p>
 * Input: grid = [[1]]
 * Output: 4
 * Example 3:
 * <p>
 * Input: grid = [[1,0]]
 * Output: 4
 * <p>
 * <p>
 * Constraints:
 * <p>
 * row == grid.length
 * col == grid[i].length
 * 1 <= row, col <= 100
 * grid[i][j] is 0 or 1.
 */
public class Problem_463_Island_Perimeter {
    //观察图可以发现：
    //陆地的周边为水域，或者为边界时，周长贡献1
    //所以可以用感染方法
    public static int islandPerimeter(int[][] grid) {
        int M = grid.length, N = grid[0].length;
        int ans = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1) {
                    //有几个岛就调几次
                    ans += infect(i, j, grid, M, N);
                }
            }
        }
        return ans;
    }

    //grid[x][y]为1时连成一片的岛的周长是多少？
    private static int infect(int x, int y, int[][] grid, int M, int N) {
        if (x == -1 || x == M || y == -1 || y == N || grid[x][y] == 0) {//陆地的周边为水域，或者为边界时，周长贡献1
            return 1;
        }
        //走到这里说明[x,y]不越界并且grid[x][y]!=0
        if (grid[x][y] == 2) {
            return 0;
        }
        grid[x][y] = 2;//为了不走回头路
        return infect(x - 1, y, grid, M, N) +
                infect(x + 1, y, grid, M, N) +
                infect(x, y - 1, grid, M, N) +
                infect(x, y + 1, grid, M, N);
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                new int[]{0, 1, 0, 0},
                new int[]{1, 1, 1, 0},
                new int[]{0, 1, 0, 0},
                new int[]{1, 1, 0, 1}};
        System.out.println(islandPerimeter(grid));
    }
}
