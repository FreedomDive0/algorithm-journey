package 双指针;

public class Problem_0011_Container_With_Most_Water {
	public int maxArea(int[] height) {
		int l = 0, r = height.length - 1;
		int max = 0;
		while (l < r) {
			if (height[l] < height[r]) {
				max = Math.max(max, (r - l) * height[l]);
				l++;
			} else {
				max = Math.max(max, (r - l) * height[r]);
				r--;
			}
		}
		return max;
	}
}
