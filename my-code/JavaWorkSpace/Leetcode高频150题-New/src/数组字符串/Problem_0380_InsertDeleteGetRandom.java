package 数组字符串;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Problem_0380_InsertDeleteGetRandom {
	class RandomizedSet {
		List<Integer> list;
		HashMap<Integer, Integer> map;

		public RandomizedSet() {
			list = new ArrayList<Integer>();
			map = new HashMap<Integer, Integer>();
		}

		public boolean insert(int val) {
			if (map.containsKey(val)) {
				return false;
			}
			int index = list.size();
			list.add(val);
			map.put(val, index);
			return true;
		}

		public boolean remove(int val) {
			if (!map.containsKey(val)) {
				return false;
			}
			int index = map.get(val);
			int lastIndex = list.size() - 1;
			if (index != lastIndex) {// 不相同才需要置换和添加到IndexMap
				list.set(index, list.get(lastIndex));
				map.put(list.get(index), index);
			}
			list.remove(lastIndex);
			map.remove(val);
			return true;
		}

		public int getRandom() {
			if (list.size() == 0) {
				return -1;
			}
			return list.get((int) (Math.random() * (list.size())));
		}
	}
}
