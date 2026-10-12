package 矩阵;

public class Problem_0073_Set_Matrix_Zeroes {
	public void setZeroes(int[][] matrix) {
		boolean[] row = new boolean[matrix.length];// 使用HashMap或HashSet就看看能不能用数组代替
		boolean[] col = new boolean[matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					row[i] = col[j] = true;
				}
			}
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (row[i] || col[j]) {
					matrix[i][j] = 0;
				}
			}
		}
	}
}
