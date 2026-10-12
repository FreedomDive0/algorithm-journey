package class03;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code01_FindMinKth {
	// 随机取一个数 判断
	public static int minKth1(int[] arr, int k) {
		int[] array = copyArray(arr);
		return process1(array, k - 1, 0, arr.length - 1);
	}

	public static int process1(int[] arr, int k, int L, int R) {
		if (L == R) {
			return arr[L];
		}
		int pivot = arr[L + (int) (Math.random() * (R - L + 1))];
		int[] range = partition(arr, L, R, pivot);
		if (k >= range[0] && k <= range[1]) {
			return arr[k];
		} else if (k < range[0]) {
			return process1(arr, k, L, range[0] - 1);
		} else {
			return process1(arr, k, range[1] + 1, R);
		}
	}

	public static int[] partition(int[] arr, int L, int R, int pivot) {
		int less = L - 1;
		int more = R + 1;
		int index = L;
		while (index < more) {
			if (arr[index] < pivot) {
				swap(arr, index++, ++less);
			} else if (arr[index] == pivot) {
				index++;
			} else {
				swap(arr, index, --more);
			}
		}
		return new int[] { less + 1, more - 1 };
	}

	public static void swap(int[] arr, int x, int y) {
		int tmp = arr[x];
		arr[x] = arr[y];
		arr[y] = tmp;
	}

	public static int[] copyArray(int[] arr) {
		int[] ans = new int[arr.length];
		for (int i = 0; i != ans.length; i++) {
			ans[i] = arr[i];
		}
		return ans;
	}

//	bfprt
	public static int minKth2(int[] arr, int k) {
		int[] array = copyArray(arr);
		return bfprt(array, 0, arr.length - 1, k - 1);
	}

//	bfprt返回在L...R排序后 在k位置的数
	public static int bfprt(int[] arr, int L, int R, int k) {
		if (L == R) {
			return arr[L];
		}
		int pivot = medianOfMedians(arr, L, R);
		int[] range = partition(arr, L, R, pivot);
		if (k >= range[0] && k <= range[1]) {
			return arr[range[0]];
		} else if (k <= range[0]) {
			return bfprt(arr, L, range[0] - 1, k);
		} else {
			return bfprt(arr, range[1] + 1, R, k);
		}
	}

//	找到局部小组中位数的中位数 每次可固定最少淘汰30%的数字
	public static int medianOfMedians(int[] arr, int L, int R) {
		int size = R - L + 1;
		int offset = size % 5 == 0 ? 0 : 1;// 组数
		int[] mid = new int[size / 5 + offset];
		for (int i = 0; i < mid.length; i++) {
			int teamFirst = L + i * 5;
			mid[i] = getMid(arr, teamFirst, Math.min(teamFirst + 4, R));
		}
		return bfprt(mid, 0, mid.length - 1, mid.length / 2);// 速度快
	}

	public static int getMid(int[] arr, int L, int R) {
		insertSort(arr, L, R);
		return arr[(L + R) / 2];
	}

	public static void insertSort(int[] arr, int L, int R) {
		for (int i = L + 1; i <= R; i++) {
			for (int j = i - 1; j >= L && arr[j] > arr[j + 1]; j--) {
				swap(arr, j, j + 1);
			}
		}
	}

	public static class MaxHeapComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o2 - o1;
		}

	}

	// for test
	// 利用大根堆，时间复杂度O(N*logK)
	public static int minKth3(int[] arr, int k) {
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new MaxHeapComparator());
		for (int i = 0; i < k; i++) {
			maxHeap.add(arr[i]);
		}
		for (int i = k; i < arr.length; i++) {
			if (arr[i] < maxHeap.peek()) {
				maxHeap.poll();
				maxHeap.add(arr[i]);
			}
		}
		return maxHeap.peek();
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) (Math.random() * maxSize) + 1];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * (maxValue + 1));
		}
		return arr;
	}

	// 复习
	// 1.找低k小的数

	public static int findKthMin(int[] arr, int k) {
		int[] array = copyArray(arr);
		return bfprt(array, 0, arr.length - 1, k - 1);
	}

	public static int bfprt1(int[] arr, int k, int l, int r) {
		if (l == r) {
			return arr[l];
		}
		int m = getMidArr(arr, l, r);
		int[] range = partition(arr, l, r, m);
		if (k < range[0]) {
			return bfprt1(arr, k, l, range[0] - 1);
		} else if (k > range[1]) {
			return bfprt1(arr, k, range[1] + 1, r);
		} else {
			return arr[range[0]];
		}
	}

//		返回中位数数组的中位数
	public static int getMidArr(int[] arr, int l, int r) {
		int size = r - l + 1;
		int offSet = size % 5 == 0 ? 0 : 1;
		int[] mArr = new int[size / 5 + offSet];
		for (int i = 0; i < mArr.length; i++) {
			int teamFirst = l + i * 5;
			insertSort(arr, teamFirst, Math.min(teamFirst + 4, r));
			mArr[i] = arr[(teamFirst + Math.min(teamFirst + 4, r)) / 2];
		}
		return bfprt1(mArr, mArr.length / 2, 0, mArr.length - 1);
	}

	public static void main(String[] args) {
		int testTime = 1000000;
		int maxSize = 100;
		int maxValue = 100;
		System.out.println("test begin");
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			int k = (int) (Math.random() * arr.length) + 1;
			int ans1 = findKthMin(arr, k);
			int ans2 = minKth2(arr, k);
			int ans3 = minKth3(arr, k);
			if (ans1 != ans2 || ans2 != ans3) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish");
	}

}
