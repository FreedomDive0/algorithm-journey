package 图;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public class Problem_0210_Course_Schedule_II {
	class Node {
		int name;
		int in;
		ArrayList<Node> nexts;

		public Node(int name) {
			this.name = name;
			in = 0;
			nexts = new ArrayList<Node>();
		}

	}

	public int[] findOrder(int numCourses, int[][] prerequisites) {
		int[] ans = new int[numCourses];
		for (int i = 0; i < numCourses; i++) {
			ans[i] = i;
		}
		if (prerequisites == null || prerequisites.length == 0) {
			return ans;
		}
		HashMap<Integer, Node> map = new HashMap<Integer, Node>();
		for (int[] courses : prerequisites) {
			if (!map.containsKey(courses[0])) {
				map.put(courses[0], new Node(courses[0]));
			}
			if (!map.containsKey(courses[1])) {
				map.put(courses[1], new Node(courses[1]));
			}
			map.get(courses[0]).in++;
			map.get(courses[1]).nexts.add(map.get(courses[0]));
		}
		Queue<Node> queue = new ArrayDeque<Node>();
		int index = 0;
		for (int i = 0; i < numCourses; i++) {
			if (!map.containsKey(i)) {
				ans[index++] = i;
			} else {
				if (map.get(i).in == 0) {
					queue.add(map.get(i));
				}
			}
		}
		int need = map.size();// 不是所有的课都需要前置课程
		int count = 0;
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			count++;
			ans[index++] = cur.name;
			for (Node next : cur.nexts) {
				if (--next.in == 0) {
					queue.add(next);
				}
			}
		}
		return count == need ? ans : new int[0];
	}
}
