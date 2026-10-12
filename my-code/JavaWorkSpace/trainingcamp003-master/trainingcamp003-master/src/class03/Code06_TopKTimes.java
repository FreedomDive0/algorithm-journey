package class03;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class Code06_TopKTimes {

	public static class Node {
		public String value;
		public int times;

		public Node(String s, int t) {
			value = s;
			times = t;
		}
	}

	public static class NodeComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			return o1.times - o2.times;
		}

	}

	public static void printTopKAndRank(String[] arr, int topK) {
		if (arr == null || arr.length == 0 || topK < 1 || topK > arr.length) {
			return;
		}
		HashMap<String, Integer> map = new HashMap<>();
		for (String str : arr) {
			if (!map.containsKey(str)) {
				map.put(str, 1);
			} else {
				map.put(str, map.get(str) + 1);
			}
		}
		topK = Math.min(arr.length, topK);
		PriorityQueue<Node> heap = new PriorityQueue<>(new NodeComparator());
		for (Entry<String, Integer> entry : map.entrySet()) {
			Node cur = new Node(entry.getKey(), entry.getValue());
			if (heap.size() < topK) {
				heap.add(cur);
			} else {
				if (heap.peek().times < cur.times) {
					heap.poll();
					heap.add(cur);
				}
			}
		}
		while (!heap.isEmpty()) {
			System.out.println(heap.poll().value);
		}
	}

	public static String[] generateRandomArray(int len, int max) {
		String[] res = new String[len];
		for (int i = 0; i != len; i++) {
			res[i] = String.valueOf((int) (Math.random() * (max + 1)));
		}
		return res;
	}

	public static void printArray(String[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// 测试
	public static List<String> topK(String[] strs, int K) {
		if (strs == null || strs.length < 1 || K < 1 || K > strs.length) {
			return null;
		}
		ArrayList<String> ans = new ArrayList<>();
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
			} else if (heap.peek().times < cur.times) {// 堆满后 以堆顶为门槛淘汰其他String
				heap.poll();
				heap.add(cur);
			}
		}
		while (!heap.isEmpty()) {
			ans.add(heap.poll().value);
		}
		for (String str : ans) {
			System.out.println(str);
		}
		return ans;
	}

	// 测试
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

	public static void main(String[] args) {
		String[] arr1 = { "A", "B", "A", "C", "A", "C", "B", "B", "K" };
		TopKStr(arr1, 2);
		topK(arr1, 2);
		String[] arr2 = generateRandomArray(50, 10);
		int topK = 3;
		printArray(arr2);
		printTopKAndRank(arr2, topK);
		TopKStr(arr2, topK);
	}
}