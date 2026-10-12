package class06;

public class Code01_MaxEOR {

	// O(N^2)
	public static int maxXorSubarray1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		// 准备一个前缀异或和数组eor
		// eor[i] = arr[0...i]的异或结果
		int[] eor = new int[arr.length];
		eor[0] = arr[0];
		// 生成eor数组，eor[i]代表arr[0..i]的异或和
		for (int i = 1; i < arr.length; i++) {
			eor[i] = eor[i - 1] ^ arr[i];
		}
		int max = Integer.MIN_VALUE;
		for (int j = 0; j < arr.length; j++) {
			for (int i = 0; i <= j; i++) { // 依次尝试arr[0..j]、arr[1..j]..arr[i..j]..arr[j..j]
				max = Math.max(max, i == 0 ? eor[j] : eor[j] ^ eor[i - 1]);
			}
		}
		return max;
	}

	// 前缀树的节点类型，每个节点向下只可能有走向0或1的路
	// node.nexts[0] == null 0方向没路
	// node.nexts[0] != null 0方向有路
	public static class Node {
		public Node[] nexts = new Node[2];
	}

	// 基于本题，定制前缀树的实现
	public static class NumTrie {
		// 头节点
		public Node head = new Node();

		// 把某个数字newNum加入到这棵前缀树里
		// num是一个32位的整数，所以加入的过程一共走32步
		public void add(int newNum) {
			Node cur = head;
			for (int move = 31; move >= 0; move--) {
				// 从高位到低位，取出每一位的状态，如果当前状态是0，
				// path(int) = 0
				// ，如果当前状态是1
				// path(int) = 1
				int path = ((newNum >> move) & 1);
				// 无路新建、有路复用
				cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
				cur = cur.nexts[path];
			}
		}

		// 该结构之前收集了一票数字，并且建好了前缀树
		// sum,和 谁 ^ 最大的结果（把结果返回）
		public int maxXor(int sum) {
			Node cur = head;
			int res = 0;
			for (int move = 31; move >= 0; move--) {
				int path = (sum >> move) & 1;
				// 期待的路
				int best = move == 31 ? path : (path ^ 1);
				// 实际走的路
				best = cur.nexts[best] != null ? best : (best ^ 1);
				// (path ^ best) 当前位位异或完的结果
				res |= (path ^ best) << move;
				cur = cur.nexts[best];
			}
			return res;
		}
	}

	public static int maxXorSubarray2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int eor = 0; // 0..i 异或和
		// 前缀树 -> numTrie
		NumTrie numTrie = new NumTrie();
		numTrie.add(0); // 一个数也没有的时候，异或和是0
		for (int i = 0; i < arr.length; i++) {
			eor ^= arr[i]; // eor -> 0..i异或和
			// X, 0~0 , 0~1, .., 0~i-1
			max = Math.max(max, numTrie.maxXor(eor));
			numTrie.add(eor);
		}
		return max;
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// 测试
	public static int maxEOR(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		int eor = 0;
		int ans = Integer.MIN_VALUE;
		TreeNode tree = new TreeNode();
		tree.addNum(0);
		for (int i = 0; i < arr.length; i++) {
			eor ^= arr[i];
			ans = Math.max(ans, tree.getMax(eor));
			tree.addNum(eor);
		}
		return ans;
	}

	public static class TreeNode {
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

	// 测试
	// 1.求子数组的最大异或和
	public static int maxEOR1(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		int eor = 0;
		int max = Integer.MIN_VALUE;
		TrieNum trie = new TrieNum();
		trie.addNum(0);// 一个数也没有的时候有0异或和
		for (int i = 0; i < arr.length; i++) {
			eor ^= arr[i];
			max = Math.max(max, trie.getMaxEOR(eor));// i结尾所有子串的最大异或和
			trie.addNum(eor);// 0~i异或和==0~j-1异或和^j~i异或和
		}
		return max;
	}

//	public static class Node {
//		Node[] nexts = new Node[2];
//	}

	public static class TrieNum {
		Node head = new Node();

		public void addNum(int num) {
			Node curNode = head;
			for (int move = 31; move >= 0; move--) {
				int cur = (num >> move) & 1;
				curNode.nexts[cur] = curNode.nexts[cur] == null ? new Node() : curNode.nexts[cur];// 有路复用 没路建路
				curNode = curNode.nexts[cur];
			}
		}

		public int getMaxEOR(int num) {
			Node curNode = head;
			int max = 0;
			for (int move = 31; move >= 0; move--) {
				int path = ((num >> move) & 1);
				int best = move == 31 ? path : (path ^ 1);// 符号位要相同 因为负数使用补码 所以高位也要尽量大 所以正负数要求相同
				best = curNode.nexts[best] == null ? (best ^ 1) : best;// 是否能取到最好值 ^1取反
				max |= (best ^ path) << move;
				curNode = curNode.nexts[best];
			}
			return max;
		}
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 30;
		int maxValue = 50;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			int comp = maxEOR1(arr);
			int res = maxXorSubarray2(arr);
			if (res != comp) {
				succeed = false;
				printArray(arr);
				System.out.println(res);
				System.out.println(comp);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
		//
		// // int[] arr = generateRandomArray(6, maxValue);
		// int[] arr = { 3, -28, -29, 2};
		//
		// for (int i = 0; i < arr.length; i++) {
		// System.out.println(arr[i] + " ");
		// }
		// System.out.println("=========");
		// System.out.println(maxXorSubarray(arr));
		// System.out.println((int) (-28 ^ -29));

	}
}
