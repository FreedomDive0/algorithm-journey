package 二分查找;

public class Problem_0074_Search_a_2D_Matrix {
	public boolean searchMatrix(int[][] matrix, int target) {
		int l = 0;
		int r = matrix.length - 1;
		while (l < r) {
			int m = (l + r + 1) >> 1;
			if (matrix[m][0] == target) {
				return true;
			} else if (matrix[m][0] < target) {
				l = m;
			} else {
				r = m - 1;
			}
		}
		int row = l;
		l = 0;
		r = matrix[0].length - 1;
		while (l <= r) {
			int m = (l + r) >> 1;
			if (matrix[row][m] == target) {
				return true;
			} else if (matrix[row][m] < target) {
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		return false;
	}
}
