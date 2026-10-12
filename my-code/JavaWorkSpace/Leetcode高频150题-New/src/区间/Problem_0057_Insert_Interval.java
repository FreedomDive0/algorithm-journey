package 区间;

import java.util.ArrayList;

public class Problem_0057_Insert_Interval {
	public int[][] insert(int[][] intervals, int[] newInterval) {
		ArrayList<int[]> ans = new ArrayList<int[]>();
		boolean flag = false;
		int ns = newInterval[0];
		int ne = newInterval[1];
		for (int[] inter : intervals) {
			int cs = inter[0];
			int ce = inter[1];
			if (cs > ne) {
				if (!flag) {// 可能情况 1.newInterval为独立片段加入 2.newInterval与原区间有相交
					ans.add(new int[] { ns, ne });
					flag = true;
				}
				ans.add(new int[] { cs, ce });
			} else if (ce < ns) {
				ans.add(new int[] { cs, ce });
			} else {
				ns = Math.min(ns, cs);
				ne = Math.max(ne, ce);
			}
		}
		if (!flag) {
			ans.add(new int[] { ns, ne });
		}
		return ans.toArray(new int[ans.size()][2]);
	}
}
