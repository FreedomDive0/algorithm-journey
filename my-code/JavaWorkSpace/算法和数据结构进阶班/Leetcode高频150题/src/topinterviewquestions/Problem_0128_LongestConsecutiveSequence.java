package topinterviewquestions;

import java.util.HashMap;

public class Problem_0128_LongestConsecutiveSequence {

	public int longestConsecutive(int[] nums) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int max = 0;
		for (int num : nums) {
			if (!map.containsKey(num)) {// 不存在的数字才添加
				map.put(num, 1);
				int preLen = map.getOrDefault(num - 1, 0);
				int posLen = map.getOrDefault(num + 1, 0);
				int all = preLen + posLen + 1;
				map.put(num - preLen, all);// 记录新连续串的头尾及长度
				map.put(num + posLen, all);
				max = Math.max(max, all);
			}
		}
		return max;
	}

}
