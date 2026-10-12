package class05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Review {

	// 1.s1 s2 s2最少删除多少==>s1子串
	// (1)s2长度较短时 O(N*2^M)
	public static int deleteMinCost1(String s1, String s2) {
		if (s1 == null || s2 == null) {
			return 0;
		}
		ArrayList<String> subS2 = new ArrayList<String>();
		processDMC(s2.toCharArray(), 0, "", subS2);
		subS2.sort(new LenComparator());
		for (String str : subS2) {
			if (s1.indexOf(str) != -1) {
				return s2.length() - str.length();// 编辑长度为：s2长度-子序列长度
			}
		}
		return s2.length();
	}

	public static void processDMC(char[] str, int index, String path, ArrayList<String> ans) {
		if (index == str.length) {
			ans.add(path);
			return;
		}
		processDMC(str, index + 1, path, ans);
		processDMC(str, index + 1, path + str[index], ans);
	}

	public static class LenComparator implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			// TODO Auto-generated method stub
			return o2.length() - o1.length();
		}

	}

	// (2)s1子串 只有插入操作==>s2 优化时间复杂度O(N^2*M)
	public static int deleteMin3(String s1, String s2) {
		if (s1.length() == 0 || s2.length() == 0) {
			return s2.length();
		}
		char[] str2 = s2.toCharArray();
		char[] str1 = s1.toCharArray();
		int M = str2.length;
		int N = str1.length;
		int[][] dp = new int[M][N];// 且dp含义发生改变 dp[i][j]表示str2->s1Sub所需只含删除行为的最短编辑距离 ∴每一列最后一行为答案不停抓取答案
		int ans = M;
		for (int start = 0; start < N; start++) {// 开始的列数 开始匹配s1Sub子串的开头
			dp[0][start] = str2[0] == str1[start] ? 0 : M;
			for (int row = 1; row < M; row++) {// 初始化start开始的第一列
				dp[row][start] = (dp[row - 1][start] != M || str2[row] == str1[start]) ? row : M;// 初始化第一列比较
																									// str2[row]和str1[start]
			}
			ans = Math.min(ans, dp[M - 1][start]);
			for (int end = start + 1; end < N && end - start < M; end++) {// end-start为s1Sub串长度
																			// 如果超过N(也就是str2长度)就无法编辑到s1Sub
				int first = end - start;// 每次新列的开头位置 如果str2长度<s1Sub长度怎么删除都变不成s1Sub
				dp[first][end] = (str2[first] == str1[end] && dp[first - 1][end - 1] == 0) ? 0 : M;
				for (int row = first + 1; row < M; row++) {
					dp[row][end] = M;
					if (dp[row - 1][end] != M) {// dp[i-1][j]包含了前面所有的位置dp[i-2,i-3...0][j]
						dp[row][end] = dp[row - 1][end] + 1;// 如果dp[i-1][j]可以搞定 把i位置删除即可
					}
					if (str2[row] == str1[end] && dp[row - 1][end - 1] != M) {// 1.保留i-1位置
						dp[row][end] = Math.min(dp[row][end], dp[row - 1][end - 1]);// 前面可以搞定并且最后一位相同则不需删除
					}
				}
				ans = Math.min(ans, dp[M - 1][end]);
			}
		}
		return ans;
	}

	// (2)s1子串 只有插入操作==>s2
	public static int deleteMinCost2(String s1, String s2) {
		if (s1.length() == 0 || s2.length() == 0) {// s1长度为0 全部删除 s2长度为0 直接是子串
			return s2.length();
		}
		char[] str2 = s2.toCharArray();
		int minCost = s2.length();
		for (int start = 0; start < s1.length(); start++) {
			for (int end = start + 1; end <= s1.length(); end++) {
				minCost = Math.min(minCost, minCostDelete(str2, s1.substring(start, end).toCharArray()));
			}
		}
		return minCost;
	}

	public static int minCostDelete(char[] str2, char[] str1) {
		int n = str2.length;
		int m = str1.length;
		int[][] dp = new int[n][m];
		dp[0][0] = str2[0] == str1[0] ? 0 : Integer.MAX_VALUE;
		for (int i = 1; i < n; i++) {
			dp[i][0] = (dp[i - 1][0] != Integer.MAX_VALUE || str2[i] == str1[0]) ? i : Integer.MAX_VALUE;// 之前匹配成功或这次字符相同删除其他部分
		}
		for (int j = 1; j < m; j++) {
			dp[0][j] = Integer.MAX_VALUE;
		}
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {// i位置可能性只有删除or不删除
				dp[i][j] = Integer.MAX_VALUE;
				if (dp[i - 1][j] != Integer.MAX_VALUE) {// 0~i-1可以搞定 把i位置删除即可
					dp[i][j] = dp[i - 1][j] + 1;
				}
				if (str2[i] == str1[j] && dp[i - 1][j - 1] != Integer.MAX_VALUE) {// 相同不用删除
					dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
				}
			}
		}
		return dp[n - 1][m - 1];
	}

	// 2.最小编辑距离
	public static int editCost(String s1, String s2, int ic, int dc, int rc) {
		if (s1 == null || s2 == null) {
			return 0;
		}
		int n = s1.length() + 1;
		int m = s2.length() + 1;
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int[][] dp = new int[n][m];
		for (int i = 1; i < n; i++) {
			dp[i][0] = i * dc;
		}
		for (int j = 1; j < m; j++) {
			dp[0][j] = j * ic;
		}
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				dp[i][j] = dp[i - 1][j - 1] + rc;// 保留i-1位置字符 str1[i-1]!=str2[j-1]
				if (str1[i - 1] == str2[j - 1]) {// 保留i-1位置字符 str1[i-1] == str2[j-1]
					dp[i][j] = dp[i - 1][j - 1];
				}
				dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ic);// 保留i-1位置字符 0~i-1搞定0~j-2 再插入
				dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dc);// 不保留i-1位置字符 0~i-2搞定0~j-1 删除i-1字符
			}
		}
		return dp[n - 1][m - 1];
	}

	// 3.完全二叉树节点个数
	public static int completeTreeNodeNum(Node head) {
		if (head == null) {
			return 0;
		}
		return processCTNN(head, 1, mostLeftLevel(head, 1));
	}

	public static int processCTNN(Node node, int level, int h) {
		if (level == h) {
			return 1;
		}
		if (mostLeftLevel(node.right, level + 1) == h) {
			return (1 << (h - level)) + processCTNN(node.right, level + 1, h);// h-level表示下方对应规模的节点
		} else {
			return (1 << (h - level - 1)) + processCTNN(node.left, level + 1, h);
		}
	}

	public static int mostLeftLevel(Node node, int level) {
		while (node != null) {
			level++;
			node = node.left;
		}
		return level - 1;// node到达null level还++ 返回需要-1
	}

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	// 4.LRU
	public static class Node2<K, V> {
		public K key;
		public V value;
		public Node2<K, V> last;
		public Node2<K, V> next;

		public Node2(K key, V value) {
			this.key = key;
			this.value = value;
			this.last = null;
			this.next = null;
		}
	}

	// 双向链表
	// 从head到tail所有节点都是串好的
	public static class NodeDoubleLinkedList<K, V> {
		private Node2<K, V> head;
		private Node2<K, V> tail;

		public NodeDoubleLinkedList() {
			head = null;
			tail = null;
		}

		// 如果一个新的节点加入，放到尾巴上去
		public void addNode(Node2<K, V> newNode) {
			if (newNode == null) {
				return;
			}
			// newNode != null
			if (head == null) { // 双向链表中一个节点也没有
				head = newNode;
				tail = newNode;
			} else { // 双向链表中之前有节点，tail（非null）
				tail.next = newNode;
				newNode.last = tail;
				tail = newNode;
			}
		}

		// 潜台词 ： 双向链表上一定有这个node
		// node分离出，但是node前后环境重新连接
		// node放到尾巴上去
		public void moveNodeToTail(Node2<K, V> node) {
			if (this.tail == node) {
				return;
			}
			if (this.head == node) { // 当前node是老头部
				this.head = node.next;
				this.head.last = null;
			} else { // 当前node是中间的一个节点
				node.last.next = node.next;
				node.next.last = node.last;
			}
			node.last = this.tail;
			node.next = null;
			this.tail.next = node;
			this.tail = node;
		}

		// 把头节点删掉并返回
		public Node2<K, V> removeHead() {
			if (this.head == null) {
				return null;
			}
			Node2<K, V> res = this.head;
			if (this.head == this.tail) { // 链表中只有一个节点的时候
				this.head = null;
				this.tail = null;
			} else {
				this.head = res.next;
				res.next = null;
				this.head.last = null;
			}
			return res;
		}

	}

	public static class MyCache<K, V> {
		private HashMap<K, Node2<K, V>> keyNodeMap;
		private NodeDoubleLinkedList<K, V> nodeList;
		private final int capacity;

		public MyCache(int cap) {
			if (cap < 1) {
				throw new RuntimeException("should be more than 0.");
			}
			keyNodeMap = new HashMap<K, Node2<K, V>>();
			nodeList = new NodeDoubleLinkedList<K, V>();
			capacity = cap;
		}

		public V get(K key) {
			if (keyNodeMap.containsKey(key)) {
				Node2<K, V> res = keyNodeMap.get(key);
				nodeList.moveNodeToTail(res);
				return res.value;
			}
			return null;
		}

		public void set(K key, V value) {
			if (keyNodeMap.containsKey(key)) {
				Node2<K, V> node = keyNodeMap.get(key);
				node.value = value;
				nodeList.moveNodeToTail(node);
			} else { // 这是一个新加的记录，有可能出现替换
				if (keyNodeMap.size() == capacity) {
					removeMostUnusedCache();
				}
				Node2<K, V> newNode = new Node2<K, V>(key, value);
				keyNodeMap.put(key, newNode);
				nodeList.addNode(newNode);
			}
		}

		private void removeMostUnusedCache() {
			Node2<K, V> removeNode = nodeList.removeHead();
			keyNodeMap.remove(removeNode.key);
		}

	}

	// 5.start每次改变一个字符==>to to一定在给定List中 每次改变字符的字符串在List中找 求所有最短转换路径
	public static List<List<String>> wordMinPaths(String start, String to, List<String> list) {
		list.add(start);
		HashMap<String, ArrayList<String>> nexts = getNexts(list);// 得到所有单词的next 相当于得到了图
		HashMap<String, Integer> distanceMap = getDistanceMap(start, nexts);
		LinkedList<String> path = new LinkedList<String>();
		List<List<String>> res = new ArrayList<>();
		getMinPaths(start, to, distanceMap, nexts, path, res);
		return res;
	}

	public static HashMap<String, ArrayList<String>> getNexts(List<String> list) {
		HashSet<String> set = new HashSet<String>(list);// 加list值加入Set
		HashMap<String, ArrayList<String>> nexts = new HashMap<String, ArrayList<String>>();
		for (String word : list) {
			nexts.put(word, getNext(word, set));
		}
		return nexts;
	}

	public static ArrayList<String> getNext(String word, HashSet<String> set) {// 只改变一个位置 可以变到的字符串之间有边
		ArrayList<String> next = new ArrayList<String>();
		char[] chars = word.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			for (char cur = 'a'; cur <= 'z'; cur++) {
				if (cur != chars[i]) {
					char aboriginal = chars[i];// 记录原来的值
					chars[i] = cur;
					if (set.contains(String.valueOf(chars))) {
						next.add(String.valueOf(chars));
					}
					chars[i] = aboriginal;// 还原原字符串 尝试别的位置
				}
			}
		}
		return next;
	}

	public static HashMap<String, Integer> getDistanceMap(String start, HashMap<String, ArrayList<String>> nexts) {
		HashMap<String, Integer> distanceMap = new HashMap<String, Integer>();
		distanceMap.put(start, 0);
		Queue<String> queue = new LinkedList<String>();
		queue.add(start);
		HashSet<String> joined = new HashSet<String>();
		joined.add(start);
		while (!queue.isEmpty()) {
			String cur = queue.poll();
			for (String path : nexts.get(cur)) {
				if (!joined.contains(path)) {
					distanceMap.put(path, distanceMap.get(cur) + 1);
					queue.add(path);// 加入队列
					joined.add(path);// 加入Set
				}
			}
		}
		return distanceMap;
	}

	public static void getMinPaths(String cur, String to, HashMap<String, Integer> distanceMap,
			HashMap<String, ArrayList<String>> nexts, LinkedList<String> path, List<List<String>> res) {
		path.add(cur);
		if (to.equals(cur)) {
			res.add(new LinkedList<String>(path));
		} else {
			for (String next : nexts.get(cur)) {
				if (distanceMap.get(next) == distanceMap.get(cur) + 1) {
					getMinPaths(next, to, distanceMap, nexts, path, res);
				}
			}
		}
		path.pollLast();
	}

	public static void main(String[] args) {
		String start = "abc";
		String end = "cab";
		String[] test = { "abc", "cab", "acc", "cbc", "ccc", "cac", "cbb", "aab", "abb" };
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < test.length; i++) {
			list.add(test[i]);
		}
		List<List<String>> res = wordMinPaths(start, end, list);
		for (List<String> obj : res) {
			for (String str : obj) {
				System.out.print(str + " -> ");
			}
			System.out.println();
		}

	}
}
