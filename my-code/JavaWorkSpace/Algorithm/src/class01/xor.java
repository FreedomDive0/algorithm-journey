package class01;

public class xor {
	// 1.不使用额外变量 交换两个变量的值
	public static void exchange(int a, int b) {
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;// a、b值可以相同但地址不可以相同
	}

	// 2.数组中只有一种数出现了奇数次
	public static int oneTimeOdd(int[] arr) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		int res = 0;
		for (int i = 0; i < arr.length; i++) {
			res ^= arr[i];
		}
		return res;
	}

	// 3.提取int变量最右侧的1
	public static int rightOne(int a) {
		return a & ((~a) + 1);
	}

	// 4.数组中两种数出现了奇数次
	public static void twoTimeOdd(int[] arr) {
		if (arr == null || arr.length < 1) {
			return;
		}
		int eor = 0;// 取到所有数异或结果 结果为目标值a^b
		for (int i = 0; i < arr.length; i++) {
			eor ^= arr[i];
		}
		int rightOne = eor & ((~eor) + 1);// 提取a^b最右侧的1 用与区分a、b
		int res = 0;
		for (int i = 0; i < arr.length; i++) {// 用最右侧1将数组分为两份处理
			if ((rightOne & arr[i]) != 0) {
				res ^= arr[i];
			}
		}
		System.out.println("两个数为：" + res + "、" + (res ^ eor));
	}

	// 5.二进制数中有多少个1
	public static int oneInNum(int N) {
		int count = 0;
		while (N != 0) {
			int rightOne = N ^ ((~N) + 1);
			count++;
			N ^= rightOne;
		}
		return count;
	}
}
