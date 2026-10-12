package topinterviewquestions;

public class Problem_0003_LongestSubstringWithoutRepeatingCharacters {
	// 方法一:
//	public int lengthOfLongestSubstring(String s) {
//		if (s == null || s.length() < 1) {
//			return 0;
//		}
//		int l = 0;
//		int r = 1;
//		int res = 1;// 只有一个字符的时候长度为1
//		while (r < s.length()) {
//			String tmp = s.substring(l, r);
//			String c = String.valueOf(s.charAt(r));
//			if (tmp.contains(c)) {
//				l = tmp.indexOf(c) + l + 1;// l下标更新 newIndex+l+1
//			} else {
//				res = Math.max(res, r - l + 1);
//			}
//			r++;
//		}
//		return res;
//	}
	// 方法二: 以i结尾
	public int lengthOfLongestSubstring(String s) {
		if (s == null || s.length() < 1) {
			return 0;
		}
		char[] str = s.toCharArray();
		int n = s.length();
		int[] map = new int[256];
		for (int i = 0; i < map.length; i++) {
			map[i] = -1;
		}
		int res = 0;
		int pre = -1;// 代替dp[] 以i-1结尾的开头下标
		int cur = 0;
		for (int i = 0; i < n; i++) {
			pre = Math.max(map[str[i]], pre);
			cur = i - pre;
			map[str[i]] = i;
			res = Math.max(res, cur);
		}
		return res;
	}
	// 错误需要把重复之前的map清空掉
//	public static int lengthOfLongestSubstring(String s) {
//		if (s == null || s.length() < 1) {
//			return 0;
//		}
//		char[] str = s.toCharArray();
//		int[] map = new int[256];
//		for (int i = 0; i < map.length; i++) {
//			map[i] = -1;
//		}
//		int res = 0;
//		int length = 0;
//		for (int i = 0; i < str.length; i++) {
//			if (map[str[i]] == -1) {
//				length++;
//				map[str[i]] = i;
//			} else {
//				length = i - map[str[i]];
//				map[str[i]] = i;
//			}
//			res = Math.max(res, length);
//		}
//		return res;
//	}

	public static void main(String[] args) {
		String s = "tmmzuxt";
//		System.out.println(lengthOfLongestSubstring(s));
	}
}
