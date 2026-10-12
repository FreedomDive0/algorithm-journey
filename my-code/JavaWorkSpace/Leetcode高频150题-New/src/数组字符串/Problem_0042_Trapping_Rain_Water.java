package 数组字符串;

public class Problem_0042_Trapping_Rain_Water {
	public int trap(int[] height) {
		if (height == null || height.length < 3) {
			return 0;
		}
		int n = height.length;
		int l = 1;
		int r = n - 2;
		int lMax = height[0];
		int rMax = height[n - 1];
		int ans = 0;
		while (l <= r) {
			if (lMax < rMax) {
				ans += Math.max(lMax - height[l], 0);
				lMax = Math.max(lMax, height[l++]);
			} else {
				ans += Math.max(rMax - height[r], 0);
				rMax = Math.max(rMax, height[r--]);
			}
		}
		return ans;
	}
}
