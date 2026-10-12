package 分治;

public class Problem_0427_Construct_Quad_Tree {
	class Node {
		public boolean val;
		public boolean isLeaf;
		public Node topLeft;
		public Node topRight;
		public Node bottomLeft;
		public Node bottomRight;

		public Node() {
			this.val = false;
			this.isLeaf = false;
			this.topLeft = null;
			this.topRight = null;
			this.bottomLeft = null;
			this.bottomRight = null;
		}

		public Node(boolean val, boolean isLeaf) {
			this.val = val;
			this.isLeaf = isLeaf;
			this.topLeft = null;
			this.topRight = null;
			this.bottomLeft = null;
			this.bottomRight = null;
		}

		public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
			this.val = val;
			this.isLeaf = isLeaf;
			this.topLeft = topLeft;
			this.topRight = topRight;
			this.bottomLeft = bottomLeft;
			this.bottomRight = bottomRight;
		}
	}

	public Node construct(int[][] grid) {
		return process(grid, 0, 0, grid.length - 1, grid[0].length - 1);
	}

	public Node process(int[][] grid, int lr, int lc, int rr, int rc) {
		if (lr == rr) {
			return new Node(grid[lr][lc] == 1, true);
		}
		Node node = new Node();
		int same = isSame(grid, lr, lc, rr, rc);
		if (same >= 0) {
			node.isLeaf = true;
			node.val = same == 1;
		} else {
			int mr = (lr + rr) / 2;
			int mc = (lc + rc) / 2;
			node.topLeft = process(grid, lr, lc, mr, mc);
			node.topRight = process(grid, lr, mc + 1, mr, rc);
			node.bottomLeft = process(grid, mr + 1, lc, rr, mc);
			node.bottomRight = process(grid, mr + 1, mc + 1, rr, rc);
		}
		return node;
	}

	public int isSame(int[][] gird, int lr, int lc, int rr, int rc) {
		int sum = 0;
		for (int i = lr; i <= rr; i++) {
			for (int j = lc; j <= rc; j++) {
				sum += gird[i][j];
			}
		}
		if (sum == 0) {
			return 0;
		} else if (sum == (rr - lr + 1) * (rc - lc + 1)) {
			return 1;
		} else {
			return -1;
		}
	}
}
