package 双指针;

public class Problem_0125_Valid_Palindrome {
	public boolean isPalindrome(String s) {
		if (s == null || s.length() < 2) {
			return true;
		}
		int l = 0, r = s.length() - 1;
		s = s.toLowerCase();
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
				str.append(s.charAt(i));
			}
		}
		s = str.toString();
		while (l < r) {
			if (s.charAt(l) != s.charAt(r)) {
				return false;
			}
			l++;
			r--;
		}
		return true;
	}
}
