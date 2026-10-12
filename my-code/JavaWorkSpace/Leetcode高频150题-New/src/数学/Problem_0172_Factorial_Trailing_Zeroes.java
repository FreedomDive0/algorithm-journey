package 数学;

public class Problem_0172_Factorial_Trailing_Zeroes {
	public int trailingZeroes(int n) {
		int res = 0;
		while (n != 0) {
			n /= 5;// 5因子数 变为25后5因子数多一 以此类推
			res += n;
		}
		return res;
	}
}
