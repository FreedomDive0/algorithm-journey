package class02;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code05_TrappingRainWaterII {
	// 二维数组接雨水
	public static class Node {// 记录水池边界值和位置
		int value;
		int row;
		int col;

		public Node(int value, int row, int cow) {
			this.value = value;
			this.row = row;
			this.col = cow;
		}

	}

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

	public static void main(String[] args) {
		int[][] matrix = new int[][] { { 9, 9, 9, 9 }, { 9, 1, 1, 9 }, { 9, 1, 1, 9 }, { 9, 9, 9, 9 } };
		System.out.println(trapRain(matrix));
	}
}
