package class01;

public class Code05_MaxOneBorderSquare {
	public static int maxSquare(int[][] m) {
		if (m == null || m.length < 1 || m[0].length < 1) {
			return 0;
		}
		int r = m.length;
		int c = m[0].length;
		int[][] rightNum = new int[r][c];
		int[][] downNum = new int[r][c];
		int ans = 0;
		for (int i = 0; i < r; i++) {
			rightNum[i][c - 1] = m[i][c - 1];
		}
		for (int j = c - 2; j >= 0; j--) {// (i,j)位置包括自身及右侧有多少个连续的1
			for (int i = 0; i < r; i++) {
				rightNum[i][j] += m[i][j] == 0 ? 0 : rightNum[i][j + 1] + 1;
			}
		}
		for (int j = 0; j < c; j++) {
			downNum[r - 1][j] = m[r - 1][j];
		}
		for (int i = r - 2; i >= 0; i--) {// (i,j)位置包括自身及下侧有多少个连续的1
			for (int j = 0; j < c; j++) {
				downNum[i][j] += m[i][j] == 0 ? 0 : downNum[i + 1][j] + 1;
			}
		}
		for (int k = Math.min(r, c); k > 0; k--) {
			if (hasBorder(rightNum, downNum, k)) {
				ans = Math.max(ans, k);
			}
		}
		return ans;
	}

	public static boolean hasBorder(int[][] rightNum, int[][] downNum, int k) {
		for (int i = 0; i < rightNum.length - k + 1; i++) {// 以(i,j)为正方形的左上角遍历矩阵
			for (int j = 0; j < downNum.length - k + 1; j++) {
				if (rightNum[i][j] >= k && downNum[i][j] >= k && rightNum[i + k - 1][j] >= k
						&& downNum[i][j + k - 1] >= k) {
					return true;
				}
			}
		}
		return false;
	}
}
