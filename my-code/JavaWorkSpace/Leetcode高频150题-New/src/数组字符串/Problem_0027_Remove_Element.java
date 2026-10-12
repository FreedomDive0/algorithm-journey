package 数组字符串;

public class Problem_0027_Remove_Element {
	public int removeElement(int[] nums, int val) {
		if (nums == null || nums.length < 1) {
			return 0;
		}
		int L = 0;
		int R = nums.length;
		while (L < R) {
			if (nums[L] == val) {
				nums[L] = nums[--R];
			} else {
				L++;
			}
		}
		return L;
	}
}
