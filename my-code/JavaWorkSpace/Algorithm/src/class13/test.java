package class13;

import java.util.HashMap;

public class test {
	public static int mySqrt(int x) {
		if (x <= 1) {
			return x;
		}
		return process(x, 1, x);
	}

	public static int process(int x, int l, int r) {
		if (l == r) {
			return l;
		}
		int middle = (l + r) >> 1;
		if (middle * middle > x) {
			return process(x, l, middle);
		} else {
			return middle;
		}
	}

	public static void main(String[] args) {
		System.out.println(mySqrt(36));
	}

	public int[] twoSum(int[] nums, int target) {
		int[] res = new int[2];
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			map.put(nums[i], i);
		}
		for (int i = 0; i < nums.length; i++) {
			if (map.get(target - nums[i]) != null && map.get(target - nums[i]) != i) {
				res[0] = i;
				res[1] = map.get(target - nums[i]);
			}
		}
		return res;
	}
}
