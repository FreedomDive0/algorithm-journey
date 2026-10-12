package class10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Review2 {
	// 2.图
	class Node {
		int id;
		int in;
		int out;
		ArrayList<Node> nexts;
		ArrayList<Edge> edges;

		public Node(int id) {
			this.id = id;
			this.in = 0;
			this.out = 0;
			this.nexts = new ArrayList<Node>();
			this.edges = new ArrayList<Edge>();
		}

	}

	class Edge {
		Node from;
		Node to;
		int weight;

		public Edge(Node from, Node to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

	}

	class Graph {
		HashMap<Integer, Node> nodes;
		HashSet<Edge> edges;

		public Graph() {
			this.nodes = new HashMap<Integer, Node>();
			this.edges = new HashSet<Edge>();
		}

	}
}
