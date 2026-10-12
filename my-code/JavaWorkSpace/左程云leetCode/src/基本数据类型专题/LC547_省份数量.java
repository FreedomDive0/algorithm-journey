package 基本数据类型专题;

public class LC547_省份数量 {
	public int findCircleNum(int[][] isConnected) {
		int n = isConnected.length;
		boolean[] visited = new boolean[n];
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				dfs(isConnected, visited, i);
				count++;
			}
		}
		return count;
	}

	private void dfs(int[][] isConnected, boolean[] visited, int i) {// 深度优先遍历城市
		for (int j = 0; j < visited.length; j++) {
			if (isConnected[i][j] == 1 && !visited[j]) {
				visited[j] = true;
				dfs(isConnected, visited, j);
			}
		}
	}
}
