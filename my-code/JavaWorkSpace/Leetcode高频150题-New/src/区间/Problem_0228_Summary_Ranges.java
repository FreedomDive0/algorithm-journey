package 区间;

import java.util.ArrayList;
import java.util.List;

public class Problem_0228_Summary_Ranges {
	public List<String> summaryRanges(int[] nums) {
		List<String> ans = new ArrayList<String>();
		int start = 0;
		int end = start;
		while (end < nums.length) {
			if (end + 1 < nums.length && nums[end + 1] == nums[end] + 1) {
				end++;
			} else {
				if (start == end) {
					ans.add(String.valueOf(nums[start]));
				} else {
					ans.add(new StringBuilder().append(nums[start]).append("->").append(nums[end]).toString());// StringBuilder拼接字符串省时间
				}
				start = end = end + 1;
			}
		}
		return ans;
	}
}
