package class07;

public class Code03_MinPatches {
	public static int minPatches(int[] arr, int aim) {
		if (arr == null || arr.length < 1 || aim < 1) {
			return -1;
		}
		long range = 0;// 当前范围
		int patches = 0;// 补充几个数
		for (int i = 0; i < arr.length; i++) {
			while (range + 1 < arr[i]) {// 扩大范围直到包含arr[i]
				patches++;
				range += range + 1;
				if (range >= aim) {// 每次扩大范围考察是否能满足题目要求
					return patches;
				}
			}
			range += arr[i];// 如果当前范围覆盖数组 扩大新范围
			if (range >= aim) {
				return patches;
			}
		}
		while (range + 1 <= aim) {// 如果数组全部值都使用还不能构成目标继续扩大
			range += range + 1;
			patches++;
		}
		return patches;
	}

}
