package class06;

public class Review {
	// 1.求子数组的最大异或和
	public static int maxEOR(int[] arr) {
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

	public static class Node {
		Node[] nexts = new Node[2];
	}

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
				max |= (best ^ path) << move;// best^path求出值 记录在结果上
				curNode = curNode.nexts[best];
			}
			return max;
		}
	}
}
