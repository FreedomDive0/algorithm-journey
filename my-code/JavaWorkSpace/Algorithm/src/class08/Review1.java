package class08;

import java.util.LinkedList;
import java.util.Queue;

import class08.Code09_MaxHappy.Employee;

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

	// 1.是否为平衡二叉树
	public static boolean isBlanceTree(Node head) {
		if (head == null) {
			return true;
		}
		return processIBT(head).isBlanceTree;
	}

	// 判断是否为平衡二叉树需要从左右子树得到的信息
	public static class BTInfo {
		boolean isBlanceTree;
		int height;

		public BTInfo(boolean isBlanceTree, int height) {
			this.isBlanceTree = isBlanceTree;
			this.height = height;
		}

	}

	public static BTInfo processIBT(Node X) {
		if (X == null) {
			return new BTInfo(true, 0);
		}
		BTInfo leftInfo = processIBT(X.left);
		BTInfo rightInfo = processIBT(X.right);
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		boolean isBlanceTree = true;
		if (!leftInfo.isBlanceTree || !rightInfo.isBlanceTree || Math.abs(leftInfo.height - rightInfo.height) > 1) {
			isBlanceTree = false;
		}
		return new BTInfo(isBlanceTree, height);
	}

	// 2.返回二叉树的最大距离
	public static int maxDistance(Node head) {
		if (head == null) {
			return 0;
		}
		return processMD(head).maxDistance;
	}

	public static class MDInfo {
		int maxDistance;
		int height;

		public MDInfo(int maxDistance, int height) {
			this.maxDistance = maxDistance;
			this.height = height;
		}

	}

	public static MDInfo processMD(Node X) {
		if (X == null) {
			return new MDInfo(0, 0);
		}
		MDInfo leftInfo = processMD(X.left);
		MDInfo rightInfo = processMD(X.right);
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		int maxDistance = Math.max(Math.max(leftInfo.maxDistance, rightInfo.maxDistance),
				leftInfo.height + rightInfo.height + 1);
		return new MDInfo(maxDistance, height);
	}

	// 3.返回最大搜索二叉子树结点数
	public static int maxBSN(Node head) {
		if (head == null) {
			return 0;
		}
		return processMBSN(head).nodes;
	}

	public static class MBSNInfo {
		boolean isBST;
		int nodes;
		int max;
		int min;

		public MBSNInfo(boolean isBST, int max, int min, int nodes) {
			this.isBST = isBST;
			this.max = max;
			this.min = min;
			this.nodes = nodes;
		}

	}

	public static MBSNInfo processMBSN(Node X) {
		if (X == null) {
			return null;
		}
		MBSNInfo leftInfo = processMBSN(X.left);
		MBSNInfo rightInfo = processMBSN(X.right);
		int min = X.value;
		int max = X.value;
		boolean isBST = false;
		int nodes = Math.max(leftInfo != null ? leftInfo.nodes : 0, rightInfo != null ? rightInfo.nodes : 0);
		if (leftInfo != null) {
			min = Math.min(min, leftInfo.min);
			max = Math.max(max, leftInfo.max);
		}
		if (rightInfo != null) {
			min = Math.min(min, rightInfo.min);
			max = Math.max(max, rightInfo.max);
		}
		if ((leftInfo == null || (leftInfo.isBST && leftInfo.max < X.value))
				&& (rightInfo == null || (rightInfo.isBST && rightInfo.min > X.value))) {
			isBST = true;
			nodes = (leftInfo != null ? leftInfo.nodes : 0) + (rightInfo != null ? rightInfo.nodes : 0) + 1;
		}
		return new MBSNInfo(isBST, max, min, nodes);
	}

	// 4.派对最大快乐值

	public static int maxHappyValue(Employee boss) {
		MHPInfo res = processMHP(boss);
		return Math.max(res.yes, res.no);
	}

	public static class MHPInfo {
		int no;
		int yes;

		public MHPInfo(int no, int yes) {
			this.no = no;
			this.yes = yes;
		}

	}

	public static MHPInfo processMHP(Employee emp) {
		if (emp == null) {
			return new MHPInfo(0, 0);
		}
		int no = 0;
		int yes = emp.happy;
		for (Employee ee : emp.nexts) {
			MHPInfo next = processMHP(ee);// 记录值只跑一边
			yes += next.no;
			no += Math.max(next.no, next.yes);
		}
		return new MHPInfo(no, yes);
	}

	// 5.是否为满二叉树
	public static boolean isFull(Node head) {
		return processIF(head).isFull;
	}

	public static class IFInfo {
		boolean isFull;
		int height;

		public IFInfo(boolean isFull, int height) {
			this.isFull = isFull;
			this.height = height;
		}

	}

	public static IFInfo processIF(Node X) {
		if (X == null) {
			return new IFInfo(true, 0);
		}
		IFInfo leftInfo = processIF(X.left);
		IFInfo rightInfo = processIF(X.right);
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
		return new IFInfo(isFull, height);
	}

	// 6.二叉树最大搜索子树头节点
	public static Node maxBSTH(Node head) {
		if (head == null) {
			return null;
		}
		return processMBSTH(head).maxHead;
	}

	public static class MBSTHInfo {
		Node maxHead;
		int max;
		int min;
		int nodes;

		public MBSTHInfo(Node maxHead, int max, int min, int nodes) {
			this.maxHead = maxHead;
			this.max = max;
			this.min = min;
			this.nodes = nodes;
		}

	}

	public static MBSTHInfo processMBSTH(Node X) {
		if (X == null) {
			return null;
		}
		int max = X.value;
		int min = X.value;
		int nodes = 0;
		Node maxHead = null;
		MBSTHInfo leftInfo = processMBSTH(X.left);
		MBSTHInfo rightInfo = processMBSTH(X.right);
		if (leftInfo != null) {
			max = Math.max(max, leftInfo.max);
			min = Math.min(min, leftInfo.min);
			nodes = Math.max(nodes, leftInfo.nodes);
			maxHead = leftInfo.maxHead;// 头节点每次跟子树先结算
		}
		if (rightInfo != null) {
			max = Math.max(max, rightInfo.max);
			min = Math.min(min, rightInfo.min);
			if (rightInfo.nodes > nodes) {// 需判断右子树搜索子树是否比左树大
				maxHead = rightInfo.maxHead;
				nodes = rightInfo.nodes;
			}
		}
		if ((leftInfo != null ? (leftInfo.maxHead == X.left) && leftInfo.max < X.value : true)
				&& (rightInfo != null ? (rightInfo.maxHead == X.right) && rightInfo.min > X.value : true)) {
			maxHead = X;
			nodes = (leftInfo != null ? leftInfo.nodes : 0) + (rightInfo != null ? rightInfo.nodes : 0) + 1;
		}
		return new MBSTHInfo(maxHead, max, min, nodes);
	}

	// 7.二叉树是否为完全二叉树
	// (1)二叉树递归套路
	public static boolean isCBT(Node head) {
		return processICBT(head).isCBT;
	}

	public static class ICBTInfo {
		boolean isFull;
		boolean isCBT;
		int height;

		public ICBTInfo(boolean isFull, boolean isCBT, int height) {
			this.isFull = isFull;
			this.isCBT = isCBT;
			this.height = height;
		}

	}

	public static ICBTInfo processICBT(Node X) {
		if (X == null) {
			return new ICBTInfo(true, true, 0);
		}
		ICBTInfo leftInfo = processICBT(X.left);
		ICBTInfo rightInfo = processICBT(X.right);
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
		boolean isCBT = false;
		if (isFull) {
			isCBT = true;
		} else {
			if (leftInfo.isCBT && rightInfo.isCBT) {
				if (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height) {// 左满右不满
					isCBT = true;
				}
				if (rightInfo.isFull && leftInfo.isCBT && leftInfo.height == rightInfo.height + 1) {// 左不满右满
					isCBT = true;
				}
				if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {// 左满右满
					isCBT = true;
				}
			}
		}
//		if (leftInfo.isFull && ((rightInfo.isCBT && leftInfo.height == rightInfo.height) || (rightInfo.isFull
//				&& (leftInfo.height == rightInfo.height || leftInfo.height == rightInfo.height + 1)))) {
//			isCBT = true;
//		}
//		if (leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
//			isCBT = true;
//		}
		return new ICBTInfo(isFull, isCBT, height);
	}

	// (2)使用宽度优先遍历
	public static boolean isCBTW(Node head) {
		if (head == null) {
			return true;
		}
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(head);
		boolean flag = false;
		boolean res = true;
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			if ((flag && (cur.left != null || cur.right != null)) || (cur.left == null && cur.right != null)) {
				return false;
			}
//			if (cur.left == null && cur.right != null) {
//				return false;
//			}
//			if (flag && (cur.left != null || cur.right != null)) {//flag判断需放在 flag变更之上 否则 变更后直接参与本次判断
//				return false;
//			}
			if (cur.left == null || cur.right == null) {
				flag = true;
			}

			if (cur.left != null) {
				queue.add(cur.left);
			}
			if (cur.right != null) {
				queue.add(cur.right);
			}
		}
		return res;
	}

	// 8.最低公共祖先
	public static Node lowestAncestor(Node head, Node o1, Node o2) {
		if (head == null || o1 == null || o2 == null) {
			return null;
		}
		return processLA(head, o1, o2).ancestor;
	}

	public static class LAInfo {
		Node ancestor;
		boolean findO1;
		boolean findO2;

		public LAInfo(Node ancestor, boolean findO1, boolean findO2) {
			this.ancestor = ancestor;
			this.findO1 = findO1;
			this.findO2 = findO2;
		}

	}

	public static LAInfo processLA(Node X, Node o1, Node o2) {
		if (X == null) {
			return new LAInfo(null, false, false);
		}
		LAInfo leftInfo = processLA(X.left, o1, o2);
		LAInfo rightInfo = processLA(X.right, o1, o2);
		Node ancestor = null;
		boolean findO1 = false;
		boolean findO2 = false;
		if (leftInfo.findO1 || rightInfo.findO1 || X == o1) {
			findO1 = true;
		}
		if (leftInfo.findO2 || rightInfo.findO2 || X == o2) {
			findO2 = true;
		}
		if (leftInfo.ancestor != null) {
			ancestor = leftInfo.ancestor;
		}
		if (rightInfo.ancestor != null) {
			ancestor = rightInfo.ancestor;
		}
		if (ancestor == null && findO1 && findO2) {// ancestor没得到答案且 findO1 findO2同时成立 第一次发现答案
			ancestor = X;
		}
		return new LAInfo(ancestor, findO1, findO2);
	}
}
