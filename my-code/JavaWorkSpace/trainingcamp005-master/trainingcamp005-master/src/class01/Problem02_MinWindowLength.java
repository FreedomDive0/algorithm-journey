package class01;

public class Problem02_MinWindowLength {

	public static int minLength(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() < s2.length()) {
			return Integer.MAX_VALUE;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int[] map = new int[256]; // map[37] = 4 37 4次
		for (int i = 0; i != str2.length; i++) {
			map[str2[i]]++;
		}
		int left = 0;
		int right = 0;
		int all = str2.length;
		int minLen = Integer.MAX_VALUE;
		// [left, right) [left, right-1] [0,0)
		// R右扩 L ==0 R
		while (right != str1.length) {
			map[str1[right]]--;
			if (map[str1[right]] >= 0) {
				all--;
			}
			if (all == 0) { // 还完了
				while (map[str1[left]] < 0) {
					map[str1[left++]]++;
				}
				// [L..R]
				minLen = Math.min(minLen, right - left + 1);
				all++;
				map[str1[left++]]++;
			}
			right++;
		}
		return minLen == Integer.MAX_VALUE ? 0 : minLen;
	}

	// 测试
	// 字符串str1和str2 求str1子串包含str2所有字符的的最小字串长度
	public static int minSubAll(String str1, String str2) {
		if (str1 == null || str2 == null || str1.length() < str2.length()) {
			return -1;
		}
		int[] map = new int[256];
		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();
		for (int i = 0; i < str2.length(); i++) {
			map[s2[i]]++;
		}
		int L = 0;
		int R = 0;
		int all = str2.length();
		int ans = Integer.MAX_VALUE;
		while (R < str1.length()) {
			map[s1[R]]--;
			if (map[s1[R]] >= 0) {
				all--;
			}
			if (all == 0) {
				while (map[s1[L]] < 0) {
					map[s1[L++]]++;
				}
				all++;
				ans = Math.min(ans, R - L + 1);
				map[s1[L++]]++;
			}
			R++;
		}
		return ans == Integer.MAX_VALUE ? 0 : ans;
	}

	public static void main(String[] args) {
		String str1 = "adabbca";
		String str2 = "acb";
		System.out.println(minLength(str1, str2));
		System.out.println(minSubAll(str1, str2));
	}

}
