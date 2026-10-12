package class01;

public class Code01_AppleMinBags {
	// 暴力法简单输入输出 观察得出结论 利用结论编写
	public static int minBags1(int n) {
		if (n <= 5) {
			return -1;
		}
		int bbag = n / 8;
		int bags = -1;
		for (int i = bbag; i >= 0; i--) {
			for (int j = 0; i * 8 + 6 * j <= n; j++) {
				if (i * 8 + 6 * j == n) {
					bags = i + j;
				}
				if (n - i * 8 >= 24) {
					break;
				}
			}
		}
		return bags;
	}

	public static int minBags2(int n) {
		if (n % 2 == 1) {
			return -1;
		}
		if (n < 18) {
			return n == 6 || n == 8 ? 1 : (n == 12 || n == 14 || n == 16) ? 2 : -1;
		}
		return (n - 18) / 8 + 3;
	}

	// 复习 2025
	// 1.1暴力尝试
	public static int appleBruteForce(int N) {
		if ((N & 1) != 0 || N < 6) {
			return -1;
		}
		int eightMax = N / 8;
		for (int i = eightMax; i >= 0; i--) {// 袋子应该从大到小尝试
			for (int j = 0; i * 8 + j * 6 <= N; j++) {
				if (i * 8 + j * 6 == N) {
					return i + j;
				}
			}
		}
		return -1;
	}

	// 1.2 打表法
	public static int apple(int N) {
		if ((N & 1) != 0) {
			return -1;
		}
		if (N < 18) {
			return (N == 6 || N == 8) ? 1 : (N == 12 || N == 14 || N == 16) ? 2 : -1;
		}
		return (N - 18) / 8 + 3;
	}

	public static void main(String[] args) {
//		for (int i = 1; i <= 100; i++) {
//			System.out.println(i + " : " + appleBruteForce(i));
//			System.out.println(i + " : " + Apple(i));
//		}
		for (int i = 1; i < 100; i++) {
			int a = apple(i);
			int b = minBags2(i);
			if (a != b) {
				System.out.println("Oops");
				break;
			}
//			System.out.println(i + " :" + a);
//			System.out.println(i + " :" + b);
		}
	}
}
