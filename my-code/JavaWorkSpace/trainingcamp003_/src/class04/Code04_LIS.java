package class04;

public class Code04_LIS {
	public static int LargestIncreasSub(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		int[] ends = new int[arr.length];// i位置表示 长度为i+1的递增子序列的最小结尾
		int tail = 0;
		ends[0] = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > ends[tail]) {// 根据ends的性质 ends内数据为递增 寻找>=最小的 floor
				ends[++tail] = arr[i];
			} else {
				int left = 0;
				int right = tail;
				while (left < right) {
					int mid = left + ((right - left) >> 1);
					if (ends[mid] < arr[i]) {
						left = mid + 1;
					} else {
						right = mid;
					}
				}
				ends[left] = arr[i];// 相邻两个数和为奇数 mid其实==left 并且>=时right向左移动不会影响left
			}
		}
		return tail + 1;
	}
}
