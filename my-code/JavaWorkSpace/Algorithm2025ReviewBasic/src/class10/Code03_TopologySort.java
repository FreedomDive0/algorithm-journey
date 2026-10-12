package class10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Code03_TopologySort {
	// directed graph and no loop
	public static List<Node> sortedTopology(Graph graph) {
		List<Node> res = new ArrayList<Node>();
		if (graph == null) {
			return res;
		}
		HashMap<Node, Integer> inMap = new HashMap<Node, Integer>();
		List<Node> zeroInList = new ArrayList<Node>();
		for (Node node : graph.nodes.values()) {
			inMap.put(node, node.in);
			if (node.in == 0) {
				zeroInList.add(node);
			}
		}
		while (!zeroInList.isEmpty()) {// zeroInList为零时 已经遍历完毕
			for (Node cur : zeroInList) {
				res.add(cur);
				for (Node next : cur.nexts) {
					inMap.put(next, inMap.get(next) - 1);// 已经为零的节点不会造成干扰 因为为零的节点不会再次被减
					if (inMap.get(next) == 0) {
						zeroInList.add(next);
					}
				}
			}
		}
		return res;
	}
}
