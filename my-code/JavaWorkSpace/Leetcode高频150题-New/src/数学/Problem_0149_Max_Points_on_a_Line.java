package 数学;

import java.util.HashMap;

public class Problem_0149_Max_Points_on_a_Line {
	public int maxPoints(int[][] points) {
		if (points == null) {
			return 0;
		}
		if (points.length < 3) {
			return points.length;
		}
		int res = 0;
		for (int i = 0; i < points.length; i++) {
			HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<Integer, HashMap<Integer, Integer>>();
			int samePoint = 1;
			int sameX = 0;
			int sameY = 0;
			int line = 0;
			for (int j = i + 1; j < points.length; j++) {
				int x = points[i][0] - points[j][0];
				int y = points[i][1] - points[j][1];
				if (x == 0 && y == 0) {
					samePoint++;
				} else if (x == 0) {
					sameX++;
				} else if (y == 0) {
					sameY++;
				} else {
					int gcd = gcd(x, y);
					x /= gcd;
					y /= gcd;
					if (!map.containsKey(x)) {
						map.put(x, new HashMap<Integer, Integer>());
					}
					if (!map.get(x).containsKey(y)) {
						map.get(x).put(y, 0);
					}
					map.get(x).put(y, map.get(x).get(y) + 1);
					line = Math.max(line, map.get(x).get(y));
				}
			}
			res = Math.max(res, Math.max(Math.max(sameX, sameY), line) + samePoint);
		}
		return res;
	}

	private int gcd(int x, int y) {
		return y == 0 ? x : gcd(y, x % y);
	}
}
