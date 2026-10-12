package class10;

public class GraphGenerator {
	// matrix 所有的边
	// N*3 的矩阵
	// [weight, from节点上面的值，to节点上面的值]
	public static Graph createGraph(Integer[][] matrix) {
		Graph graph = new Graph();
		if (matrix == null || matrix.length == 0 || matrix[0].length < 3) {
			return graph;
		}
		for (int i = 0; i < matrix.length; i++) {
			int fromIdx = matrix[i][1];
			int toIdx = matrix[i][2];
			int weight = matrix[i][0];
			if (!graph.nodes.containsKey(fromIdx)) {
				graph.nodes.put(fromIdx, new Node(fromIdx));
			}
			if (!graph.nodes.containsKey(toIdx)) {
				graph.nodes.put(toIdx, new Node(toIdx));
			}
			Node from = graph.nodes.get(fromIdx);
			Node to = graph.nodes.get(toIdx);
			Edge edge = new Edge(weight, from, to);
			from.out++;
			from.nexts.add(to);
			from.edges.add(edge);
			to.in++;
			graph.edges.add(edge);

			graph.nodes.put(to.value, to);
		}
		return graph;
	}
}
