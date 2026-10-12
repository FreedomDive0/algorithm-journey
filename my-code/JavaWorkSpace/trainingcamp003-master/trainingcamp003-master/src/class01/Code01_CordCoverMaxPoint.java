package class01;

import java.util.Arrays;

public class Code01_CordCoverMaxPoint {

	public static int maxPoint1(int[] arr, int L) {
		int res = 1;
		for (int i = 0; i < arr.length; i++) {
			int nearest = nearestIndex(arr, i, arr[i] - L);
			res = Math.max(res, i - nearest + 1);
		}
		return res;
	}

	public static int nearestIndex(int[] arr, int R, int value) {
		int L = 0;
		int index = R;
		while (L <= R) {
			int mid = L + ((R - L) >> 1);
			if (arr[mid] >= value) {
				index = mid;
				R = mid - 1;
			} else {
				L = mid + 1;
			}
		}
		return index;
	}

	public static int maxPoint2(int[] arr, int L) {
		int left = 0;
		int right = 0;
		int N = arr.length;
		int max = 0;
		while (left < N) {
			while (right < N && arr[right] - arr[left] <= L) {
				right++;
			}
			max = Math.max(max, right - (left++));
		}
		return max;
	}

	// for test
	public static int test(int[] arr, int L) {
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			int pre = i - 1;
			while (pre >= 0 && arr[i] - arr[pre] <= L) {
				pre--;
			}
			max = Math.max(max, i - pre);
		}
		return max;
	}

	// for test
	public static int[] generateArray(int len, int max) {
		int[] ans = new int[(int) (Math.random() * len) + 1];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = (int) (Math.random() * max);
		}
		Arrays.sort(ans);
		return ans;
	}

	// 测试
	public static int CordMaxCover(int[] arr, int K) {
//		if (arr == null || arr.length < 1 || K < 1) {
//			return 0;
//		}
		int max = 0;
		int left = 0;
		int right = 0;
		while (left < arr.length) {
			while (right < arr.length && arr[right] - arr[left] <= K) {
				right++;
			}
			max = Math.max(max, right - (left++));
		}
		return max;
	}

	// 测试
	// 1.有序arr[] 数代表X轴上的位置 k长度的绳子 做多cover几个点 在点上也叫cover
	public static int maxCover(int[] arr, int k) {
//		if (arr == null || arr.length < 1 || k < 0) {
//			return -1;
//		}
		int l = 0;
		int r = 0;
		int max = 0;
		while (l < arr.length) {
			while (r < arr.length && arr[r] - arr[l] <= k) {
				r++;
			}
			max = Math.max(max, r - (l++));// 跳出r是不符合条件的点 不需要加1
		}
		return max;
	}

	public static void main(String[] args) {
		int len = 100;
		int max = 1000;
		int testTime = 100000;
		System.out.println("测试开始");
		System.out.println(maxPoint2(new int[] { 1, 2, 3 }, 0));
		for (int i = 0; i < testTime; i++) {
			int L = (int) (Math.random() * max);
			int[] arr = generateArray(len, max);
			int ans1 = maxCover(arr, L);
			int ans2 = maxPoint2(arr, L);
			int ans3 = test(arr, L);
			if (ans1 != ans2 || ans2 != ans3) {
				System.out.println("oops!");
				break;
			}
		}

	}

}
