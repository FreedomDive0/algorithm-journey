package class10;

public class GraphGenerator {
	public static Graph graphGenerator(int[][] g) {
		Graph graph = new Graph();
		for (int i = 0; i < g.length; i++) {
			int weight = g[i][0];
			int from = g[i][1];
			int to = g[i][2];
			if (!graph.nodes.containsKey(from)) {
				graph.nodes.put(from, new Node(from));
			}
			if (!graph.nodes.containsKey(to)) {
				graph.nodes.put(to, new Node(to));
			}
			// 处理图中的点 和 边
			Node fromNode = graph.nodes.get(from);
			Node toNode = graph.nodes.get(to);
			Edge edge = new Edge(weight, fromNode, toNode);
			fromNode.edges.add(edge);
			fromNode.nexts.add(toNode);
			fromNode.out++;
			toNode.in++;
			graph.edges.add(edge);
		}
		return graph;
	}
}
