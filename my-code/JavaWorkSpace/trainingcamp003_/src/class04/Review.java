package class04;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

public class Review {
	// 1.String[] strs 打印蕴含目录 同一级字母顺序排列不能乱
	public static class Node {
		String path;
		TreeMap<String, Node> nexts;

		public Node(String path) {
			nexts = new TreeMap<String, Node>();
			this.path = path;
		}

	}

	public static void printFolder(String[] folders) {
		if (folders == null || folders.length < 1) {
			return;
		}
		Node head = generateFolderTree(folders);
		printFolderTree(head, 0);
	}

	public static Node generateFolderTree(String[] strs) {
		Node head = new Node("");
		for (int i = 0; i < strs.length; i++) {
			Node cur = head;
			String[] path = strs[i].split("\\\\");
			for (String str : path) {
				if (!cur.nexts.containsKey(str)) {
					cur.nexts.put(str, new Node(str));
					cur = cur.nexts.get(str);
				} else {
					cur = cur.nexts.get(str);
				}
			}
		}
		return head;
	}

	public static void printFolderTree(Node head, int level) {
		if (level != 0) {
			System.out.println(generateSpace(level) + head.path);
		}
		for (Node node : head.nexts.values()) {
			printFolderTree(node, level + 1);
		}
	}

	public static String generateSpace(int num) {
		String space = "";
		for (int i = 1; i < num; i++) {// 第一个目录不加空格
			space += "    ";
		}
		return space;
	}

	// 2.搜索二叉树==>双向链表
	public static class Node1 {
		public int value;
		public Node1 left;
		public Node1 right;

		public Node1(int data) {
			this.value = data;
		}
	}

	public static Node1 BSTToDoublieLinkedList(Node1 head) {
		if (head == null) {
			return null;
		}
		return processBSTTDL(head).start;
	}

	public static class BSTTDLLInfo {
		Node1 start;
		Node1 end;

		public BSTTDLLInfo(Node1 start, Node1 end) {
			this.start = start;
			this.end = end;
		}

	}

	public static BSTTDLLInfo processBSTTDL(Node1 node) {
		if (node == null) {
			return new BSTTDLLInfo(null, null);
		}
		BSTTDLLInfo leftInfo = processBSTTDL(node.left);
		BSTTDLLInfo rightInfo = processBSTTDL(node.right);
		if (leftInfo.end != null) {
			leftInfo.end.right = node;
		}
		node.left = leftInfo.end;
		node.right = rightInfo.start;
		if (rightInfo.start != null) {
			rightInfo.start.left = node;
		}
		Node1 start = leftInfo.start == null ? node : leftInfo.start;
		Node1 end = rightInfo.end == null ? node : rightInfo.end;
		return new BSTTDLLInfo(start, end);
	}

	// 3.无重复值二叉树 给定先序遍历pre[] 中序遍历in[] return后序遍历pos[]
	public static int[] preAndInArrayToPosArray(int[] pre, int[] in) {
		if (pre == null || in == null || pre.length < 1 || in.length < 1 || pre.length != in.length) {
			return null;
		}
		int[] pos = new int[pre.length];
		int n = pre.length - 1;
		HashMap<Integer, Integer> inMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < in.length; i++) {
			inMap.put(in[i], i);
		}
		processPAITP(pre, 0, n, in, 0, n, pos, 0, n, inMap);
		return pos;
	}

	public static void processPAITP(int[] pre, int L1, int R1, int[] in, int L2, int R2, int[] pos, int L3, int R3,
			HashMap<Integer, Integer> inMap) {
		if (L1 < 0 || R1 > in.length || L2 < 0 || R2 > in.length || L3 < 0 || R3 > in.length || L1 > R1 || L2 > R2
				|| L3 > R3) {
			return;
		}
		if (L1 == R1) {
			pos[L3] = pre[L1];
			return;
		}
		pos[R3] = pre[L1];// 解决头
		int mid = inMap.get(pre[L1]);
		int leftSize = mid - L2;
		// 解决左树
		processPAITP(pre, L1 + 1, L1 + leftSize, in, L2, mid - 1, pos, L3, L3 + leftSize - 1, inMap);// 一定要注意下标的使用
		// 解决右树
		processPAITP(pre, L1 + leftSize + 1, R1, in, mid + 1, R2, pos, L3 + leftSize, R3 - 1, inMap);
	}

	// 4.最长递增子序列 O(N*logN)
	public static int[] LIS(int[] arr) {
		if (arr == null || arr.length < 1) {
			return null;
		}
		int n = arr.length;
		int[] dp = new int[n];
		int[] ends = new int[n];// 长度为i+1的递增子序列最小结尾
		dp[0] = 1;
		ends[0] = arr[0];
		int l = 0;
		int right = 0;// ends右边界
		for (int i = 1; i < n; i++) {
			l = 0;
			int r = right;
			while (l <= r) {
				int mid = l + (r - l) / 2;
				if (ends[mid] < arr[i]) {
					l = mid + 1;
				} else {
					r = mid - 1;
				}
			}
			right = Math.max(right, l);
			ends[l] = arr[i];
			dp[i] = l + 1;
		}
		return dp;
	}

	// 5.信封有长和宽 只有A长>B长&&A宽>B宽 B才能装入A 返回最多嵌套层数
	public static class Envelope {
		public int l;
		public int h;

		public Envelope(int weight, int hight) {
			l = weight;
			h = hight;
		}
	}

	public static class EnvelopeComparator implements Comparator<Envelope> {
		@Override
		public int compare(Envelope o1, Envelope o2) {
			return o1.l != o2.l ? o1.l - o2.l : o2.h - o1.h;
		}
	}

	public static int envelopeProblem(Envelope[] envelopes) {
		if (envelopes == null || envelopes.length < 1) {
			return 0;
		}
		Arrays.sort(envelopes, new EnvelopeComparator());
		int[] widths = new int[envelopes.length];
		for (int i = 0; i < widths.length; i++) {
			widths[i] = envelopes[i].h;
		}
		int[] ends = new int[envelopes.length];// 长度为i+1的递增子序列最小结尾
		ends[0] = widths[0];
		int l = 0;
		int right = 0;// ends右边界
		for (int i = 1; i < widths.length; i++) {
			l = 0;
			int r = right;
			while (l <= r) {
				int mid = l + (r - l) / 2;
				if (ends[mid] < widths[i]) {
					l = mid + 1;
				} else {
					r = mid - 1;
				}
			}
			right = Math.max(right, l);
			ends[l] = widths[i];
		}
		return right + 1;
	}

	// 6.给定arr[] return子数组最大累加和
	public static int subArrayMaxSum(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		int cur = 0;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			cur += arr[i];
			max = Math.max(max, cur);
			cur = cur < 0 ? 0 : cur;
		}
		return max;
	}

	// 7.matrix[][] return子矩阵 最大累加和
	public static int subMatrixMaxSum(int[][] matrix) {
		if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
			return 0;
		}
		int cur = 0;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < matrix.length; i++) {
			int[] s = new int[matrix[0].length];// 用累加和压缩数组 分别求每个矩形范围内的最大值
			for (int j = i; j < matrix.length; j++) {
				cur = 0;
				for (int k = 0; k < matrix[0].length; k++) {
					s[k] += matrix[j][k];
					cur += s[k];
					max = Math.max(max, cur);
					cur = cur < 0 ? 0 : cur;
				}
			}
		}
		return max;
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 1, 3, 5, 7, 9 };
		int l = 0;
		int r = arr.length - 1;
		while (l <= r) {
			int mid = (r + l) / 2;
			if (arr[mid] < 10) {
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
		System.out.println("l:" + l);
	}
}
