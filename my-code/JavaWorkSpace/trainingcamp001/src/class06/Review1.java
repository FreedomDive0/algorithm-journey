package class06;

public class Review1 {
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

	// Morris遍历
	public static void morris(Node head) {
		if (head == null) {
			return;
		}
		Node cur = head;
		Node mostRight = null;
		while (cur != null) {
			System.out.print(cur.value + " ");
			mostRight = cur.left;
			if (mostRight != null) {// cur是否有左树
				if (mostRight.right != null && mostRight.right != cur) {// 得到左树最右节点
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {// (1)为空第一次到来
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {// (2)不为空等于cur第二次到来
					mostRight.right = null;
				}
			} // 1.没有左树 or 2.有左树的节点第二次回到自己位置(左树遍历完毕)
			cur = cur.right;
		}
	}

	// 1.morris遍历改先序遍历
	public static void morrisPre(Node head) {
		if (head == null) {
			return;
		}
		Node cur = head;
		Node mostRight = null;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {// cur是否有左树
				if (mostRight.right != null && mostRight.right != cur) {// 得到左树最右节点
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {// (1)为空第一次到来
					mostRight.right = cur;
					System.out.print(cur.value + " ");
					cur = cur.left;
					continue;
				} else {// (2)不为空等于cur第二次到来
					mostRight.right = null;
				}
			} // 1.没有左树 or 2.有左树的节点第二次回到自己位置(左树遍历完毕)
			else {
				System.out.print(cur.value + " ");
			}
			cur = cur.right;
		}
		System.out.println();
	}

	// 改中序遍历
	public static void morrisIn(Node head) {
		if (head == null) {
			return;
		}
		Node cur = head;
		Node mostRight = null;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {// cur是否有左树
				if (mostRight.right != null && mostRight.right != cur) {// 得到左树最右节点
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {// (1)为空第一次到来
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {// (2)不为空等于cur第二次到来
					mostRight.right = null;
				}
			} // 1.没有左树 or 2.有左树的节点第二次回到自己位置(左树遍历完毕)
			System.out.print(cur.value + " ");
			cur = cur.right;
		}
		System.out.println();
	}

	// 改后序遍历
	public static void morrisPos(Node head) {
		if (head == null) {
			return;
		}
		Node cur = head;
		Node mostRight = null;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {// cur是否有左树
				if (mostRight.right != null && mostRight.right != cur) {// 得到左树最右节点
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {// (1)为空第一次到来
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {// (2)不为空等于cur第二次到来
					mostRight.right = null;
					printReverse(cur.left);// 先将mostRight向上指的指针断掉
				}
			} // 1.没有左树 or 2.有左树的节点第二次回到自己位置(左树遍历完毕)
			cur = cur.right;
		}
		printReverse(head);
		System.out.println();
	}

	public static void printReverse(Node head) {
		if (head == null) {
			return;
		}
		Node cur = reverse(head);
		Node tmp = cur;
		while (cur != null) {
			System.out.print(cur.value + " ");
			cur = cur.right;
		}
		reverse(tmp);
	}

	public static Node reverse(Node head) {
		if (head == null) {
			return head;
		}
		Node pre = null;
		Node next = null;
		while (head != null) {
			next = head.right;
			head.right = pre;
			pre = head;
			head = next;
		}
		return pre;
	}

	// 1.是否为搜索二叉树
	public static boolean morrisIsBST(Node head) {
		if (head == null) {
			return true;
		}
		Node cur = head;
		Node mostRight = null;
		Integer pre = null;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {// cur是否有左树
				if (mostRight.right != null && mostRight.right != cur) {// 得到左树最右节点
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {// (1)为空第一次到来
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {// (2)不为空等于cur第二次到来
					mostRight.right = null;
				}
			} // 1.没有左树 or 2.有左树的节点第二次回到自己位置(左树遍历完毕)
			if (pre != null && pre >= cur.value) {// 中序遍历值递增
				return false;
			}
			pre = cur.value;
			cur = cur.right;
		}
		return true;
	}

	// 2.树的最小高度
	public static int minHeight(Node head) {// 将树按右边界划分
		if (head == null) {
			return 0;
		}
		Node cur = head;
		Node mostRight = null;
		int minHeight = Integer.MAX_VALUE;
		int curLevel = 0;// 不是普通点的高度 是从mostRight返回cur后 如果mostRight(会包含除了整棵树最右节点所有叶节点)是叶节点则比较高度
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {// cur是否有左树
				int leftHeight = 1;
				while (mostRight.right != null && mostRight.right != cur) {// 得到左树最右节点
					leftHeight++;
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {// (1)为空第一次到来
					mostRight.right = cur;
					cur = cur.left;
					curLevel++;
					continue;
				} else {// (2)不为空等于cur第二次到来
					if (mostRight.left == null) {
						minHeight = Math.min(minHeight, curLevel);
					}
					curLevel -= leftHeight;
					mostRight.right = null;
				}
			} // 1.没有左树 or 2.有左树的节点第二次回到自己位置(左树遍历完毕)
			else {
				curLevel++;
			}
			cur = cur.right;
		}
		int finalRight = 1;
		cur = head;
		while (cur.right != null) {
			finalRight++;
			cur = cur.right;
		}
		if (cur.left == null) {
			minHeight = Math.min(finalRight, minHeight);
		}
		return minHeight;
	}

	// 先中后序遍历
	public static void f(Node head) {
		if (head == null) {
			return;
		}
//		System.out.print(head.value + " ");
		f(head.left);
//		System.out.print(head.value + " ");
		f(head.right);
		System.out.print(head.value + " ");
	}

	public static void main(String[] args) {
		Node head = new Node(4);
		head.left = new Node(2);
		head.right = new Node(6);
		head.left.left = new Node(1);
		head.left.right = new Node(3);
		head.right.left = new Node(5);
		head.right.right = new Node(7);
//		morris(head);
//		morrisPre(head);
//		morrisIn(head);
//		morrisPos(head);
//		f(head);
		minHeight(head);
	}
}
