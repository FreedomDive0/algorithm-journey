package topinterviewquestions;

public class Problem_0041_FirstMissingPositive {
	public static int firstMissingPositive(int[] arr) {
		int l = 0;
		int r = arr.length;
		while (l < r) {
			if (arr[l] == l + 1) {// 需要先判断 要不当前位置值会满足arr[arr[l] - 1] == arr[l]发生误判
				l++;
			} else if (arr[l] <= l || arr[l] > r || arr[arr[l] - 1] == arr[l]) {
				arr[l] = arr[--r];
			} else {
				swap(arr, l, arr[l] - 1);
			}
		}
		return l + 1;
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
//	错误版本
//	public static int firstMissingPositive(int[] arr) {
//		int l = 0;
//		int r = arr.length;
//		while (l < r) {
//			if (arr[l] <= l || arr[l] > r || arr[arr[l] - 1] == arr[l]) {
//				arr[l] = arr[--r];
//			} else if (arr[l] == l + 1) {
//				l++;
//			} else {
//				swap(arr, l, arr[l] - 1);
//			}
//		}
//		return l + 1;
//	}
}
