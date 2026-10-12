package class04;

import java.util.LinkedList;
import java.util.Queue;

public class Review {
	// 1.KMP算法
	public static int KMP(String str, String match) {
		if (str == null || match == null || match.length() > str.length()) {
			return -1;
		}
		char[] strs = str.toCharArray();
		char[] matchs = match.toCharArray();
		int[] next = getArr(matchs);
		int index = 0;
		int indexM = 0;
		while (index < str.length() && indexM < match.length()) {
			if (strs[index] == matchs[indexM]) {
				index++;
				indexM++;
			} else if (next[indexM] == -1) {
				index++;
			} else {
				indexM = next[indexM];
			}
		}
		return indexM == matchs.length ? index - indexM : -1;
	}

	public static int[] getArr(char[] match) {
		if (match.length == 1) {
			return new int[] { -1 };
		}
		int[] next = new int[match.length];
		next[0] = -1;
		next[1] = 0;
		int index = 2;
		int jumpIndex = 0;
		while (index < match.length) {
			if (match[index - 1] == match[jumpIndex]) {
				next[index++] = ++jumpIndex;
			} else if (next[jumpIndex] == -1) {// jumpIndex来到0位置 该位置没有能匹配的前后缀
				index++;
			} else {
				jumpIndex = next[jumpIndex];
			}
		}
		return next;
	}

//	2.旋转词
	public static boolean isRoatition(String str1, String str2) {
		if (str1 == null && str2 == null) {
			return true;
		}
		if (str1 == null && str2 != null || str1 != null && str2 == null || str1.length() != str2.length()) {
			return false;
		}
		String all = str1 + str1;
		return KMP(all, str2) != -1 ? true : false;
	}

//	3.二叉树T1和T2 T1是否具有T2结构
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int value) {
			this.value = value;
		}

	}

	// 序列化与反序列化
	// 先序
	public static Queue<String> preS(Node root) {
		Queue<String> queue = new LinkedList<>();
		AddS(root, queue);
		return queue;
	}

	public static void AddS(Node root, Queue<String> queue) {
		if (root == null) {
			queue.add("null");
			return;
		} else {
			queue.add(String.valueOf(root.value));
			AddS(root.left, queue);
			AddS(root.right, queue);
		}
	}

//	public static boolean isContain(Node head, Node node) {
//		if (node == null) {
//			return true;
//		}
//		if (head == null) {
//			return false;
//		}
//		Queue<String> listA = preS(head);
//		Queue<String> listB = preS(node);
//		// 需后续完善
//		return KMP1(listA, listB) != -1 ? true : false;
//	}
}
