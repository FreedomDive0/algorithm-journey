package 基本数据类型专题;

public class LC695_岛屿的最大面积 {

	public int maxAreaOfIsland(int[][] grid) {
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (!visited[i][j]) {
					dfs(grid, i, j, visited);
				}
			}
		}
		return max;
	}

	int max = 0;

	public int dfs(int[][] grid, int x, int y, boolean[][] visited) {
		if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
			return 0;
		}
		int count = grid[x][y];
		visited[x][y] = true;
		count += dfs(grid, x, y - 1, visited);
		count += dfs(grid, x + 1, y, visited);
		count += dfs(grid, x, y + 1, visited);
		count += dfs(grid, x - 1, y, visited);
		max = Math.max(max, count);
		return count;
	}
}
