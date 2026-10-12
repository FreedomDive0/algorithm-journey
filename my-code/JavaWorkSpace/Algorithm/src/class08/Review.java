package class08;

import java.util.ArrayList;
import java.util.List;

public class Review {
	public static class Node {
		public int value;
		public Node left;
		public Node right;
		public Node parent;

		public Node(int data) {
			this.value = data;
		}
	}

	// 是否为平衡二叉树
	public static class isBSTInfor {
		boolean isbst;
		int max;

		public isBSTInfor(boolean isbst, int max) {
			this.isbst = isbst;
			this.max = max;
		}

	}

	public static boolean isBST(Node root) {
		return processIsBST(root).isbst;
	}

	public static isBSTInfor processIsBST(Node root) {
		if (root == null) {
			return new isBSTInfor(true, 0);
		}
		isBSTInfor leftInfo = processIsBST(root.left);
		isBSTInfor rightInfo = processIsBST(root.right);
		if (leftInfo.isbst && rightInfo.isbst && Math.abs(leftInfo.max - rightInfo.max) < 2) {
			return new isBSTInfor(true, Math.max(leftInfo.max, rightInfo.max) + 1);
		}
		return new isBSTInfor(false, Math.max(leftInfo.max, rightInfo.max) + 1);
	}

	// maxDistance
	public static int maxDistance(Node root) {
		return processM(root).maxDistance;
	}

	public static class maxInfo {
		int maxDistance;
		int height;

		public maxInfo(int maxDistance, int height) {
			this.maxDistance = maxDistance;
			this.height = height;
		}

	}

	public static maxInfo processM(Node root) {
		if (root == null) {
			return new maxInfo(0, 0);
		}
		maxInfo leftInfo = processM(root.left);
		maxInfo rightInfo = processM(root.right);
		int max = Math.max(Math.max(leftInfo.maxDistance, rightInfo.maxDistance),
				leftInfo.height + rightInfo.height + 1);
		return new maxInfo(max, Math.max(leftInfo.height, rightInfo.height) + 1);
	}

	// 最大搜索子树
	public static int maxSTree(Node root) {
		if (root == null) {
			return 0;
		}
		return processMS(root).maxSize;
	}

	public static class maxSTInfo {
		int maxSize;
		int min;
		int max;
		boolean isST;

		public maxSTInfo(int maxSize, int min, int max, boolean isST) {
			this.maxSize = maxSize;
			this.min = min;
			this.max = max;
			this.isST = isST;
		}

	}

	public static maxSTInfo processMS(Node root) {
		if (root == null) {
			return null;
		}
		maxSTInfo leftInfo = processMS(root.left);
		maxSTInfo rightInfo = processMS(root.right);
		int min = root.value;
		int max = root.value;
		int maxSize = 0;
		if (leftInfo != null) {
			min = Math.min(min, leftInfo.min);
			max = Math.max(max, leftInfo.max);
			maxSize = Math.max(leftInfo.maxSize, maxSize);
		}
		if (rightInfo != null) {
			min = Math.min(min, rightInfo.min);
			max = Math.max(max, rightInfo.max);
			maxSize = Math.max(rightInfo.maxSize, maxSize);
		}
		boolean isBst = false;
		if ((leftInfo != null ? (leftInfo.isST && leftInfo.max < root.value) : true)
				&& (rightInfo != null ? (rightInfo.isST && rightInfo.min > root.value) : true)) {
			isBst = true;
			maxSize = (leftInfo != null ? leftInfo.maxSize : 0) + (rightInfo != null ? rightInfo.maxSize : 0) + 1;
		}
		return new maxSTInfo(maxSize, min, max, isBst);
	}

	// 派对最大快乐值
	public static class Employee {
		public int happy;
		public List<Employee> nexts;

		public Employee(int h) {
			happy = h;
			nexts = new ArrayList<>();
		}

	}

	public static class mHInfo {
		int yes;
		int no;

		public mHInfo(int yes, int no) {
			this.yes = yes;
			this.no = no;
		}

	}

	public static int maxHappy(Employee root) {
		if (root == null) {
			return 0;
		}
		mHInfo all = processMH(root);
		return Math.max(all.no, all.yes);
	}

	public static mHInfo processMH(Employee root) {
		if (root.nexts.isEmpty()) {
			return new mHInfo(root.happy, 0);
		}
		int yes = root.happy;
		int no = 0;
		for (Employee e : root.nexts) {
			mHInfo next = processMH(e);
			yes += next.no;
			no += Math.max(next.no, next.yes);
		}
		return new mHInfo(yes, no);
	}

	// 满二叉树
	public static boolean isFull(Node root) {
		isFInfo info = processF(root);
		return (Math.pow(2, info.height) - 1) == info.nodes;
	}

	public static class isFInfo {
		int height;
		int nodes;

		public isFInfo(int height, int nodes) {
			this.height = height;
			this.nodes = nodes;
		}

	}

	public static isFInfo processF(Node root) {
		if (root == null) {
			return new isFInfo(0, 0);
		}
		isFInfo leftInfo = processF(root.left);
		isFInfo rightInfo = processF(root.right);
		return new isFInfo(Math.max(leftInfo.height, rightInfo.height) + 1, leftInfo.nodes + rightInfo.nodes + 1);
	}

	// 完全二叉树
	public static boolean isBCT(Node root) {
		return processBCT(root).isBct;
	}

	public static class BCTInfo {
		boolean isFull;
		boolean isBct;
		int height;

		public BCTInfo(boolean isFull, boolean isBct, int height) {
			this.isFull = isFull;
			this.isBct = isBct;
			this.height = height;
		}

	}

	public static BCTInfo processBCT(Node root) {
		if (root == null) {
			return new BCTInfo(true, true, 0);
		}
		BCTInfo leftInfo = processBCT(root.left);
		BCTInfo rightInfo = processBCT(root.right);
		boolean isFull = false;
		boolean isBCT = false;
		if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height) {
			isFull = true;
			isBCT = true;
		}
		if ((leftInfo.isBct && rightInfo.isFull && leftInfo.height == rightInfo.height + 1)
				|| (leftInfo.isFull && rightInfo.isBct && leftInfo.height == rightInfo.height)) {
			isBCT = true;
		}
		return new BCTInfo(isFull, isBCT, Math.max(leftInfo.height, rightInfo.height) + 1);
	}

	// 最低公共祖先
	public static Node lowestA(Node root, Node a, Node b) {
		return processL(root, a, b).ans;
	}

	public static class lowAInfo {
		boolean isA;
		boolean isB;
		Node ans;

		public lowAInfo(boolean isA, boolean isB, Node ans) {
			this.isA = isA;
			this.isB = isB;
			this.ans = ans;
		}

	}

	public static lowAInfo processL(Node root, Node a, Node b) {
		if (root == null) {
			return new lowAInfo(false, false, null);
		}
		lowAInfo leftInfo = processL(root.left, a, b);
		lowAInfo rightInfo = processL(root.right, a, b);
		Node ans = null;
		boolean isA = root == a || leftInfo.isA || rightInfo.isA;
		boolean isB = root == b || leftInfo.isB || rightInfo.isB;
		if (leftInfo.ans != null) {
			ans = leftInfo.ans;// 先发现 子覆盖父
		}
		if (rightInfo.ans != null) {
			ans = rightInfo.ans;
		}
		if (leftInfo.ans == null && rightInfo.ans == null && isA && isB) {
			ans = root;
		}
		return new lowAInfo(isA, isB, ans);
	}

	public static void main(String[] args) {

	}
}
