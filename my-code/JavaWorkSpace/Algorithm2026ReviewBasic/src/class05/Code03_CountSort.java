package class05;

public class Code03_CountSort {
	// only for 0~200 value
	public static void countSort(int[] arr) {
		if (arr == null || arr.length < 2)
			return;
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
		}
		int[] bucket = new int[max + 1];
		for (int i = 0; i < arr.length; i++) {
			bucket[arr[i]]++;
		}
		int idx = 0;
		for (int i = 0; i < bucket.length; i++) {
			while (bucket[i]-- > 0) {
				arr[idx++] = i;
			}
		}
	}
}
