package class06;

public class Code01_SplitNumber1 {
	// 1.数字裂开方式
	public static int splitNumber(int N) {
		return f(1, N);
	}

	// pre 上次分裂数字 rest 剩余数字
	public static int f(int pre, int rest) {
		if (rest == 0) {// 剩余数字为0 不需分裂
			return 1;
		}
		if (pre > rest) {//
			return 0;
		}
		int ways = 0;
		for (int i = pre; i <= rest; i++) {
			ways += f(i, rest - i);
		}
		return ways;
	}
}
