package 基本数据类型专题;

import java.util.HashMap;

public class LC560_和为K的子数组 {
	public static void main(String[] args) {
		System.out.println(subarraySum(new int[] { 1, 1, 1 }, 2));
	}

	public static int subarraySum(int[] nums, int k) {
		HashMap<Integer, Integer> preSum = new HashMap<Integer, Integer>();
		preSum.put(0, 1);// 记录该前缀和的数量
		int pre = 0;
		int count = 0;
		for (int num : nums) {
			pre += num;// 前缀和
			if (preSum.containsKey(pre - k)) {// pre - (pre - k)
				count += preSum.get(pre - k);
			}
			preSum.put(pre, preSum.getOrDefault(pre, 0) + 1);
		}
		return count;
	}
}
