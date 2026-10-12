package topinterviewquestions;

public class Problem_0008_StringToInteger {
	// 一般转为负数解决 因为负数绝对值比正数绝对值大 可以容纳更多范围
	// 2147483647
	// -2147483648
	// -Integer.MIN_VALUE == Integer.MIN_VALUE;
	// 系统最小10000... -系统最小 二进制取反+1 还是自己
	public int myAtoi(String s) {
		if (s == null || s.length() < 1) {
			return 0;
		}
		s = s.trim();
		char[] str = s.toCharArray();
		long res = 0;// long防止了越界 在同一范围内使用负数做防止越界
		int pos = -1;
		boolean flag = false;
		for (int i = 0; i < str.length; i++) {
			int num = 0;
			if (str[i] >= '0' && str[i] <= '9') {
				num = str[i] - '0';
				res = res * 10 + num;
				if (pos <= 0 && res > Integer.MAX_VALUE) {
					return Integer.MAX_VALUE;
				} else if (pos > 0 && -res < Integer.MIN_VALUE) {
					return Integer.MIN_VALUE;
				}
				flag = true;
			} else if ((str[i] == '-' || str[i] == '+') && pos == -1 && !flag) {
				pos = str[i] == '-' ? 1 : 0;
				flag = true;
			} else {
				break;
			}
		}
		return pos <= 0 ? (int) res : (int) -res;
	}

	public static void main(String[] args) {
//		System.out.println(myAtoi("   -42"));
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
	}
}
