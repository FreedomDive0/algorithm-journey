package class07;

import java.util.Stack;

public class Code03_VisibleMountains {
	public static class Record {
		public int Height;
		public int times;

		public Record(int height) {
			Height = height;
		}

	}

	public static int visibleM(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int length = arr.length;
		int maxIndex = 0;// 最大值下标 放在栈底最后被弹出
		for (int i = 1; i < length; i++) {
			if (arr[i] > arr[maxIndex]) {
				maxIndex = i;
			}
		}
		Stack<Record> stack = new Stack<Record>();
		stack.add(new Record(arr[maxIndex]));
		int paris = 0;
		int index = getNextIndex(maxIndex, length);
		while (index != maxIndex) {// 确定方向小找大 防止重复
			if (stack.peek().Height == arr[index]) {// 当前高度与栈顶相同
				stack.peek().times++;
				index = getNextIndex(index, length);
			} else if (stack.peek().Height > arr[index]) {// 当前高度小于栈顶高度
				stack.add(new Record(arr[index]));
				index = getNextIndex(index, length);
			} else {// 当前高度大于栈顶高度 弹出结算
				int k = stack.pop().times;
				paris += 2 * k + getInnerPairs(k);
			}
		}
		while (stack.size() > 2) {// 栈中元素多余两个 每个高度一定有两个比它高的山山存在
			int k = stack.pop().times;
			paris += 2 * k + getInnerPairs(k);
		}
		if (stack.size() == 2) {
			int k = stack.pop().times;// 需要考虑最高山峰有几个
			paris += k * (stack.peek().times >= 2 ? 2 : 1) + getInnerPairs(k);
		}
		if (stack.size() == 1) {// 最高山峰不存在比它大的 可见山峰对 只在内部产生
			int k = stack.pop().times;
			paris += getInnerPairs(k);
		}
		return paris;
	}

	// 循环数组下一个下标
	public static int getNextIndex(int i, int length) {
		return i < length - 1 ? i + 1 : 0;
	}

	public static int getLastIndex(int i, int length) {
		return i > 0 ? i - 1 : length - 1;
	}

	// 组合C(k,2)
	public static int getInnerPairs(int k) {
		return k == 1 ? 0 : (k * (k - 1) / 2);
	}
}
