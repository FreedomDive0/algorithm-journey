package 双指针;

public class Problem_0167_Two_Sum_II_Input_Array_Is_Sorted {
	public int[] twoSum(int[] numbers, int target) {
		int l = 0, r = numbers.length - 1;
		int[] res = new int[2];
		while (l < r) {
			if (numbers[l] + numbers[r] > target) {
				r--;
			} else if (numbers[l] + numbers[r] < target) {
				l++;
			} else {
				res[0] = l + 1;
				res[1] = r + 1;
				break;
			}
		}
		return res;
	}
}
