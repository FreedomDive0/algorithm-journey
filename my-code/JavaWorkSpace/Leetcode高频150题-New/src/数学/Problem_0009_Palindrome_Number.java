package 数学;

public class Problem_0009_Palindrome_Number {
	// 1.转为字符串处理
	public boolean isPalindrome(int x) {
		String s = String.valueOf(x);
		int l = 0, r = s.length();
		while (l < r) {
			if (s.charAt(l++) != s.charAt(r--)) {
				return false;
			}
		}
		return true;
	}

	// 2.数字处理
	public boolean isPalindrome1(int x) {
		if (x < 0 || x % 10 == 0 && x != 0) {// 负数和结尾为0的数不可以
			return false;
		}
		int reverse = 0;
		while (reverse < x) {
			reverse = x % 10 + reverse * 10;
			x /= 10;
		}
		return reverse == x || reverse / 10 == x;
	}
}
