package 位运算;

public class Problem_0136_SingleNumber {
	public int singleNumber(int[] nums) {
		int n = 0;
		for (int i : nums) {
			n ^= i;
		}
		return n;
	}
}
