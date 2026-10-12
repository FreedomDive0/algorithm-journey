package class04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Code03_PreAndInArrayToPosArray {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int v) {
			value = v;
		}
	}

	public static int[] preInToPos1(int[] pre, int[] in) {
		if (pre == null || in == null || pre.length != in.length) {
			return null;
		}
		int N = pre.length;
		int[] pos = new int[N];
		process1(pre, 0, N - 1, in, 0, N - 1, pos, 0, N - 1);
		return pos;
	}

	// L1...R1 L2...R2 L3...R3
	public static void process1(int[] pre, int L1, int R1, int[] in, int L2, int R2, int[] pos, int L3, int R3) {
		if (L1 > R1) {
			return;
		}
		if (L1 == R1) {
			pos[L3] = pre[L1];
			return;
		}
		pos[R3] = pre[L1];
		int mid = L2;
		for (; mid <= R2; mid++) {
			if (in[mid] == pre[L1]) {
				break;
			}
		}
		int leftSize = mid - L2;
		process1(pre, L1 + 1, L1 + leftSize, in, L2, mid - 1, pos, L3, L3 + leftSize - 1);
		process1(pre, L1 + leftSize + 1, R1, in, mid + 1, R2, pos, L3 + leftSize, R3 - 1);
	}

	public static int[] preInToPos2(int[] pre, int[] in) {
		if (pre == null || in == null || pre.length != in.length) {
			return null;
		}
		int N = pre.length;
		HashMap<Integer, Integer> inMap = new HashMap<>();
		for (int i = 0; i < N; i++) {
			inMap.put(in[i], i);
		}
		int[] pos = new int[N];
		process2(pre, 0, N - 1, in, 0, N - 1, pos, 0, N - 1, inMap);
		return pos;
	}

	public static void process2(int[] pre, int L1, int R1, int[] in, int L2, int R2, int[] pos, int L3, int R3,
			HashMap<Integer, Integer> inMap) {
		if (L1 > R1) {
			return;
		}
		if (L1 == R1) {
			pos[L3] = pre[L1];
			return;
		}
		pos[R3] = pre[L1];
		int mid = inMap.get(pre[L1]);
		int leftSize = mid - L2;
		process2(pre, L1 + 1, L1 + leftSize, in, L2, mid - 1, pos, L3, L3 + leftSize - 1, inMap);
		process2(pre, L1 + leftSize + 1, R1, in, mid + 1, R2, pos, L3 + leftSize, R3 - 1, inMap);
	}

	// for test
	public static int[] getPreArray(Node head) {
		ArrayList<Integer> arr = new ArrayList<>();
		fillPreArray(head, arr);
		int[] ans = new int[arr.size()];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = arr.get(i);
		}
		return ans;
	}

	// for test
	public static void fillPreArray(Node head, ArrayList<Integer> arr) {
		if (head == null) {
			return;
		}
		arr.add(head.value);
		fillPreArray(head.left, arr);
		fillPreArray(head.right, arr);
	}

	// for test
	public static int[] getInArray(Node head) {
		ArrayList<Integer> arr = new ArrayList<>();
		fillInArray(head, arr);
		int[] ans = new int[arr.size()];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = arr.get(i);
		}
		return ans;
	}

	// for test
	public static void fillInArray(Node head, ArrayList<Integer> arr) {
		if (head == null) {
			return;
		}
		fillInArray(head.left, arr);
		arr.add(head.value);
		fillInArray(head.right, arr);
	}

	// for test
	public static int[] getPosArray(Node head) {
		ArrayList<Integer> arr = new ArrayList<>();
		fillPostArray(head, arr);
		int[] ans = new int[arr.size()];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = arr.get(i);
		}
		return ans;
	}

	// for test
	public static void fillPostArray(Node head, ArrayList<Integer> arr) {
		if (head == null) {
			return;
		}
		fillPostArray(head.left, arr);
		fillPostArray(head.right, arr);
		arr.add(head.value);
	}

	// for test
	public static Node generateRandomTree(int value, int N) {
		HashSet<Integer> hasValue = new HashSet<Integer>();
		return createTree(value, 1, N, hasValue);
	}

	// for test
	public static Node createTree(int value, int level, int N, HashSet<Integer> hasValue) {
		if (level > N) {
			return null;
		}
		int cur = 0;
		do {
			cur = (int) (Math.random() * value) + 1;
		} while (hasValue.contains(cur));
		hasValue.add(cur);
		Node head = new Node(cur);
		head.left = createTree(value, level + 1, N, hasValue);
		head.right = createTree(value, level + 1, N, hasValue);
		return head;
	}

	// for test
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	// 测试
	public static int[] PITP(int[] pre, int[] in) {
		if (pre == null || in == null || pre.length != in.length) {
			return null;
		}
		HashMap<Integer, Integer> inOrderMap = new HashMap<>();// 记录中序遍历位置
		for (int i = 0; i < in.length; i++) {
			inOrderMap.put(in[i], i);
		}
		int[] pos = new int[pre.length];
		int len = pre.length;
		process(pre, in, pos, 0, len - 1, 0, len - 1, 0, len - 1, inOrderMap);
		return pos;
	}

	public static void process(int[] pre, int[] in, int[] pos, int l1, int r1, int l2, int r2, int l3, int r3,
			HashMap<Integer, Integer> inOrderMap) {
		if (l1 > r1) {
			return;
		}
		if (l3 == r3) {
			pos[l3] = pre[l1];
			return;
		}
		pos[r3] = pre[l1];// 先序遍历头左右 后序遍历左右头 先头填后尾 处理左右树
		int headIndex = inOrderMap.get(pre[l1]);
		int leftSize = headIndex - l2;
		process(pre, in, pos, l1 + 1, l1 + leftSize, l2, headIndex - 1, l3, l3 + leftSize - 1, inOrderMap);// 左树
		process(pre, in, pos, l1 + leftSize + 1, r1, headIndex + 1, r2, l3 + leftSize, r3 - 1, inOrderMap);// 右树
	}

	// 测试
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
		processPAITP(pre, L1 + 1, L1 + leftSize, in, L2, mid - 1, pos, L3, L3 + leftSize - 1, inMap);// 解决左树
		processPAITP(pre, L1 + leftSize + 1, R1, in, mid + 1, R2, pos, L3 + leftSize, R3 - 1, inMap);// 解决右树
	}

	public static void main(String[] args) {
		System.out.println("test begin");
		int N = 5;
		int value = 1000;
		int testTime = 100000;
		for (int i = 0; i < testTime; i++) {
			Node head = generateRandomTree(value, N);
			int[] pre = getPreArray(head);
			int[] in = getInArray(head);
			int[] pos = getPosArray(head);
			int[] ans1 = preInToPos2(pre, in);
			int[] ans2 = preAndInArrayToPosArray(pre, in);
			if (!isEqual(pos, ans1) || !isEqual(ans1, ans2)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test end");

	}
}
