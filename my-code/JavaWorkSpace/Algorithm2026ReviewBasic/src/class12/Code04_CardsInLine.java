package class12;

public class Code04_CardsInLine {
	public static int winDP(int[] arr) {
		int N = arr.length;
		int[][] f = new int[N][N];
		int[][] s = new int[N][N];
		for (int i = 0; i < N; i++) {
			f[i][i] = arr[i];
		}
		for (int time = 1; time < N; time++) {// 斜线输出方式
			int row = 0;
			int col = time;
			while (col < N) {
				f[row][col] = Math.max(arr[row] + s[row + 1][col], arr[col] + s[row][col - 1]);
				s[row][col] = Math.min(f[row + 1][col], f[row][col - 1]);
				row++;
				col++;
			}
		}
		return Math.max(f[0][N - 1], s[0][N - 1]);
	}
}
