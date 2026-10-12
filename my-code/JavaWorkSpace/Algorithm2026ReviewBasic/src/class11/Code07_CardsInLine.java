package class11;

public class Code07_CardsInLine {
	// 摸牌问题
	public static int win(int[] arr) {
		return Math.max(first(arr, 0, arr.length - 1), second(arr, 0, arr.length - 1));
	}

	public static int first(int[] arr, int l, int r) {
		if (l == r) {
			return arr[l];
		}
		return Math.max(arr[l] + second(arr, l + 1, r), arr[r] + second(arr, l, r - 1));// 自己拿最大的
	}

	public static int second(int[] arr, int l, int r) {
		if (l == r) {
			return 0;
		}
		return Math.min(first(arr, l + 1, r), first(arr, l, r - 1));// 对面挑剩小的
	}

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

	public static void main(String[] args) {
		int[] arr = { 4, 7, 9, 5, 19, 29, 80, 4 };
		// A 4 9
		// B 7 5
		System.out.println(win(arr));
		System.out.println(winDP(arr));
	}
}
