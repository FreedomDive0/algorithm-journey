package class01;

import java.util.LinkedList;

public class Code02_AllLessNumSubArray {
	public static int getNum(int[] arr, int num) {
		LinkedList<Integer> qMax = new LinkedList<Integer>();
		LinkedList<Integer> qMin = new LinkedList<Integer>();
		int res = 0;
		int l = 0;
		int r = 0;
		while (l < arr.length) {
			while (r < arr.length) {
				// 最大最小值处理
				while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[r]) {
					qMax.pollLast();
				}
				qMax.addLast(r);
				while (!qMin.isEmpty() && arr[qMin.peekLast()] >= arr[r]) {
					qMin.pollLast();
				}
				qMin.addLast(r);
				// 判断此时子串是否符合条件
				if (arr[qMax.peekFirst()] - arr[qMin.peekFirst()] > num) {
					break;
				}
				r++;
			}
			res += r - l;
			// 跟新最大最小队列后 就不需要从新的开头再计算
			if (l == qMax.peekFirst()) {
				qMax.pollFirst();
			}
			if (l == qMin.peekFirst()) {
				qMin.pollFirst();
			}
			l++;
		}
		return res;
	}

	// for test
	public static int getNum1(int[] arr, int num) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		LinkedList<Integer> qmin = new LinkedList<Integer>();
		LinkedList<Integer> qmax = new LinkedList<Integer>();
		int L = 0;
		int R = 0;
		// [L..R) -> [0,0) 窗口内无数 [1,1)
		// [0,1) -> [0~0]
		int res = 0;
		while (L < arr.length) { // L是开头位置，尝试每一个开头

			// 如果此时窗口的开头是L,下面的while工作:R向右扩到违规为止

			while (R < arr.length) { // R是最后一个达标位置的再下一个
				while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[R]) {
					qmin.pollLast();
				}
				qmin.addLast(R);
				// R -> arr[R],
				while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
					qmax.pollLast();
				}
				qmax.addLast(R);

				if (arr[qmax.getFirst()] - arr[qmin.getFirst()] > num) {
					break;
				}
				R++;
			}
			// R是最后一个达标位置的再下一个，第一个违规的位置
			res += R - L;
			if (qmin.peekFirst() == L) {
				qmin.pollFirst();
			}
			if (qmax.peekFirst() == L) {
				qmax.pollFirst();
			}
			L++;
		}
		return res;
	}

	// for test
	public static int[] getRandomArray(int len) {
		if (len < 0) {
			return null;
		}
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			arr[i] = (int) (Math.random() * 10);
		}
		return arr;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr != null) {
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}
	}

	// 复习
	public static int AllLessNumSubArr(int[] arr, int num) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		int res = 0;
		int R = 0;
		int L = 0;
		LinkedList<Integer> queueMax = new LinkedList<>();
		LinkedList<Integer> queueMin = new LinkedList<>();
		while (L < arr.length) {
			while (R < arr.length) {
				// 构建大小双端队列
				while (!queueMax.isEmpty() && arr[queueMax.peekLast()] <= arr[R]) {
					queueMax.pollLast();
				}
				queueMax.addLast(R);
				while (!queueMin.isEmpty() && arr[queueMin.peekLast()] >= arr[R]) {
					queueMin.pollLast();
				}
				queueMin.addLast(R);
				if (arr[queueMax.peekFirst()] - arr[queueMin.peekFirst()] > num) {// index到达不符合条件的位置
					break;
				}
				R++;
			}
			if (L == queueMax.peekFirst()) {// 弹出开头
				queueMax.pollFirst();
			}
			if (L == queueMin.peekFirst()) {
				queueMin.pollFirst();
			}
			res += R - L;
			L++;// L变化必须在外侧循环判断否则一直在内循环死循环
		}
		return res;
	}

	public static void main(String[] args) {
		int[] arr = getRandomArray(30);
		int num = 5;
		printArray(arr);
		System.out.println(getNum(arr, num) == AllLessNumSubArr(arr, num));

	}

}
