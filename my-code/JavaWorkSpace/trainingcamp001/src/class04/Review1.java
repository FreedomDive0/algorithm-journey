package class04;

import java.util.ArrayList;

public class Review1 {
	// 1.字符串匹配
	public static int KMP(String str, String match) {
		if (str == null || match == null || match.length() < 1 || str.length() < match.length()) {
			return -1;
		}
		char[] s = str.toCharArray();
		char[] m = match.toCharArray();
		int sIndex = 0;
		int mIndex = 0;
		int[] next = getNexts(m);
		while (sIndex < str.length() && mIndex < match.length()) {
			if (s[sIndex] == m[mIndex]) {
				sIndex++;
				mIndex++;
			} else if (mIndex > 0) {
				mIndex = next[mIndex];
			} else {
				sIndex++;
			}
		}
		return mIndex < match.length() ? -1 : sIndex - mIndex;
	}

	// 得到next数组
	public static int[] getNexts(char[] match) {
		if (match.length == 1) {
			return new int[] { -1 };
		}
		int[] next = new int[match.length];
		next[0] = -1;
		next[1] = 0;
		int index = 2;
		int curMatch = 0;
		while (index < match.length) {
			if (match[index - 1] == match[curMatch]) {
				next[index++] = ++curMatch;// curMatch既表示当前前缀比较位置又表示当前前后缀相同部分长度
			} else if (curMatch > 0) {
				curMatch = next[curMatch];
			} else {
				index++;// 此时curIndex==0
			}
		}
		return next;
	}

	// 2.是否为旋转数组
	public static boolean isRotation(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() != s2.length()) {
			return false;
		}
		String all = s1 + s1;
		return KMP(all, s2) != -1;
	}

	// 3.T2二叉树是否为T1的子结构
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int v) {
			value = v;
		}
	}

	// (1)每个节点比对
	public static boolean treeEqual(Node t1, Node t2) {
		if (t2 == null) {
			return true;
		}
		if (t1 == null) {
			return false;
		}
		if (isSameStructure(t1, t2)) {
			return true;
		}
		return treeEqual(t1.left, t2) || treeEqual(t1.right, t2);
	}

	public static boolean isSameStructure(Node t1, Node t2) {
		if ((t1 == null && t2 != null) || (t1 != null && t2 == null)) {
			return false;
		}
		if (t1 == null && t2 == null) {
			return true;
		}
		if (t1.value != t2.value) {
			return false;
		}
		return isSameStructure(t1.left, t2.left) && isSameStructure(t1.right, t2.right);
	}

	// (2)序列化后,使用KMP
	public static ArrayList<String> preSerial1(Node head) {
		ArrayList<String> ans = new ArrayList<String>();
		preS(head, ans);
		return ans;
	}

	public static void preS(Node head, ArrayList<String> ans) {
		if (head == null) {
			ans.add(null);
		} else {
			ans.add(String.valueOf(head.value));
			preS(head.left, ans);
			preS(head.right, ans);
		}
	}

	public static boolean containsTree(Node t1, Node t2) {
		if (t2 == null) {
			return true;
		}
		if (t1 == null) {
			return false;
		}
		ArrayList<String> arrT1 = preSerial1(t1);
		ArrayList<String> arrT2 = preSerial1(t2);
		String[] s1 = new String[arrT1.size()];
		String[] s2 = new String[arrT2.size()];
		for (int i = 0; i < s1.length; i++) {
			s1[i] = arrT1.get(i);
		}
		for (int i = 0; i < s2.length; i++) {
			s2[i] = arrT2.get(i);
		}
		return KMPT(s1, s2) != -1;
	}

	public static int KMPT(String[] s1, String[] s2) {
		int[] nexts = getNexts(s2);
		int index = 0;
		int mIndex = 0;
		while (index < s1.length && mIndex < s2.length) {
			if (isEqual1(s1[index], s2[mIndex])) {
				index++;
				mIndex++;
			} else if (mIndex > 0) {
				mIndex = nexts[mIndex];
			} else {
				index++;
			}
		}
		return mIndex == s2.length ? index - mIndex : -1;
	}

	public static int[] getNexts(String[] match) {
		int[] nexts = new int[match.length];
		nexts[0] = -1;
		nexts[1] = 0;
		int mIndex = 0;
		int index = 2;
		while (index < match.length) {
			if (isEqual1(match[index - 1], match[mIndex])) {// 空无法调用方法
				nexts[index++] = ++mIndex;
			} else if (mIndex > 0) {
				mIndex = nexts[mIndex];
			} else {
				index++;
			}
		}
		return nexts;
	}

	public static boolean isEqual1(String a, String b) {
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
}
