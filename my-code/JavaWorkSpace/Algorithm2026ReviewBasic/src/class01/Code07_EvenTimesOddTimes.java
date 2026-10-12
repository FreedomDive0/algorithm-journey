package class01;

public class Code07_EvenTimesOddTimes {
	// 1. 一个数组中只有一种数出现奇数次
	public static void oneTimeOdd(int[] arr) {
		if (arr == null || arr.length < 1)
			return;
		int eor = arr[0];
		for (int i = 1; i < arr.length; i++) {
			eor ^= arr[i];
		}
		System.out.println(eor);
	}

	// 2. arr中，有两种数，出现奇数次
	public static void twoTimeOdd(int[] arr) {
		if (arr == null || arr.length < 2)
			return;
		int eorDouble = arr[0];
		for (int i = 1; i < arr.length; i++) {
			eorDouble ^= arr[i];
		}
		int rightOne = eorDouble & ((~eorDouble) + 1);
		int eorSingle = 0;
		for (int i = 0; i < arr.length; i++) {
			if ((rightOne & arr[i]) != 0) {
				eorSingle ^= arr[i];
			}
		}
		System.out.println(eorSingle + " " + (eorDouble ^ eorSingle));
	}

	// 3. 一个数二进制形式下有多少个1
	public static int bit1counts1(int N) {
		int count = 0;
		while (N != 0) {
			int rightOne = N & ((~N) + 1);
			N ^= rightOne;
			count++;
		}
		return count;
	}
}
