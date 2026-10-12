package class06;

public class Code01_MorrisTraversal {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int value) {
			this.value = value;
		}
	}

	public static void morris(Node head) {
		if (head == null) {
			return;
		}
		Node cur = head;
		Node mostRight = null;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;// 找到左树最右孩子
				}
				if (mostRight.right == null) {// 第一次找到
					mostRight.right = cur;
					cur = cur.left;
					continue;// 跳过该回合
				} else {// R!=null -> R == cur
					mostRight.right = null;
				}
			}
			cur = cur.right;// 1.没有左孩子 or 2.第二遍到达自身
		}
		System.out.println();
	}

//	中序遍历
	public static void morrisIn(Node head) {
		if (head == null) {
			return;
		}
		Node cur = head;
		Node mostRight = null;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;// 找到左树最右孩子
				}
				if (mostRight.right == null) {// 第一次找到
					mostRight.right = cur;
					cur = cur.left;
					continue;// 跳过该回合
				} else {// R!=null -> R == cur
					mostRight.right = null;
				}
			}
			System.out.print(cur.value + " ");// 左移过程不打印即可实现中序遍历 continue跳过循环
			cur = cur.right;// 1.没有左孩子 or 2.第二遍到达自身
		}
		System.out.println();
	}

//	先序遍历
	public static void morrisPre(Node head) {
		if (head == null) {
			return;
		}
		Node cur = head;
		Node mostRight = null;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;// 找到左树最右孩子
				}
				if (mostRight.right == null) {// 第一次找到
					mostRight.right = cur;
					System.out.print(cur.value + " ");
					cur = cur.left;
					continue;// 跳过该回合
				} else {// R!=null -> R == cur
					mostRight.right = null;
				}
			} else {
				System.out.print(cur.value + " ");// 第二次回去不打印 第一次遇到会赋值为cur 第二次改为null
			}
			cur = cur.right;// 1.没有左孩子 or 2.第二遍到达自身
		}
		System.out.println();
	}

//	后序遍历
	public static void morrisPos(Node head) {
		if (head == null) {
			return;
		}
		Node cur = head;
		Node mostRight = null;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;// 找到左树最右孩子
				}
				if (mostRight.right == null) {// 第一次找到
					mostRight.right = cur;
					cur = cur.left;
					continue;// 跳过该回合
				} else {// R!=null -> R == cur
					mostRight.right = null;
					printEdge(cur.left);// 第二次回到打印左孩子 但是无法打印整棵树的右孩子
				}
			}
			cur = cur.right;// 1.没有左孩子 or 2.第二遍到达自身
		}
		printEdge(head);// 补充打印整棵树的右孩子
		System.out.println();
	}

//	反转打印
	public static void printEdge(Node head) {
		Node tail = reverseEdge(head);
		Node cur = tail;
		while (cur != null) {
			System.out.print(cur.value + " ");
			cur = cur.right;
		}
		reverseEdge(tail);
	}

	public static Node reverseEdge(Node from) {
		Node pre = null;
		Node next = null;
		while (from != null) {
			next = from.right;
			from.right = pre;
			pre = from;
			from = next;
		}
		return pre;
	}

//	判断是否为搜索二叉树 中序遍历值在递增
	public static boolean isBST(Node head) {
		if (head == null) {
			return true;
		}
		Node cur = head;
		Node mostRight = null;
		Integer pre = null;
		boolean ans = true;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;// 找到左树最右孩子
				}
				if (mostRight.right == null) {// 第一次找到
					mostRight.right = cur;
					cur = cur.left;
					continue;// 跳过该回合
				} else {// R!=null -> R == cur
					mostRight.right = null;
				}
			}
			if (pre != null && pre >= cur.value) {
				ans = false;
			}
			pre = cur.value;
			cur = cur.right;// 1.没有左孩子 or 2.第二遍到达自身
		}
		return ans;
	}

	// for test -- print tree
	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	public static void printInOrder(Node head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.right, height + 1, "v", len);
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}

	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		Node head = new Node(4);
		head.left = new Node(2);
		head.right = new Node(6);
		head.left.left = new Node(1);
		head.left.right = new Node(3);
		head.right.left = new Node(5);
		head.right.right = new Node(7);
		printTree(head);
//		morris(head);
		morrisIn(head);
		morrisPre(head);
//		morrisPos(head);
//		printTree(head);

	}
}
