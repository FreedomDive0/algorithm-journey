package class10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Code03_TopologySort {
	public static List<Node> topoSort(Graph graph) {
		ArrayList<Node> res = new ArrayList<Node>();
		HashMap<Node, Integer> inMap = new HashMap<Node, Integer>();
		Queue<Node> zeroInNode = new LinkedList<Node>();
		for (Node node : graph.nodes.values()) {
			inMap.put(node, node.in);
			if (node.in == 0) {
				zeroInNode.add(node);
			}
		}
		while (!zeroInNode.isEmpty()) {
			Node cur = zeroInNode.poll();
			res.add(cur);
			for (Node next : cur.nexts) {
				inMap.put(next, next.in--);// 已经如果队列的点入度会变为负数
				if (next.in == 0) {
					zeroInNode.add(next);
				}
			}
		}
		return res;
	}
}
