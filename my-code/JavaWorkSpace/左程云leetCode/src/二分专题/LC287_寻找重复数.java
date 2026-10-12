package 二分专题;

public class LC287_寻找重复数 {
	public static void main(String[] args) {
		System.out.println(findDuplicate(new int[] { 3, 1, 3, 4, 2 }));
	}

	public static int findDuplicate(int[] nums) {
		int l = 1;
		int n = nums.length;
		int r = n - 1;
		int ans = -1;
		while (l <= r) {// 为了解决数组长度为2的问题 如果将该数组排序 重复数字范围一定会越界越过自己下标
			int mid = (l + r) >> 1;
			int cnt = 0;
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] <= mid) {
					cnt++;
				}
			}
			if (cnt <= mid) {
				l = mid + 1;
			} else {
				r = mid - 1;
				ans = mid;
			}
		}
		return ans;
	}
}
