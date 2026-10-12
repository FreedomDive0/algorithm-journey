package 动态规划专题;

public class LC300_最长递增子序列 {
	// 优化二分查找+贪心
	public int lengthOfLIS(int[] nums) {
		int n = nums.length;
		if (n <= 1) {
			return n;
		}
		int[] tails = new int[n];// 该优化的关键
		// tails[k]位置存储 长度为k+1的子序列的最小的结尾元素 该数组是严格递增的
		tails[0] = nums[0];// 先填入第一个元素
		int end = 0;// tails数组最后一个有元素的下标
		for (int i = 1; i < tails.length; i++) {
			if (nums[i] > tails[end]) {// 子序列可以增长
				tails[++end] = nums[i];
			} else {// 找到第一个>=nums[i] 元素的下标进行更新(二分法)
				int left = 0;
				int right = end;
				while (left < right) {
					int mid = left + (right - left) >>> 1;
					if (tails[mid] < nums[i]) {
						left = mid + 1;
					} else {
						right = mid;
					}
				}
				tails[left] = nums[i];
			}
		}
		return end + 1;// 长度为下标+1
	}

	public int lengthOfLIS1(int[] nums) {
		int N = nums.length;
		int[] dp = new int[N];
		for (int i = 0; i < N; i++) {
			dp[i] = 1;
		}
		for (int i = N - 2; i >= 0; i--) {
			int max = Integer.MIN_VALUE;
			for (int j = i; j < dp.length; j++) {
				if (nums[i] >= nums[j]) {
					continue;
				}
				max = Math.max(max, dp[j] + 1);
			}
			dp[i] = max;
		}
		return dp[0];
	}

}
