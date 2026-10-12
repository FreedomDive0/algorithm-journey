package 数学;

import java.util.ArrayList;

public class Problem_0066_Plus_One {
	public int[] plusOne(int[] digits) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int offset = 0;
		digits[digits.length - 1]++;
		for (int i = digits.length - 1; i >= 0; i--) {
			digits[i] += offset;
			offset = digits[i] / 10;
			digits[i] %= 10;
			list.add(digits[i]);
		}
		if (offset == 1) {
			list.add(1);
		}
		int[] res = new int[list.size()];
		int index = 0;
		for (int i = list.size() - 1; i >= 0; i--) {
			res[index++] = list.get(i);
		}
		return res;
	}

	// 数学
	public int[] plusOne1(int[] digits) {
		for (int i = digits.length - 1; i >= 0; i--) {
			digits[i]++;
			digits[i] = digits[i] % 10;
			if (digits[i] != 0)// 没有进位直接返回 如果一直进位到最后创建len+1数组开头为1
				return digits;
		}
		digits = new int[digits.length + 1];
		digits[0] = 1;
		return digits;
	}
}
