package coding_for_great_offer.class35;

import java.util.PriorityQueue;

// 来自网易
// map[i][j] == 0，代表(i,j)是海洋，渡过的话代价是2
// map[i][j] == 1，代表(i,j)是陆地，渡过的话代价是1
// map[i][j] == 2，代表(i,j)是障碍，无法渡过
// 每一步上、下、左、右都能走，返回从左上角走到右下角最小代价是多少，如果无法到达返回-1
// A*算法的思想原型
public class Code04_WalkToEnd {

	public static int minCost(int[][] map) {
		if (map[0][0] == 2) {
			return -1;
		}
		int n = map.length;
		int m = map[0].length;
		PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> a.cost - b.cost);
		boolean[][] visited = new boolean[n][m];
		add(map, 0, 0, 0, heap, visited);
		while (!heap.isEmpty()) {
			Node cur = heap.poll();
			if (cur.row == n - 1 && cur.col == m - 1) { //表示到达右下角
				return cur.cost;
			}
			//弹出一个，就把它的上下左右压进去
			add(map, cur.row - 1, cur.col, cur.cost, heap, visited);
			add(map, cur.row + 1, cur.col, cur.cost, heap, visited);
			add(map, cur.row, cur.col - 1, cur.cost, heap, visited);
			add(map, cur.row, cur.col + 1, cur.cost, heap, visited);
		}
		return -1;
	}

	/**
	 *
	 * @param m 输入矩阵
 	 * @param i 当前来到(i,j)位置
	 * @param j 当前来到(i,j)位置
	 * @param pre 来到(i,j)位置之前已经形成的最小代价
	 * @param heap 小根堆
	 * @param visited 记录当前位置是否之前已经进过小根堆
	 */
	public static void add(int[][] m, int i, int j, int pre, PriorityQueue<Node> heap, boolean[][] visited) {
		if (i >= 0 && i < m.length && j >= 0 && j < m[0].length && m[i][j] != 2 && !visited[i][j]) {
			heap.add(new Node(i, j, pre + (m[i][j] == 0 ? 2 : 1)));
			visited[i][j] = true;
		}
	}

	public static class Node {
		public int row;
		public int col;
		public int cost; //到达(row,col)这个节点的最小代价

		public Node(int a, int b, int c) {
			row = a;
			col = b;
			cost = c;
		}
	}

}