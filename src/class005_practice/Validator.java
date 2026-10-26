package class005_practice;

public class Validator {
	public static void main(String[] args) {
		int T = 50000;
		int N = 1000;
		int V = 200;
		for (int i = 0; i < T; i++) {
			int n = (int) (Math.random() * (N + 1));
			int[] arr = randomArr(n, V);
			int[] arr1 = copyArr(arr);
			int[] arr2 = copyArr(arr);
			int[] arr3 = copyArr(arr);
			selectSort(arr1);
			bubbleSort(arr2);
			insertSort(arr3);
			if (!sameArr(arr1, arr2) || !sameArr(arr2, arr3)) {
				printArr(arr, "arr");
				printArr(arr1, "arr1");
				printArr(arr2, "arr2");
				printArr(arr3, "arr3");
				System.out.println("Fucking Fucked");
			}

		}
		System.out.println("Fin");
	}

	// 生成长度为n的 最大值为v的随机数组
	public static int[] randomArr(int n, int v) {
		if (n < 0)
			return null;
		int[] arr = new int[n];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * (v + 1));
		}
		return arr;
	}

	// 复制数组
	public static int[] copyArr(int[] arr) {
		if (arr == null)
			return null;
		int[] copy = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			copy[i] = arr[i];
		}
		return copy;
	}

	// 判断数组是否相同
	public static boolean sameArr(int[] arr1, int[] arr2) {
		if (arr1 == null && arr2 == null)
			return true;
		if (arr1 == null || arr2 == null)
			return false;
		if (arr1.length != arr2.length)
			return false;
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i])
				return false;
		}
		return true;
	}

	// 打印数组
	public static void printArr(int[] arr, String arrName) {
		if (arr == null || arr.length < 1)
			return;
		System.out.print(arrName + ": ");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// 数组交换方法
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	// 选择排序
	public static void selectSort(int[] arr) {
		if (arr == null || arr.length < 2)
			return;
		for (int i = 0; i < arr.length; i++) {
			int minIdx = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[minIdx])
					minIdx = j;
			}
			swap(arr, i, minIdx);
		}
	}

	// 冒泡排序
	public static void bubbleSort(int[] arr) {
		if (arr == null || arr.length < 2)
			return;
		for (int i = arr.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (arr[j] > arr[j + 1])
					swap(arr, j, j + 1);
			}
		}
	}

	// 插入排序
	public static void insertSort(int[] arr) {
		if (arr == null || arr.length < 2)
			return;
		for (int i = 1; i < arr.length; i++) {
			for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
				swap(arr, j - 1, j);
			}
		}
	}
}
