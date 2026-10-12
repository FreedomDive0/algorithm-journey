package class02;

public class Code06_PrintUniquePairAndTriad {
	// 有序数组arr aim 返回所有和为aim的二元组
	public static void pair(int[] arr, int aim) {
		if (arr == null || arr.length < 1) {
			return;
		}
		int left = 0;
		int right = arr.length - 1;
		while (left < right) {
			if (arr[left] + arr[right] < aim) {
				left++;
			} else if (arr[left] + arr[right] > aim) {
				right--;
			} else {
				if (left == 0 || (left > 0 && arr[left] != arr[left - 1]))
					System.out.println("(" + arr[left++] + "," + arr[right--] + ")");
			}
		}
	}

	// 三元组
	public static void triad(int[] arr, int aim) {
		if (arr == null || arr.length < 1) {
			return;
		}
		for (int i = 0; i < arr.length - 2; i++) {
			int curAim = aim - arr[i];
			int left = i + 1;
			int right = arr.length - 1;
			while (left < right) {
				if (arr[left] + arr[right] < curAim) {
					left++;
				} else if (arr[left] + arr[right] > curAim) {
					right--;
				} else {
					if ((i == 0 || (i > 0 && arr[i] != arr[i - 1]))
							&& (left == 0 || (left > 0 && arr[left] != arr[left - 1])))
						System.out.println("(" + arr[i] + "," + arr[left++] + "," + arr[right--] + ")");
				}
			}
		}
	}
}
