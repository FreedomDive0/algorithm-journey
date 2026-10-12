package 一维动态规划;

public class Problem_0300_Longest_Increasing_Subsequence {
	public int lengthOfLIS(int[] nums) {
		int n = nums.length;
		int[] end = new int[n];
		int R = -1;
		for (int i = 0; i < n; i++) {
			int index = findIndex(end, 0, R, nums[i]);
			if (index == -1) {
				end[++R] = nums[i];
			} else {
				end[index] = nums[i];
			}
		}
		return R + 1;
	}

	public static int findIndex(int[] end, int L, int R, int target) {
		int l = L;
		int r = R;
		int index = R;
		while (l <= r) {
			int mid = (r + l) / 2;
			if (end[mid] >= target) {
				index = mid;
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		return l > R ? -1 : index;
	}

	public static void main(String[] args) {
		int[] nums = new int[] { 1, 2, 3 };
		System.out.println(findIndex(nums, 0, 2, 4));
	}
}
