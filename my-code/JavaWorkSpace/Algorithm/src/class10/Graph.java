package class10;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {
	public HashMap<Integer, Node> nodes;
	public HashSet<Edge> edges;

	public Graph() {
		this.nodes = new HashMap<Integer, Node>();
		this.edges = new HashSet<Edge>();
	}

}
