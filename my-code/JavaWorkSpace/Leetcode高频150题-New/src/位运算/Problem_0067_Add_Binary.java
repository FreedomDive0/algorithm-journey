package 位运算;

public class Problem_0067_Add_Binary {
	public String addBinary(String a, String b) {
		int n = a.length(), m = b.length();
		if (n > m) {
			return addBinary(b, a);
		}
		char[] s = new char[m + 1];
		int offset = 0;
		int index = m;
		int index1 = n - 1;
		int index2 = m - 1;
		while (index1 >= 0 || index2 >= 0 || offset != 0) {
			int sum = offset;
			sum += index1 >= 0 ? a.charAt(index1--) - '0' : 0;
			sum += index2 >= 0 ? b.charAt(index2--) - '0' : 0;
			s[index--] = (char) ((sum & 1) + '0');
			offset = sum >> 1;
		}
		if (s[0] != 0) {// char 0为''空
			return new String(s);
		}
		return new String(s, 1, s.length - 1);
	}

	// 二进制转化函数 Integer.parseInt(数字, 进制)
//	public String addBinary(String a, String b) {
//		char[] num1 = a.toCharArray();
//		char[] num2 = b.toCharArray();
//		int index1 = num1.length - 1;
//		int index2 = num2.length - 1;
//		StringBuilder s = new StringBuilder();
//		boolean odd = false;
//		while (index1 >= 0 && index2 >= 0) {
//			if (odd) {
//				if (num1[index1] == '1' && num2[index2] == '1') {
//					odd = true;
//					s.append("1");
//				} else if (num1[index1] == '1' || num2[index2] == '1') {
//					odd = true;
//					s.append("0");
//				} else {
//					odd = false;
//					s.append("1");
//				}
//			} else {
//				if (num1[index1] == '1' && num2[index2] == '1') {
//					odd = true;
//					s.append("0");
//				} else if (num1[index1] == '1' || num2[index2] == '1') {
//					odd = false;
//					s.append("1");
//				} else {
//					odd = false;
//					s.append("0");
//				}
//			}
//			index1--;
//			index2--;
//		}
//		while (index1 >= 0) {
//			if (odd) {
//				if (num1[index1] == '1') {
//					odd = true;
//					s.append("0");
//				} else {
//					odd = false;
//					s.append("1");
//				}
//			} else {
//				if (num1[index1] == '1') {
//					odd = false;
//					s.append("1");
//				} else {
//					odd = false;
//					s.append("0");
//				}
//			}
//			index1--;
//		}
//		while (index2 >= 0) {
//			if (odd) {
//				if (num2[index2] == '1') {
//					odd = true;
//					s.append("0");
//				} else {
//					odd = false;
//					s.append("1");
//				}
//			} else {
//				if (num2[index2] == '1') {
//					odd = false;
//					s.append("1");
//				} else {
//					odd = false;
//					s.append("0");
//				}
//			}
//			index2--;
//		}
//		if (odd) {
//			s.append("1");
//		}
//		return s.reverse().toString();
//	}
	public static void main(String[] args) {
		char a = 0;
		System.out.println(a);
	}
}
