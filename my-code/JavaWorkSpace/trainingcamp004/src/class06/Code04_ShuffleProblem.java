package class06;

public class Code04_ShuffleProblem {
	// arr[] 长度为2*N 原数组为[L1,L2,...Ln,R1,R2,...,Rn] 调整为[R1,L1,R2,L2,...,Rn,Ln]
	public static int[] shuffle(int[] arr) {
		if (arr == null || arr.length < 2) {
			return arr;
		}
		process(arr, 0, arr.length - 1);
		return arr;
	}

	public static void process(int[] arr, int s, int e) {
		while (e - s + 1 > 0) {// 将整体拆分为小块解决
			int base = 3;
			int k = 1;
			int len = e - s + 1;
			while ((base * 3) - 1 < len) {// 解决的规模大小
				base *= 3;
				k++;
			}
			int half = (base - 1) / 2;
			int mid = (s + e) / 2;
			swap(arr, s + half, mid, mid + half);
			cycle(arr, s, base - 1, k);
			s = s + base - 1;
		}
	}

	// 根据在原数组的(i)下标得到新数组的位置 N为数组长度一半
	public static int getIndex(int i, int N) {// 下标0位置不使用
		if (i <= N) {
			return 2 * i;// 左部分
		} else {
			return 2 * (i - N) - 1;// 右部分
		}
	}

	// 交换数组两个部分
	public static void swap(int[] arr, int s, int mid, int e) {
		reverse(arr, s, mid);
		reverse(arr, mid + 1, e);
		reverse(arr, s, e);
	}

	// 反转数组
	public static void reverse(int[] arr, int s, int e) {
		while (s < e) {
			int tmp = arr[s];
			arr[s++] = arr[e];
			arr[e--] = tmp;
		}
	}

	// 在3^-1长度内进行循环交换
	public static void cycle(int[] arr, int s, int len, int k) {// k进行的轮数
		for (int i = 0, trigger = 1; i < k; i++, trigger *= 3) {// trigger每轮的新开头
			int preValue = arr[s + trigger - 1];// 每一轮起始的位置
			int cur = getIndex(trigger, len / 2);// 得到下一个下标
			while (cur != trigger) {// 进行小轮里的循环
				int tmp = arr[s + cur - 1];
				arr[s + cur - 1] = preValue;
				preValue = tmp;
				cur = getIndex(cur, len / 2);
			}
			arr[s + trigger - 1] = preValue;
		}
	}
}
