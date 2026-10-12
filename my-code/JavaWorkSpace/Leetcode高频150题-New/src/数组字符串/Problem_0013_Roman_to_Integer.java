package 数组字符串;

public class Problem_0013_Roman_to_Integer {
	public int romanToInt(String s) {
		int[] nums = new int[s.length()];
		char[] chs = s.toCharArray();
		for (int i = 0; i < chs.length; i++) {
			switch (chs[i]) {
			case 'I':
				nums[i] = 1;
				break;
			case 'V':
				nums[i] = 5;
				break;
			case 'X':
				nums[i] = 10;
				break;
			case 'L':
				nums[i] = 50;
				break;
			case 'C':
				nums[i] = 100;
				break;
			case 'D':
				nums[i] = 500;
				break;
			case 'M':
				nums[i] = 1000;
				break;
			}
		}
		int ans = nums[chs.length - 1];
		for (int i = chs.length - 2; i >= 0; i++) {
			ans += nums[i] < nums[i + 1] ? -nums[i] : nums[i];
		}
		return ans;
	}
}
