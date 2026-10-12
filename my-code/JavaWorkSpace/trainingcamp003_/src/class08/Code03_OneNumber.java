package class08;

public class Code03_OneNumber {
	public int countDigitOne(int N) {
		if (N < 1) {
			return 0;
		}
		int len = getLen(N);
		if (len == 1) {
			return 1;
		}
		int base = (int) Math.pow(10, len - 1);// 得到1开头 其他位为0 且长度与N相同的数字9999->1000 节省后续计算
		int first = N / base;// 得到最高位的数字
		int firstOne = first == 1 ? N % base + 1 : base;// 首位为1 首位不为1
		int otherOne = first * (len - 1) * (base / 10);
		return firstOne + otherOne + countDigitOne(N % base);
	}

	public int getLen(int N) {// 返回数字位数
		int len = 0;
		while (N > 0) {
			N /= 10;
			len++;
		}
		return len;
	}

	// 测试
	// 3.给定一个正数N 在纸上写下1~N所有数字 return写了多少个1
	public static int OneNumber(int N) {
		if (N < 1) {
			return 0;
		}
		int len = getLen1(N);
		if (len == 1) {
			return 1;
		}
		int base = (int) Math.pow(10, len - 1);
		int first = N / base;
		int firstOne = first == 1 ? N % base + 1 : base;
		int otherOne = first * (len - 1) * base / 10;
		return firstOne + otherOne + OneNumber(N % base);
	}

	public static int getLen1(int N) {
		int len = 0;
		while (N > 0) {
			len++;
			N /= 10;
		}
		return len;
	}

	public static void main(String[] args) {

	}
}
