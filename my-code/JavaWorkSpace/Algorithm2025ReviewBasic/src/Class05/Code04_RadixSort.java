package class05;

public class Code04_RadixSort {
	public static void RadixSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int maxDigitCount = getMaxDigit(arr);
		int[] bucket = new int[arr.length];
		for (int d = 1; d <= maxDigitCount; d++) {
			int[] count = new int[10];
			for (int num : arr) {
				int digitNum = getDigitNum(num, d);
				count[digitNum]++;
			}
			for (int i = 1; i < count.length; i++) {
				count[i] += count[i - 1];
			}
			for (int i = arr.length - 1; i >= 0; i--) {
				bucket[--count[getDigitNum(arr[i], d)]] = arr[i];
			}
			for (int i = 0; i < bucket.length; i++) {
				arr[i] = bucket[i];
			}
		}
	}

	public static int getMaxDigit(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
		}
		int digitCount = 0;
		while (max != 0) {
			digitCount++;
			max /= 10;
		}
		return digitCount;
	}

	public static int getDigitNum(int num, int d) {
//		while (--d > 0) {
//			num /= 10;
//		}
//		return num % 10;
		return (num / (int) (Math.pow(10, d - 1))) % 10;// 优化
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 91, 82, 33, 04, 45, 65, 85, 75, 75 };
		RadixSort(arr);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}

}
