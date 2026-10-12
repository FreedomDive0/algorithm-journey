package topinterviewquestions;

public class Problem_0045_JumpGameII {
	public static int jump(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int curMax = 0;
		int step = 0;
		int nextMax = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (i > curMax) {
				step++;
				curMax = nextMax;
			}
			nextMax = Math.max(nextMax, i + arr[i]);
		}
		return step;
	}
}
