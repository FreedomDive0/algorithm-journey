package 字符串专题;

public class LC151_颠倒字符串中的单词 {
	public static void main(String[] args) {
		System.out.println(reverseWords("a good   example"));
	}

	public static String reverseWords(String s) {
		if (s == null) {
			return s;
		}
		int index = s.length() - 1, count = 0;
		StringBuilder res = new StringBuilder();
		while (index >= 0) {
			while (index >= 0 && s.charAt(index) == ' ') {// 去空格
				index--;
			}
			while (index >= 0 && s.charAt(index) != ' ') {// 找单词
				index--;
				count++;
			}
			res.append(s.substring(index + 1, index + count + 1) + " ");
			count = 0;
		}
		return res.toString().trim();
	}
}
