package class07;

import java.util.HashMap;
import java.util.Map;

public class Code07_MaxPointsOneLine {
	public static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	public static int maxPoints(Point[] points) {
		if (points == null || points.length < 1) {
			return 0;
		}
		int max = 0;
		Map<Integer, Map<Integer, Integer>> map = new HashMap<Integer, Map<Integer, Integer>>();// 记录x，y斜率点的个数
		for (int i = 0; i < points.length; i++) {
			map.clear();
			int same = 1;// 记录自己
			int sameX = 0;
			int sameY = 0;
			int line = 0;
			for (int j = i + 1; j < points.length; j++) {
				int x = points[i].x - points[j].x;
				int y = points[i].y - points[j].y;
				if (x == 0 && y == 0) {
					same++;
				} else if (x == 0) {
					sameX++;
				} else if (y == 0) {
					sameY++;
				} else {
					int factor = gcd(x, y);
					x /= factor;// 除最大公约数
					y /= factor;
					if (!map.containsKey(x)) {// 查看是否存在该斜率的点
						map.put(x, new HashMap<>());
					}
					if (!map.get(x).containsKey(y)) {
						map.get(x).put(y, 0);
					}
					map.get(x).put(y, map.get(x).get(y) + 1);
					line = Math.max(line, map.get(x).get(y));
				}
			}
			max = Math.max(Math.max(max, line + same), Math.max(sameX, sameY));
		}
		return max;
	}

	public static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}

	public static void main(String[] args) {

	}
}
