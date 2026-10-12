package topinterviewquestions;

public class Problem_0042_TrappingRainWater {
	public static int trap(int[] arr) {
		if (arr == null || arr.length < 3) {
			return 0;
		}
		int l = 1;
		int r = arr.length - 2;
		int leftMax = arr[0];
		int rightMax = arr[r + 1];
		int water = 0;
		while (l <= r) {
			if (leftMax <= rightMax) {
				water += Math.max(0, leftMax - arr[l]);
				leftMax = Math.max(leftMax, arr[l++]);
			} else {
				water += Math.max(0, rightMax - arr[r]);
				rightMax = Math.max(rightMax, arr[r--]);
			}
		}
		return water;
	}
}
