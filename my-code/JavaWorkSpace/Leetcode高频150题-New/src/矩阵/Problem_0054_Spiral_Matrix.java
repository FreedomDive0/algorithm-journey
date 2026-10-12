package 矩阵;

import java.util.ArrayList;
import java.util.List;

public class Problem_0054_Spiral_Matrix {
	public List<Integer> spiralOrder(int[][] matrix) {
		int lr = 0;
		int lc = 0;
		int rr = matrix.length - 1;
		int rc = matrix[0].length - 1;
		ArrayList<Integer> ans = new ArrayList<Integer>();
		while (lr <= rr && lc <= rc) {
			process(matrix, lr++, lc++, rr--, rc--, ans);
		}
		return ans;
	}

	public void process(int[][] matrix, int lr, int lc, int rr, int rc, ArrayList<Integer> ans) {
		int r = lr;
		int c = lc;
		if (lr == rr) {
			while (lc <= rc) {// 当只剩下一个数 lc <= rc 最后lc会>rc 所以不会进入下面的循环
				ans.add(matrix[lr][lc++]);
			}
		} else if (lc == rc) {
			while (lr <= rr) {
				ans.add(matrix[lr++][lc]);
			}
		} else {
			while (c < rc) {
				ans.add(matrix[lr][c++]);
			}
			while (r < rr) {
				ans.add(matrix[r++][rc]);
			}
			while (c > lc) {
				ans.add(matrix[rr][c--]);
			}
			while (r > lr) {
				ans.add(matrix[r--][lc]);
			}
		}
	}
}
