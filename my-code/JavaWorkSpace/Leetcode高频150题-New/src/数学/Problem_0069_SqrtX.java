package 数学;

public class Problem_0069_SqrtX {
	public int mySqrt(int x) {
		int l = 0;
		int r = x;
		while (l <= r) {
			int m = l + ((r - l) >> 1);
			if ((long) m * m == x) {
				return m;
			} else if ((long) m * m < x) {
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		return r;
	}
}
