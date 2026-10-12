package topinterviewquestions;

public class Problem_0026_RemoveDuplicatesFromSortedArray {
	public static int removeDuplicates(int[] nums) {
		if (nums == null || nums.length < 1) {
			return 0;
		}
		if (nums.length < 2) {
			return 1;
		}
		int done = 0;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] != nums[done]) {
				nums[++done] = nums[i];
			}
		}
		return done + 1;
	}
}
