package 区间;

import java.util.Arrays;
import java.util.Comparator;

public class Problem_0452_Minimum_Number_of_Arrows_to_Burst_Balloons {
	class Com implements Comparator<int[]> {

		@Override
		public int compare(int[] o1, int[] o2) {
			// TODO Auto-generated method stub
			return Integer.compare(o1[1], o2[1]);
		}

	}

	public int findMinArrowShots(int[][] points) {
		if (points.length == 1) {
			return 1;
		}
		Arrays.sort(points, new Com());
		int pos = points[0][1];// 根据图像分析最优射箭结果一定包含(所有右边界最左和所有左边界最右两种情况)
		int nums = 1;// 所以以某一个边界为标准射箭进行尝试
		for (int[] ballon : points) {
			if (ballon[0] > pos) {
				nums++;
				pos = ballon[1];
			}
		}
		return nums;
	}

}
