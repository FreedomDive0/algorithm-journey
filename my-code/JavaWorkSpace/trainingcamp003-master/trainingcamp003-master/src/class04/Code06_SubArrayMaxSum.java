package class04;

public class Code06_SubArrayMaxSum {

	public static int maxSum(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int cur = 0;
		for (int i = 0; i < arr.length; i++) {
			cur += arr[i];
			max = Math.max(max, cur);
			cur = cur < 0 ? 0 : cur;
		}
		return max;
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// 测试
	public static int maxSubSum(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int cur = 0;
		for (int i = 0; i < arr.length; i++) {
			cur += arr[i];
			max = cur > max ? cur : max;
			cur = cur < 0 ? 0 : cur;
		}
		return max;
	}

	// 测试
	// 6.给定arr[] return子数组最大累加和
	public static int subArrayMaxSum(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		int cur = 0;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			cur += arr[i];
			max = Math.max(max, cur);
			cur = cur < 0 ? 0 : cur;
		}
		return max;
	}

	public static void main(String[] args) {
		int[] arr1 = { -2, -3, -5, 40, -10, -10, 100, 1 };
		System.out.println(maxSum(arr1));
		System.out.println(subArrayMaxSum(arr1));
		int[] arr2 = { -2, -3, -5, 0, 1, 2, -1 };
		System.out.println(maxSum(arr2));
		System.out.println(subArrayMaxSum(arr2));
		int[] arr3 = { -2, -3, -5, -1 };
		System.out.println(maxSum(arr3));
		System.out.println(subArrayMaxSum(arr3));
	}

}
