package class06;

import java.util.ArrayList;
import java.util.List;

public class Code01_MorrisTraversal {
	public static class Node {
		public int value;
		Node left;
		Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static void morris(Node head) {
		if (head == null) {
			return;
		}
		Node cur = head;
		Node mostRight = null;
		while (cur != null) {
			if (cur.left != null) {
				mostRight = cur.left;
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
				}
			}
			cur = cur.right;
		}
	}

	// 1.morris遍历实现先序遍历
	public static List<Integer> morrisPre(Node head) {
		List<Integer> ans = new ArrayList<Integer>();
		if (head == null) {
			return ans;
		}
		Node cur = head;
		Node mostRight = null;
		while (cur != null) {
			if (cur.left != null) {
				mostRight = cur.left;
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					ans.add(cur.value);
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
				}
			} else {
				ans.add(cur.value);
			}
			cur = cur.right;
		}
		return ans;
	}

	// 2.morris遍历实现中序遍历
	public static List<Integer> morrisIn(Node head) {
		List<Integer> ans = new ArrayList<Integer>();
		if (head == null) {
			return ans;
		}
		Node cur = head;
		Node mostRight = null;
		while (cur != null) {
			if (cur.left != null) {
				mostRight = cur.left;
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					ans.add(cur.value);
					mostRight.right = null;
				}
			} else {
				ans.add(cur.value);
			}
			cur = cur.right;
		}
		return ans;
	}

	// 3.morris遍历实现后序遍历
	public static List<Integer> morrisPos(Node head) {
		List<Integer> ans = new ArrayList<Integer>();
		if (head == null) {
			return ans;
		}
		Node cur = head;
		Node mostRight = null;
		while (cur != null) {
			if (cur.left != null) {
				mostRight = cur.left;
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
					ans.addAll(reversePrint(cur.left));
				}
			}
			cur = cur.right;
		}
		ans.addAll(reversePrint(head));
		return ans;
	}

	public static List<Integer> reversePrint(Node cur) {
		List<Integer> ans = new ArrayList<Integer>();
		if (cur == null) {
			return ans;
		}
		Node tail = reverse(cur);
		cur = tail;
		while (cur != null) {
			ans.add(cur.value);
			cur = cur.right;
		}
		reverse(tail);
		return ans;
	}

	public static Node reverse(Node cur) {
		Node pre = null;
		Node next = null;
		while (cur != null) {
			next = cur.right;
			cur.right = pre;
			pre = cur;
			cur = next;
		}
		return pre;
	}

	// 4.morris遍历判断是否为搜索二叉树
	public static boolean isBST(Node head) {
		if (head == null) {
			return true;
		}
		Integer pre = null;
		Node cur = head;
		Node mostRight = null;
		while (cur != null) {
			if (cur.left != null) {
				mostRight = cur.left;
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
				}
			}
			if (pre != null) {
				if (pre >= cur.value) {
					return false;
				}
			}
			pre = cur.value;
			cur = cur.right;
		}
		return true;
	}
}
