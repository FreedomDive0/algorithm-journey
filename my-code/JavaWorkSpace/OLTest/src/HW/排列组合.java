package HW;

public class 排列组合 {
//	1.有重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合。
//	ArrayList<String> res = new ArrayList<String>();
//
//	public String[] permutation(String S) {
//		char[] chars = S.toCharArray();
//		boolean[] visited = new boolean[chars.length];
//		Arrays.sort(chars);
//		dfs(chars, new StringBuilder(), visited);
//		return res.toArray(new String[res.size()]);)
//    }
//
//	public void dfs(char[] chars, StringBuilder temp, boolean[] visited) {
//		if (temp.length() == chars.length) {
//			res.add(temp.toString());
//			return;
//		}
//		for (int i = 0; i < chars.length; i++) {
//			if (visited[i] || i > 0 && visited[i - 1] && chars[i] == chars[i - 1]) {
//				continue;
//			}
//			temp.append(chars[i]);
//			visited[i] = true;
//			dfs(chars, temp, visited);
//			temp.deleteCharAt(temp.length() - 1);
//			visited[i] = false;
//		}
//	}
//	2.给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
//	ArrayList<List<Integer>> list = new ArrayList<List<Integer>>();
//
//	public List<List<Integer>> combine(int n, int k) {
//		Deque<Integer> path = new ArrayDeque<>();
//		dfs(n, 1, path, k);
//		return list;
//	}
//
//	public void dfs(int n, int begin, Deque<Integer> path, int k) {
//		if (path.size() == k) {
//			list.add(new ArrayList<Integer>(path));
//			return;
//		}
//		for (int i = begin; i <= n; i++) {
//			path.addLast(i);
//			dfs(n, i + 1, path, k);
//			path.removeLast();
//		}
//	}
}
