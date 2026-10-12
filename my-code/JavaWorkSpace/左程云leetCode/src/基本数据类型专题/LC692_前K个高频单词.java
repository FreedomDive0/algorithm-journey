package 基本数据类型专题;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class LC692_前K个高频单词 {
	public static void main(String[] args) {
//		String[] words = new String[] { "i", "love", "leetcode", "i", "love", "coding" };
//		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
//		for (int i = 0; i < words.length; i++) {
//			map.put(words[i], map.getOrDefault(words[i], 0) + 1);
//		}
//		for (String str : map.keySet()) {
//			System.out.println(str + ":" + map.get(str));
//		}
//		for (Integer n : map.values()) {
//			System.out.println(n);
//		}
	}

	public List<String> topKFrequent(String[] words, int k) {
		List<String> ans = new ArrayList<String>();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		PriorityQueue<Node> queue = new PriorityQueue<Node>(new MyCom());
		for (int i = 0; i < words.length; i++) {
			map.put(words[i], map.getOrDefault(words[i], 0) + 1);
		}
		for (String key : map.keySet()) {
			queue.add(new Node(key, map.get(key)));
		}
		for (int i = 0; i < k; i++) {
			ans.add(queue.poll().value);
		}
		return ans;
	}

	class MyCom implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			return o1.count - o2.count == 0 ? o2.value.compareTo(o1.value) : o2.count - o1.count;
		}

	}

	class Node {
		String value;
		int count;

		public Node(String value, int count) {
			this.value = value;
			this.count = count;
		}
	}
}
