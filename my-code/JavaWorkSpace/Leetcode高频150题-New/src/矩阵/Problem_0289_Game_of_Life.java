package 矩阵;

public class Problem_0289_Game_of_Life {
	public void gameOfLife(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				int neighbors = getNeighbors(board, i, j);
				if (neighbors == 3 || neighbors == 2 && board[i][j] == 1) {
					set(board, i, j);
				}
			}
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = board[i][j] >> 1;
			}
		}
	}

	public void set(int[][] board, int i, int j) {
		board[i][j] |= 2;
	}

	public int getNeighbors(int[][] board, int i, int j) {
		int count = 0;
		count += ok(board, i - 1, j - 1) ? 1 : 0;
		count += ok(board, i - 1, j) ? 1 : 0;
		count += ok(board, i - 1, j + 1) ? 1 : 0;
		count += ok(board, i, j - 1) ? 1 : 0;
		count += ok(board, i, j + 1) ? 1 : 0;
		count += ok(board, i + 1, j - 1) ? 1 : 0;
		count += ok(board, i + 1, j) ? 1 : 0;
		count += ok(board, i + 1, j + 1) ? 1 : 0;
		return count;
	}

	public boolean ok(int[][] board, int i, int j) {
		// (board[i][j] & 1) == 1 board[i][j]可能被修改 要验证最低位是否为1
		return i >= 0 && i < board.length && j >= 0 && j < board[0].length && (board[i][j] & 1) == 1;
	}
}
