package class05;

public class Code04_RadixSort {
	public static void radixSort1(int[] arr) {
		if (arr == null || arr.length < 2)
			return;
		int[] help = new int[arr.length];
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
		}
		int digitCount = maxBits(max);
		for (int digit = 1; digit <= digitCount; digit++) {
			int[] count = new int[10];// 每次需要重置Count
			for (int i = 0; i < arr.length; i++) {
				count[getDigit1(arr[i], digit)]++;
			}
			for (int i = 1; i < count.length; i++) {
				count[i] += count[i - 1];// 处理为<=i的数有几个
			}
			for (int i = arr.length - 1; i >= 0; i--) {
				help[--count[getDigit1(arr[i], digit)]] = arr[i];
			}
			for (int i = 0; i < help.length; i++) {
				arr[i] = help[i];
			}
		}
	}

	public static int getDigit1(int num, int digit) {
		return (num / (int) Math.pow(10, digit - 1)) % 10;
	}

	public static int maxBits(int num) {
		int count = 0;
		while (num > 0) {
			count++;
			num /= 10;
		}
		return count;
	}
}
