package 图;

import java.util.HashMap;
import java.util.List;

public class Problem_0399_Evaluate_Division {
	public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
		int size = equations.size();
		UnionFind unionfind = new UnionFind(size << 1);
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		int id = 0;
		for (int i = 0; i < size; i++) {
			List<String> list = equations.get(i);
			String v1 = list.get(0);
			String v2 = list.get(1);
			if (!map.containsKey(v1)) {
				map.put(v1, id++);
			}
			if (!map.containsKey(v2)) {
				map.put(v2, id++);
			}
			unionfind.union(map.get(v1), map.get(v2), values[i]);
		}
		int n = queries.size();
		double[] res = new double[n];
		for (int i = 0; i < n; i++) {
			String v1 = queries.get(i).get(0);
			String v2 = queries.get(i).get(1);
			Integer id1 = map.get(v1);
			Integer id2 = map.get(v2);
			if (id1 == null || id2 == null) {
				res[i] = -1.0d;
			} else {
				res[i] = unionfind.isConnected(id1, id2);
			}
		}
		return res;
	}

	class UnionFind {
		private int[] parent;
		private double[] weight;

		public UnionFind(int n) {
			parent = new int[n];
			weight = new double[n];
			for (int i = 0; i < n; i++) {
				parent[i] = i;
				weight[i] = 1.0d;
			}
		}

		public void union(int x, int y, double value) {
			int findX = find(x);
			int findY = find(y);
			if (findX == findY) {
				return;
			}
			parent[findX] = findY;
			weight[findX] = value * weight[y] / weight[x];
		}

		public double isConnected(int x, int y) {
			int findX = find(x);
			int findY = find(y);
			if (findX == findY) {
				return weight[x] / weight[y];
			}
			return -1.0d;
		}

		public int find(int x) {
			if (x != parent[x]) {// 递归处理 不需要while
				int father = parent[x];
				parent[x] = find(parent[x]);// 压缩
				weight[x] *= weight[father];
			}
			return parent[x];
		}
	}
}
