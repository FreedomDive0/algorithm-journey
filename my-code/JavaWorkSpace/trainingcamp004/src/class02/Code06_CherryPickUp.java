package class02;

public class Code06_CherryPickUp {
	public static int cherryPickup(int[][] matrix) {
		if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
			return 0;
		}
		return process(matrix, matrix.length, matrix[0].length, 0, 0, 0);
	}

	public static int process(int[][] matrix, int N, int M, int Ar, int Ac, int Br) {
		if (Ar == N - 1 && Ac == M - 1) {
			return matrix[Ar][Ac];
		}
		int Bc = Ar + Ac - Br;
		int rightDown = Integer.MIN_VALUE;
		if (Ac < M - 1 && Br < N - 1) {
			rightDown = process(matrix, N, M, Ar, Ac + 1, Br + 1);
		}
		int rightRight = Integer.MIN_VALUE;
		if (Ac < M - 1 && Bc < M - 1) {
			rightRight = process(matrix, N, M, Ar, Ac + 1, Br);
		}
		int downRight = Integer.MIN_VALUE;
		if (Ar < N - 1 && Bc < M - 1) {
			downRight = process(matrix, N, M, Ar + 1, Ac, Br);
		}
		int downDown = Integer.MIN_VALUE;
		if (Ar < N - 1 && Br < N - 1) {
			downDown = process(matrix, N, M, Ar + 1, Ac, Br + 1);
		}
		return Math.max(Math.max(downDown, downRight), Math.max(rightRight, rightDown)) + matrix[Ar][Ac]
				+ (Ar != Br ? matrix[Br][Bc] : 0);
	}

	// 记忆化搜索
	public static int cherryPickup2(int[][] matrix) {
		if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
			return 0;
		}
		int N = matrix.length;
		int M = matrix[0].length;
		int[][][] dp = new int[N][M][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int k = 0; k < N; k++) {
					dp[i][j][k] = Integer.MIN_VALUE;
				}
			}
		}
		return process1(matrix, matrix.length, matrix[0].length, 0, 0, 0, dp);
	}

	public static int process1(int[][] matrix, int N, int M, int Ar, int Ac, int Br, int[][][] dp) {
		if (Ar == N || Ac == M || Br == N || Ar + Ac - Br == M) {// 越界处理方便后续计算
			return Integer.MIN_VALUE;
		}
		if (dp[Ar][Ac][Br] != Integer.MIN_VALUE) {// 该位置存在值直接返回
			return dp[Ar][Ac][Br];
		}
		if (Ar == N - 1 && Ac == M - 1) {// BaseCase
			dp[Ar][Ac][Br] = matrix[Ar][Ac];
			return dp[Ar][Ac][Br];
		}
		int next = Integer.MIN_VALUE;
		next = Math.max(next, process1(matrix, N, M, Ar, Ac + 1, Br + 1, dp));
		next = Math.max(next, process1(matrix, N, M, Ar, Ac + 1, Br, dp));
		next = Math.max(next, process1(matrix, N, M, Ar + 1, Ac, Br + 1, dp));
		next = Math.max(next, process1(matrix, N, M, Ar + 1, Ac, Br, dp));
		dp[Ar][Ac][Br] = next + matrix[Ar][Ac] + (Ar != Br ? matrix[Br][Ar + Ac - Br] : 0);
		return dp[Ar][Ac][Br];
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 1, 1, 1, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1, 1, 1, 1, 1, 1 } };

		System.out.println(cherryPickup(matrix));
		System.out.println(cherryPickup2(matrix));

	}
}
