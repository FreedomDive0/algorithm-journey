package class02;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Review {
	// 1.matrix[][]行列都有序 找num
	// 2. N个机器打包 arr[]每个机器上的物品数 当每个机器上的物品相同时 机器才能开始工作 每一轮工人可以向相邻机器搬运物品 最少几轮可以使机器工作
	// 如果不能使机器工作返回-1
	public static int minTurns(int[] arr) {
		if (arr == null || arr.length < 1) {
			return -1;
		}
		int sum = 0;
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			sum += arr[i];
		}
		if (sum % n != 0) {
			return -1;
		}
		int avr = sum / n;
		int ans = 0;
		int leftSum = 0;
		for (int i = 0; i < n; i++) {
			int leftRest = leftSum - avr * i;
			int rightRest = sum - leftSum - arr[i] - avr * (n - i - 1);
			if (leftRest >= 0 || rightRest >= 0) {
				ans = Math.max(ans, Math.max(Math.abs(leftRest), Math.abs(rightRest)));
			} else {
				ans = Math.max(ans, Math.abs(leftRest) + Math.abs(rightRest));
			}
			leftSum += arr[i];
		}
		return ans;
	}

	// 3.arr[] 长度N 将数组分成两个部分 return Max(|leftMax-rightMax|)
	public static int maxABSBetweenLeftAndRight(int[] arr) {
		if (arr == null || arr.length < 2) {
			return -1;
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
		}
		return Math.max(Math.abs(max - arr[0]), Math.abs(max - arr[arr.length - 1]));
	}

	// 4.arr[] 非负 求柱状图能接多少水
	public static int maxTrappingWater(int[] arr) {
		if (arr == null || arr.length < 3) {
			return -1;
		}
		int left = 1;
		int right = arr.length - 2;
		int leftMax = arr[0];
		int rightMax = arr[arr.length - 1];
		int water = 0;
		while (left != right) {
			if (leftMax <= rightMax) {
				water += Math.max(0, leftMax - arr[left]);
				leftMax = Math.max(leftMax, arr[left++]);
			} else {
				water += Math.max(0, rightMax - arr[right]);
				rightMax = Math.max(rightMax, arr[right--]);
			}
		}
		return water;
	}

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

	// 6.有序数组arr[] 正整数aim
	// 6.1 return累加和为aim的所有二元组
	public static void printUniquePair(int[] arr, int aim) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int left = 0;
		int right = arr.length - 1;
		while (left < right) {
			if (arr[left] + arr[right] < aim) {
				left++;
			} else if (arr[left] + arr[right] > aim) {
				right--;
			} else {
				if (left == 0 || arr[left] != arr[left - 1]) {
					System.out.println(arr[left] + "," + arr[right]);
					left++;
					right--;
				}
			}
		}
	}

	// 6.2 return累加和为aim的所有三元组
	public static void printUniqueTriad(int[] arr, int aim) {
		if (arr == null || arr.length < 3) {
			return;
		}
		for (int i = 0; i < arr.length - 2; i++) {
			int curAim = aim - arr[i];
			int left = i + 1;
			int right = arr.length - 1;
			while (left < right) {
				if (arr[left] + arr[right] < curAim) {
					left++;
				} else if (arr[left] + arr[right] > curAim) {
					right--;
				} else {
					if (left == 0 || arr[left] != arr[left - 1]) {
						System.out.println(arr[i] + "," + arr[left] + "," + arr[right]);
						left++;
						right--;
					}
				}
			}
		}
	}

	// 7.长度为N的arr 可生成N^2个数值对 排序 先一维数字递增 后二维数字递增 给定非负数K 求第K小的数值对
	public static int[] kthMinPair(int[] arr, int k) {
		if (arr == null || arr.length < 1) {
			return null;
		}
		int n = arr.length;
		if (k > n * n) {
			return null;
		}
		int firstNum = getMinKth1(arr, (k - 1) / n);
		int equalNum = 0;
		int lessNum = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == firstNum) {
				equalNum++;
			}
			if (arr[i] < firstNum) {
				lessNum++;
			}
		}
		int secondNum = getMinKth1(arr, (k - lessNum * n - 1) / equalNum);
		return new int[] { firstNum, secondNum };
	}

	public static int getMinKth1(int[] arr, int index) {
		int L = 0;
		int R = arr.length - 1;
		int pivot = 0;
		int[] range = null;
		while (L < R) {
			pivot = arr[L + (int) Math.random() * (R - L + 1)];
			range = partition1(arr, L, R, pivot);
			if (index < range[0]) {
				R = range[0] - 1;
			} else if (index > range[1]) {
				L = range[1] + 1;
			} else {
				return pivot;
			}
		}
		return arr[L];
	}

	public static int[] partition1(int[] arr, int L, int R, int pivot) {
		int less = L - 1;
		int more = R + 1;
		int i = L;
		while (i < more) {
			if (arr[i] < pivot) {
				swap1(arr, ++less, i++);
			} else if (arr[i] > pivot) {
				swap1(arr, i, --more);// 注意大于区范围
			} else {
				i++;
			}
		}
		return new int[] { less + 1, more - 1 };
	}

	public static void swap1(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
}
