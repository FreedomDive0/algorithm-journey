package 图的广度优先搜索;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;

public class Problem_0127_Word_Ladder {
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		wordList.add(beginWord);
		HashMap<String, ArrayList<String>> nexts = getNexts(wordList);
		HashSet<String> used = new HashSet<String>();
		Queue<String> queue = new ArrayDeque<String>();
		queue.add(beginWord);
		int step = 1;
		used.add(beginWord);
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				String cur = queue.poll();
				for (String next : nexts.get(cur)) {
					if (next.equals(endWord)) {
						return step + 1;
					}
					if (!used.contains(next)) {
						queue.add(next);
					}
				}
				used.add(cur);
			}
			step++;
		}
		return 0;
	}

	public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
		wordList.add(beginWord);
		HashMap<String, ArrayList<String>> nexts = getNexts(wordList);
		HashMap<String, Integer> stepMap = new HashMap<String, Integer>();
		HashSet<String> used = new HashSet<String>();
		Queue<String> queue = new ArrayDeque<String>();
		queue.add(beginWord);
		stepMap.put(beginWord, 0);
		used.add(beginWord);
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				String cur = queue.poll();
				Integer step = stepMap.get(cur);
				for (String next : nexts.get(cur)) {
					if (next.equals(endWord)) {
						return step + 1;
					}
					if (!used.contains(next)) {
						stepMap.put(next, step + 1);
						queue.add(next);
					}
				}
				used.add(cur);
			}
		}
		return 0;
	}

	public HashMap<String, ArrayList<String>> getNexts(List<String> wordList) {
		HashMap<String, ArrayList<String>> res = new HashMap<>();
		HashSet<String> set = new HashSet<String>(wordList);
		for (String word : wordList) {
			res.put(word, getNext(word, set));
		}
		return res;
	}

	public ArrayList<String> getNext(String word, HashSet<String> set) {
		char[] str = word.toCharArray();
		ArrayList<String> next = new ArrayList<String>();
		for (char c = 'a'; c <= 'z'; c++) {
			for (int i = 0; i < str.length; i++) {
				if (c != str[i]) {
					char tmp = str[i];
					str[i] = c;
					if (set.contains(String.valueOf(str))) {// 注意字符数组转换String
						next.add(String.valueOf(str));
					}
					str[i] = tmp;
				}
			}
		}
		return next;
	}
}
