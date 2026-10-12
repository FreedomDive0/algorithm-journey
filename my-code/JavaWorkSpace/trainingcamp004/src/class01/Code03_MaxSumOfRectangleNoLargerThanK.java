package class01;

import java.util.TreeSet;

public class Code03_MaxSumOfRectangleNoLargerThanK {
	public static int maxSumOfRectangleNoLargerThanK(int[][] matrix, int k) {
		if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
			return 0;
		}
		int ans = Integer.MIN_VALUE;
		TreeSet<Integer> set = new TreeSet<>();
		for (int i = 0; i < matrix.length; i++) {// 从哪一行开始
			int[] cur = new int[matrix[0].length];
			for (int j = i; j < matrix[0].length; j++) {// 从i开始不停尝试
				set.add(0);// 记得添加0
				int sum = 0;
				for (int index = 0; index < cur.length; index++) {// 压缩数组
					cur[index] += matrix[j][index];
					sum += cur[index];
					if (set.ceiling(sum - k) != null) {
						ans = Math.max(ans, sum - set.ceiling(sum - k));
					}
					set.add(sum);
				}
				set.clear();
			}
		}
		return ans;
	}

	public static void main(String[] args) {

	}
}
