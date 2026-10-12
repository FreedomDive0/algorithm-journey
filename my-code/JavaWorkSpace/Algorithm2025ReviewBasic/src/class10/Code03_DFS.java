package class10;

import java.util.HashSet;
import java.util.Stack;

public class Code03_DFS {
	public static void dfs(Node node) {
		if (node == null) {
			return;
		}
		Stack<Node> stack = new Stack<Node>();
		HashSet<Node> set = new HashSet<Node>();
		stack.push(node);
		System.out.println(node.Value);
		set.add(node);
		while (!stack.isEmpty()) {
			Node cur = stack.pop();
			for (Node next : cur.nexts) {
				if (!set.contains(next)) {
					set.add(next);
					stack.push(cur);
					System.out.println(next.Value);
					stack.push(next);
					break;
				}
			}
		}
	}
}
