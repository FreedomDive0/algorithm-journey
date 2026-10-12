package class01;

public class Code07_EvenTimesOddTimes {
	// 1.不使用额外变量交换数组中的两个数
	public static void swapWithoutTemp(int[] arr, int i, int j) {
		if (i == j)// 相同位置异或会让值变为0
			return;
		arr[i] ^= arr[j];
		arr[j] ^= arr[i];
		arr[i] ^= arr[j];
	}

	// 2.数组中只有一种数出现了奇数次
	public static void printOddTimesNum1(int[] arr) {
		int eor = 0;
		for (int num : arr) {
			eor ^= num;
		}
		System.out.println(eor);
	}

	// 3.提取整数最右侧的1
	public void extractMostRightOne(int num) {
		int mostRightOne = num & ((~num) + 1);
		System.out.println(mostRightOne);
	}

	// 4.数组中有两种数出现了奇数次
	public static void printOddTimesNum2(int[] arr) {
		int oddNumEor = 0;
		for (int num : arr) {
			oddNumEor ^= num;
		}
		int mostRightOne = oddNumEor & ((~oddNumEor) + 1);
		int eor = 0;
		for (int num : arr) {
			if ((num & mostRightOne) == 0) {
				eor ^= num;
			}
		}
		System.out.println("num1: " + eor + " num2: " + (oddNumEor ^ eor));
	}

	// 5.整数二进制有多少个1
	public static int oneInNum(int num) {
		int count = 0;
		while (num != 0) {
			int rightOne = num & ((~num) + 1);
			count++;
			num ^= rightOne;// 不用减法是因为负数不成立
		}
		return count;
	}

	// 测试
	public static void main(String[] args) {
//		int[] arr = { 9, 8, 3, 0, 4, 6, 8, 7, 7 };
//		System.out.println("arr[0]: " + arr[0] + "arr[1]: " + arr[1]);
//		swapWithoutTemp(arr, 0, 0);
//		System.out.println("arr[0]: " + arr[0] + "arr[1]: " + arr[1]);
		int[] arr1 = { 3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1 };
		printOddTimesNum1(arr1);
		int[] arr2 = { 4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2 };
		printOddTimesNum2(arr2);
		System.out.println(oneInNum(-1));
	}
}
