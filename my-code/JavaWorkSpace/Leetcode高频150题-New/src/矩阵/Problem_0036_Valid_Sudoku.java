package 矩阵;

public class Problem_0036_Valid_Sudoku {
	public boolean isValidSudoku(char[][] board) {
		boolean[][] row = new boolean[9][9];
		boolean[][] col = new boolean[9][9];
		boolean[][] box = new boolean[9][9];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] != '.') {
					if (row[i][board[i][j] - '1'] || col[board[i][j] - '1'][j]
							|| box[3 * (i / 3) + j / 3][board[i][j] - '1']) {
						return false;
					}
					row[i][board[i][j] - '1'] = true;
					col[board[i][j] - '1'][j] = true;
					box[3 * (i / 3) + j / 3][board[i][j] - '1'] = true;
				}
			}
		}
		return true;
	}
}
