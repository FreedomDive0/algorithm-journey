package 位运算;

public class Problem_0201_Bitwise_AND_of_Numbers_Range {
	public int rangeBitwiseAnd(int left, int right) {
		while (left < right) {// 找left和right的公共前缀
			right = right & (right - 1);// 去除最右侧的1
		}
		return right;
	}
}
