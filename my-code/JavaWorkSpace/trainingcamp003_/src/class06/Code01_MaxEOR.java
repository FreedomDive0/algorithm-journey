package class06;

public class Code01_MaxEOR {
	// 最大子数组异或和
	public static int maxEOR(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		int eor = 0;
		int ans = Integer.MIN_VALUE;
		NumTree tree = new NumTree();
		tree.addNum(0);
		for (int i = 0; i < arr.length; i++) {
			eor ^= arr[i];
			ans = Math.max(ans, tree.getMax(eor));
			tree.addNum(eor);
		}
		return ans;
	}

	public static class Node {
		public Node[] nexts = new Node[2];
	}

	public static class NumTree {
		Node head = new Node();

		public void addNum(int newNum) {
			Node cur = head;
			for (int move = 31; move >= 0; move--) {
				int bit = ((newNum >> move) & 1);// 从高位开始得到每一位
				cur.nexts[bit] = cur.nexts[bit] == null ? new Node() : cur.nexts[bit];
				cur = cur.nexts[bit];
			}
		}

		public int getMax(int eor) {// 得到与eor最大异或和的数字
			Node cur = head;
			int ans = 0;
			for (int move = 31; move >= 0; move--) {
				int bit = ((eor >> move) & 1);// 从高位开始得到每一位
				int best = move == 31 ? bit : (bit ^ 1);// 正数负数 符号都相同 正数不变 负数变正数 后面的位置尽可能变1
				best = cur.nexts[best] != null ? best : (best ^ 1);// 正数越大越好 负数使用补码也是越大越好 得到实际能得到的值
				ans |= (best ^ bit) << move;// 返回答案
				cur = cur.nexts[best];// 向下移动
			}
			return ans;
		}
	}
}
