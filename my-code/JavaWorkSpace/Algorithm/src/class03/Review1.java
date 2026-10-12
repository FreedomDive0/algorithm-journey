package class03;

public class Review1 {
	// 1.归并排序
	// (1)递归实现
	public static void MergeSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		process(arr, 0, arr.length - 1);
	}

	public static void process(int[] arr, int l, int r) {
		if (l == r) {
			return;
		}
		int mid = l + ((r - l) >> 1);
		process(arr, l, mid);
		process(arr, mid + 1, r);
		merge(arr, l, mid, r);
	}

	public static void merge(int[] arr, int l, int mid, int r) {
		int[] help = new int[r - l + 1];// 导入help数组 比一个数组内移动更快
		int p1 = l;// 左右两部分下标
		int p2 = mid + 1;
		int index = 0;// help下标
		while (p1 <= mid && p2 <= r) {// 两部分未越界 谁小取谁
			help[index++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
		}
		while (p1 <= mid) {// 将两数组中剩余数字导入help
			help[index++] = arr[p1++];
		}
		while (p2 <= r) {
			help[index++] = arr[p2++];
		}
		for (int i = 0; i < help.length; i++) {// 放回原数组
			arr[l + i] = help[i];
		}
	}

	// (2)非递归实现
	public static void mergeSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int mergeSize = 1;// merge左右部分大小
		int N = arr.length;
		while (mergeSize < N) {// mergeSize范围
			int L = 0;
			while (L < N) {// 每一小组的下标
				int M = L + mergeSize - 1;// 使用中点坐标而不使用右边界 是因为中点越界后不需要考虑右边界
				if (M >= N) {
					break;
				}
				int R = Math.min(M + mergeSize, N - 1);// 确定右边界R
				merge(arr, L, M, R);
				L = R + 1;// 更新左坐标
			}
			if (mergeSize > N / 2) {// 看似冗余 为了防止mergeSize超越int范围 当mergeSize很接近int最大值 左移后可能溢出
				break;
			}
			mergeSize <<= 1;// 更新左右部分大小
		}
	}

	// Q1 数组的小和
	public static int arrSmallSum(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		return processASS(arr, 0, arr.length - 1);
	}

	public static int processASS(int[] arr, int l, int r) {
		if (l == r) {
			return 0;
		}
		int mid = l + ((r - l) >> 1);
		return processASS(arr, l, mid) + processASS(arr, mid + 1, r) + mergeASS(arr, l, mid, r);
	}

	public static int mergeASS(int[] arr, int l, int mid, int r) {
		int[] help = new int[r - l + 1];
		int p1 = l;
		int p2 = mid + 1;
		int index = 0;
		int res = 0;
		while (p1 <= mid && p2 <= r) {
			res += arr[p1] < arr[p2] ? arr[p1] * (r - p2 + 1) : 0;// 利用归并排序优势：把大型问题转化为小问题解决 排序后方便操作和计算
			help[index++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
		}
		while (p1 <= mid) {
			help[index++] = arr[p1++];
		}
		while (p2 <= r) {
			help[index++] = arr[p2++];
		}
		for (int i = 0; i < help.length; i++) {
			arr[l + i] = help[i];
		}
		return res;
	}

	// Q2 逆序对
	public static int ReverseOrder(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		return processRO(arr, 0, arr.length - 1);
	}

	public static int processRO(int[] arr, int l, int r) {
		if (l == r) {
			return 0;
		}
		int m = l + ((r - l) >> 1);
		return processRO(arr, l, m) + processRO(arr, m + 1, r) + mergeRO(arr, l, m, r);
	}

	public static int mergeRO(int[] arr, int l, int m, int r) {
		int[] help = new int[r - l + 1];
		int p1 = l;
		int p2 = m + 1;
		int res = 0;
		int index = 0;
		while (p1 <= m && p2 <= r) {
			res += arr[p1] > arr[p2] ? m - p1 + 1 : 0;
			help[index++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];// 左右值相同时 左边先进
		}
		while (p1 <= m) {
			help[index++] = arr[p1++];
		}
		while (p2 <= r) {
			help[index++] = arr[p2++];
		}
		for (int i = 0; i < help.length; i++) {
			arr[l + i] = help[i];
		}
		System.out.println("l：" + l + " " + "r：" + r + " " + "res：" + res);
		return res;
	}

	// 快排 QuickSort
	// Partition
	public static int partition(int[] arr, int l, int r) {
		if (l > r) {
			return -1;
		}
		if (l == r) {
			return l;
		}
		int lessEqual = l - 1;
		int index = l;
		while (index < r) {
			if (arr[index] <= arr[r]) {
				swap(arr, ++lessEqual, index);
			}
			index++;
		}
		swap(arr, ++lessEqual, r);
		return lessEqual;
	}

	// 荷兰国旗
	public static int[] NetherLand(int[] arr, int l, int r) {
		if (l > r) {
			return new int[] { -1, -1 };
		}
		if (l == r) {
			return new int[] { l, l };
		}
		int less = l - 1;
		int more = r;
		int index = l;
		while (index < more) {// 最多到达大于区 大于区已操作
			if (arr[index] < arr[r]) {
				swap(arr, index++, ++less);
			} else if (arr[index] > arr[r]) {
				swap(arr, index, --more);
			} else {
				index++;
			}
		}
		swap(arr, more, r);
		return new int[] { less + 1, more };
	}

	// QuickSort1.0
	public static void QuickSort1(int[] arr) {
		if (arr == null || arr.length < 1) {
			return;
		}
		processq1(arr, 0, arr.length - 1);
	}

	public static void processq1(int[] arr, int l, int r) {
		if (l >= r) {
			return;
		}
		int target = partition(arr, l, r);
		processq1(arr, l, target - 1);
		processq1(arr, target + 1, r);
	}

	// QuickSort2.0
	public static void QuickSort2(int[] arr) {
		if (arr == null || arr.length < 1) {
			return;
		}
		processq2(arr, 0, arr.length - 1);
	}

	public static void processq2(int[] arr, int l, int r) {
		if (l >= r) {
			return;
		}
		int[] equal = NetherLand(arr, l, r);
		processq2(arr, l, equal[0] - 1);
		processq2(arr, equal[1] + 1, r);
	}

	// QuickSort3.0 随机快排
	public static void QuickSort3(int[] arr) {
		if (arr == null || arr.length < 1) {
			return;
		}
		processq3(arr, 0, arr.length - 1);
	}

	public static void processq3(int[] arr, int l, int r) {
		if (l >= r) {
			return;
		}
		swap(arr, l + (int) Math.random() * (r - l + 1), r);
		int[] equal = NetherLand(arr, l, r);
		processq3(arr, l, equal[0] - 1);
		processq3(arr, equal[1] + 1, r);
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public static void main(String[] args) {
		int[] arr = { 9, 8, 3, 0, 4, 6, 8, 7, 7 };
		int[] arr1 = { 9, 8, 3, 0, 4, 6, 8, 7, 7 };
//		mergeSort(arr);
//		MergeSort(arr1);
		int[] arr2 = { 9, 6, 5, 3, 2, 1, 4, 3 };
		System.out.println(partition(arr2, 0, arr2.length - 1));
		for (int i = 0; i < arr2.length; i++) {
			System.out.print(arr2[i] + "  ");
		}
		System.out.println();
		int[] arr3 = { 9, 6, 5, 3, 2, 1, 4, 3 };
		System.out.println(ReverseOrder(arr3));
		int[] arr4 = { 9, 6, 5, 3, 2, 1, 4, 3 };
		int[] a = NetherLand(arr4, 0, arr4.length - 1);
		System.out.println(a[0] + " " + a[1]);
		for (int i = 0; i < 100; i++) {
			System.out.println((int) Math.random() * 10);
		}
	}

}
