package class07;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Review1 {
	public static class Node {
		int value;
		Node left;
		Node right;
		Node parent;

		public Node(int value) {
			this.value = value;
			this.left = null;
			this.right = null;
			this.parent = null;
		}

	}

	// 1.二叉树的先中后序遍历 实质为递归序 三次到达每个节点 不同位置输出为不同序遍历
	// 先序遍历
	public static void preOrder(Node head) {
		if (head == null) {
			return;
		}
		System.out.println(head.value);
		preOrder(head.left);
		preOrder(head.right);
	}

	// 中序遍历
	public static void inOrder(Node head) {
		if (head == null) {
			return;
		}
		inOrder(head.left);
		System.out.println(head.value);
		inOrder(head.right);
	}

	// 后序遍历
	public static void posOrder(Node head) {
		if (head == null) {
			return;
		}
		posOrder(head.left);
		posOrder(head.right);
		System.out.println(head.value);
	}

	// 递归序
	public static void f(Node head) {
		if (head == null) {
			return;
		}
		// 1
		f(head.left);
		// 2
		f(head.right);
		// 3
	}

	// 1.1非递归实现
	// (1)先序遍历
	public static void pOrder(Node head) {
		if (head == null) {
			return;
		}
		Stack<Node> stack = new Stack<Node>();
		stack.add(head);
		while (stack.isEmpty()) {
			Node cur = stack.pop();
			System.out.println(cur.value);
			if (cur.right != null) {
				stack.add(cur.right);
			}
			if (cur.left != null) {
				stack.add(cur.left);
			}
		}
	}

	// (2)后序遍历
	// 双栈实现
	public static void poOrder(Node head) {
		if (head == null) {
			return;
		}
		Stack<Node> stack = new Stack<Node>();
		Stack<Node> ans = new Stack<Node>();
		stack.add(head);
		while (!stack.isEmpty()) {
			Node cur = stack.pop();
			ans.add(cur);
			if (cur.right != null) {
				stack.add(cur.right);
			}
			if (cur.left != null) {
				stack.add(cur.left);
			}
		}
		while (!ans.isEmpty()) {
			System.out.print(ans.pop().value + " ");
		}
	}

	// 单栈实现
	public static void psOrder(Node head) {
		if (head == null) {
			return;
		}
		Stack<Node> stack = new Stack<Node>();
		stack.add(head);
		while (!stack.isEmpty()) {
			Node cur = stack.peek();
			if (cur.left != null && cur.left != head && cur.right != head) {// 处理左子树条件
				stack.add(cur.left);
			} else if (cur.right != null && cur.right != head) {// 处理右子树条件
				stack.add(cur.right);
			} else {// 左右子树处理完毕 处理子树头结点
				System.out.print(stack.pop().value + " ");
				head = cur;// 标记处理位置
			}
		}
	}

	// (3)中序遍历
	public static void iOrder(Node head) {
		if (head == null) {
			return;
		}
		Stack<Node> stack = new Stack<Node>();
		while (!stack.isEmpty() || head != null) {// head!=null这个条件为设置初始stack没有元素的情况
			if (head != null) {// 保证了左头顺序 左子树处理完毕
				stack.add(head);
				head = head.left;
			} else {
				head = stack.pop();// 处理右子树
				System.out.print(head.value + " ");
				head = head.right;
			}
		}
	}

	// 2.宽度优先遍历
	public static void widthOrder(Node head) {
		if (head == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(head);
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			System.out.print(cur.value + " ");
			if (cur.left != null) {
				queue.add(cur.left);
			}
			if (cur.right != null) {
				queue.add(cur.right);
			}
		}
	}

	// 3.树的最大宽度
	// (1)使用HashMap
	public static int maxWidth1(Node head) {
		if (head == null) {
			return 0;
		}
		Queue<Node> queue = new LinkedList<Node>();
		HashMap<Node, Integer> levelMap = new HashMap<Node, Integer>();
		levelMap.put(head, 1);
		queue.add(head);
		int max = 0;
		int curLevel = 1;
		int curLevelNodes = 0;
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			if (cur.left != null) {
				levelMap.put(cur.left, levelMap.get(cur) + 1);
				queue.add(cur.left);
			}
			if (cur.right != null) {
				levelMap.put(cur.right, levelMap.get(cur) + 1);
				queue.add(cur.right);
			}
			if (curLevel == levelMap.get(cur)) {
				curLevelNodes++;
			} else {
				curLevel++;
				max = Math.max(max, curLevelNodes);
				curLevelNodes = 1;
			}
		}
		max = Math.max(max, curLevelNodes);// 最后一层还得比较
		return max;
	}

	// (2)curEnd nextEnd标记
	public static int maxWidth2(Node head) {
		if (head == null) {
			return 0;
		}
		Queue<Node> queue = new LinkedList<Node>();
		Node curEnd = head;
		Node NextEnd = null;
		queue.add(head);
		int max = 1;
		int curLevelNodes = 1;
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			if (cur.left != null) {
				queue.add(cur.left);
				NextEnd = cur.left;
			}
			if (cur.right != null) {
				queue.add(cur.right);
				NextEnd = cur.right;
			}
			if (curEnd != cur) {
				curLevelNodes++;
			} else {
				max = Math.max(max, curLevelNodes);
				curLevelNodes = 1;// 为了处理一层只有一个节点情况
				curEnd = NextEnd;
			}
		}
		return max;
	}

	// 4.二叉树的序列化与反序列化
	// (1)先序序列化
	public static Queue<String> preSerial(Node head) {
		Queue<String> ans = new LinkedList<String>();
		pres(head, ans);
		return ans;
	}

	public static void pres(Node head, Queue<String> ans) {
		if (head == null) {
			ans.add(null);
		} else {
			ans.add(String.valueOf(head));
			pres(head.left, ans);
			pres(head.right, ans);
		}
	}

	// (1)先序反序列化
	public static Node preUnSerial(Queue<String> ans) {
		if (ans == null || ans.size() == 0) {
			return null;
		}
		return upres(ans);
	}

	public static Node upres(Queue<String> ans) {
		String s = ans.poll();
		if (s == null) {
			return null;
		}
		Node head = new Node(Integer.valueOf(s));
		head.left = upres(ans);
		head.right = upres(ans);
		return head;
	}

	// (2)后序序列化
	public static Queue<String> posSerial(Node head) {
		Queue<String> ans = new LinkedList<String>();
		poss(head, ans);
		return ans;
	}

	public static void poss(Node head, Queue<String> ans) {
		if (head == null) {
			ans.add(null);
		} else {
			poss(head.left, ans);
			poss(head.right, ans);
			ans.add(String.valueOf(head.value));
		}
	}

	public static Node uposSerial(Queue<String> ans) {
		if (ans == null || ans.size() == 0) {
			return null;
		}
		Stack<String> stack = new Stack<String>();
		while (!ans.isEmpty()) {
			stack.push(ans.poll());
		}
		return upos(stack);
	}

	public static Node upos(Stack<String> stack) {
		String s = stack.pop();
		if (s == null) {
			return null;
		}
		Node head = new Node(Integer.valueOf(s));
		head.right = upos(stack);
		head.left = upos(stack);
		return head;
	}

	// (1)层序序列化
	public static Queue<String> levelSerial1(Node head) {
		Queue<String> ans = new LinkedList<String>();
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(head);
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			if (cur != null) {
				ans.add(String.valueOf(cur.value));
				queue.add(cur.left);
				queue.add(cur.right);
			} else {
				ans.add(null);
			}
		}
		return ans;
	}

	public static Node uLevel(Queue<String> ans) {
		if (ans == null || ans.size() == 0) {
			return null;
		}
		Node head = createNode(ans.poll());
		Queue<Node> queue = new LinkedList<Node>();
		if (head != null) {
			queue.add(head);
		}
		Node cur = null;
		while (!queue.isEmpty()) {
			cur = queue.poll();
			cur.left = createNode(ans.poll());// 先创建节点
			cur.right = createNode(ans.poll());
			if (cur.left != null) {// 如果不为空加入队列
				queue.add(cur.left);
			}
			if (cur.right != null) {
				queue.add(cur.right);
			}
		}
		return head;
	}

	public static Node createNode(String value) {// 根据String创建节点
		if (value == null) {
			return null;
		} else {
			return new Node(Integer.valueOf(value));
		}
	}

	// 5.二叉树的打印函数
	public static void printTree(Node head) {
		if (head == null) {
			return;
		}
		processPT(head, 0, "H", 17);
	}

	public static void processPT(Node head, int level, String to, int len) {
		if (head == null) {
			return;
		}
		processPT(head.right, level + 1, "v", len);
		String val = to + head.value + to;
		int lenL = (len - val.length()) / 2;
		int lenR = len - val.length() - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		val = getSpace(len * level) + val;
		System.out.println(val);
		processPT(head.left, level + 1, "^", len);
	}

	public static String getSpace(int num) {
		String s = " ";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < num; i++) {
			sb.append(s);
		}
		return sb.toString();
	}

	// 6.有parent节点的二叉树的给定节点的后继节点
	public static Node successorNode(Node x) {
		if (x == null) {
			return null;
		}
		if (x.right != null) {
			x = x.right;
			while (x.left != null) {
				x = x.left;
			}
			return x;
		} else {
			Node parent = x.parent;
			while (parent != null && parent.left != x) {
				x = parent;
				parent = parent.parent;
			}
			return parent;
		}
	}

	// 7.折纸问题
	public static void paperFold(int num) {
		printPaperFold(1, num, true);
	}

	public static void printPaperFold(int cur, int num, boolean down) {
		if (cur > num) {
			return;
		}
		printPaperFold(cur + 1, num, true);
		System.out.println(down ? "凹" : "凸");
		printPaperFold(cur + 1, num, false);
	}

	public static void main(String[] args) {
		paperFold(3);
	}
}
