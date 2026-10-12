package class02;

public class Code03_LongestLessSumSubArrayLength {
	// 正负零 子数组累加和<=k 的最大子数组长度
	public static int longestLessSumSubArrayLength(int[] arr, int k) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		int n = arr.length;
		int[] minSum = new int[n];
		int[] minIdx = new int[n];
		minSum[n - 1] = arr[n - 1];
		minIdx[n - 1] = n - 1;
		for (int i = n - 2; i >= 0; i--) {
			minSum[i] = arr[i] + Math.min(0, minSum[i + 1]);
			minIdx[i] = minSum[i + 1] <= 0 ? minIdx[i + 1] : i;
		}
		int l = 0;
		int r = minIdx[0];
		int sum = minSum[0];
		int len = 0;
		while (r < n) {
			if (sum <= k) {
				len = Math.max(len, r - l + 1);
				if (r + 1 == n) {
					break;
				}
				sum += minSum[r + 1];
				r = minIdx[r + 1];
			} else {
				sum -= arr[l++];
				if (l == n) {
					break;
				}
			}
		}
		return len;
	}

	public static void main(String[] args) {
		int[] arr1 = new int[] { 3, -2, 5, -1 };
		System.out.println(longestLessSumSubArrayLength(arr1, 4));
	}
}
