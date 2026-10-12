package class01;

public class Code01_CordCoverMaxPoint {
	public static int CordMaxCover(int[] arr, int K) {
		if (arr == null || arr.length < 1 || K < 1) {
			return 0;
		}
		int max = 0;
		int left = 0;
		int right = 0;
		while (left < arr.length) {
			while (right < arr.length && arr[right] - arr[left] <= K) {
				right++;
			}
			max = Math.max(max, right - (left++));
		}
		return max;
	}
}
