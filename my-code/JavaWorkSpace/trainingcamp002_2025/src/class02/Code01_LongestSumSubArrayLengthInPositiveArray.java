package class02;

public class Code01_LongestSumSubArrayLengthInPositiveArray {
	// 返回累加和等于K的最大长度
	public static int getMaxLength(int[] arr, int K) {
		if (arr == null || arr.length < 1) {
			return -1;
		}
		int len = -1;
		int sum = arr[0];
		int l = 0;
		int r = 0;
		while (r < arr.length) {
			if (sum == K) {
				len = Math.max(len, r - l + 1);
				sum -= arr[l++];
			} else if (sum < K) {
				r++;
				if (r == arr.length) {
					break;
				}
				sum += arr[r];
			} else {
				sum -= arr[l++];
			}
		}
		return len;
	}
}
