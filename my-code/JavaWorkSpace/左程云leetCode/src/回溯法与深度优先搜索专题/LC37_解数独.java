package 回溯法与深度优先搜索专题;

public class LC37_解数独 {
//	public static void main(String[] args) {
//		System.out.println(getCubeIndex(4, 4));
//	}

	public void solveSudoku(char[][] board) {
		boolean[][] rowUsed = new boolean[9][9];
		boolean[][] colUsed = new boolean[9][9];
		boolean[][] cubeUsed = new boolean[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == '.') {
					continue;
				}
				rowUsed[i][board[i][j] - '1'] = true;
				colUsed[j][board[i][j] - '1'] = true;
				int index = (i / 3) * 3 + j / 3;
				cubeUsed[index][board[i][j] - '1'] = true;
			}
		}
		process(board, 0, 0, rowUsed, colUsed, cubeUsed);
	}

	// 因为本题只有一个解 所以需要返回值 在找到最终解停止递归
	public boolean process(char[][] board, int x, int y, boolean[][] rowUsed, boolean[][] colUsed,
			boolean[][] cubeUsed) {
		if (y == board.length) {
			y = 0;
			x++;
			if (x == board.length) {
				return true;
			}
		}
		if (board[x][y] == '.') {
			for (int i = 0; i < 9; i++) {
				int index = (x / 3) * 3 + y / 3;
				if (colUsed[y][i] || rowUsed[x][i] || cubeUsed[index][i])
					continue;
				rowUsed[x][i] = true;
				colUsed[y][i] = true;
				cubeUsed[index][i] = true;
				board[x][y] = (char) ('1' + i);
				if (process(board, x, y + 1, rowUsed, colUsed, cubeUsed)) {// 如果找到了解 就直接返回
					return true;
				}
				rowUsed[x][i] = false;
				colUsed[y][i] = false;
				cubeUsed[index][i] = false;
			}
			board[x][y] = '.';
		} else {
			return process(board, x, y + 1, rowUsed, colUsed, cubeUsed);
		}
		return false;
	}

//	public int getCubeIndex(int x, int y) {
//		int xStep = 0;
//		int yStep = 0;
//		while (x > 2) {
//			x -= 3;
//			xStep++;
//		}
//		while (y > 2) {
//			y -= 3;
//			yStep++;
//		}
//		return xStep * 3 + yStep;
//	}

}
