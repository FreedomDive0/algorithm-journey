package class01;

public class Review {
	public static void main(String[] args) {
		int[] arr = { 9, 8, 3, 0, 4, 6, 8, 7, 7 };
//		bubbleSort(arr);
//		insertSort(arr);
//		selectionSort(arr);
//		selectSort(arr);
//		bubbleeSort(arr);
		insertSort1(arr);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}

	// 冒泡排序
	public static void bubbleSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		for (int i = arr.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (arr[j] > arr[j + 1]) {
					int tmp = arr[j + 1];
					arr[j + 1] = arr[j];
					arr[j] = tmp;
				}
			}
		}
	}

	// 插入排序
	public static void insertSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j > 0; j--) {
				if (arr[j] < arr[j - 1]) {
					int tmp = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = tmp;
				}
			}
		}
	}

	// 选择排序
	public static void selectionSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int index = -1;
		for (int i = 0; i < arr.length; i++) {
			index = arr.length - 1;
			for (int j = arr.length - 1; j >= i; j--) {
				if (arr[index] > arr[j]) {
					index = j;
				}
			}
			int tmp = arr[i];
			arr[i] = arr[index];
			arr[index] = tmp;
		}
	}

	// 选择排序
	public static void selectSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		for (int i = 0; i < arr.length - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < arr.length; j++) {
				minIndex = arr[minIndex] > arr[j] ? j : minIndex;
			}
			swap(arr, i, minIndex);
		}
	}

	// 冒泡排序
	public static void bubbleeSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		for (int i = arr.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (arr[j] > arr[j + 1]) {
					swap(arr, j, j + 1);
				}
			}
		}
	}

	// 插入排序
	public static void insertSort1(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		for (int i = 1; i < arr.length; i++) {
			for (int j = i; j - 1 >= 0 && arr[j - 1] > arr[j]; j--) {
				swap(arr, j - 1, j);
			}
		}
	}

	public static void swap(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
}
