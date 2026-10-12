package 基本数据类型专题;

public class LC剑指Offer_39数组中出现次数超过一半的数字 {
//  摩尔投票法
//	推论一： 若记 众数 的票数为 +1+1 ，非众数 的票数为 -1−1 ，则一定有所有数字的 票数和 > 0>0 。
//	推论二： 若数组的前 aa 个数字的 票数和 = 0=0 ，则 数组剩余 (n-a)(n−a) 个数字的 票数和一定仍 >0>0 ，即后 (n-a)(n−a) 个数字的 众数仍为 xx 。

	public int majorityElement(int[] nums) {
		int votes = 0, x = 0;
		for (int num : nums) {
			if (votes == 0) {// 用票数作为标记
				x = num;
			}
			votes += num == x ? 1 : -1;// 第一次也得+1
		}
		return x;
	}
//	public int majorityElement(int[] nums) {
//		int ans = 0;
//		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
//		for (int i = 0; i < nums.length; i++) {
//			map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
//			if (map.get(nums[i]) >= nums.length / 2) {
//				ans = nums[i];
//				break;
//			}
//		}
//		return ans;
//	}
}
