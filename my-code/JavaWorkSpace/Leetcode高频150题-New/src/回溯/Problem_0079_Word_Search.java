package 回溯;

public class Problem_0079_Word_Search {
//	public boolean exist(char[][] board, String word) {
//		if (board == null) {
//			return false;
//		}
//		int n = board.length;
//		int m = board[0].length;
//		boolean[][] passed = new boolean[n][m];
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < m; j++) {
//				if (process(board, word.toCharArray(), i, j, passed, 0)) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}

//	public boolean process(char[][] board, char[] word, int i, int j, boolean[][] passed, int index) {
//		if (index == word.length) {
//			return true;
//		}
//		if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
//			return false;
//		}
//		if (word[index] != board[i][j] || passed[i][j]) {
//			return false;
//		}
//		passed[i][j] = true;
//		if (process(board, word, i - 1, j, passed, index + 1)) {
//			return true;
//		}
//		if (process(board, word, i, j + 1, passed, index + 1)) {
//			return true;
//		}
//		if (process(board, word, i + 1, j, passed, index + 1)) {
//			return true;
//		}
//		if (process(board, word, i, j - 1, passed, index + 1)) {
//			return true;
//		}
//		passed[i][j] = false;
//		return false;
//	}
	public boolean exist(char[][] board, String word) {
		if (board == null) {
			return false;
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (process(board, word.toCharArray(), i, j, 0)) {
					return true;
				}
			}
		}
		return false;
	}

	// 优化
	public boolean process(char[][] board, char[] word, int i, int j, int index) {
		if (index == word.length) {
			return true;
		}
		if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || word[index] != board[i][j]) {
			return false;
		}
		board[i][j] = '*';
		boolean res = false;
		res = process(board, word, i - 1, j, index + 1) || process(board, word, i, j + 1, index + 1)
				|| process(board, word, i + 1, j, index + 1) || process(board, word, i, j - 1, index + 1);
		board[i][j] = word[index];
		return res;
	}
	// 错误版本
//	public boolean process(char[][] board, char[] word, int i, int j, boolean[][] passed, int index) {
//		if (index == word.length) {
//			return true;
//		}
//		if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
//			return false;
//		}
//		if (i - 1 >= 0 && !passed[i - 1][j] && word[index] == board[i - 1][j]) {
//			passed[i - 1][j] = true;
//			if (process(board, word, i - 1, j, passed, index + 1)) {
//				return true;
//			}
//			passed[i - 1][j] = false;
//		}
//		if (j + 1 < board[0].length && !passed[i][j + 1] && word[index] == board[i][j + 1]) {
//			passed[i][j + 1] = true;
//			if (process(board, word, i, j + 1, passed, index + 1)) {
//				return true;
//			}
//			passed[i][j + 1] = false;
//		}
//		if (i + 1 < board.length && !passed[i + 1][j] && word[index] == board[i + 1][j]) {
//			passed[i + 1][j] = true;
//			if (process(board, word, i + 1, j, passed, index + 1)) {
//				return true;
//			}
//			passed[i + 1][j] = false;
//		}
//		if (j - 1 >= 0 && !passed[i][j - 1] && word[index] == board[i][j - 1]) {
//			passed[i][j - 1] = true;
//			if (process(board, word, i, j - 1, passed, index + 1)) {
//				return true;
//			}
//			passed[i][j - 1] = false;
//		}
//		return false;
//	}
}
