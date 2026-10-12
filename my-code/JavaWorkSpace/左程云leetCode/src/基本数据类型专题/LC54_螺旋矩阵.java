package 基本数据类型专题;

import java.util.ArrayList;
import java.util.List;

public class LC54_螺旋矩阵 {
	public static void main(String[] args) {
		List<Integer> arr = spiralOrder(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } });
		for (int i = 0; i < arr.size(); i++) {
			System.out.print(arr.get(i));
		}
	}

	public static List<Integer> spiralOrder(int[][] matrix) {
		ArrayList<Integer> ans = new ArrayList<Integer>();
		if (matrix == null || matrix.length == 0) {
			return ans;
		}
		int m = matrix.length, n = matrix[0].length;
		int count = (Math.min(m, n) + 1) / 2;
		for (int i = 0; i < count; i++) {
			for (int j = i; j < n - i; j++) {
				ans.add(matrix[i][j]);
			}
			for (int j = i + 1; j < m - i; j++) {
				ans.add(matrix[j][n - 1 - i]);
			}
			for (int j = n - 1 - (i + 1); j >= i && (m - 2 * i != 1); j--) {// 只剩一层不打印
				ans.add(matrix[m - 1 - i][j]);
			}
			for (int j = m - 1 - (i + 1); j >= i + 1 && n - 2 * i != 1; j--) {
				ans.add(matrix[j][i]);
			}
		}
		return ans;
	}

}
