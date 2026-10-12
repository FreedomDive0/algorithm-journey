package class04;

public class Code03_Nim {
	// arr[]正数数组 代表铜板数量 每次每个人可以在任意一个位置 拿任意个铜板 谁最先拿光铜板谁赢
	public static void Nim(int[] arr) {
		int eor = 0;
		for (int num : arr) {
			eor ^= num;
		}
		if (eor == 0) {
			System.out.println("后手赢");// 先手不管怎么拿都会让后手面对eor!=0的情况
		} else {
			System.out.println("先手赢");// 先手一定会让让后手面对eor==0的情况
		}
	}
}
