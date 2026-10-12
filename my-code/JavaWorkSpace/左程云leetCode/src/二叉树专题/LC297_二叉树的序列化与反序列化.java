package 二叉树专题;

import java.util.ArrayList;
import java.util.Arrays;

public class LC297_二叉树的序列化与反序列化 {
	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		if (root == null) {
			return "null";
		}
		return String.valueOf(root.val) + "," + serialize(root.left) + "," + serialize(root.right);
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		String[] tmp = data.split(",");
		ArrayList<String> arr = new ArrayList<String>(Arrays.asList(tmp));
		return process(arr);
	}

	public TreeNode process(ArrayList<String> list) {
		if ("null".equals(list.get(0))) {
			list.remove(0);
			return null;
		}
		TreeNode node = new TreeNode(Integer.valueOf(list.get(0)));
		list.remove(0);
		node.left = process(list);
		node.right = process(list);
		return node;
	}
}
