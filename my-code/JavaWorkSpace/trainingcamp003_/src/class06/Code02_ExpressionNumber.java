package class06;

public class Code02_ExpressionNumber {
	// 暴力递归
	public static int expressGetDesired(String express, boolean desired) {
		if (express == null || express.length() < 1) {
			return 0;
		}
		if (!isVaild(express)) {
			return 0;
		}
		return process(express.toCharArray(), 0, express.length() - 1, desired);
	}

	// 表达式在L...R上有多少种满足desired的表达方式
	public static int process(char[] chs, int L, int R, boolean desired) {
		if (L == R) {
			if (chs[L] == '1') {
				return desired ? 1 : 0;
			} else {
				return desired ? 0 : 1;
			}
		}
		int ans = 0;
		// 用运算符做区分
		if (desired) {
			for (int i = L + 1; i < R; i += 2) {
				switch (chs[i]) {
				case '&':
					ans += process(chs, L, i - 1, true) * process(chs, i + 1, R, true);
					break;
				case '|':
					ans += process(chs, L, i - 1, true) * process(chs, i + 1, R, false);
					ans += process(chs, L, i - 1, true) * process(chs, i + 1, R, true);
					ans += process(chs, L, i - 1, false) * process(chs, i + 1, R, true);
					break;
				case '^':
					ans += process(chs, L, i - 1, true) * process(chs, i + 1, R, false);
					ans += process(chs, L, i - 1, false) * process(chs, i + 1, R, true);
					break;
				}
			}
		} else {
			for (int i = L + 1; i < R; i += 2) {
				switch (chs[i]) {
				case '&':
					ans += process(chs, L, i - 1, false) * process(chs, i + 1, R, true);
					ans += process(chs, L, i - 1, false) * process(chs, i + 1, R, false);
					ans += process(chs, L, i - 1, true) * process(chs, i + 1, R, false);
					break;
				case '|':
					ans += process(chs, L, i - 1, false) * process(chs, i + 1, R, false);
					break;
				case '^':
					ans += process(chs, L, i - 1, false) * process(chs, i + 1, R, false);
					ans += process(chs, L, i - 1, true) * process(chs, i + 1, R, true);
					break;
				}
			}
		}
		return ans;
	}

	public static boolean isVaild(String express) {// 检验表达式合法性
		if ((express.length() & 1) == 0) {// 长度必须为奇数
			return false;
		}
		char[] chs = express.toCharArray();
		for (int i = 0; i < chs.length; i += 2) {// 偶数位置为0或1
			if ((chs[i] != '0') && (chs[i] != '1')) {
				return false;
			}
		}
		for (int i = 1; i < chs.length - 1; i += 2) {// 偶数位置为运算符
			if ((chs[i] != '&') && (chs[i] != '|') && (chs[i] != '^')) {
				return false;
			}
		}
		return true;
	}

	// 动态规划
	public static int dpway(String express, boolean desired) {
		if (express == null || express.length() < 1) {
			return 0;
		}
		if (!isVaild(express)) {
			return 0;
		}
		char[] chs = express.toCharArray();
		int N = chs.length;
		int[][] tdp = new int[N][N];
		int[][] fdp = new int[N][N];
		for (int i = 0; i < N; i += 2) {// 填对角线的值
			tdp[i][i] = chs[i] == '1' ? 1 : 0;
			fdp[i][i] = chs[i] == '0' ? 1 : 0;
		}
		for (int row = N - 3; row >= 0; row -= 2) {// 从下往上填
			for (int col = row + 2; col < N; col += 2) {// 从左往右填 col = row + 2由图像得出
				for (int i = row + 1; i < col; i += 2) {// 用计算符号划分
					switch (chs[i]) {
					case '&':
						tdp[row][col] += tdp[row][i - 1] * tdp[i + 1][col];
						break;
					case '|':
						tdp[row][col] += tdp[row][i - 1] * fdp[i + 1][col];
						tdp[row][col] += fdp[row][i - 1] * tdp[i + 1][col];
						tdp[row][col] += tdp[row][i - 1] * tdp[i + 1][col];
						break;
					case '^':
						tdp[row][col] += tdp[row][i - 1] * fdp[i + 1][col];
						tdp[row][col] += fdp[row][i - 1] * tdp[i + 1][col];
						break;
					}
					switch (chs[i]) {
					case '&':
						fdp[row][col] += fdp[row][i - 1] * tdp[i + 1][col];
						fdp[row][col] += tdp[row][i - 1] * fdp[i + 1][col];
						fdp[row][col] += fdp[row][i - 1] * fdp[i + 1][col];
						break;
					case '|':
						fdp[row][col] += fdp[row][i - 1] * fdp[i + 1][col];
						break;
					case '^':
						fdp[row][col] += tdp[row][i - 1] * tdp[i + 1][col];
						fdp[row][col] += fdp[row][i - 1] * fdp[i + 1][col];
						break;
					}
				}
			}
		}
		return desired ? tdp[0][N - 1] : fdp[0][N - 1];
	}

	public static void main(String[] args) {
		String express = "1^0&0|1&1^0&0^1|0|1&1";
		boolean desired = true;
		System.out.println(expressGetDesired(express, desired));
//		System.out.println(num2(express, desired));
		System.out.println(dpway(express, desired));
//		System.out.println(dpLive(express, desired));
	}
}
