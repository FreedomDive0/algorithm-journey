package class10;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;

public class Code02_DFS {
	// 图的深度优先遍历
	public void depthFirstSearch(Node node) {
		if (node == null)
			return;
		Deque<Node> stack = new ArrayDeque<Node>();
		HashSet<Node> arrived = new HashSet<Node>();
		stack.push(node);
		arrived.add(node);
		System.out.println(node.value);
		while (!stack.isEmpty()) {
			Node cur = stack.pop();
			for (Node next : cur.nexts) {
				if (!arrived.contains(next)) {
					stack.push(cur);
					stack.push(next);
					arrived.add(next);
					System.out.println(next.value);
					break;
				}
			}
		}
	}
}
