package class01;

public class Code05_BSNearRight {
	// 有序数组 <=targetNum最右的位置
	public static int bsNearRight(int[] sortArr, int targetNum) {
		if (sortArr == null || sortArr.length < 1)
			return -1;
		int idx = -1;
		int l = 0;
		int r = sortArr.length - 1;
		int mid = 0;
		while (l <= r) {
			mid = l + ((r - l) >> 1);
			if (sortArr[mid] <= targetNum) {
				idx = mid;
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
		return idx;
	}
}
