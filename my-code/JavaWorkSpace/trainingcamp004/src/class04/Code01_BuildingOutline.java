package class04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Code01_BuildingOutline {
	// 大楼信息
	public static class build {
		public int pos;
		public boolean isAdd;
		public int height;

		public build(int pos, boolean isAdd, int height) {
			this.pos = pos;
			this.isAdd = isAdd;
			this.height = height;
		}

	}

	public static class buildComparator implements Comparator<build> {

		@Override
		public int compare(build o1, build o2) {
			if (o1.pos != o2.pos) {// 位置不同 pos小的排在前面
				return o1.pos - o2.pos;
			}
			if (o1.isAdd != o2.isAdd) {// 位置相同 加法排在前面
				return o1.isAdd ? 1 : -1;
			}
			return 0;// 位置相同 都是加法or减法谁排在前面都可以
		}

	}

	// 1.N*3矩阵 matrix [0]起始位置 [1]结束位置 [2]楼高 返回大楼轮廓
	public static List<List<Integer>> buildingOutline(int[][] matrix) {
		int N = matrix.length;
		build[] builds = new build[N << 1];
		for (int i = 0; i < matrix.length; i++) {
			builds[i * 2] = new build(matrix[i][0], true, matrix[i][2]);
			builds[i * 2 + 1] = new build(matrix[i][1], false, matrix[i][2]);
		}
		Arrays.sort(builds, new buildComparator());
		TreeMap<Integer, Integer> heightMap = new TreeMap<Integer, Integer>();// 存储到X位置 所有的高度出现的次数
		TreeMap<Integer, Integer> posHeighest = new TreeMap<Integer, Integer>();// 存储X位置的最大高度
		for (int i = 0; i < builds.length; i++) {
			int height = builds[i].height;
			if (builds[i].isAdd) {// 处理加法
				if (heightMap.containsKey(height)) {
					heightMap.put(height, heightMap.get(height) + 1);
				} else {
					heightMap.put(height, 1);
				}
			} else {// 处理下降
				if (heightMap.get(height) == 1) {
					heightMap.remove(height);
				} else {
					heightMap.put(height, heightMap.get(height) - 1);
				}
			}
			if (heightMap.isEmpty()) {// 删光了高度为0
				posHeighest.put(builds[i].pos, 0);
			} else {
				posHeighest.put(builds[i].pos, heightMap.lastKey());// 最后保留的高度为该位置最高高度
			}
		}
		int preHeight = 0;
		int preX = 0;
		List<List<Integer>> ans = new ArrayList<>();
		for (Entry<Integer, Integer> entry : posHeighest.entrySet()) {
			int curX = entry.getKey();
			int curHeight = entry.getValue();
			if (curHeight != preHeight) {
				if (preHeight != 0) {// 排除0位置
					ans.add(new ArrayList<Integer>(Arrays.asList(preX, curX, preHeight)));
				}
				preX = curX;// 高度发生改变才更新
				preHeight = curHeight;
			}
		}
		return ans;
	}
}
