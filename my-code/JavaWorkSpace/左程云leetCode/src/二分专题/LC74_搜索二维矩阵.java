package 二分专题;

public class LC74_搜索二维矩阵 {
//	public boolean searchMatrix(int[][] matrix, int target) {
//		int l = 0;
//		int r = matrix.length - 1;
//		while (l <= r) {// 先找到<=右边界
//			int mid = (l + r + 1) / 2;
//			if (matrix[mid][0] <= target) {
//				l = mid;
//			} else {
//				r = mid - 1;
//			}
//		}
//		if (matrix[r][0] > target)
//			return false;
//		int index = r;
//		l = 0;
//		r = matrix[0].length - 1;
//		while (l <= r) {
//			int mid = l + (r - l) / 2;
//			if (matrix[index][mid] == target) {
//				return true;
//			} else if (matrix[index][mid] < target) {
//				l = mid + 1;
//			} else {
//				r = mid - 1;
//			}
//		}
//		return false;
//	}
	public boolean searchMatrix(int[][] matrix, int target) {
		int l = 0, r = matrix.length - 1;
		while (l < r) {// 确定行
			int m = (l + r + 1) / 2;
			if (matrix[m][0] <= target) {
				l = m;
			} else {
				r = m - 1;
			}
		}
		int rox = l;
		l = 0;
		r = matrix[rox].length - 1;
		while (l <= r) {
			int m = (l + r) / 2;
			if (matrix[rox][m] < target) {
				l = m + 1;
			} else if (matrix[rox][m] > target) {
				r = m - 1;
			} else {
				return true;
			}
		}
		return false;
	}
}
