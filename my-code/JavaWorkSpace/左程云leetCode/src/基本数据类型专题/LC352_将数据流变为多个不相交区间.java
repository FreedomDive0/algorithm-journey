package 基本数据类型专题;

import java.util.Map;
import java.util.TreeMap;

public class LC352_将数据流变为多个不相交区间 {
	class SummaryRanges {
		TreeMap<Integer, Integer> map;

		public SummaryRanges() {
			map = new TreeMap<Integer, Integer>();// TreeMap以key排序
		}

		public void addNum(int val) {
			Map.Entry<Integer, Integer> area1 = map.ceilingEntry(val + 1);// 返回>=val+1的键值对
			Map.Entry<Integer, Integer> area0 = map.floorEntry(val);// 返回<=val的键值对
			if (area0 != null && area0.getKey() <= val && area0.getValue() >= val) {// 情况1在小于区中
				return;
			} else {
				boolean leftSide = area0 != null && area0.getValue() + 1 == val;// 是否为val左右边界
				boolean rightSide = area1 != null && area1.getKey() == val + 1;
				if (leftSide && rightSide) {// 情况2合并两个区域
					int left = area0.getKey();
					int right = area1.getValue();
					map.remove(area0.getKey());
					map.remove(area1.getKey());
					map.put(left, right);
				} else if (leftSide) {// 情况3扩张左区域
					map.put(area0.getKey(), area0.getValue() + 1);
				} else if (rightSide) {// 情况4扩张右区域
					int right = area1.getValue();
					map.remove(area1.getKey());
					map.put(val, right);
				} else {// 情况5生成独立区域
					map.put(val, val);
				}
			}
		}

		public int[][] getIntervals() {
			int[][] ans = new int[map.size()][2];
			int index = 0;
			for (int key : map.keySet()) {
				ans[index][0] = key;
				ans[index][1] = map.get(key);
				index++;
			}
			return ans;
		}
	}
}
