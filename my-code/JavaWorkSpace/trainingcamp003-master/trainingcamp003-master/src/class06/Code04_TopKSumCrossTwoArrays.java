package class06;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Code04_TopKSumCrossTwoArrays {

	// 放入大根堆中的结构
	public static class Node {
		public int index1;// arr1中的位置
		public int index2;// arr2中的位置
		public int sum;// arr1[index1] + arr2[index2]的值

		public Node(int i1, int i2, int s) {
			index1 = i1;
			index2 = i2;
			sum = s;
		}
	}

	// 生成大根堆的比较器
	public static class MaxHeapComp implements Comparator<Node> {
		@Override
		public int compare(Node o1, Node o2) {
			return o2.sum - o1.sum;
		}
	}

	public static int[] topKSum(int[] arr1, int[] arr2, int topK) {
		if (arr1 == null || arr2 == null || topK < 1) {
			return null;
		}
		// arr1 50 arr2 20 1000 topk 100万
		topK = Math.min(topK, arr1.length * arr2.length);

		int[] res = new int[topK];
		int resIndex = 0;

		PriorityQueue<Node> maxHeap = new PriorityQueue<>(new MaxHeapComp());

		// set[i][j] == false, arr1[i] arr2[j] 之前，没进过
		// set[i][j] == true; arr1[i] arr2[j] 之前，进过
		boolean[][] set = new boolean[arr1.length][arr2.length];
		int i1 = arr1.length - 1;
		int i2 = arr2.length - 1;
		maxHeap.add(new Node(i1, i2, arr1[i1] + arr2[i2]));
		set[i1][i2] = true;
		while (resIndex != topK) {
			Node curNode = maxHeap.poll();
			res[resIndex++] = curNode.sum;
			i1 = curNode.index1;
			i2 = curNode.index2;
			if (i1 - 1 >= 0 && !set[i1 - 1][i2]) {
				set[i1 - 1][i2] = true;
				maxHeap.add(new Node(i1 - 1, i2, arr1[i1 - 1] + arr2[i2]));
			}
			if (i2 - 1 >= 0 && !set[i1][i2 - 1]) {
				set[i1][i2 - 1] = true;
				maxHeap.add(new Node(i1, i2 - 1, arr1[i1] + arr2[i2 - 1]));
			}
		}
		return res;
	}

	// For test, this method is inefficient but absolutely right
	public static int[] topKSumTest(int[] arr1, int[] arr2, int topK) {
		int[] all = new int[arr1.length * arr2.length];
		int index = 0;
		for (int i = 0; i != arr1.length; i++) {
			for (int j = 0; j != arr2.length; j++) {
				all[index++] = arr1[i] + arr2[j];
			}
		}
		Arrays.sort(all);
		int[] res = new int[Math.min(topK, all.length)];
		index = all.length - 1;
		for (int i = 0; i != res.length; i++) {
			res[i] = all[index--];
		}
		return res;
	}

	public static int[] generateRandomSortArray(int len) {
		int[] res = new int[len];
		for (int i = 0; i != res.length; i++) {
			res[i] = (int) (Math.random() * 50000) + 1;
		}
		Arrays.sort(res);
		return res;
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static boolean isEqual(int[] arr1, int[] arr2) {
		if (arr1 == null || arr2 == null || arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i != arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	// 测试
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

	public static class NodeComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			// TODO Auto-generated method stub
			return o2.sum - o1.sum;
		}

	}

	// 测试
	// 4.给定两个有序数组
	public static int[] topKSumCrossTwoArrays(int[] arr1, int[] arr2, int k) {
		if (arr1 == null || arr2 == null || k < 1) {
			return null;
		}
		int n = arr1.length;
		int m = arr2.length;
		k = Math.min(k, n * m);
		int curIndex = 0;
		boolean[][] selected = new boolean[n][m];
		PriorityQueue<TKSCTANode> heap = new PriorityQueue<TKSCTANode>(new TKSCTANodeComparator());
		heap.add(new TKSCTANode(n - 1, m - 1, arr1[n - 1] + arr2[m - 1]));
		selected[n - 1][m - 1] = true;
		int[] res = new int[k];
		while (curIndex != k) {
			TKSCTANode cur = heap.poll();
			int index1 = cur.index1;
			int index2 = cur.index2;
			res[curIndex++] = cur.sum;
			if (index1 - 1 >= 0 && !selected[index1 - 1][index2]) {
				heap.add(new TKSCTANode(index1 - 1, index2, arr1[index1 - 1] + arr2[index2]));
				selected[index1 - 1][index2] = true;
			}
			if (index2 - 1 >= 0 && !selected[index1][index2 - 1]) {
				heap.add(new TKSCTANode(index1, index2 - 1, arr1[index1] + arr2[index2 - 1]));
				selected[index1][index2 - 1] = true;
			}
		}
		return res;
	}

	public static class TKSCTANode {
		int index1;
		int index2;
		int sum;

		public TKSCTANode(int index1, int index2, int sum) {
			this.index1 = index1;
			this.index2 = index2;
			this.sum = sum;
		}

	}

	public static class TKSCTANodeComparator implements Comparator<TKSCTANode> {

		@Override
		public int compare(TKSCTANode o1, TKSCTANode o2) {
			return o2.sum - o1.sum;
		}

	}

	public static void main(String[] args) {
		int a1Len = 5000;
		int a2Len = 4000;
		int k = 20000000;
		int[] arr1 = generateRandomSortArray(a1Len);
		int[] arr2 = generateRandomSortArray(a2Len);
		long start = System.currentTimeMillis();
		int[] res = topK(arr1, arr2, k);
		long end = System.currentTimeMillis();
		System.out.println(end - start + " ms");

		start = System.currentTimeMillis();
		int[] absolutelyRight = topKSumCrossTwoArrays(arr1, arr2, k);
		end = System.currentTimeMillis();
		System.out.println(end - start + " ms");

		System.out.println(isEqual(res, absolutelyRight));

	}

}
