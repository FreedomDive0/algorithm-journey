package 数组字符串;

public class Problem_0238_Product_of_Array_Except_Self {
	public int[] productExceptSelf(int[] nums) {
		int zeros = 0;// 记录0的个数
		int product = 1;// 乘积
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				zeros++;
			} else {
				product *= nums[i];
			}
		}
		if (zeros > 1) {
			for (int i = 0; i < nums.length; i++) {
				nums[i] = 0;
			}
		} else {
			if (zeros == 1) {
				for (int i = 0; i < nums.length; i++) {
					nums[i] = nums[i] == 0 ? product : 0;
				}
			} else {
				for (int i = 0; i < nums.length; i++) {
					nums[i] = product / nums[i];
				}
			}
		}
		return nums;
	}

	// 2.方法2
	public int[] productExceptSelf1(int[] nums) {
		int n = nums.length;
		int[] ans = new int[n];
		ans[0] = nums[0];
		for (int i = 1; i < ans.length; i++) {
			ans[i] = ans[i - 1] * nums[i];
		}
		int right = 1;
		for (int i = n - 1; i > 0; i--) {// i的范围
			ans[i] = ans[i - 1] * right;
			right *= nums[i];
		}
		ans[0] = right;// 最后将0位置补上
		return ans;
	}
}
