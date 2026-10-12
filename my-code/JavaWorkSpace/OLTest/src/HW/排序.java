package HW;

public class 排序 {
//	1.数据表记录包含表索引index和数值value（int范围的正整数），请对表索引相同的记录进行合并，即将相同索引的数值进行求和运算，输出按照index值升序进行输出。
//	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		int n = in.nextInt();
//		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
//		for (int i = 0; i < n; i++) {
//			int key = in.nextInt();
//			int value = in.nextInt();
//			if (map.containsKey(key)) {
//				map.put(key, map.get(key) + value);
//			} else {
//				map.put(key, value);
//			}
//		}
//		for (Integer key : map.keySet()) {
//			System.out.println(key + " " + map.get(key));
//		}
//	}
//	2.给定 n 个字符串，请对 n 个字符串按照字典序排列。
//	public static void main(String[] args) throws Exception {
//		Scanner in = new Scanner(System.in);
//		int n = in.nextInt();
//		String[] array = new String[n];
//		for (int i = 0; i < n; i++) {
//			array[i] = in.next();// nextLine会将nextInt的回车接受
//		}
//		Arrays.sort(array);
//		for (String str : array) {
//			System.out.println(str);
//		}
//	}
//	3.定义一个单词的“兄弟单词”为：交换该单词字母顺序（注：可以交换任意次），而不添加、删除、修改原有的字母就能生成的单词。兄弟单词要求和原来的单词不同。例如： ab 和 ba 是兄弟单词。 ab 和 ab 则不是兄弟单词。现在给定你 n 个单词，另外再给你一个单词 x ，让你寻找 x 的兄弟单词里，按字典序排列后的第 k 个单词是什么？
//	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		while (in.hasNext()) {
//			String[] strs = in.nextLine().split(" ");
//			int n = Integer.valueOf(strs[0]);
//			int k = Integer.valueOf(strs[strs.length - 1]);
//			String str = strs[strs.length - 2];
//			ArrayList<String> res = isBrother(strs, str);
//			System.out.println(res.size());
//			if (res.size() >= k) {
//				Collections.sort(res);
//				System.out.println(res.get(k - 1));
//			}
//		}
//	}
//
//	public static ArrayList isBrother(String[] strs, String str) {
//		ArrayList<String> res = new ArrayList<String>();
//		for (int i = 1; i < strs.length - 2; i++) {
//			String s1 = strs[i];
//			if (s1.length() != str.length() || s1.equals(str)) {
//				continue;
//			}
//			char[] c1 = s1.toCharArray();
//			char[] s = str.toCharArray();
//			Arrays.sort(c1);
//			Arrays.sort(s);
//			if (new String(c1).equals(new String(s))) {
//				res.add(s1);
//			}
//		}
//		return res;
//	}
//	4.以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
//	public static int[][] merge(int[][] intervals) {
//		if (intervals == null || intervals.length == 0) {
//			return new int[0][];
//		}
//		if (intervals.length == 1) {
//			return intervals;
//		}
//		PriorityQueue<int[]> queue = new PriorityQueue(new Comparator() {
//			@Override
//			public int compare(Object o1, Object o2) {
//				int[] num1 = (int[]) o1;
//				int[] num2 = (int[]) o2;
//				return num1[0] - num2[0];
//			}
//		});
//		for (int i = 0; i < intervals.length; i++) {
//			queue.add(intervals[i]);
//		}
//		ArrayList<int[]> res = new ArrayList<int[]>();
//		int[] num1 = queue.poll();
//		int[] num2 = null;
//		while (!queue.isEmpty()) {
//			num2 = queue.poll();
//			if (num1[1] >= num2[0]) {
//				if (num1[1] <= num2[1]) {
//					num1[1] = num2[1];
//				} else {
//					continue;
//				}
//			} else {
//				res.add(num1);
//				num1 = num2;
//			}
//		}
//		res.add(num1);
//		return res.toArray(new int[res.size()][2]);
//	}
//	5.给定一些同学的信息（名字，成绩）序列，请你将他们的信息按照成绩从高到低或从低到高的排列,相同成绩都按先录入排列在前的规则处理。
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int cnt = Integer.parseInt(br.readLine());
//		int asc = Integer.parseInt(br.readLine());
//		PriorityQueue<String[]> queue = new PriorityQueue<String[]>(
//				(a, b) -> a[1].equals(b[1]) ? Integer.parseInt(a[2]) - Integer.parseInt(b[2])
//						: asc == 0 ? Integer.parseInt(b[1]) - Integer.parseInt(a[1])
//								: Integer.parseInt(a[1]) - Integer.parseInt(b[1]));
//		for (int i = 0; i < cnt; i++) {
//			String[] path = br.readLine().split(" ");
//			queue.offer(new String[] { path[0], path[1], String.valueOf(i) });
//		}
//		while (!queue.isEmpty()) {
//			String[] res = queue.poll();
//			System.out.println(res[0] + " " + res[1]);
//		}
//	}
}
