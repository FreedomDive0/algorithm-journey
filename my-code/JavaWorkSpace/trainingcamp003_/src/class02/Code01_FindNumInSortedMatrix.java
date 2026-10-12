package class02;

public class Code01_FindNumInSortedMatrix {
	// 在有序二维数组中寻找num
	public static boolean FindNum(int[][] matrix, int num) {
		if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
			return false;
		}
		int r = matrix.length - 1;
		int c = 0;
		while (r >= 0 && c < matrix[0].length) {
			if (matrix[r][c] == num) {
				return true;
			} else if (matrix[r][c] > num) {
				r--;
			} else {
				c++;
			}
		}
		return false;
	}
}
