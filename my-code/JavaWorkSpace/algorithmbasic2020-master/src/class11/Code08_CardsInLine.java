package class11;

public class Code08_CardsInLine {

	public static int win1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
	}

	// L....R
	// F S L+1..R
	// L..R-1
	public static int f(int[] arr, int L, int R) {
		if (L == R) {
			return arr[L];
		}

		return Math.max(arr[L] + s(arr, L + 1, R), arr[R] + s(arr, L, R - 1));
	}

	// arr[L..R]
	public static int s(int[] arr, int L, int R) {
		if (L == R) {
			return 0;
		}
		return Math.min(f(arr, L + 1, R), // arr[i]
				f(arr, L, R - 1) // arr[j]
		);
	}

	public static int win2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		int[][] f = new int[N][N];
		int[][] s = new int[N][N];
		for (int i = 0; i < N; i++) {
			f[i][i] = arr[i];
		}
		// s[i][i] = 0;
		for (int i = 1; i < N; i++) {
			int L = 0;
			int R = i;
			while (L < N && R < N) {

				f[L][R] = Math.max(arr[L] + s[L + 1][R], arr[R] + s[L][R - 1]);
				s[L][R] = Math.min(f[L + 1][R], // arr[i]
						f[L][R - 1] // arr[j]
				);

				L++;
				R++;

			}
		}
		return Math.max(f[0][N - 1], s[0][N - 1]);
	}

	// 测试
	public static int cardInLine(int[] cards) {
		return Math.max(f(cards, 0, cards.length - 1), s(cards, 0, cards.length - 1));
	}

	public static int first(int[] cards, int l, int r) {// 先手摸牌
		if (l == r) {
			return cards[l];// 只剩一张直接拿
		}
		return Math.max(cards[l] + second(cards, l + 1, r), cards[r] + second(cards, l, r - 1));// 拿左or右 后手拿牌和后续操作要大的
	}

	public static int second(int[] cards, int l, int r) {// 后手摸牌
		if (l == r) {
			return 0;// 没得选
		}
		return Math.min(first(cards, l + 1, r), first(cards, l, r - 1));// 先手摸的人 会让后手的人摸到两种结果较小的
	}

	// (2)dp
	public static int cardInLineDP(int[] cards) {
		int n = cards.length;
		int[][] fDp = new int[n][n];
		int[][] sDp = new int[n][n];
		for (int i = 0; i < n; i++) {
			fDp[i][i] = cards[i];
		}
		for (int i = 1; i < n; i++) {// 规定次数
			int l = 0;
			int r = i;// 确定开始
			while (l < n && r < n) {// r<n 可以卡主l<r
				fDp[l][r] = Math.max(cards[l] + sDp[l + 1][r], cards[r] + sDp[l][r - 1]);
				sDp[l][r] = Math.min(fDp[l + 1][r], fDp[l][r - 1]);
				l++;
				r++;
			}
		}
//		for (int r = n - 2; r >= 0; r--) {//存在问题是只能规定执行次数 r每次其实没有规定 只在一列不停循环
//			for (int l = 0; l <= r; l++) {
//				fDp[l][l + 1] = Math.max(cards[l] + sDp[l + 1][l + 1], cards[l + 1] + sDp[l][l]);
//				sDp[l][l + 1] = Math.min(fDp[l + 1][l + 1], fDp[l][l]);
//			}
//		}
//		System.out.println("s");
//		for (int i = 0; i < sDp.length; i++) {
//			for (int j = 0; j < sDp.length; j++) {
//				System.out.print(sDp[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("f");
//		for (int i = 0; i < sDp.length; i++) {
//			for (int j = 0; j < sDp.length; j++) {
//				System.out.print(fDp[i][j] + " ");
//			}
//			System.out.println();
//		}
		return Math.max(fDp[0][n - 1], sDp[0][n - 1]);
	}

	public static void main(String[] args) {
		int[] arr = { 4, 7, 9, 5, 19, 29, 80, 4 };
		// A 4 9
		// B 7 5
		System.out.println(cardInLineDP(arr));
		System.out.println(win2(arr));

	}

}
