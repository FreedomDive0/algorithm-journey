package 堆;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Problem_0373_Find_K_Pairs_with_Smallest_Sums {

	public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
		int n = nums1.length;
		int m = nums2.length;
		List<List<Integer>> res = new ArrayList<>();
		PriorityQueue<int[]> heap = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);// 堆中存放sum,i,j
		heap.add(new int[] { nums1[0] + nums2[0], 0, 0 });
		while (!heap.isEmpty() && res.size() < k) {
			int[] tmp = heap.poll();
			int i = tmp[1];
			int j = tmp[2];
			res.add(List.of(nums1[i], nums2[j]));
			if (j == 0 && i + 1 < n) {// i,j出堆 只有可能i+1,j or i,j+1入堆 但是可能会造成重复Eg：(0,1)与(1,0)都可使(1,1)入堆
										// 所以(i+1,j)和(i,j+1)只取一边
				heap.add(new int[] { nums1[i + 1] + nums2[0], i + 1, 0 });// 取i，j+1则需将i+1，j一边单独补充(1,0) (2,0)...(k,0)
			}
			if (j + 1 < m) {
				heap.add(new int[] { nums1[i] + nums2[j + 1], i, j + 1 });
			}
		}
		return res;
	}
}
