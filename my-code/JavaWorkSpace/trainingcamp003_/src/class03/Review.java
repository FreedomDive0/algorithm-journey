package class03;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Review {
	// 1.Job有 hard 和 money两个属性 给定Jobs[] 给定people[] 员工的能力 return int[]每个员工的最大报酬
	public static class Job {
		int hard;
		int money;

		public Job(int hard, int money) {
			this.hard = hard;
			this.money = money;
		}

	}

	public static class Jobomparator implements Comparator<Job> {

		@Override
		public int compare(Job o1, Job o2) {
			// TODO Auto-generated method stub
			return o1.hard != o2.hard ? o1.hard - o2.hard : o2.money - o1.money;// 将工作排序变为难度增加 报酬变少的顺序
		}

	}

	public static int[] chooseJob(Job[] jobs, int[] people) {
		if (jobs == null || jobs.length < 1 || people == null || people.length < 1) {
			return null;
		}
		Arrays.sort(jobs, new Jobomparator());
		TreeMap<Integer, Integer> jobList = new TreeMap<Integer, Integer>();// floorKey方法
		Job pre = jobs[0];
		int[] ans = new int[people.length];
		jobList.put(pre.hard, pre.money);
		for (int i = 1; i < jobs.length; i++) {
			if (pre.hard != jobs[i].hard && jobs[i].money > pre.money) {// 只有难度增加报酬也增加的工作才入选
				jobList.put(jobs[i].hard, jobs[i].money);
				pre = jobs[i];
			}
		}
		for (int i = 0; i < people.length; i++) {
			Integer key = jobList.floorKey(people[i]);// 有可能能力太低没有报酬
			ans[i] = key == null ? 0 : jobList.get(key);
		}
		return ans;
	}

	// 2.背包容量为w n袋零食 v[] 一共有多少种装法
	public static int snacksWays(int w, int[] v) {
		if (w <= 0 || v == null || v.length < 1) {
			return -1;
		}
		return processSW(v, 0, w);
	}

	public static int processSW(int[] v, int index, int rest) {
		if (rest < 0) {
			return 0;
		}
		if (index == v.length) {
			return 1;
		}
		int next1 = processSW(v, index + 1, rest);
		int next2 = processSW(v, index + 1, rest - v[index]);
		return next1 + next2;
	}

	// 3.matrix[][] 全为正数 可以向下向右走 return 最小路径和
	public static int minPathSum(int[][] matrix) {
		if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
			return -1;
		}
		return processMPS(matrix, 0, 0, matrix.length, matrix[0].length);
	}

	public static int processMPS(int[][] matrix, int row, int col, int n, int m) {
		if (row == n - 1 && col == m - 1) {
			return matrix[row][col];
		}
		if (row == n - 1) {
			return matrix[row][col] + processMPS(matrix, row, col + 1, n, m);
		}
		if (col == m - 1) {
			return matrix[row][col] + processMPS(matrix, row + 1, col, n, m);
		}
		int next3 = matrix[row][col] + processMPS(matrix, row + 1, col, n, m);
		int next4 = matrix[row][col] + processMPS(matrix, row, col + 1, n, m);
		int min = Math.min(next3, next4);
		return min;
	}

	public static int MPSDP(int[][] matrix) {
		if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
			return -1;
		}
		int n = matrix.length;
		int m = matrix[0].length;
		int[] dp = new int[m];
		dp[m - 1] = matrix[n - 1][m - 1];
		for (int col = m - 2; col >= 0; col--) {
			dp[col] = dp[col + 1] + matrix[n - 1][col];
		}
		for (int row = n - 2; row >= 0; row--) {
			dp[m - 1] += matrix[row][m - 1];
			for (int col = m - 2; col >= 0; col--) {
				dp[col] = matrix[row][col] + Math.min(dp[col], dp[col + 1]);
			}
		}
		return dp[0];
	}

	// 4.str1,str2最长公共子序列
	public static int LCSubsequnce(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() < 1 || s2.length() < 1) {
			return 0;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int n = str1.length;
		int m = str2.length;
		int[][] dp = new int[n][m];
		dp[0][0] = str1[0] == str2[0] ? 1 : 0;
		for (int i = 1; i < n; i++) {
			dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
		}
		for (int j = 1; j < m; j++) {
			dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j - 1];
		}
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				if (str1[i] == str2[j]) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
				}
			}
		}
		return dp[n - 1][m - 1];
	}

	// 5.str1,str2最长共子串
	public static String LCSubString(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() < 1 || s2.length() < 1) {
			return null;
		}
		int end = 0;
		int max = 0;
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int n = str1.length;
		int m = str2.length;
		int row = 0;
		int col = m - 1;
		while (row < n) {
			int i = row;
			int j = col;
			int len = 0;
			while (i < n && j < m) {
				if (str1[i] != str2[j]) {
					len = 0;
				} else {
					len++;
				}
				if (len > max) {
					end = i;
					max = len;
				}
				i++;
				j++;// 对角线移动 节省空间
			}
			if (col > 0) {
				col--;
			} else {
				row++;
			}
		}
		return s1.substring(end - max + 1, end + 1);
	}

	// 6.String[] 正数K return词频最大的前K个string
	public static void TopKStr(String[] strs, int k) {
		if (strs == null || strs.length < 1 || k <= 0) {
			return;
		}
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < strs.length; i++) {
			if (!map.containsKey(strs[i])) {
				map.put(strs[i], 1);
			} else {
				map.put(strs[i], map.get(strs[i]) + 1);
			}
		}
		k = Math.min(k, strs.length);
		PriorityQueue<WordFrequency> heap = new PriorityQueue<WordFrequency>(new WFComparator());
		for (Entry<String, Integer> entry : map.entrySet()) {
			if (heap.size() < k) {
				heap.add(new WordFrequency(entry.getKey(), entry.getValue()));
			} else {
				if (heap.peek().frequency < entry.getValue()) {
					heap.poll();
					heap.add(new WordFrequency(entry.getKey(), entry.getValue()));
				}
			}
		}
		while (!heap.isEmpty()) {
			System.out.println(heap.poll().word);
		}
	}

	public static class WordFrequency {
		String word;
		int frequency;

		public WordFrequency(String word, int frequency) {
			this.word = word;
			this.frequency = frequency;
		}

	}

	public static class WFComparator implements Comparator<WordFrequency> {

		@Override
		public int compare(WordFrequency o1, WordFrequency o2) {
			return o1.frequency - o2.frequency;
		}

	}

	// 7.TopRecord结构 提供K add(String str) top()
	public static class Node {
		public String str;
		public int times;

		public Node(String s, int t) {
			str = s;
			times = t;
		}
	}

	public static class TopKRecord {
		private Node[] heap;
		private int heapSize;
		private HashMap<String, Node> strNodeMap;
		private HashMap<Node, Integer> nodeIndexMap;

		public TopKRecord(int k) {
			heap = new Node[k];
			heapSize = 0;
			strNodeMap = new HashMap<String, Node>();
			nodeIndexMap = new HashMap<Node, Integer>();
		}

		public void add(String str) {
			Node curNode = null;
			int preIndex = -1;
			if (!strNodeMap.containsKey(str)) {
				curNode = new Node(str, 1);
				strNodeMap.put(str, curNode);
				nodeIndexMap.put(curNode, -1);
			} else {
				curNode = strNodeMap.get(str);
				curNode.times++;
				preIndex = nodeIndexMap.get(curNode);
			}
			if (preIndex == -1) {
				if (heapSize < heap.length) {
					heap[heapSize] = curNode;
					nodeIndexMap.put(curNode, heapSize);
					heapInsert(heapSize++);
				} else if (heap[0].times < curNode.times) {
					nodeIndexMap.put(heap[0], -1);
					heap[0] = curNode;
					nodeIndexMap.put(curNode, 0);
					heapify(0, heapSize);
				}
			} else {
				heapify(preIndex, heapSize);
			}
		}

		public void printTopK() {
			System.out.println("TOP: ");
			for (int i = 0; i < heap.length; i++) {
				if (heap[i] == null) {
					break;
				}
				System.out.print("Str: " + heap[i].str);
				System.out.println(" Times: " + heap[i].times);
			}
		}

		private void heapify(int index, int heapSize) {
			int leftSon = index * 2 + 1;
			while (leftSon < heapSize) {
				int maxIndex = leftSon + 1 < heapSize
						? heap[leftSon].times < heap[leftSon + 1].times ? leftSon + 1 : leftSon
						: leftSon;
				maxIndex = heap[index].times < heap[maxIndex].times ? index : maxIndex;
				if (index == maxIndex) {
					return;
				}
				swap(index, maxIndex);
				index = maxIndex;
				leftSon = index * 2 + 1;
			}
		}

		private void heapInsert(int index) {
			while (index != 0) {
				if (heap[index].times < heap[(index - 1) / 2].times) {
					swap(index, (index - 1) / 2);
					index = (index - 1) / 2;
				} else {
					break;
				}
			}
		}

		private void swap(int index1, int index2) {
			nodeIndexMap.put(heap[index1], index2);
			nodeIndexMap.put(heap[index2], index1);
			Node tmp = heap[index1];
			heap[index1] = heap[index2];
			heap[index2] = tmp;
		}
	}
}
