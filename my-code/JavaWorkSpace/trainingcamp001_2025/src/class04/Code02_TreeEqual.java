package class04;

import java.util.ArrayList;
import java.util.List;

public class Code02_TreeEqual {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int v) {
			value = v;
		}
	}

	// big做头节点的树，其中是否有某棵子树的结构，是和small为头的树，完全一样的
	// 1.暴力方法 每个节点验证是否是子树
	public static boolean containsTree11(Node big, Node small) {
		if (small == null) {// small是空树肯定包含
			return true;
		}
		if (big == null) {// small不为空 big为空
			return false;
		}
		if (isSameStructure(big, small)) {
			return true;
		}
		return containsTree11(big.left, small) || containsTree11(big.right, small);
	}

	public static boolean isSameStructure(Node n1, Node n2) {
		if (n1 == null && n2 == null) {
			return true;
		}
		if (n1 == null || n2 == null || n1.value != n2.value) {
			return false;
		}
		return isSameStructure(n1.left, n2.left) && isSameStructure(n1.right, n2.right);
	}

	// 2.序列化后查看是否包含字符串
	public static boolean containsTree22(Node big, Node small) {
		if (small == null) {
			return true;
		}
		if (big == null) {
			return false;
		}
		List<String> s1 = preSerialize(big);
		List<String> s2 = preSerialize(small);
		String[] str1 = new String[s1.size()];
		String[] str2 = new String[s2.size()];
		for (int i = 0; i < s1.size(); i++) {
			str1[i] = s1.get(i);
		}
		for (int i = 0; i < s2.size(); i++) {
			str2[i] = s2.get(i);
		}
		return KMP(str1, str2) != -1;
	}

	public static List<String> preSerialize(Node node) {
		List<String> ans = new ArrayList<String>();
		preProcess(node, ans);
		return ans;
	}

	public static void preProcess(Node node, List<String> ans) {
		if (node == null) {
			ans.add(null);
		} else {
			ans.add(String.valueOf(node.value));
			preProcess(node.left, ans);
			preProcess(node.right, ans);
		}
	}

	public static int KMP(String[] s, String[] m) {
		if (s == null || m == null || s.length < m.length) {
			return -1;
		}
		int[] nexts = getNexts(m);
		int idx = 0;
		int matchIdx = 0;
		while (idx < s.length && matchIdx < m.length) {
			if (isStrEqual(s[idx], m[matchIdx])) {
				matchIdx++;
				idx++;
			} else if (matchIdx != 0) {
				matchIdx = nexts[matchIdx];
			} else {
				idx++;
			}
		}
		return matchIdx == m.length ? idx - matchIdx : -1;
	}

	public static int[] getNexts(String[] match) {
		if (match.length < 2) {
			return new int[] { -1 };
		}
		int n = match.length;
		int[] nexts = new int[n];
		nexts[0] = -1;
		nexts[1] = 0;
		int idx = 2;
		int cn = 0;
		while (idx < n) {
			if (isStrEqual(match[idx - 1], match[cn])) {
				nexts[idx++] = ++cn;
			} else if (cn != 0) {
				cn = nexts[cn];
			} else {
				idx++;
			}
		}
		return nexts;
	}

	public static boolean isStrEqual(String a, String b) {
		if (a == null && b == null) {
			return true;
		}
		if (a == null || b == null) {
			return false;
		}
		return a.equals(b);
	}
}
