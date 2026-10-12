package 数组字符串;

public class Problem_0135_Candy {
	// 1.左右上升坡度取最大值 时间复杂度O(N) 空间复杂度O(N)
	public int candy(int[] arr) {
		int n = arr.length;
		int[] help = new int[n];
		help[0] = 1;
		for (int i = 1; i < n; i++) {
			help[i] = arr[i] > arr[i - 1] ? help[i - 1] + 1 : 1;
		}
		for (int i = n - 2; i >= 0; i--) {
			if (arr[i] > arr[i + 1]) {
				help[i] = Math.max(help[i], help[i + 1] + 1);
			}
		}
		int ans = 0;
		for (int i = 0; i < n; i++) {
			ans += help[i];
		}
		return ans;
	}
	// 2.纯coding扣每个坡[待实现...] 时间复杂度O(N) 空间复杂度O(1)
}
