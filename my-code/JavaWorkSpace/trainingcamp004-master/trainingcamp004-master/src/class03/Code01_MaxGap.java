package class03;

import java.util.Arrays;

public class Code01_MaxGap {

	public static int maxGap(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		int len = nums.length;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < len; i++) {
			min = Math.min(min, nums[i]);
			max = Math.max(max, nums[i]);
		}
		if (min == max) {
			return 0;
		}
		// 不止一种数，min~max一定有range, len个数据，准备len+1个桶
		boolean[] hasNum = new boolean[len + 1]; // hasNum[i] i号桶是否进来过数字
		int[] maxs = new int[len + 1]; // maxs[i] i号桶收集的所有数字的最大值
		int[] mins = new int[len + 1]; // mins[i] i号桶收集的所有数字的最小值

		int bid = 0; // 桶号
		for (int i = 0; i < len; i++) {
			bid = bucket(nums[i], len, min, max);
			mins[bid] = hasNum[bid] ? Math.min(mins[bid], nums[i]) : nums[i];
			maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];
			hasNum[bid] = true;
		}
		int res = 0;
		int lastMax = maxs[0]; // 上一个非空桶的最大值
		int i = 1;
		for (; i <= len; i++) {
			if (hasNum[i]) {
				res = Math.max(res, mins[i] - lastMax);
				lastMax = maxs[i];
			}
		}
		return res;
	}

	public static int bucket(long num, long len, long min, long max) {
		return (int) ((num - min) * len / (max - min));
	}

	// for test
	public static int comparator(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		Arrays.sort(nums);
		int gap = Integer.MIN_VALUE;
		for (int i = 1; i < nums.length; i++) {
			gap = Math.max(nums[i] - nums[i - 1], gap);
		}
		return gap;
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}

	// for test
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	// 测试
	public static int maxGap1(int[] arr) {
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
		if (min == max) {
			return 0;
		}
		boolean[] hasNum = new boolean[N + 1];// 准备桶
		int[] mins = new int[N + 1];// 通的作用 O(N)可以使数据有序
		int[] maxs = new int[N + 1];// 多准备一个桶 杀死了桶内范围产生答案的可能性 空桶两侧答案一定大于桶内答案 为后续答案求解流程做铺垫
		for (int i = 0; i < N; i++) {
			int index = bucket1(arr[i], min, max, N);
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

	public static int bucket1(int num, int min, int max, int N) {
		return (int) ((num - min) * N / (max - min));
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			if (maxGap(arr1) != maxGap1(arr2)) {
				succeed = false;
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}

}
