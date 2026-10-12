package 字符串专题;

public class LC38_外观数列 {
	public static void main(String[] args) {
		countAndSay(4);
	}

//	public static String countAndSay(int n) {
//		if (n == 1) {
//			return "1";
//		}
//		String last = countAndSay(n - 1);
//		char[] str = last.toCharArray();
//		char c = str[0];
//		int index = 1;
//		int count = 1;
//		String res = "";
//		while (index < str.length) {
//			if (str[index] == c) {
//				count++;
//			} else {
//				res += String.valueOf(count) + c;
//				c = str[index];
//				count = 1;
//			}
//			index++;
//		}
//		res += String.valueOf(count) + c;
//		return res;
//	}
	public static String countAndSay(int n) {
		if (n == 1) {
			return "1";
		}
		String last = countAndSay(n - 1);
		StringBuilder res = new StringBuilder();
		int i = 0, j = 1, len = last.length();// i,j双指针
		while (j < len) {// j控制边界
			if (last.charAt(i) != last.charAt(j)) {
				res.append(j - i).append(last.charAt(i));
				i = j;
			}
			j++;
		}
		res.append(j - i).append(last.charAt(i));
		return res.toString();
	}
}
