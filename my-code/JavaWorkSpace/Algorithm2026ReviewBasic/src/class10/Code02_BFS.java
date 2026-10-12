package class10;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;

public class Code02_BFS {
	// 宽度优先遍历
	public static void BreathFirstSearch(Node node) {
		if (node == null)
			return;
		Deque<Node> queue = new ArrayDeque<Node>();
		HashSet<Node> arrived = new HashSet<Node>();
		queue.offer(node);
		arrived.add(node);
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			System.out.println(cur.value);
			for (Node next : cur.nexts) {
				if (!arrived.contains(next)) {
					queue.offer(next);
					arrived.add(next);
				}
			}
		}
	}
}
