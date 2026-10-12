package class02;

public class Code03_MaxABSBetweenLeftAndRight {
	// arr长度为N 0~小于N的前缀为左部分 剩余为右部分 求max|max左-max右|
	public static int maxAbs(int[] arr) {// 流程优化的体
		if (arr == null || arr.length < 2) {
			return -1;
		}
		int max = Integer.MIN_VALUE;
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			max = Math.max(max, arr[i]);// 无论怎么划分左右max总存在其中之一
		}
		// 只需判断0 和 n位置谁小取谁
		return Math.abs(max - Math.min(arr[0], arr[n - 1]));
	}
}
