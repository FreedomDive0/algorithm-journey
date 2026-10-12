package 图;

public class Problem_0130_Surrounded_Regions {
	// 从周边遍历
	public void solve(char[][] board) {
		int n = board.length;
		int m = board[0].length;
		for (int i = 0; i < n; i++) {
			if (board[i][0] == 'O') {
				free(board, i, 0, n, m);
			}
			if (board[i][m - 1] == 'O') {
				free(board, i, m - 1, n, m);
			}
		}
		for (int j = 0; j < m; j++) {
			if (board[0][j] == 'O') {
				free(board, 0, j, n, m);
			}
			if (board[n - 1][j] == 'O') {
				free(board, n - 1, j, n, m);
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (board[i][j] == 'O') {
					board[i][j] = 'X';
				}
				if (board[i][j] == 'F') {
					board[i][j] = 'O';
				}
			}
		}
	}

	public void free(char[][] board, int i, int j, int n, int m) {
		if (i < 0 || i == n || j < 0 || j == m || board[i][j] != 'O') {
			return;
		}
		board[i][j] = 'F';
		free(board, i - 1, j, n, m);
		free(board, i + 1, j, n, m);
		free(board, i, j + 1, n, m);
		free(board, i, j - 1, n, m);
	}

	// 全部遍历
	public void solve1(char[][] board) {
		boolean[] ans = new boolean[1];
		int n = board.length;
		int m = board[0].length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (board[i][j] == 'O') {
					ans[0] = true;
					can(board, i, j, n, m, ans);
					board[i][j] = ans[0] ? 'T' : 'F';
				}
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				char can = board[i][j];
				if (can == 'T' || can == 'F') {
					board[i][j] = '.';
					change(board, i, j, n, m, can);
				}
			}
		}
	}

	public void can(char[][] board, int i, int j, int n, int m, boolean[] ans) {
		if (i < 0 || i == n || j < 0 || j == m) {// ans变false不能提前终止 把相关'O'->'.' 以便后续操作
			ans[0] = false;
			return;
		}
		if (board[i][j] == 'O') {
			board[i][j] = '.';
			can(board, i - 1, j, n, m, ans);
			can(board, i, j + 1, n, m, ans);
			can(board, i + 1, j, n, m, ans);
			can(board, i, j - 1, n, m, ans);
		}
	}

	public void change(char[][] board, int i, int j, int n, int m, char can) {
		if (i < 0 || i == n || j < 0 || j == m) {
			return;
		}
		if (board[i][j] == '.') {
			board[i][j] = can == 'T' ? 'X' : 'O';
			change(board, i - 1, j, n, m, can);
			change(board, i, j + 1, n, m, can);
			change(board, i + 1, j, n, m, can);
			change(board, i, j - 1, n, m, can);
		}
	}
}
