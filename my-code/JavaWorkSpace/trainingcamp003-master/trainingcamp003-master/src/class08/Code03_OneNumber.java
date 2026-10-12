package class08;

public class Code03_OneNumber {

	public static int solution1(int num) {
		if (num < 1) {
			return 0;
		}
		int count = 0;
		for (int i = 1; i != num + 1; i++) {
			count += get1Nums(i);
		}
		return count;
	}

	public static int get1Nums(int num) {
		int res = 0;
		while (num != 0) {
			if (num % 10 == 1) {
				res++;
			}
			num /= 10;
		}
		return res;
	}

	public static int solution2(int num) {
		if (num < 1) {
			return 0;
		}
		// num -> 13625
		// len = 5位数
		int len = getLenOfNum(num);
		if (len == 1) {
			return 1;
		}
		// num 13625
		// tmp1 10000
		// num 7872328738273
		// tmp1 1000000000000
		int tmp1 = powerBaseOf10(len - 1);
		// num最高位 num / tmp1
		int first = num / tmp1;
		// 最高1 N % tmp1 + 1
		// 最高位first tmp1
		int firstOneNum = first == 1 ? num % tmp1 + 1 : tmp1;
		// 除去最高位之外，剩下1的数量
		// 最高位1 10(k-2次方) * (k-1) * 1
		// 最高位first 10(k-2次方) * (k-1) * first
		int otherOneNum = first * (len - 1) * (tmp1 / 10);
		return firstOneNum + otherOneNum + solution2(num % tmp1);
	}

	public static int getLenOfNum(int num) {
		int len = 0;
		while (num != 0) {
			len++;
			num /= 10;
		}
		return len;
	}

	public static int powerBaseOf10(int base) {
		return (int) Math.pow(10, base);
	}

	// 测试
	public static int oneNumber(int N) {
		if (N < 1) {
			return 0;
		}
		int len = getLen(N);
		if (len < 2) {
			return 1;
		}
		int base = (int) Math.pow(10, len - 1);// 得到1开头 其他位为0 且长度与N相同的数字9999->1000 节省后续计算
		int first = N / base;// 得到最高位的数字
		int firstOne = first == 1 ? N % base + 1 : base;// 首位为1 首位不为1
		int otherOne = first * (len - 1) * (base / 10);
		return firstOne + otherOne + oneNumber(N % base);
	}

	public static int getLen(int N) {// 返回数字位数
		int len = 0;
		while (N > 0) {
			N /= 10;
			len++;
		}
		return len;
	}

	public static void main(String[] args) {
		int num = 50000000;
		long start1 = System.currentTimeMillis();
		System.out.println(oneNumber(num));
		long end1 = System.currentTimeMillis();
		System.out.println("cost time: " + (end1 - start1) + " ms");

		long start2 = System.currentTimeMillis();
		System.out.println(solution2(num));
		long end2 = System.currentTimeMillis();
		System.out.println("cost time: " + (end2 - start2) + " ms");

	}
}
