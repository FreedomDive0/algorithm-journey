package class03;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class Code06_TopKTimes {
//	public static List<String> topK(String[] strs, int K) {
//		if (strs == null || strs.length < 1 || K < 1 || K > strs.length) {
//			return null;
//		}
//		LinkedList<String> ans = new LinkedList<>();
//		HashMap<String, Integer> numMap = new HashMap<>();
//		for (int i = 0; i < strs.length; i++) {// 构建string和数量map
//			if (numMap.containsKey(strs[i])) {
//				numMap.put(strs[i], numMap.get(strs[i]) + 1);
//			} else {
//				numMap.put(strs[i], 1);
//			}
//		}
//		PriorityQueue<Node> heap = new PriorityQueue<>(new NodeComparator());// 大小为K的堆
//		for (Entry<String, Integer> entry : numMap.entrySet()) {
//			Node cur = new Node(entry.getKey(), entry.getValue());
//			if (heap.size() < K) {// 堆没满
//				heap.add(cur);
//			} else if (heap.peek().times < cur.times) {// 堆满后 以堆顶为门槛淘汰其他String
//				heap.poll();
//				heap.add(cur);
//			}
//		}
//		while (!heap.isEmpty()) {
//			ans.addLast(heap.poll().value);
//		}
////		for (String str : ans) {
////			System.out.println(str);
////		}
//		return ans;
//	}
//
//	public static class NodeComparator implements Comparator<Node> {
//
//		@Override
//		public int compare(Node o1, Node o2) {
//			return o1.times != o2.times ? o1.times - o2.times : o1.value.compareTo(o2.value);
//		}
//
//	}
//
//	public static class Node {
//		String value;
//		int times;
//
//		public Node(String value, int times) {
//			this.value = value;
//			this.times = times;
//		}
//
//	}
	// 含字典序比较
	public static List<String> topKFrequent(String[] strs, int K) {
		if (strs == null || strs.length < 1 || K < 1 || K > strs.length) {
			return null;
		}
		LinkedList<String> ans = new LinkedList<>();
		HashMap<String, Integer> numMap = new HashMap<>();
		for (int i = 0; i < strs.length; i++) {// 构建string和数量map
			if (numMap.containsKey(strs[i])) {
				numMap.put(strs[i], numMap.get(strs[i]) + 1);
			} else {
				numMap.put(strs[i], 1);
			}
		}
		PriorityQueue<Node> heap = new PriorityQueue<>(new NodeComparator());// 大小为K的堆
		for (Entry<String, Integer> entry : numMap.entrySet()) {
			Node cur = new Node(entry.getKey(), entry.getValue());
			if (heap.size() < K) {// 堆没满
				heap.add(cur);
			} else if (cur.compare(heap.peek()) < 0) {// 堆满后 以堆顶为门槛淘汰其他String
				heap.poll();
				heap.add(cur);
			}
		}
		while (!heap.isEmpty()) {
			ans.addFirst(heap.poll().value);
		}
		return ans;
	}

	public static class NodeComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			return o1.times != o2.times ? o1.times - o2.times : o2.value.compareTo(o1.value);
		}

	}

	public static class Node {
		String value;
		int times;

		public Node(String value, int times) {
			this.value = value;
			this.times = times;
		}

		public int compare(Node n) {
			return times != n.times ? n.times - times : value.compareTo(n.value);
		}
	}

	public static void main(String[] args) {
		System.out.println("i".compareTo("love"));
	}
}
