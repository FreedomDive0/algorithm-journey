package class02;

public class Code04_TrappingRainWater {
	// 1维数组接雨水
	public static int trapRain(int[] arr) {
		if (arr == null || arr.length < 3) {
			return 0;
		}
		int left = 1;// 两边无法接住雨水
		int right = arr.length - 2;
		int leftMax = arr[0];// 以左右两边最大值为界 按照小的作为边界
		int rightMax = arr[arr.length - 1];
		int water = 0;
		while (left <= right) {
			if (leftMax <= rightMax) {
				water += Math.max(0, leftMax - arr[left]);
				leftMax = Math.max(leftMax, arr[left++]);
			} else {
				water += Math.max(0, rightMax - arr[right]);
				rightMax = Math.max(rightMax, arr[right--]);
			}
		}
		return water;
	}
}
