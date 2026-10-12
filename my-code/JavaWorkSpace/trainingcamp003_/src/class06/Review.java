package class06;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class Review {
	// 4.给定两个有序数组
	public static int[] topKSumCrossTwoArrays(int[] arr1, int[] arr2, int k) {
		if (arr1 == null || arr2 == null || k < 1) {
			return null;
		}
		int n = arr1.length;
		int m = arr2.length;
		k = Math.min(k, n * m);
		int curIndex = 0;
		boolean[][] selected = new boolean[n][m];
		PriorityQueue<TKSCTANode> heap = new PriorityQueue<TKSCTANode>(new TKSCTANodeComparator());
		heap.add(new TKSCTANode(n - 1, m - 1, arr1[n - 1] + arr2[m - 1]));
		selected[n - 1][m - 1] = true;
		int[] res = new int[k];
		while (curIndex != k) {
			TKSCTANode cur = heap.poll();
			int index1 = cur.index1;
			int index2 = cur.index2;
			res[curIndex++] = cur.sum;
			if (index1 - 1 >= 0 && !selected[index1 - 1][index2]) {// >=0
				heap.add(new TKSCTANode(index1 - 1, index2, arr1[index1 - 1] + arr2[index2]));
				selected[index1 - 1][index2] = true;// 修改标志位
			}
			if (index2 - 1 >= 0 && !selected[index1][index2 - 1]) {
				heap.add(new TKSCTANode(index1, index2 - 1, arr1[index1] + arr2[index2 - 1]));
				selected[index1][index2 - 1] = true;
			}
		}
		return res;
	}

	public static class TKSCTANode {
		int index1;
		int index2;
		int sum;

		public TKSCTANode(int index1, int index2, int sum) {
			this.index1 = index1;
			this.index2 = index2;
			this.sum = sum;
		}

	}

	public static class TKSCTANodeComparator implements Comparator<TKSCTANode> {

		@Override
		public int compare(TKSCTANode o1, TKSCTANode o2) {
			return o2.sum - o1.sum;
		}

	}

	// 5.正数数组能不能分成4个累加和相同部分 分割位数值舍弃
	public static boolean splitforParts(int[] arr) {
		if (arr == null || arr.length < 7) {
			return false;
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int sum = arr[0];
		for (int i = 1; i < arr.length; i++) {// 放置的前缀和为 i-1的前缀和 这样才能得到s1的位置 不需要向后取一位
			map.put(sum, i);
			sum += arr[i];
		}
		int lsum = arr[0];
		for (int s1 = 1; s1 < arr.length - 5; s1++) {// 第一刀
			int checkSum = lsum * 2 + arr[s1];
			if (map.containsKey(checkSum)) {// 第二刀
				int s2 = map.get(checkSum);
				checkSum += arr[s2] + lsum;
				if (map.containsKey(checkSum)) {// 第三刀
					int s3 = map.get(checkSum);
					if (checkSum + arr[s3] + lsum == sum) {// 最后一部分是否相同
						return true;
					}
				}
			}
			lsum += arr[s1];// 更新lsum
		}

		return false;
	}

	// 6.两个字符串交错组成
	public static boolean stringCross(String s1, String s2, String s3) {
		if (s1 == null || s2 == null || s3 == null) {
			return false;
		}
		if (s1.length() + s2.length() != s3.length()) {
			return false;
		}
		char[] chs1 = s1.toCharArray();
		char[] chs2 = s2.toCharArray();
		char[] aim = s3.toCharArray();
		int n = chs1.length;
		int m = chs2.length;
		boolean[][] dp = new boolean[n + 1][m + 1];
		dp[0][0] = true;
		for (int i = 1; i <= n; i++) {
			if (chs1[i - 1] != aim[i - 1]) {// 注意下标
				break;
			}
			dp[i][0] = true;
		}
		for (int j = 1; j <= m; j++) {
			if (chs2[j - 1] != aim[j - 1]) {
				break;
			}
			dp[0][j] = true;
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if ((chs1[i - 1] == aim[i + j - 1] && dp[i - 1][j])
						|| (chs2[j - 1] == aim[i + j - 1] && dp[i][j - 1])) {
					dp[i][j] = true;
				}
			}
		}
		return dp[n][m];
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int sum = arr[0];
		for (int i = 1; i < arr.length; i++) {
			map.put(sum, i);
			sum += arr[i];
		}
		HashMap<Integer, Integer> map1 = new HashMap<Integer, Integer>();
		int sum1 = 0;
		for (int i = 0; i < arr.length; i++) {
			sum1 += arr[i];
			map1.put(sum1, i);
		}
		System.out.println("Map:");
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			System.out.println("sum:" + entry.getKey() + " " + "index:" + entry.getValue());
		}
		System.out.println("Map1:");
		for (Entry<Integer, Integer> entry : map1.entrySet()) {
			System.out.println("sum:" + entry.getKey() + " " + "index:" + entry.getValue());
		}
	}
}
