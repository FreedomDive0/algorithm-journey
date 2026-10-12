package 基本数据类型专题;

public class LC42_接雨水 {
	public static void main(String[] args) {
		System.out.println(trap(new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 }));
	}

	public static int trap(int[] height) {// 双指针 按列求
		int water = 0;
		int[] max_Right = new int[height.length];
		int max_Left = 0;
		for (int i = height.length - 2; i >= 0; i--) {// 右侧高度需要提前求出
			max_Right[i] = Math.max(max_Right[i + 1], height[i + 1]);
		}
		// 该列能盛的最多的水由左右最高高度决定
		for (int i = 1; i < height.length; i++) {// 因为列前进方向与左侧最大值求出方向一致 可以接水与求左侧最大值同时进行
			max_Left = Math.max(max_Left, height[i - 1]);
			int curHeight = Math.min(max_Left, max_Right[i]);// 当前列左右侧最大值
			if (curHeight > height[i]) {
				water += curHeight - height[i];
			}
		}
		return water;
	}
}
