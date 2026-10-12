package topinterviewquestions;

public class Problem_0013_RomanToInteger {
	public static int romanToInt(String s) {
		char[] str = s.toCharArray();
		int[] nums = new int[str.length];
		for (int i = 0; i < nums.length; i++) {
			switch (str[i]) {
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
		int res = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			res += nums[i] >= nums[i + 1] ? nums[i] : -nums[i];
		}
		return res + nums[nums.length - 1];
	}
}
