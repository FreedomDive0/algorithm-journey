package class04;

import java.util.ArrayList;

public class Code02_TreeEqual {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int v) {
			value = v;
		}
	}

	public static boolean containsTree1(Node big, Node small) {
		if (small == null) {
			return true;
		}
		if (big == null) {
			return false;
		}
		if (isSameValueStructure(big, small)) {
			return true;
		}
		return containsTree1(big.left, small) || containsTree1(big.right, small);
	}

//	判断树结构是否相同 
	public static boolean isSameValueStructure(Node head1, Node head2) {
		if (head1 == null && head2 != null) {
			return false;
		}
		if (head1 != null && head2 == null) {
			return false;
		}
		if (head1 == null && head2 == null) {
			return true;
		}
		if (head1.value != head2.value) {
			return false;
		}
		return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
	}

	public static boolean containsTree2(Node big, Node small) {
		if (small == null) {
			return true;
		}
		if (big == null) {
			return false;
		}
		ArrayList<String> biglist = preSerial(big);
		ArrayList<String> smallist = preSerial(small);
		String[] str = new String[biglist.size()];
		for (int i = 0; i < str.length; i++) {
			str[i] = biglist.get(i);
		}

		String[] match = new String[smallist.size()];
		for (int i = 0; i < match.length; i++) {
			match[i] = smallist.get(i);
		}
		return KMP(str, match) != -1;
	}

	// 先序序列化
	public static ArrayList<String> preSerial(Node head) {
		ArrayList<String> ans = new ArrayList<String>();
		pre(head, ans);
		return ans;
	}

	public static void pre(Node head, ArrayList<String> ans) {
		if (head == null) {
			ans.add(null);
		} else {
			ans.add(String.valueOf(head.value));
			pre(head.left, ans);
			pre(head.right, ans);
		}
	}

	public static int KMP(String[] str, String[] match) {
		if (str == null || match == null || str.length < 1 || match.length > str.length) {
			return -1;
		}
		int[] next = getNext(match);
		int x = 0;
		int y = 0;
		while (x < str.length && y < match.length) {
			if (isEqual(str[x], match[y])) {
				x++;
				y++;
			} else if (y > 0) {
				y = next[y];
			} else {
				x++;
			}
		}
		return y == match.length ? x - y : -1;
	}

	public static int[] getNext(String[] match) {
		if (match.length == 1) {
			return new int[] { -1 };
		}
		int[] next = new int[match.length];
		int cn = 0;// 表示当前下标 所对应next应该参考的下标
		int index = 2;
		next[0] = -1;
		next[1] = 0;
		while (index < match.length) {
			if (isEqual(match[index - 1], match[cn])) {// 比较位置为index-1
				next[index++] = ++cn;// 1.更新next 2.index后移 3.cn更新位置
			} else if (cn > 0) {// 不相等且cn没到0位置 向前更新cn
				cn = next[cn];
			} else {// cn到了0 index无法匹配任何子串 后移 并且记录当前next为0
				next[index++] = 0;
			}
		}
		return next;
	}

//	因为序列化时 会产生null字符串 所有光使用equals会报空指针异常 需额外处理
	public static boolean isEqual(String a, String b) {
		if (a == null && b == null) {
			return true;
		} else {
			if (a == null || b == null) {
				return false;
			} else {
				return a.equals(b);
			}
		}
	}

	// for test
	public static Node generateRandomBST(int maxLevel, int maxValue) {
		return generate(1, maxLevel, maxValue);
	}

	// for test
	public static Node generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel || Math.random() < 0.5) {
			return null;
		}
		Node head = new Node((int) (Math.random() * maxValue));
		head.left = generate(level + 1, maxLevel, maxValue);
		head.right = generate(level + 1, maxLevel, maxValue);
		return head;
	}

	public static void main(String[] args) {
		int bigTreeLevel = 7;
		int smallTreeLevel = 4;
		int nodeMaxValue = 5;
		int testTimes = 100000;
		System.out.println("test begin");
		for (int i = 0; i < testTimes; i++) {
			Node big = generateRandomBST(bigTreeLevel, nodeMaxValue);
			Node small = generateRandomBST(smallTreeLevel, nodeMaxValue);
			boolean ans1 = containsTree1(big, small);
			boolean ans2 = containsTree2(big, small);
			if (ans1 != ans2) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish!");

	}
}
