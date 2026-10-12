package 基本数据类型专题;

public class LC684_冗余连接 {
	class DSU {
		int[] parent;

		public DSU(int n) {
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = i;
			}
		}

		public int findFather(int n) {
			while (n != parent[n]) {
				n = parent[n];
			}
			return n;
		}

		public boolean isConnected(int a, int b) {
			return findFather(a) == findFather(b);
		}

		public void union(int a, int b) {
			if (!isConnected(a, b)) {
				a = findFather(a);
				b = findFather(b);
				parent[a] = b;
			}
//			int c1 = 0, c2 = 0;
//			int tmpA = a, tmpB = b;
//			while (tmpA != parent[tmpA]) {
//				c1++;
//				tmpA = parent[tmpA];
//			}
//			while (tmpB != parent[tmpB]) {
//				c2++;
//				tmpB = parent[tmpB];
//			}
//			int small = c1 > c2 ? tmpA : tmpB;
//			int big = small == tmpA ? tmpB : tmpA;
//			small = small == tmpA ? a : b;
//			while (small != parent[small]) {// 扁平化处理
//				parent[small] = big;
//			}
		}
	}

	public int[] findRedundantConnection(int[][] edges) {
		int len = edges.length;
		DSU uf = new DSU(len + 1);
		int[] ans = new int[2];
		for (int i = 0; i < edges.length; i++) {
			int a = edges[i][0], b = edges[i][1];
			if (!uf.isConnected(a, b)) {
				uf.union(a, b);
			} else {
				ans = new int[] { a, b };
				break;
			}
		}
		return ans;
	}

}
