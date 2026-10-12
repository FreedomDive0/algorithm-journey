package 回溯法与深度优先搜索专题;

public class LC79_单词搜索 {
	public static void main(String[] args) {
		System.out.println(
				exist(new char[][] { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } }, "SEE"));
	}

	public static boolean exist(char[][] board, String word) {
		boolean[][] visited = new boolean[board.length][board[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (flag) {
					break;
				}
				process(board, word, 0, i, j, visited);
			}
		}
		return flag;
	}

	static boolean flag = false;

	public static void process(char[][] board, String word, int index, int x, int y, boolean[][] visited) {
		if (flag || x < 0 || x >= board.length || y < 0 || y >= board[0].length || visited[x][y]) {
			return;
		} // flag也应该首先判断
		if (index == word.length() - 1) {
			flag = word.charAt(index) == board[x][y];
			return;
		}
		if (board[x][y] != word.charAt(index)) {
			return;
		}
		visited[x][y] = true;// visited位置很重要
		process(board, word, index + 1, x, y - 1, visited);
		process(board, word, index + 1, x + 1, y, visited);
		process(board, word, index + 1, x, y + 1, visited);
		process(board, word, index + 1, x - 1, y, visited);
		visited[x][y] = false;
	}
}
