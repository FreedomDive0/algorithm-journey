package class06;

public class Code03_JumpGame {
	public static int JumpGame(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		int steps = 0;// 当前步数
		int curR = 0;// 当前最右范围
		int nextMax = 0;// 当前步数内最右距离
		for (int i = 0; i < arr.length; i++) {
			if (i > nextMax) {
				steps++;
				nextMax = curR;// 因为此时步数已超 所以要先更新旧curR
			}
			curR = Math.max(curR, i + arr[i]);
		}
		return steps;
	}

	public static void main(String[] args) {
		int[] arr = { 3, 2, 3, 1, 1, 4 };
		System.out.println(JumpGame(arr));

	}
}
