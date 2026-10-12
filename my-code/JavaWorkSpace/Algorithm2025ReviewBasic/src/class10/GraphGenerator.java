package class10;

public class GraphGenerator {
	// matrix 所有的边
	// N*3 的矩阵
	// [weight, from节点上面的值，to节点上面的值]
	public static Graph createGraph(Integer[][] matrix) {
		Graph graph = new Graph();
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return graph;
		}
		for (Integer[] data : matrix) {
			int weight = data[0];
			int from = data[1];
			int to = data[2];
			if (!graph.nodes.containsKey(from)) {
				graph.nodes.put(from, new Node(from));
			}
			if (!graph.nodes.containsKey(to)) {
				graph.nodes.put(to, new Node(to));
			}
			Node fromNode = graph.nodes.get(from);
			Node toNode = graph.nodes.get(to);
			Edge edge = new Edge(weight, fromNode, toNode);
			fromNode.out++;
			fromNode.nexts.add(toNode);
			fromNode.edges.add(edge);
			toNode.in++;
			toNode.edges.add(edge);
			graph.edges.add(edge);
		}
		return graph;
	}
}
