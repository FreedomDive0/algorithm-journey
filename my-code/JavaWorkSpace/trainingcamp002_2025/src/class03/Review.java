package class03;

public class Review {
	public static int islands(int[][] pos) {
		if (pos == null || pos.length < 1 || pos[0].length < 1) {
			return 0;
		}
		int res = 0;
		for (int i = 0; i < pos.length; i++) {
			for (int j = 0; j < pos[0].length; j++) {
				if (pos[i][j] == 1) {
					res++;
					infect(pos, i, j);
				}
			}
		}
		return res;
	}

	// 感染算法
	private static void infect(int[][] pos, int i, int j) {
		if (i < 0 || i >= pos.length || j < 0 || j >= pos[0].length || pos[i][j] != 1) {
			return;
		}
		pos[i][j] = 2;
		infect(pos, i - 1, j);
		infect(pos, i + 1, j);
		infect(pos, i, j - 1);
		infect(pos, i, j + 1);
	}
}
