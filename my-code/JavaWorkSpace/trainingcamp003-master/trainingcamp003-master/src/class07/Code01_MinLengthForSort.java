package class07;

public class Code01_MinLengthForSort {

	public static int getMinLength(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int min = arr[arr.length - 1];
		int noMinIndex = -1;
		for (int i = arr.length - 2; i != -1; i--) {
			if (arr[i] > min) {
				noMinIndex = i;
			} else {
				min = Math.min(min, arr[i]);
			}
		}
		if (noMinIndex == -1) {
			return 0;
		}
		int max = arr[0];
		int noMaxIndex = -1;
		for (int i = 1; i != arr.length; i++) {
			if (arr[i] < max) {
				noMaxIndex = i;
			} else {
				max = Math.max(max, arr[i]);
			}
		}
		return noMaxIndex - noMinIndex + 1;
	}

	// 测试
	// 1.无序数组 在子数组上排序 就能使整个数组有序 求子数组最短长度
	public static int minLengthForSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int lMax = arr[0];
		int rIndex = -1;
		for (int i = 1; i < arr.length; i++) {
			if (lMax > arr[i]) {
				rIndex = i;
			} else {
				lMax = Math.max(lMax, arr[i]);
			}
		}
		if (rIndex == -1) {
			return 0;
		}
		int rMin = arr[arr.length - 1];
		int lIndex = arr.length - 1;
		for (int i = arr.length - 2; i >= 0; i--) {
			if (rMin < arr[i]) {
				lIndex = i;
			} else {
				rMin = Math.min(rMin, arr[i]);
			}
		}
		return rIndex - lIndex + 1;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19 };
		System.out.println(minLengthForSort(arr));

	}

}
