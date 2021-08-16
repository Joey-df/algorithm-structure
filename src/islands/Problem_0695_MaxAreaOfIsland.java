package islands;

/**
 * Given a non-empty 2D array grid of 0's and 1's,
 * an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.)
 * You may assume all four edges of the grid are surrounded by water.
 * <p>
 * Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)
 * <p>
 * Example 1:
 * <p>
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,1,1,0,1,0,0,0,0,0,0,0,0],
 * [0,1,0,0,1,1,0,0,1,0,1,0,0],
 * [0,1,0,0,1,1,0,0,1,1,1,0,0],
 * [0,0,0,0,0,0,0,0,0,0,1,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
 * Example 2:
 * <p>
 * [[0,0,0,0,0,0,0,0]]
 * Given the above grid, return 0.
 * Note: The length of each dimension in the given grid does not exceed 50.
 *
 * 有人面试被问过：
 * 一个二维数组，里面仅包含0或1，求最多的1连在一起的个数
 */
//岛屿面积
public class Problem_0695_MaxAreaOfIsland {
    public static int maxAreaOfIsland(int[][] grid) {
        int M = grid.length, N = grid[0].length;
        int ans = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1)
                    ans = Math.max(ans, area(i, j, grid, M, N));
            }
        }
        return ans;
    }

    //计算一个岛的面积
    //出发点为grid[x][y]==1
    private static int area(int x, int y, int[][] grid, int M, int N) {
        if (x == -1 || x == M || y == -1 || y == N || grid[x][y] != 1) { //越界 或者 不等于1
            return 0;
        }
        //不越界 并且grid[x][y]==1
        grid[x][y] = 2; //防止走回头路
        return 1 + area(x - 1, y, grid, M, N) +
                area(x + 1, y, grid, M, N) +
                area(x, y - 1, grid, M, N) +
                area(x, y + 1, grid, M, N);
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                new int[]{1, 1, 0, 0, 0},
                new int[]{1, 1, 1, 0, 0},
                new int[]{0, 0, 0, 0, 1},
                new int[]{0, 0, 0, 1, 1}};
        System.out.println(maxAreaOfIsland(grid));
    }
}
