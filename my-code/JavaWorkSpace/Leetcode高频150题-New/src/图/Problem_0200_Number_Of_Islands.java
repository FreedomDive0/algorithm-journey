package 图;

public class Problem_0200_Number_Of_Islands {
	public int numIslands(char[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int res = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == '1') {
					res++;
					infect(grid, i, j, n, m);
				}
			}
		}
		return res;
	}

	public void infect(char[][] grid, int i, int j, int n, int m) {// 防止重复计算
		if (i < 0 || i >= n || j < 0 || j >= m || grid[i][j] != '1') {
			return;
		}
		grid[i][j] = '2';
		infect(grid, i - 1, j, n, m);
		infect(grid, i, j + 1, n, m);
		infect(grid, i + 1, j, n, m);
		infect(grid, i, j - 1, n, m);
	}
}
