package class01;

public class Code06_GenerateArrIJK {
	// 生成长度为M的数组 使任意ijk(i<j<k)都有 arr[i]+arr[k]!=2arr[j] 返回生成的数组
	public static int[] generate(int M) {
		if (M == 1) {
			return new int[] { 1 };
		}
		int halfSize = (M + 1) / 2;// 用满足条件的奇数和偶数数组组成满足条件的大数组
		int[] ans = new int[M];// 左符合 右符合 i为奇 k为偶 2*arr[j]为偶 ∴arr[i]+arr[k]!=2arr[j]
		int index = 0;
		int[] base = generate(halfSize);
		for (; index < halfSize; index++) {
			ans[index] = base[index] * 2 - 1;
		}
		for (int i = 0; i < halfSize && index < M; i++) {
			ans[index++] = base[i] * 2;
		}
		return ans;
	}

	public static void main(String[] args) {
		int[] arr = generate(10);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}
}
