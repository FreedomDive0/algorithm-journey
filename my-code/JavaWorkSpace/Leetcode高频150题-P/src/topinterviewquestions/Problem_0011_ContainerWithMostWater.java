package topinterviewquestions;

public class Problem_0011_ContainerWithMostWater {
	public int maxArea(int[] height) {
		if (height == null || height.length < 2) {
			return 0;
		}
		int l = 0;
		int r = height.length - 1;
		int max = 0;
		while (l < r) {// 可能性舍弃
			max = Math.max(max, Math.min(height[l], height[r]) * (r - l));// 在每次结算时不是严格考虑每个位置的最优解而是看最终结果是否能再提升
			if (height[l] > height[r]) {
				r--;
			} else {
				l++;
			}
		}
		return max;
	}
}
