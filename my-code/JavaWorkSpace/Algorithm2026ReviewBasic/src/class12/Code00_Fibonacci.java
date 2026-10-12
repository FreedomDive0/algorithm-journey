package class12;

public class Code00_Fibonacci {
	// 斐波那契数列
	public static int f(int n) {
		if (n == 1 || n == 2) {
			return 1;
		}
		return f(n - 1) + f(n - 2);
	}
}
