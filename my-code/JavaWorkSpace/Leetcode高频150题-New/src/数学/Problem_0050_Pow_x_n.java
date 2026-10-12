package 数学;

public class Problem_0050_Pow_x_n {
	public double myPow(double x, int n) {
		if (n == 0) {
			return 1D;
		}
		int pow = Math.abs(n == Integer.MIN_VALUE ? n + 1 : n);
		double ans = 1D;
		double t = x;
		while (pow > 0) {
			if ((pow & 1) != 0) {
				ans *= t;
			}
			t *= t;
			pow >>= 1;
		}
		if (n == Integer.MIN_VALUE) {
			ans *= x;
		}
		return n > 0 ? ans : (1D / ans);
	}
}
