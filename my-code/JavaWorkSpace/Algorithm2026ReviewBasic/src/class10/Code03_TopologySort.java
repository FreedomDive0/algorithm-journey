package class10;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;

public class Code03_TopologySort {
	// 拓扑排序
	public List<Node> sortedTopology(Graph graph) {
		List<Node> res = new ArrayList<Node>();
		if (graph == null) {
			return res;
		}
		HashMap<Node, Integer> inMap = new HashMap<Node, Integer>();
		Deque<Node> zeroQueue = new ArrayDeque<Node>();
		for (Node node : graph.nodes.values()) {
			inMap.put(node, node.in);
			if (node.in == 0) {
				zeroQueue.offer(node);
			}
		}
		while (!zeroQueue.isEmpty()) {
			Node cur = zeroQueue.poll();
			res.add(cur);
			for (Node next : cur.nexts) {
				inMap.put(next, inMap.get(next) - 1);
				if (inMap.get(next) == 0) {
					zeroQueue.offer(next);
				}
			}
		}
		return res;
	}
}
