package 数组字符串;

public class Problem_0169_MajorityElement {
	public int majorityElement(int[] nums) {
		int cand = 0;
		int hp = 0;
		for (int i = 0; i < nums.length; i++) {
			if (hp == 0) {
				cand = nums[i];
				hp = 1;
			} else {
				if (cand == nums[i]) {
					hp++;
				} else {
					hp--;
				}
			}
		}
		return cand;
	}
}
