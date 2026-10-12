package 数组字符串;

public class Problem_0189_RotateArray {
//	public void rotate(int[] nums, int k) {
//		int N = nums.length;
//		k = k % N;
//		reverse(nums, 0, N - k - 1);// 左逆
//		reverse(nums, N - k, N - 1);// 右逆
//		reverse(nums, 0, N - 1);// 整体逆
//	}
//
//	public void reverse(int[] nums, int l, int r) {
//		while (l < r) {
//			int tmp = nums[l];
//			nums[l++] = nums[r];
//			nums[r--] = tmp;
//		}
//	}
	public void rotate(int[] nums, int k) {
		int N = nums.length;
		k = k % N;
		if (k == 0) {// 需要提前判断是否需要旋转
			return;
		}
		int L = 0;
		int R = N - 1;
		int lpart = N - k;
		int rpart = k;
		int exchangePart = Math.min(lpart, rpart);
		int difference = lpart - rpart;// difference差值
		exchange(nums, L, R, exchangePart);
		while (difference != 0) {
			if (difference > 0) {
				L += exchangePart;
				lpart = difference;
			} else {
				R -= exchangePart;
				rpart = -difference;
			}
			exchangePart = Math.min(lpart, rpart);
			difference = lpart - rpart;
			exchange(nums, L, R, exchangePart);
		}
	}

	public void exchange(int[] nums, int start, int end, int size) {
		int index = end - size + 1;
		while (size-- != 0) {
			int tmp = nums[index];
			nums[index] = nums[start];
			nums[start] = tmp;
			index++;
			start++;
		}
	}

}
