package class07;

import java.util.Stack;

public class Code02_RecoverBST {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	// 搜索二叉树没有重复值 两个值发生两个交换 恢复搜索二叉树
	public static Node[] getErrNode(Node head) {
		Node[] errs = new Node[2];
		if (head == null) {
			return null;
		}
		Stack<Node> stack = new Stack<Node>();
		Node pre = null;
		while (head != null || !stack.isEmpty()) {
			if (head != null) {// 先处理左分支
				stack.add(head);
				head = head.left;
			} else {
				head = stack.pop();
				if (pre != null && pre.value > head.value) {// 两种情况：1.两个值不相连 第一次遇到的值 为后面换上来的 第二次遇到的值 为前面换到后面的是head
					errs[0] = errs[0] != null ? errs[0] : pre;// 2.两个值相连 直接返回
					errs[1] = head;
				}
				pre = head;
				head = head.right;
			}
		}
		return errs;
	}

	// 获得错误节点的父节点
	public static Node[] getParNode(Node head, Node e1, Node e2) {
		Node[] parents = new Node[2];
		if (head == null) {
			return null;
		}
		Stack<Node> stack = new Stack<Node>();
		while (head != null || !stack.isEmpty()) {
			if (head != null) {// 先处理左分支
				stack.add(head);
				head = head.left;
			} else {
				head = stack.pop();
				if (head.left == e1 || head.right == e1) {
					parents[0] = head;
				}
				if (head.left == e2 || head.right == e2) {
					parents[1] = head;
				}
				head = head.right;
			}
		}
		return parents;
	}

	// 恢复节点位置
	public static Node recoverTree(Node head) {
		Node[] errs = getErrNode(head);
		Node[] parents = getParNode(head, errs[0], errs[1]);
		Node e1 = errs[0];
		Node e1L = e1.left;
		Node e1R = e1.right;
		Node e1P = parents[0];
		Node e2 = errs[1];
		Node e2L = e2.left;
		Node e2R = e2.right;
		Node e2P = parents[1];
		if (e1 == head) {
			if (e1 == e2P) {// e1是e2的父节点
				e1.left = e2L;
				e1.right = e2R;
				e2.left = e1L;
				e2.right = e1;
			} else if (e2 == e2P.left) {// e2是左孩子
				e1.left = e2L;
				e1.right = e2R;
				e2.left = e1L;
				e2.right = e1R;
				e2P.left = e1;
			} else {// e2是右孩子
				e1.left = e2L;
				e1.right = e2R;
				e2.left = e1L;
				e2.right = e1R;
				e2P.right = e1;
			}
			head = e2;
		} else if (e2 == head) {// 注意e1 e2相连的情况
			if (e2 == e1P) {// e1是e2的父节点
				e1.left = e2;
				e1.right = e2R;
				e2.left = e1L;
				e2.right = e1R;
			} else if (e1 == e1P.left) {// e2是左孩子
				e1.left = e2L;
				e1.right = e2R;
				e2.left = e1L;
				e2.right = e1R;
				e1P.left = e2;
			} else {// e2是右孩子
				e1.left = e2L;
				e1.right = e2R;
				e2.left = e1L;
				e2.right = e1R;
				e1P.right = e2;
			}
			head = e1;
		} else {
			if (e1 == e2P) {
				if (e1 == e1P.left) {
					e1.left = e2L;
					e1.right = e2R;
					e2.left = e1L;
					e2.right = e1;
					e1P.left = e2;
				} else {
					e1.left = e2L;
					e1.right = e2R;
					e2.left = e1L;
					e2.right = e1;
					e1P.right = e2;
				}
			} else if (e2 == e1P) {
				if (e2 == e2P.left) {
					e1.left = e2;
					e1.right = e2R;
					e2.left = e1L;
					e2.right = e1R;
					e2P.left = e1;
				} else {
					e1.left = e2;
					e1.right = e2R;
					e2.left = e1L;
					e2.right = e1R;
					e2P.right = e1;
				}
			} else {
				if (e1 == e1P.left) {
					if (e2 == e2P.left) {
						e1.left = e2L;
						e1.right = e2R;
						e2.left = e1L;
						e2.right = e1R;
						e2P.left = e1;
						e1P.left = e2;
					} else {
						e1.left = e2L;
						e1.right = e2R;
						e2.left = e1L;
						e2.right = e1R;
						e2P.right = e1;
						e1P.left = e2;
					}
				} else {
					if (e2 == e2P.left) {
						e1.left = e2L;
						e1.right = e2R;
						e2.left = e1L;
						e2.right = e1R;
						e2P.left = e1;
						e1P.right = e2;
					} else {
						e1.left = e2L;
						e1.right = e2R;
						e2.left = e1L;
						e2.right = e1R;
						e2P.right = e1;
						e1P.right = e2;
					}
				}
			}
		}
		return head;
	}
}
