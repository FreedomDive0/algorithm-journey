package 二分专题;

public class LC69_sqrt_x {
//	public static void main(String[] args) {
//
//		System.out.println(mySqrt(2147395599));
//	}
//
//	public static int mySqrt(int x) {
//		int l = 0;
//		int r = x;
//		int res = 0;
//		while (l <= r) {
//			int middle = l + (r - l) / 2;
//			if ((long) middle * middle <= x) {// 有整型数字出现要注意 求和与乘法
//				l = middle + 1;
//				res = middle;
//			} else {
//				r = middle - 1;
//			}
//		}
//		return res;
//	}

	public int mySqrt(int x) {
		int l = 0, r = x;
		int res = 0;
		while (l <= r) {
			int m = l + (r - l) / 2;// 防止l+r数字太大 超出范围
			if ((long) m * m <= x) {// 注意数字范围 乘法与加法
				l = m + 1;
				res = m;
			} else {
				r = m - 1;
			}
		}
		return res;
	}
}
