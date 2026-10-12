package class01;

import java.util.TreeSet;

public class Code02_MaxSubArraySumLessOrEqualK {
	public static int maxSubArraySumFooler(int[] arr, int k) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		TreeSet<Integer> set = new TreeSet<>();
		set.add(0);
		int sum = 0;
		int ans = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			if (set.ceiling(sum - k) != null) {// >=sum-k最小值
				ans = Math.max(ans, sum - set.ceiling(sum - k));
			}
			set.add(sum);
		}
		return ans;
	}
}
