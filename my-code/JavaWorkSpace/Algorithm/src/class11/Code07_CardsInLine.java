package class11;

public class Code07_CardsInLine {
	public static int maxScore(int[] cards) {
		if (cards == null || cards.length == 0) {
			return 0;
		}
		return Math.max(first(cards, 0, cards.length - 1), second(cards, 0, cards.length - 1));
	}

	public static int first(int[] cards, int start, int end) {
		if (start == end) {
			return cards[start];
		}
		// 要拿(先手+后手)最大
		return Math.max(cards[start] + second(cards, start + 1, end), cards[end] + second(cards, start, end - 1));
	}

	public static int second(int[] cards, int start, int end) {
		if (start == end) {
			return 0;
		}
		// 后手拿 被迫后手拿最小
		return Math.min(first(cards, start + 1, end), first(cards, start, end - 1));
	}

	public static int dpWay(int[] cards) {
		if (cards == null || cards.length == 0) {
			return 0;
		}
		int N = cards.length;
		int[][] f = new int[N][N];
		int[][] s = new int[N][N];
		for (int i = 0; i < N; i++) {
			f[i][i] = cards[i];
		}
		for (int i = 1; i < N; i++) {// 对角线添加值
			int L = 0;
			int R = i;
			while (L < N && R < N) {
				f[L][R] = Math.max(cards[L] + s[L + 1][R], cards[R] + s[L][R - 1]);
				s[L][R] = Math.min(f[L + 1][R], f[L][R - 1]);
				L++;
				R++;
			}
		}
		return Math.max(f[0][N - 1], s[0][N - 1]);
	}

	// 复习
	public static int cardWinner(int[] card) {
		return Math.max(first(card, 0, card.length - 1), second(card, 0, card.length - 1));
	}

	public static int first1(int[] card, int L, int R) {
		if (L == R) {
			return card[L];
		}
		return Math.max(card[L] + second1(card, L + 1, R), card[R] + second1(card, L, R - 1));
	}

	public static int second1(int[] card, int L, int R) {
		if (L == R) {
			return 0;
		}
		return Math.min(first1(card, L + 1, R), first1(card, L, R - 1));
	}

	// 9.摸牌问题 动态规划
	public static int cardWinnerDP(int[] card) {
		int N = card.length;
		int[][] f = new int[N][N];
		int[][] s = new int[N][N];
		for (int i = 0; i < N; i++) {
			f[i][i] = card[i];
		}
		for (int i = 1; i < N; i++) {
			int R = i;
			int L = 0;
			while (L < N && R < N) {
				f[R][L] = Math.max(card[L] + s[R][L + 1], card[R] + s[R - 1][L]);
				s[R][L] = Math.min(f[R][L + 1], f[R - 1][L]);
				L++;
				R++;
			}
		}
		return Math.max(f[N - 1][0], s[N - 1][0]);
	}

	public static void main(String[] args) {
		int[] arr = { 4, 7, 9, 5, 19, 29, 80, 4 };
		// A 4 9
		// B 7 5
		System.out.println(maxScore(arr));
		System.out.println(dpWay(arr));
		System.out.println(cardWinnerDP(arr));

	}
}
