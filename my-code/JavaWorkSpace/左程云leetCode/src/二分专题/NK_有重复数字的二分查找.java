package 二分专题;

//https:www.nowcoder.com/practice/7bc4a1c7c371425d9faa9d1b511fe193?tpId=190&&tqId=35227&rp=1&ru=/ta/job-code-high-rd&qru=/ta/job-code-high-rd/question-ranking
public class NK_有重复数字的二分查找 {
	// 请实现有重复数字的升序数组的二分查找。
	// 输出在数组中第一个大于等于查找值的位置（下标从1开始算起），如果数组中不存在这样的数(指不存在大于等于查找值的数)，则输出数组长度加一。
	public static void main(String[] args) {
		System.out.println(upper_bound_(1, 0, new int[] { 1 }));
	}

	public static int upper_bound_(int n, int v, int[] a) {
		int l = 0;
		int r = n - 1;
		int index = n + 1;
		while (l <= r) {
			int mid = l + (r - l) / 2;
			if (a[mid] >= v) {
				index = mid + 1;
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		return index;
	}
}
