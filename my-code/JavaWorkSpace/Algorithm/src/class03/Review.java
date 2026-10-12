package class03;

public class Review {
//	public static void main(String[] args) {
//		int[] arr = new int[] { 9, 8, 3, 0, 4, 6, 8, 7, 7 };
//		MerSort(arr);
//		for (int i = 0; i < arr.length; i++) {
//			System.out.print(arr[i] + " ");
//		}
//	}
//
////	归并排序
//	public static void MerSort(int[] arr) {
//		if (arr == null || arr.length < 2) {
//			return;
//		}
//		process(arr, 0, arr.length - 1);
//	}
//
//	public static void process(int[] arr, int l, int r) {
//		if (l == r) {
//			return;
//		}
//		int m = l + ((r - l) >> 1);
//		process(arr, l, m);
//		process(arr, m + 1, r);
//		merge(arr, l, m, r);
//	}
//
//	public static void merge(int[] arr, int l, int m, int r) {
//		int[] help = new int[r - l + 1];
//		int index = 0;
//		int lIndex = l;
//		int rIndex = m + 1;
//		while (lIndex <= m && rIndex <= r) {
//			if (arr[lIndex] < arr[rIndex]) {
//				help[index++] = arr[lIndex++];
//			} else {
//				help[index++] = arr[rIndex++];
//			}
//		}
//		while (lIndex <= m) {
//			help[index++] = arr[lIndex++];
//		}
//		while (rIndex <= r) {
//			help[index++] = arr[rIndex++];
//		}
//		for (int i = 0; i < index; i++) {
//			arr[l++] = help[i];
//		}
//	}

//	快速排序
	public static void QuickSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		process(arr, 0, arr.length - 1);
	}

	public static void process(int[] arr, int l, int r) {
		if (l >= r) {// 注意条件
			return;
		}
		int[] res = NetherLand(arr, l, r);
		process(arr, l, res[0]);
		process(arr, res[1], r);
	}

	public static int[] NetherLand(int[] arr, int l, int r) {
//		if (l > r) {
//			return new int[] { -1, -1 };
//		}
//		if (l == r) {
//			return new int[] { l, r };
//		}
		int less = l - 1;
		int more = r;
		int index = l;
		while (index < more) {
			if (arr[index] < arr[r]) {
				swap(arr, index++, ++less);
			} else if (arr[index] == arr[r]) {
				index++;
			} else {
				swap(arr, index, --more);
			}
		}
		swap(arr, index, r);
		return new int[] { less, more + 1 };
	}

	public static void swap(int[] arr, int i, int j) {
		if (arr == null || i == j) {
			return;
		}
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 9, 8, 3, 0, 4, 6, 8, 7, 7 };
//		int[] res = NetherLand(arr, 0, 8);
		QuickSort(arr);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
//		for (int i = 0; i < res.length; i++) {
//			System.out.println(res[i]);
//		}
	}
}
