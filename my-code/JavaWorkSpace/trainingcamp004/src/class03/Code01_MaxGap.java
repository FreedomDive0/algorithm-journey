package class03;

public class Code01_MaxGap {
	public static int maxGap(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int min = arr[0];
		int max = arr[0];
		int N = arr.length;
		for (int i = 1; i < N; i++) {// 得到数的范围
			max = Math.max(max, arr[i]);
			min = Math.min(min, arr[i]);
		}
		if (min == max) {// 值相同所有数相同
			return 0;
		}
		boolean[] hasNum = new boolean[N + 1];// 准备桶
		int[] mins = new int[N + 1];// 通的作用 O(N)可以使数据有序
		int[] maxs = new int[N + 1];// 多准备一个桶 杀死了桶内范围产生答案的可能性 空桶两侧答案一定大于桶内答案 为后续答案求解流程做铺垫
		for (int i = 0; i < N; i++) {
			int index = bucket(arr[i], min, max, N);
			mins[index] = hasNum[index] ? Math.min(mins[index], arr[i]) : arr[i];// 因为含有空桶的设计 桶内只需要存放最大值与最小值
			maxs[index] = hasNum[index] ? Math.max(maxs[index], arr[i]) : arr[i];
			hasNum[index] = true;
		}
		int lastMmax = maxs[0];
		int ans = 0;
		for (int i = 1; i <= N; i++) {
			if (hasNum[i]) {
				ans = Math.max(ans, mins[i] - lastMmax);
				lastMmax = maxs[i];
			}
		}
		return ans;
	}

	public static int bucket(int num, int min, int max, int N) {
		return (int) ((num - min) * N / (max - min));
	}
}
