package Tools;

public class Tools {
	// 生成随机数组
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random());
		}
		return arr;
	}

	// 打印数组
	public static void printArr(int[] arr) {
		if (arr == null)
			return;
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// 复制数组
	public static int[] copyArr(int[] arr) {
		if (arr == null)
			return null;
		int[] copyArr = new int[arr.length];
		for (int i = 0; i < copyArr.length; i++) {
			copyArr[i] = arr[i];
		}
		return copyArr;
	}

	// 判断数组是否相等
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if (arr1 == null && arr2 != null || arr2 == null && arr1 != null)
			return false;
		if (arr1 == null && arr2 == null)
			return true;
		if (arr1.length != arr2.length)
			return false;
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i])
				return false;
		}
		return true;
	}

	// 数组交换
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
