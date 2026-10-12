package class06;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code04_TopKSumCrossTwoArrays {
	public static int[] topK(int[] arr1, int[] arr2, int K) {
		if (arr1 == null || arr2 == null || K < 1) {
			return null;
		}
		PriorityQueue<Node> heap = new PriorityQueue<>(new NodeComparator());// 大根堆
		int N = arr1.length;
		int M = arr2.length;
		K = Math.min(K, N * M);// K最长不能超过两个数组长度积
		int[] ans = new int[K];
		boolean[][] isEnter = new boolean[N][M];// 标志位防止重复加入
		heap.add(new Node(N - 1, M - 1, arr1[N - 1] + arr2[M - 1]));
		isEnter[N - 1][M - 1] = true;
		for (int ansIndex = 0; ansIndex < K; ansIndex++) {
			Node cur = heap.poll();
			ans[ansIndex] = cur.sum;
			if (cur.index1 - 1 >= 0 && !isEnter[cur.index1 - 1][cur.index2]) {// 因为更大的值只可能在i,j形成二维表中当前位置左或上
				heap.add(new Node(cur.index1 - 1, cur.index2, arr1[cur.index1 - 1] + arr2[cur.index2]));
				isEnter[cur.index1 - 1][cur.index2] = true;
			}
			if (cur.index2 - 1 >= 0 && !isEnter[cur.index1][cur.index2 - 1]) {
				heap.add(new Node(cur.index1, cur.index2 - 1, arr1[cur.index1] + arr2[cur.index2 - 1]));
				isEnter[cur.index1][cur.index2 - 1] = true;
			}
		}
		return ans;
	}

	public static class Node {
		int index1;
		int index2;
		int sum;

		public Node(int index1, int index2, int sum) {
			this.index1 = index1;
			this.index2 = index2;
			this.sum = sum;
		}

	}

	public static class NodeComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			// TODO Auto-generated method stub
			return o2.sum - o1.sum;
		}

	}
}
