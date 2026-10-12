package class01;

public class Review {
	// 1.有序arr[] 数代表X轴上的位置 k长度的绳子 做多cover几个点 在点上也叫cover
	public static int maxCover(int[] arr, int k) {
//		if (arr == null || arr.length < 1 || k < 0) {
//			return -1;
//		}
		int l = 0;
		int r = 0;
		int max = 0;
		while (l < arr.length) {
			while (r < arr.length && arr[r] - arr[l] <= k) {
				r++;
			}
			max = Math.max(max, r - (l++));// 跳出r是不符合条件的点 不需要加1
		}
		return max;
	}

	// 2.括号匹配问题 1)是否符合 2)需要补几个
	public static int bracketsMatch(String str) {
		if (str == null || str.length() < 1) {
			return 0;
		}
		int count = 0;
		int need = 0;
		char[] chs = str.toCharArray();
		for (int i = 0; i < chs.length; i++) {
			if (chs[i] == '(') {
				count++;
			} else if (count == 0) {
				need++;
			} else {
				count--;
			}
		}
		return count + need;
	}

	// 3.String最长的括号有效子串长度
	// 4.正方形涂色R，G两种颜色 anyR比G靠左
	// 5.matrix[][] 由0，1组成 1组成的边围成的正方形边长最长是多少
	// 6.给定一个正整数M 构造出一个长度为M的arr 且any i，j，k i < j < k 都有arr[i]+arr[k]!=2*arr[j]
	// 7.给定二叉树头节点head
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int val) {
			value = val;
		}
	}

	// 7.1 从head出发必须到叶节点 最长路径
	public static int maxFromHeadToLeaf(Node head) {
		if (head == null) {
			return 0;
		}
		processMFHTL(head, 0);
		return max1;
	}

	public static int max1 = Integer.MIN_VALUE;

	public static void processMFHTL(Node cur, int preSum) {
		if (cur.left == null && cur.right == null) {
			max1 = Math.max(max1, preSum + cur.value);
			return;
		}
		if (cur.left != null) {
			processMFHTL(cur.left, preSum + cur.value);
		}
		if (cur.right != null) {
			processMFHTL(cur.right, preSum + cur.value);
		}
	}

	// 7.2 any节点向下到any节点
	public static int maxFromAnyDownToAny(Node head) {
		if (head == null) {
			return 0;
		}
		return processMFADTA(head).allMax;
	}

	public static class MFADTAInfo {
		public int allMax;
		public int headMax;

		public MFADTAInfo(int allMax, int headMax) {
			this.allMax = allMax;
			this.headMax = headMax;
		}

	}

	public static MFADTAInfo processMFADTA(Node cur) {
		if (cur == null) {
			return null;
		}
		MFADTAInfo leftInfo = processMFADTA(cur.left);
		MFADTAInfo rightInfo = processMFADTA(cur.right);
		int p1 = Integer.MIN_VALUE;
		int p4 = Integer.MIN_VALUE;
		if (leftInfo != null) {
			p1 = leftInfo.allMax;
			p4 = leftInfo.headMax + cur.value;
		}
		int p2 = Integer.MIN_VALUE;
		int p5 = Integer.MIN_VALUE;
		if (rightInfo != null) {
			p2 = rightInfo.allMax;
			p5 = rightInfo.headMax + cur.value;
		}
		int p3 = cur.value;
		int allMax = Math.max(Math.max(p1, p2), Math.max(Math.max(p3, p4), p5));
		int headMax = Math.max(Math.max(p4, p5), p3);
		return new MFADTAInfo(allMax, headMax);
	}

	// 7.3 any to any
	public static int AnyToAnyMax(Node head) {
		if (head == null) {
			return 0;
		}
		return processATA(head).allMax;
	}

	public static class ATAInfo {
		public int allMax;
		public int headMax;

		public ATAInfo(int allMax, int headMax) {
			this.allMax = allMax;
			this.headMax = headMax;
		}

	}

	public static ATAInfo processATA(Node cur) {
		if (cur == null) {
			return null;
		}
		ATAInfo leftInfo = processATA(cur.left);
		ATAInfo rightInfo = processATA(cur.right);
		int p1 = Integer.MIN_VALUE;
		int p4 = Integer.MIN_VALUE;
		if (leftInfo != null) {
			p1 = leftInfo.allMax;
			p4 = leftInfo.headMax + cur.value;
		}
		int p2 = Integer.MIN_VALUE;
		int p5 = Integer.MIN_VALUE;
		if (rightInfo != null) {
			p2 = rightInfo.allMax;
			p5 = rightInfo.headMax + cur.value;
		}
		int p3 = cur.value;
		int p6 = Integer.MIN_VALUE;
		if (leftInfo != null && rightInfo != null) {
			p6 = leftInfo.headMax + rightInfo.headMax + cur.value;
		}
		int allMax = Math.max(Math.max(p6, Math.max(p1, p2)), Math.max(Math.max(p3, p4), p5));
		int headMax = Math.max(Math.max(p4, p5), p3);
		return new ATAInfo(allMax, headMax);
	}
}
