package 基本数据类型专题;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LC652_寻找重复的子树 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	HashMap<String, Integer> count = new HashMap<String, Integer>();
	List<TreeNode> res = new ArrayList<TreeNode>();

	public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
		dfs(root);
		return res;
	}

	public String dfs(TreeNode node) {
		if (node == null) {
			return null;
		}
		String left = dfs(node.left);
		String right = dfs(node.right);
		String cur = left + "," + right + "," + node.val;// 注意需要添加，区分左右树
		int cnt = count.getOrDefault(cur, 0);
		if (cnt == 1) {
			res.add(node);
		}
		count.put(cur, cnt + 1);
		return cur;
	}
}
