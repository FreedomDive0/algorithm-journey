package class02;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code05_TrappingRainWaterII {

	public static class Node {
		public int value;
		public int row;
		public int col;

		public Node(int v, int r, int c) {
			value = v;
			row = r;
			col = c;
		}

	}

	public static class NodeComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			return o1.value - o2.value;
		}

	}

	public static int trapRainWater(int[][] heightMap) {
		if (heightMap == null || heightMap.length == 0 || heightMap[0] == null || heightMap[0].length == 0) {
			return 0;
		}
		int N = heightMap.length;
		int M = heightMap[0].length;
		// isEnter[i][j] == true 之前进过
		// isEnter[i][j] == false 之前没进过
		boolean[][] isEnter = new boolean[N][M];
		// 小根堆
		PriorityQueue<Node> heap = new PriorityQueue<>(new NodeComparator());
		for (int col = 0; col < M - 1; col++) {
			isEnter[0][col] = true;
			heap.add(new Node(heightMap[0][col], 0, col));
		}
		for (int row = 0; row < N - 1; row++) {
			isEnter[row][M - 1] = true;
			heap.add(new Node(heightMap[row][M - 1], row, M - 1));
		}
		for (int col = M - 1; col > 0; col--) {
			isEnter[N - 1][col] = true;
			heap.add(new Node(heightMap[N - 1][col], N - 1, col));
		}
		for (int row = N - 1; row > 0; row--) {
			isEnter[row][0] = true;
			heap.add(new Node(heightMap[row][0], row, 0));
		}

		int water = 0; // 每个位置的水量，累加到water上去
		int max = 0; // 每个node在弹出的时候，如果value更大，更新max，否则max的值维持不变
		while (!heap.isEmpty()) {
			Node cur = heap.poll();
			max = Math.max(max, cur.value);
			int r = cur.row;
			int c = cur.col;
//			if (r > 0 && !isEnter[r - 1][c]) { // 如果有上面的位置并且上面位置没进过堆
//				water += Math.max(0, max - heightMap[r - 1][c]);
//				isEnter[r - 1][c] = true;
//				heap.add(new Node(heightMap[r - 1][c], r - 1, c));
//			}
//			if (r < N - 1 && !isEnter[r + 1][c]) {
//				water += Math.max(0, max - heightMap[r + 1][c]);
//				isEnter[r + 1][c] = true;
//				heap.add(new Node(heightMap[r + 1][c], r + 1, c));
//			}
//			if (c > 0 && !isEnter[r][c - 1]) {
//				water += Math.max(0, max - heightMap[r][c - 1]);
//				isEnter[r][c - 1] = true;
//				heap.add(new Node(heightMap[r][c - 1], r, c - 1));
//			}
//			if (c < M - 1 && !isEnter[r][c + 1]) {
//				water += Math.max(0, max - heightMap[r][c + 1]);
//				isEnter[r][c + 1] = true;
//				heap.add(new Node(heightMap[r][c + 1], r, c + 1));
//			}
			if (r > 0 && !isEnter[r - 1][c]) {
				water += Math.max(0, max - heightMap[r - 1][c]);
				heap.add(new Node(r - 1, c, heightMap[r - 1][c]));
				isEnter[r - 1][c] = true;
			}
			if (r + 1 < N && !isEnter[r + 1][c]) {
				water += Math.max(0, max - heightMap[r + 1][c]);
				heap.add(new Node(r + 1, c, heightMap[r + 1][c]));
				isEnter[r + 1][c] = true;
			}
			if (c > 0 && !isEnter[r][c - 1]) {
				water += Math.max(0, max - heightMap[r][c - 1]);
				heap.add(new Node(r, c - 1, heightMap[r][c - 1]));
				isEnter[r][c - 1] = true;
			}
			if (c + 1 < M && !isEnter[r][c + 1]) {
				water += Math.max(0, max - heightMap[r][c + 1]);
				heap.add(new Node(r, c + 1, heightMap[r][c + 1]));
				isEnter[r][c + 1] = true;
			}
		}
		return water;
	}

	// 测试
	public static class MyCom implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			// TODO Auto-generated method stub
			return o1.value - o2.value;
		}

	}

	public static int trapRain(int[][] matrix) {
		if (matrix == null || matrix.length < 3 || matrix[0].length < 3) {
			return 0;
		}
		int water = 0;
		int row = matrix.length;
		int cow = matrix[0].length;
		boolean[][] isEntered = new boolean[row][cow];// 判断(r,c)是否进入过队列
		PriorityQueue<Node> heap = new PriorityQueue<>(new MyCom());// 存放按顺序输出边界
		// 将矩阵边界入队
		for (int i = 0; i < cow; i++) {
			heap.add(new Node(matrix[0][i], 0, i));
			isEntered[0][i] = true;
		}
		for (int i = 0; i < row; i++) {
			heap.add(new Node(matrix[i][cow - 1], i, cow - 1));
			isEntered[i][cow - 1] = true;
		}
		for (int i = cow - 1; i >= 0; i--) {
			heap.add(new Node(matrix[row - 1][i], row - 1, i));
			isEntered[row - 1][i] = true;
		}
		for (int i = row - 1; i >= 0; i--) {
			heap.add(new Node(matrix[i][0], i, 0));
			isEntered[i][0] = true;
		}
		int max = 0;
		while (!heap.isEmpty()) {
			Node cur = heap.poll();// 以边界为参考查看四周
			max = Math.max(max, cur.value);
			int r = cur.row;
			int c = cur.col;
			// 以边界为最低标注判断四周
			if (r > 0 && !isEntered[r - 1][c]) {
				water += Math.max(0, max - matrix[r - 1][c]);
				heap.add(new Node(matrix[r - 1][c], r - 1, c));
				isEntered[r - 1][c] = true;
			}
			if (r + 1 < row && !isEntered[r + 1][c]) {
				water += Math.max(0, max - matrix[r + 1][c]);
				heap.add(new Node(matrix[r + 1][c], r + 1, c));
				isEntered[r + 1][c] = true;
			}
			if (c > 0 && !isEntered[r][c - 1]) {
				water += Math.max(0, max - matrix[r][c - 1]);
				heap.add(new Node(matrix[r][c - 1], r, c - 1));
				isEntered[r][c - 1] = true;
			}
			if (c + 1 < cow && !isEntered[r][c + 1]) {
				water += Math.max(0, max - matrix[r][c + 1]);
				heap.add(new Node(matrix[r][c + 1], r, c + 1));
				isEntered[r][c + 1] = true;
			}
		}
		return water;
	}

	// 测试
	// 5.matrix[][] 接水
	public static int maxTrappingWaterMatrix(int[][] matrix) {
		if (matrix == null || matrix.length < 3 || matrix[0].length < 3) {
			return 0;
		}
		int n = matrix.length;
		int m = matrix[0].length;
		boolean[][] isEntered = new boolean[n][m];
		PriorityQueue<NodeInfo> heap = new PriorityQueue<NodeInfo>(new NodeInfoComparator());
		for (int i = 0; i < n; i++) {// 第一列最后一列
			heap.add(new NodeInfo(matrix[i][0], i, 0));
			isEntered[i][0] = true;
			heap.add(new NodeInfo(matrix[i][m - 1], i, m - 1));
			isEntered[i][m - 1] = true;
		}
		for (int j = 1; j < m - 1; j++) {// 第一行最后一行
			heap.add(new NodeInfo(matrix[0][j], 0, j));
			isEntered[0][j] = true;
			heap.add(new NodeInfo(matrix[n - 1][j], n - 1, j));
			isEntered[n - 1][j] = true;
		}
		int water = 0;
		int max = Integer.MIN_VALUE;
		while (!heap.isEmpty()) {
			NodeInfo cur = heap.poll();
			max = Math.max(max, cur.num);
			int r = cur.row;
			int c = cur.col;
			if (r > 0 && !isEntered[r - 1][c]) {
				water += Math.max(0, max - matrix[r - 1][c]);// max边界值 - matrix对应位置高度 是水的高度
				heap.add(new NodeInfo(matrix[r - 1][c], r - 1, c));
				isEntered[r - 1][c] = true;
			}
			if (r + 1 < n && !isEntered[r + 1][c]) {
				water += Math.max(0, max - matrix[r + 1][c]);
				heap.add(new NodeInfo(matrix[r + 1][c], r + 1, c));
				isEntered[r + 1][c] = true;
			}
			if (c > 0 && !isEntered[r][c - 1]) {
				water += Math.max(0, max - matrix[r][c - 1]);
				heap.add(new NodeInfo(matrix[r][c - 1], r, c - 1));
				isEntered[r][c - 1] = true;
			}
			if (c + 1 < m && !isEntered[r][c + 1]) {
				water += Math.max(0, max - matrix[r][c + 1]);
				heap.add(new NodeInfo(matrix[r][c + 1], r, c + 1));
				isEntered[r][c + 1] = true;
			}
		}
		return water;
	}

	public static class NodeInfo {
		int num;
		int row;
		int col;

		public NodeInfo(int num, int row, int col) {
			this.num = num;
			this.row = row;
			this.col = col;
		}

	}

	public static class NodeInfoComparator implements Comparator<NodeInfo> {

		@Override
		public int compare(NodeInfo o1, NodeInfo o2) {
			return o1.num - o2.num;
		}

	}

	public static void main(String[] args) {
		int[][] matrix = new int[][] { { 9, 9, 9, 9 }, { 9, 1, 1, 9 }, { 9, 1, 1, 9 }, { 9, 9, 9, 9 } };
		System.out.println(trapRainWater(matrix));
		System.out.println(maxTrappingWaterMatrix(matrix));
	}
}
