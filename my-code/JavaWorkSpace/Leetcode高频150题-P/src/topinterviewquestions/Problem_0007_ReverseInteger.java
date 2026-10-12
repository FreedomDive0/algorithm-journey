package topinterviewquestions;

public class Problem_0007_ReverseInteger {
	public static int reverse(int x) {
		int res = 0;
		while (x != 0) {
			if (res > Integer.MAX_VALUE / 10 || res < Integer.MIN_VALUE / 10) {// 提前1位 超过就不要继续判断了 因为后续数字就出现错误了
				return 0;
			}
			int num = x % 10;
			res = res * 10 + num;
			x /= 10;
		}
		return res;
	}

	public static void main(String[] args) {
		System.out.println(reverse(-123));
	}
}
