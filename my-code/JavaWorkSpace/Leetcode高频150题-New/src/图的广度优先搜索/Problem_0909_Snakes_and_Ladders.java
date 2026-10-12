package 图的广度优先搜索;

import java.util.ArrayDeque;
import java.util.Queue;

public class Problem_0909_Snakes_and_Ladders {
	public int snakesAndLadders(int[][] board) {
		int n = board.length;
		boolean[] vis = new boolean[n * n + 1];
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.add(new int[] { 1, 0 });
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int id = cur[0];
			for (int i = 1; i <= 6; i++) {
				int next = id + i;
				if (next > n * n) {
					break;
				}
				int[] rc = id2rc(next, n);
				if (board[rc[0]][rc[1]] > 0) {
					next = board[rc[0]][rc[1]];
				}

				if (next == n * n) {
					return cur[1] + 1;
				}
				if (!vis[next]) {
					vis[next] = true;
					queue.add(new int[] { next, cur[1] + 1 });
				}
			}
		}
		return -1;
	}

	public int[] id2rc(int id, int n) {
		int r = (id - 1) / n;
		int c = (id - 1) % n;
		if ((r & 1) == 1) {
			c = n - c - 1;
		}
		return new int[] { n - r - 1, c };
	}

	public static void main(String[] args) {
		System.out.println((-1 / 6));
	}
}
