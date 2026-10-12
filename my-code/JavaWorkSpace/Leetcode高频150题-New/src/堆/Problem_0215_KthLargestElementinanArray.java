package 堆;

//bfprt
public class Problem_0215_KthLargestElementinanArray {
	// 1.随机partition
//	public int findKthLargest(int[] nums, int k) {
//		return process(nums, 0, nums.length - 1, nums.length - k);// 题目是第k大 需要转换为第k小
//	}
//
//	public int process(int[] nums, int l, int r, int k) {
//		if (l == r) {
//			return nums[l];
//		}
//		int pivot = nums[l + (int) (Math.random() * (r - l + 1))];
//		int[] range = partition(nums, l, r, pivot);
//		if (k >= range[0] && k <= range[1]) {
//			return nums[k];
//		} else if (k < range[0]) {
//			return process(nums, l, range[0] - 1, k);
//		} else {
//			return process(nums, range[1] + 1, r, k);
//		}
//	}
//
//	public int[] partition(int[] nums, int l, int r, int pivot) {
//		int less = l - 1;
//		int more = r + 1;
//		int index = l;
//		while (index < more) {
//			if (nums[index] < pivot) {
//				swap(nums, index++, ++less);
//			} else if (nums[index] > pivot) {
//				swap(nums, index, --more);
//			} else {
//				index++;
//			}
//		}
//		return new int[] { less + 1, more - 1 };
//	}
	// 2.bfprt
	public int findKthLargest(int[] nums, int k) {
		return bfprt(nums, 0, nums.length - 1, nums.length - k);
	}

	public int bfprt(int[] nums, int l, int r, int index) {
		if (l == r) {
			return nums[l];
		}
		int midOfMid = getMidArrMid(nums, l, r);
		int[] range = partition(nums, l, r, midOfMid);
		if (index < range[0]) {
			return bfprt(nums, l, range[0] - 1, index);
		} else if (index > range[1]) {
			return bfprt(nums, range[1] + 1, r, index);
		} else {
			return nums[index];
		}
	}

	public int[] partition(int[] nums, int l, int r, int pivot) {
		int less = l - 1;
		int more = r + 1;
		int index = l;
		while (index < more) {
			if (nums[index] < pivot) {
				swap(nums, index++, ++less);
			} else if (nums[index] > pivot) {
				swap(nums, index, --more);
			} else {
				index++;
			}
		}
		return new int[] { less + 1, more - 1 };
	}

	public int getMidArrMid(int[] nums, int l, int r) {
		int size = r - l + 1;
		int offset = size % 5 == 0 ? 0 : 1;
		int[] mids = new int[size / 5 + offset];
		for (int i = 0; i < mids.length; i++) {
			int firstTeam = l + i * 5;
			mids[i] = getMid(nums, firstTeam, Math.min(firstTeam + 4, r));
		}
		return bfprt(mids, 0, mids.length - 1, mids.length / 2);
	}

	public int getMid(int[] nums, int l, int r) {
		insertSort(nums, l, r);
		return nums[(l + r) / 2];
	}

	public void insertSort(int[] nums, int l, int r) {
		for (int i = l + 1; i <= r; i++) {
			for (int j = i - 1; j >= l && nums[j] > nums[j + 1]; j--) {
				swap(nums, i, j);
			}
		}
	}

	public void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
//	public class MyCom implements Comparator<Integer> {
//
//		@Override
//		public int compare(Integer o1, Integer o2) {
//			return o2 - o1;
//		}
//
//	}
//
//	public int findKthLargest(int[] nums, int k) {
//		PriorityQueue<Integer> heap = new PriorityQueue<Integer>(new MyCom());
//		for (int i = 0; i < nums.length; i++) {
//			heap.add(nums[i]);
//		}
//		int res = 0;
//		for (int i = 0; i < k; i++) {
//			res = heap.poll();
//		}
//		return res;
//	}

}
