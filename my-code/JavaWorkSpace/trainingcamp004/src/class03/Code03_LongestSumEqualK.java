package class03;

import java.util.HashMap;

public class Code03_LongestSumEqualK {
	public static class Node {
		int value;
		Node left;
		Node right;

		public Node(int value) {
			this.value = value;
			this.left = null;
			this.right = null;
		}

	}

	public static int lsek(Node head, int K) {
		if (head == null) {
			return 0;
		}
		HashMap<Integer, Integer> sumMap = new HashMap<>();
		sumMap.put(0, -1);
		process(head, 0, 0, K, sumMap);
		return ans;
	}

	static int ans = 0;

	public static void process(Node x, int preSum, int level, int K, HashMap<Integer, Integer> sumMap) {
		if (x != null) {
			int allSum = preSum + x.value;// 当前节点累加和
			if (sumMap.containsKey(allSum - K)) {// 存在答案记录答案
				ans = Math.max(ans, level - sumMap.get(allSum - K));
			}
			if (!sumMap.containsKey(allSum)) {
				sumMap.put(allSum, level);
			}
			process(x.left, allSum, level + 1, K, sumMap);
			process(x.right, allSum, level + 1, K, sumMap);
			if (sumMap.get(allSum) == level) {// 如果这个累加和是由该层提供的分支走完后删除
				sumMap.remove(allSum);
			}
		}
	}
}
