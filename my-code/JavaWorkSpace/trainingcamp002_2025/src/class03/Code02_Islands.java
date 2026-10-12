package class03;

public class Code02_Islands {
	public static int countIslands1(int[][] m) {
		if (m == null || m.length < 1) {
			return 0;
		}
		int count = 0;
		int N = m.length;
		int M = m[0].length;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (m[i][j] == 1) {
					count++;
					infect(m, i, j, N, M);
				}
			}
		}
		return count;
	}

	public static void infect(int[][] m, int i, int j, int N, int M) {
		if (i < 0 || i >= N || j < 0 || j >= M || m[i][j] != 1) {
			return;
		}
		m[i][j] = 2;
		infect(m, i, j - 1, N, M);
		infect(m, i - 1, j, N, M);
		infect(m, i, j + 1, N, M);
		infect(m, i + 1, j, N, M);
	}
}
