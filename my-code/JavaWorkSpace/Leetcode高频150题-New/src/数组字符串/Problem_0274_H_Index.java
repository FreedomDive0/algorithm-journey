package 数组字符串;

public class Problem_0274_H_Index {
//	public int hIndex(int[] citations) {
//		PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
//		for (Integer num : citations) {
//			if (num != 0) {
//				heap.add(num);
//			}
//		}
//		int ans = 0;
//		while (!heap.isEmpty() && heap.size() > ans) {
//			while (!heap.isEmpty() && heap.peek() <= ans) {
//				heap.poll();
//			}
//			if (ans < heap.size()) {
//				ans++;
//			} else {
//				break;
//			}
//		}
//		return ans;
//	}
	// 计数排序
//	public int hIndex(int[] citations) {
//		int len = citations.length;
//		int[] counts = new int[len + 1];
//		for (int i = 0; i < len; i++) {
//			if (citations[i] > len) {
//				counts[len]++;
//			} else {
//				counts[citations[i]]++;
//			}
//		}
//		for (int i = counts.length - 2; i >= 0; i--) {
//			counts[i] += counts[i + 1];
//		}
//		int max = 0;
//		for (int i = counts.length - 1; i >= 0; i--) {
//			if (counts[i] >= i) {
//				max = i;
//				break;
//			}
//		}
//		return max;
//	}
	public int hIndex(int[] citations) {
		int len = citations.length;
		int[] counts = new int[len + 1];
		for (int i = 0; i < len; i++) {
			if (citations[i] > len) {
				counts[len]++;
			} else {
				counts[citations[i]]++;
			}
		}
		int times = 0;
		for (int i = counts.length - 1; i >= 0; i--) {
			times += counts[i];
			if (times >= i) {
				return i;
			}
		}
		return 0;
	}
}
