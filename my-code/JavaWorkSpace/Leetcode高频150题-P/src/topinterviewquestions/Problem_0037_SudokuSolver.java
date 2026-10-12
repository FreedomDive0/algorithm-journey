package topinterviewquestions;

public class Problem_0037_SudokuSolver {
	public static void solveSudoku(char[][] board) {
		boolean[][] row = new boolean[9][10];
		boolean[][] col = new boolean[9][10];
		boolean[][] bucket = new boolean[9][10];
		initMaps(board, row, col, bucket);
		process(board, row, col, bucket, 0, 0);
	}

	public static void initMaps(char[][] board, boolean[][] row, boolean[][] col, boolean[][] bucket) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] != '.') {
					int num = board[i][j] - '0';
					int index = 3 * (i / 3) + j / 3;
					row[i][num] = true;
					col[j][num] = true;
					bucket[index][num] = true;
				}
			}
		}
	}

	public static boolean process(char[][] board, boolean[][] row, boolean[][] col, boolean[][] bucket, int i, int j) {
		if (i == 9) {
			return true;
		}
		int nextI = j == 8 ? i + 1 : i;
		int nextJ = j == 8 ? 0 : j + 1;
		if (board[i][j] != '.') {
			return process(board, row, col, bucket, nextI, nextJ);// 带回填值的返回值
		} else {
			for (int num = 1; num < 10; num++) {
				int index = 3 * (i / 3) + j / 3;
				if ((!row[i][num]) && (!col[j][num]) && (!bucket[index][num])) {
					board[i][j] = (char) (num + '0');
					row[i][num] = true;
					col[j][num] = true;
					bucket[index][num] = true;
					if (process(board, row, col, bucket, nextI, nextJ)) {
						return true;
					}
					board[i][j] = '.';
					row[i][num] = false;
					col[j][num] = false;
					bucket[index][num] = false;
				}
			}
		}
		return false;
	}
}
