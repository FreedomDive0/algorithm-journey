package 图;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public class Problem_0207_Course_Schedule {
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

	public boolean canFinish(int numCourses, int[][] prerequisites) {
		if (prerequisites == null || prerequisites.length == 0) {
			return true;
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
		for (Node course : map.values()) {
			if (course.in == 0) {
				queue.add(course);
			}
		}
		int need = map.size();// 不是所有的课都需要前置课程
		int count = 0;
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			count++;
			for (Node next : cur.nexts) {
				if (--next.in == 0) {
					queue.add(next);
				}
			}
		}
		return count == need;
	}
}
