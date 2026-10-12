package 位运算;

public class Problem_0137_Single_Number_II {
	public int singleNumber(int[] nums) {
		int[] counts = new int[32];
		for (int num : nums) {
			for (int j = 0; j < 32; j++) {
				counts[j] += (num & 1);
				num >>>= 1;
			}
		}
		int res = 0;
		for (int i = 0; i < 32; i++) {
			res <<= 1;
			res += (counts[31 - i] % 3);// 二进制每个位3的余数就是要找数字的二进制位

		}
		return res;
	}
}
