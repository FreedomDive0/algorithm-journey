package class11;

public class Code07_CardsInLine {
	// 绝顶聪明摸牌
	public static int win1(int[] arr) {
		return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
	}

	// 先手摸牌
	public static int f(int[] arr, int l, int r) {
		if (l == r) {
			return arr[l];
		}
		return Math.max(arr[l] + s(arr, l + 1, r), arr[r] + s(arr, l, r - 1));
	}

	public static int s(int[] arr, int l, int r) {
		if (l == r) {
			return 0;
		}
		return Math.min(f(arr, l + 1, r), f(arr, l, r - 1));// 因为是后手 先手玩家一定会让后手玩家摸到更差的结果
	}

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

	public static void main(String[] args) {
		int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
		System.out.println(win1(arr));
		System.out.println(winDP(arr));

	}
}
