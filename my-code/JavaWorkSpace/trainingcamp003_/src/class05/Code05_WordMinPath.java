package class05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Code05_WordMinPath {
	// 1.建立start到list的词的最短路径表
	public static List<List<String>> minPath(String start, String to, List<String> list) {
		HashMap<String, ArrayList<String>> nexts = getNexts(list);// 建立图
		HashMap<String, Integer> distance = getDistance(start, nexts);// 得到start到所有点的最短距离
		List<List<String>> paths = new ArrayList<>();
		LinkedList<String> pathList = new LinkedList<>();
		getShortestPaths(start, to, nexts, distance, pathList, paths);
		return paths;
	}

	// 根据list建立nexts表 距离为1即有边
	public static HashMap<String, ArrayList<String>> getNexts(List<String> words) {
		HashMap<String, ArrayList<String>> nexts = new HashMap<>();
		HashSet<String> set = new HashSet<>(words);
		for (int i = 0; i < words.size(); i++) {
			nexts.put(words.get(i), getNext(words.get(i), set));
		}
		return nexts;
	}

	// 得到每个字符串的相邻边
	public static ArrayList<String> getNext(String word, HashSet<String> set) {
		char[] chs = word.toCharArray();
		ArrayList<String> next = new ArrayList<>();
		for (int i = 0; i < chs.length; i++) {
			for (char cur = 'a'; cur <= 'z'; cur++) {// 字母从a-z变化
				if (chs[i] != cur) {// 和原字符串相同不处理自己和自己没边
					char tmp = chs[i];
					chs[i] = cur;
					if (set.contains(String.valueOf(chs))) {
						next.add(String.valueOf(chs));
					}
					chs[i] = tmp;// 还原字符串
				}
			}
		}
		return next;
	}

	// 得到start到每个点的最短路径
	public static HashMap<String, Integer> getDistance(String start, HashMap<String, ArrayList<String>> nexts) {
		HashMap<String, Integer> distances = new HashMap<>();// start到每个点距离
		Queue<String> queue = new LinkedList<String>();// 队列 用于宽度优先遍历
		HashSet<String> entered = new HashSet<>();// 判断字符串是否被处理过
		distances.put(start, 0);
		queue.add(start);
		entered.add(start);
		while (!queue.isEmpty()) {
			String cur = queue.poll();
			for (String next : nexts.get(cur)) {// 得到每个点的邻居进行处理
				if (!entered.contains(next)) {
					distances.put(next, distances.get(cur) + 1);// 记录答案
					queue.add(next);// 宽度优先遍历
					entered.add(next);// 标志处理过
				}
			}
		}
		return distances;
	}

	// 得到start -> to 最短变化路径
	public static void getShortestPaths(String cur, String to, HashMap<String, ArrayList<String>> nexts,
			HashMap<String, Integer> distance, LinkedList<String> path, List<List<String>> paths) {
		path.add(cur);// 先加入
		if (cur.equals(to)) {// 找到答案
			paths.add(new LinkedList<String>(path));// 必须重新创建 否则内部值会被删完
		} else {
			for (String next : nexts.get(cur)) {
				if (distance.get(cur) + 1 == distance.get(next)) {// 每次路径长度+1
					getShortestPaths(next, to, nexts, distance, path, paths);// 深度优先遍历
				}
			}
		}
		path.pollLast();// 处理完该路径下所有情况 最后弹出
	}

	public static void main(String[] args) {
		String start = "abc";
		String end = "cab";
		String[] test = { "abc", "cab", "acc", "cbc", "ccc", "cac", "cbb", "aab", "abb" };
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < test.length; i++) {
			list.add(test[i]);
		}
		List<List<String>> res = minPath(start, end, list);
		for (List<String> obj : res) {
			for (String str : obj) {
				System.out.print(str + " -> ");
			}
			System.out.println();
		}

	}
}
