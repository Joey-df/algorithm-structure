package questions;

//leetcode 695题
public class Problem_0695_MaxAreaofIsland {

	public static int maxAreaOfIsland1(int[][] grid) {
		UnionFind union = new UnionFind(grid);
		int N = grid.length;
		int M = grid[0].length;
		for (int i = 1; i < N; i++) {
			union.union(i - 1, 0, i, 0);
		}
		for (int j = 1; j < M; j++) {
			union.union(0, j - 1, 0, j);
		}
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M; j++) {
				union.union(i - 1, j, i, j);
				union.union(i, j - 1, i, j);
			}
		}
		return union.maxSize();
	}

	public static class UnionFind {
		private int[] parent;
		private int[] size;
		private int[] help;
		private final int row;
		private final int col;
		private final int N;

		public UnionFind(int[][] grid) {
			row = grid.length;
			col = grid[0].length;
			N = row * col;
			parent = new int[N];
			size = new int[N];
			help = new int[N];
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					if (grid[i][j] == 1) {
						int cur = index(i, j);
						parent[cur] = cur;
						size[cur] = 1;
					}
				}
			}
		}

		private int index(int r, int c) {
			return r * col + c;
		}

		private int find(int i) {
			int hi = 0;
			while (i != parent[i]) {
				help[hi++] = i;
				i = parent[i];
			}
			for (hi--; hi >= 0; hi--) {
				parent[help[hi]] = i;
			}
			return i;
		}

		public void union(int r1, int c1, int r2, int c2) {
			int i1 = index(r1, c1);
			int i2 = index(r2, c2);
			if (size[i1] == 0 || size[i2] == 0) {
				return;
			}
			int f1 = find(i1);
			int f2 = find(i2);
			if (f1 != f2) {
				if (size[f1] >= size[f2]) {
					size[f1] += size[f2];
					parent[f2] = f1;
				} else {
					size[f2] += size[f1];
					parent[f1] = f2;
				}
			}
		}

		public int maxSize() {
			int max = size[0];
			for (int i = 1; i < N; i++) {
				max = Math.max(max, size[i]);
			}
			return max;
		}
	}

	public static int maxAreaOfIsland2(int[][] grid) {
		int N = grid.length;
		int M = grid[0].length;
		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (grid[i][j] == 1) {
					max = Math.max(max, f(grid, i, j));
				}
			}
		}
		return max;
	}

	public static int f(int[][] g, int i, int j) {
		if (i < 0 || i == g.length || j < 0 || j == g[0].length || g[i][j] != 1) {
			return 0;
		}
		g[i][j] = 2;
		return 1 + f(g, i - 1, j) + f(g, i + 1, j) + f(g, i, j - 1) + f(g, i, j + 1);
	}

}
