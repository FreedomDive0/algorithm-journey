package 区间;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Problem_0056_Merge_Intervals {
	class Com implements Comparator<int[]> {

		@Override
		public int compare(int[] o1, int[] o2) {
			// TODO Auto-generated method stub
			return o1[0] - o2[0];
		}

	}

	public int[][] merge(int[][] intervals) {
		Arrays.sort(intervals, new Com());
		int start = intervals[0][0];
		int end = intervals[0][1];
		ArrayList<int[]> ans = new ArrayList<int[]>();
		for (int i = 1; i < intervals.length; i++) {
			if (intervals[i][0] <= end) {
				end = Math.max(end, intervals[i][1]);
			} else {
				ans.add(new int[] { start, end });
				start = intervals[i][0];
				end = intervals[i][1];
			}
		}
		ans.add(new int[] { start, end });
		return ans.toArray(new int[ans.size()][2]);
	}
}
