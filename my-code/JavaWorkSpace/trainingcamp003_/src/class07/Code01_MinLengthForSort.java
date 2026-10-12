package class07;

public class Code01_MinLengthForSort {
	public static int minSortLength(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int leftMax = arr[0];
		int maxIndex = -1;
		for (int i = 1; i < arr.length; i++) {
			if (leftMax > arr[i]) {// 左侧最大值大于右侧 说明他俩顺序反了记录出错位置i
				maxIndex = i;
			} else {
				leftMax = arr[i];
			}
		}
		if (maxIndex == -1) {// 加速如果没有逆序出现返回0
			return 0;
		}
		int rightMin = arr[maxIndex];
		int minIndex = maxIndex;
		for (int i = maxIndex - 1; i >= 0; i--) {
			if (rightMin < arr[i]) {
				minIndex = i;
			} else {
				rightMin = arr[i];
			}
		}
		return maxIndex - minIndex + 1;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19 };
		System.out.println(minSortLength(arr));

	}
}
