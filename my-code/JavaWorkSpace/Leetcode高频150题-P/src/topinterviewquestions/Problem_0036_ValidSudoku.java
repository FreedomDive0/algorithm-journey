package topinterviewquestions;

public class Problem_0036_ValidSudoku {
	public static boolean isValidSudoku(char[][] board) {
		boolean[][] row = new boolean[9][10];
		boolean[][] col = new boolean[9][10];
		boolean[][] bucket = new boolean[9][10];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] != '.') {
					int num = board[i][j] - '0';
					int index = 3 * (i / 3) + j / 3;// 计算每个小格子
					if (row[i][num] || col[j][num] || bucket[index][num]) {
						return false;
					}
					row[i][num] = true;
					col[j][num] = true;
					bucket[index][num] = true;
				}
			}
		}
		return true;
	}
}
