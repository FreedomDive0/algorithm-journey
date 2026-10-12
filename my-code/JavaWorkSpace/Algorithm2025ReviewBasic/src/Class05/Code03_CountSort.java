package class05;

public class Code03_CountSort {
	// only for 0~200 value
	public static void CountSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int max = Integer.MIN_VALUE;
		for (int num : arr) {
			max = Math.max(num, max);
		}
		int[] bucket = new int[max + 1];
		for (int num : arr) {
			bucket[num]++;
		}
		int idx = 0;
		for (int i = 0; i < bucket.length; i++) {
			while (bucket[i]-- > 0) {
				arr[idx++] = i;
			}
		}
	}
}
