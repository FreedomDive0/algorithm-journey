package 分治;

public class Problem_0108_Convert_Sorted_Array_to_Binary_Search_Tree {
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

	public TreeNode sortedArrayToBST(int[] nums) {
		return process(nums, 0, nums.length - 1);
	}

	public TreeNode process(int[] nums, int L, int R) {
		if (L > R) {
			return null;
		}
		if (L == R) {
			return new TreeNode(nums[L]);
		}
		int mid = (L + R) / 2;
		TreeNode head = new TreeNode(nums[mid]);
		head.left = process(nums, L, mid - 1);
		head.right = process(nums, mid + 1, R);
		return head;
	}

}
