package 数组字符串;

public class Problem_0151_Reverse_Words_in_a_String {
	public String reverseWords(String s) {
		if (s == null || s.length() < 1) {
			return "";
		}
		StringBuilder res = new StringBuilder();
		int index = s.length() - 1, count = 0;
		while (index >= 0) {
			while (index >= 0 && s.charAt(index) == ' ') {
				index--;
			}
			while (index >= 0 && s.charAt(index) != ' ') {
				index--;
				count++;
			}
			res.append(s.substring(index + 1, index + 1 + count) + " ");
			count = 0;
		}
		return res.toString().trim();
	}
//	public String reverseWords(String s) {
//		s = s.trim();
//		List<String> words = new ArrayList<>();
//		int start = 0;
//		int end = 0;
//		while (end < s.length()) {
//			end = s.indexOf(' ', start);
//			if (end == -1) {
//				words.add(s.substring(start, s.length()));
//				break;
//			}
//			words.add(s.substring(start, end));
//			start = end + 1;
//		}
//		StringBuilder res = new StringBuilder();
//		for (int i = words.size() - 1; i >= 0; i--) {
//			if (!words.get(i).equals(" ")) {
//				res.append(words.get(i) + " ");
//			}
//		}
//		return res.toString().trim();
//	}
}
