package class12;

public class Code04_CardsInLine {
	// 动态规划
	public static int winDP(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		int N = arr.length;
		int[][] f = new int[N][N];
		int[][] s = new int[N][N];
		for (int i = 0; i < N; i++) {
			f[i][i] = arr[i];
		}
		for (int col = 1; col < N; col++) {
			int l = 0;
			int r = col;
			while (l < N && r < N) {
				f[l][r] = Math.max(arr[l] + s[l + 1][r], arr[r] + s[l][r - 1]);
				s[l][r] = Math.min(f[l + 1][r], f[l][r - 1]);
				l++;
				r++;
			}
		}
		return Math.max(f[0][N - 1], s[0][N - 1]);
	}
}
