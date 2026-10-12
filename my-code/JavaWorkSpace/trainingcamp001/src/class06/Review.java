package class06;

public class Review {
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

	// 1.morris遍历
	public static void Morris(Node head) {
		if (head == null) {
			return;
		}
		Node cur = head;
		Node mostRight = null;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {// 第一次到达左树最右节点
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
//					cur = mostRight.right;不需要这一步 当来到需要返回的位置 会发生mostRight==null||断链cur直接向右移动
					mostRight.right = null;
				}
			}
			cur = cur.right;
		}
	}

	// 1.1 Morris实现中序遍历
	public static void MorrisIn(Node head) {
		if (head == null) {
			return;
		}
		Node cur = head;
		Node mostRight = null;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {// 第一次到达左树最右节点
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
				}
			}
			System.out.print(cur.value + " ");
			cur = cur.right;
		}
	}

	// 1.2 Morris实现先序遍历
	public static void MorrisPre(Node head) {
		if (head == null) {
			return;
		}
		Node cur = head;
		Node mostRight = null;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {// 第一次到达左树最右节点
					mostRight.right = cur;
					System.out.print(cur.value + " ");
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
				}
			} else {
				System.out.print(cur.value + " ");
			}
			cur = cur.right;// 1.没有左孩子 2.从孩子返回
		}
	}

	// 1.2 Morris实现后序遍历 将整棵树按右边界划分 右边界逆序打印
	public static void MorrisPos(Node head) {
		if (head == null) {
			return;
		}
		Node cur = head;
		Node mostRight = null;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {// 第一次到达左树最右节点
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
					posPrint(cur.left);// 第二次返回 逆序打印左树
				}
			}
			cur = cur.right;// 1.没有左孩子 2.从孩子返回
		}
		posPrint(head);// 整棵树的右边界无法在流程中打印
	}

	public static void posPrint(Node node) {
		if (node == null) {
			return;
		}
		Node tail = ReverseNode(node);
		Node head = tail;
		while (tail != null) {
			System.out.print(tail.value + " ");
			tail = tail.right;
		}
		ReverseNode(head);
	}

	public static Node ReverseNode(Node node) {
		if (node == null) {
			return node;
		}
		Node pre = null;
		Node next = null;
		while (node != null) {
			next = node.right;
			node.right = pre;
			pre = node;
			node = next;
		}
		return pre;
	}

	// 2.奇数放在奇数位 偶数放在偶数位 双指针
	public static void OddEven(int[] arr) {
		if (arr == null || arr.length < 1) {
			return;
		}
		int odd = 1;
		int even = 0;
		int index = arr.length - 1;
		while (odd < arr.length && even < arr.length) {
			if ((arr[index] & 1) != 0) {
				swap(arr, index, odd);
				odd += 2;
			} else {
				swap(arr, index, even);
				even += 2;
			}
		}
	}

	public static void swap(int[] arr, int i, int j) {
		if (arr == null || arr.length < 1 || i == j || i >= arr.length || j >= arr.length) {
			return;
		}
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	// 3.isBST 是否为搜索二叉树 常规解决方法 得到leftmax rightmin进行比较 ————>转化为中序遍历递增
	public static boolean isBST(Node head) {
		if (head == null) {
			return true;
		}
		Node cur = head;
		Node mostRight = null;
		Integer pre = null;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {// 第一次到达左树最右节点
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
				}
			}
			if (pre != null && pre >= cur.value) {
				return false;
			}
			pre = cur.value;
			cur = cur.right;
		}
		return true;
	}

	// 4.二叉树的minHeight
	public static int minHeight(Node head) {
		if (head == null) {
			return 0;
		}
		Node cur = head;
		Node mostRight = null;
		int curLevel = 0;
		int min = Integer.MAX_VALUE;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				int rightEdge = 1;// ∵cur.left!=null ∴初始值为1
				while (mostRight.right != null && mostRight.right != cur) {
					rightEdge++;// 每次从孩子节点返回会重新计算高度
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {// 第一次到达左树最右节点
					mostRight.right = cur;
					cur = cur.left;
					curLevel++;// 无论向左还是向右都是向下移动 需要层数++
					continue;
				} else {// 从右孩子返回时结算高度 判断mostRight是不是叶节点
					if (mostRight.left == null) {
						min = Math.min(min, curLevel);
					}
					curLevel -= rightEdge;
					mostRight.right = null;
				}
			} else {
				curLevel++;// 无论向左还是向右都是向下移动 需要层数++
			}
			cur = cur.right;
		}
		// 检测最右边界
		int rightEdge = 1;
		cur = head;
		while (cur.right != null) {
			cur = cur.right;
			rightEdge++;
		}
		if (cur.left == null && cur.right == null) {
			min = Math.min(min, rightEdge);
		}
		return min;
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);
		MorrisPos(head);
	}
}
