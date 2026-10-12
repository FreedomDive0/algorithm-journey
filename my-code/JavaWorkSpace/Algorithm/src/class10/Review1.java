package class10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Review1 {
	// 1.并查集 Kruskal方法使用特殊版 原版在UnionFind
	public static class UnionFind {
		HashMap<Node, Node> parentMap;
		HashMap<Node, Integer> sizeMap;

		public UnionFind() {
			this.parentMap = new HashMap<Node, Node>();
			this.sizeMap = new HashMap<Node, Integer>();
		}

		public Node findFather(Node node) {
			Stack<Node> stack = new Stack<Node>();
			while (node != parentMap.get(node)) {
				stack.add(node);
				node = parentMap.get(node);
			}
			while (!stack.isEmpty()) {// 扁平化
				parentMap.put(stack.pop(), node);
			}
			return node;
		}

		public boolean isSameSet(Node n1, Node n2) {
			return findFather(n1) == findFather(n2);
		}

		public void Union(Node n1, Node n2) {
			Node n1F = findFather(n1);
			Node n2F = findFather(n2);
			if (n1F != n2F) {
				Node big = sizeMap.get(n1F) > sizeMap.get(n2F) ? n1F : n2F;
				Node small = big == n1F ? n2F : n1F;
				parentMap.put(small, big);// 小挂大 找得快
				sizeMap.put(big, sizeMap.get(big) + sizeMap.get(small));
				sizeMap.remove(small);
			}
		}

		public void makeSet(Collection<Node> nodes) {
			parentMap.clear();
			sizeMap.clear();
			for (Node n : nodes) {
				parentMap.put(n, n);
				sizeMap.put(n, 1);
			}
		}
	}

	// 2.1 图转换器
	public Graph GraphGenerator(Integer[][] matrix) {
		Graph graph = new Graph();
		for (int i = 0; i < matrix.length; i++) {
			Integer weight = matrix[i][0];
			Integer from = matrix[i][1];
			Integer to = matrix[i][2];
			if (!graph.nodes.containsKey(from)) {
				graph.nodes.put(from, new Node(from));
			}
			if (!graph.nodes.containsKey(to)) {
				graph.nodes.put(to, new Node(to));
			}
			Node fromNode = graph.nodes.get(from);
			Node toNode = graph.nodes.get(to);
			Edge edge = new Edge(weight, toNode, fromNode);
			fromNode.out++;
			toNode.in++;
			fromNode.nexts.add(toNode);
			fromNode.edges.add(edge);
			graph.edges.add(edge);
		}
		return graph;
	}

	// 3.宽度优先遍历
	public void BFS(Node node) {// 参数为点
		if (node == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<Node>();
		HashSet<Node> set = new HashSet<Node>();// 图中可能包含环
		queue.add(node);
		set.add(node);
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			System.out.println(cur.value);
			for (Node n : cur.nexts) {
				if (!set.contains(n)) {
					set.add(n);
					queue.add(n);
				}
			}
		}
	}

	// 4.深度优先遍历
	public void DFS(Node node) {
		if (node == null) {
			return;
		}
		Stack<Node> stack = new Stack<Node>();
		HashSet<Node> set = new HashSet<Node>();
		stack.add(node);// 记录深度遍历路径
		set.add(node);
		System.out.println(node.value);
		while (!stack.isEmpty()) {
			Node cur = stack.pop();
			for (Node next : cur.nexts) {
				if (!set.contains(next)) {
					System.out.println(node.value);
					stack.add(cur);
					stack.add(next);
					set.add(next);
					break;
				}
			}
		}
	}

	// 5.拓扑排序
	public List<Node> ToPologySort(Graph graph) {
		if (graph == null) {
			return null;
		}
		ArrayList<Node> res = new ArrayList<Node>();
		Queue<Node> zero = new LinkedList<Node>();
		HashMap<Node, Integer> inMap = new HashMap<Node, Integer>();
		for (Node node : graph.nodes.values()) {// 储存所有点的入度 后期需要修改
			inMap.put(node, node.in);
			if (node.in == 0) {
				zero.add(node);
			}
		}
		while (!zero.isEmpty()) {
			Node cur = zero.poll();
			res.add(cur);
			for (Node n : cur.nexts) {
				inMap.put(n, inMap.get(n) - 1);
				if (inMap.get(n) == 0) {
					zero.add(n);
				}
			}
		}
		return res;
	}

	// 6.1最小生成树kruskal
	public class KComparator implements Comparator<Edge> {

		@Override
		public int compare(Edge o1, Edge o2) {
			return o1.weight - o2.weight;
		}

	}

	public Set<Edge> K(Graph graph) {
		if (graph == null) {
			return null;
		}
		Set<Edge> res = new HashSet<Edge>();
		PriorityQueue<Edge> heap = new PriorityQueue<Edge>(new KComparator());
		UnionFind unionFind = new UnionFind();
		unionFind.makeSet(graph.nodes.values());
		for (Edge edge : graph.edges) {// 所有边加入小根堆
			heap.add(edge);
		}
		while (!heap.isEmpty()) {
			Edge edge = heap.poll();
			if (!unionFind.isSameSet(edge.from, edge.to)) {// 每次选取一个最小的边
				res.add(edge);
				unionFind.Union(edge.from, edge.to);
			}
		}
		return res;
	}

	// 6.2最小生成树 Prim
	public Set<Edge> P(Graph graph) {
		if (graph == null) {
			return null;
		}
		Set<Edge> res = new HashSet<Edge>();
		HashSet<Node> usedNode = new HashSet<Node>();
		HashSet<Edge> usedEdge = new HashSet<Edge>();
		PriorityQueue<Edge> heap = new PriorityQueue<Edge>(new KComparator());
		for (Node node : graph.nodes.values()) {// 防森林
			usedNode.add(node);
			for (Edge edge : node.edges) {// 初始点解锁的边
				if (!usedEdge.contains(edge)) {
					usedEdge.add(edge);
					heap.add(edge);
				}
			}
			while (!heap.isEmpty()) {
				Edge curEdge = heap.poll();
				if (!usedNode.contains(curEdge.to)) {// 找到新点 并且解锁新的边
					res.add(curEdge);
					usedNode.add(curEdge.to);
					for (Edge e : curEdge.to.edges) {
						if (!usedEdge.contains(e)) {
							usedEdge.add(e);
							heap.add(e);
						}
					}
				}
			}
		}
		return res;
	}

	// 7.1Dijkstra 顶点到到其余各点的最短路径问题
	public static HashMap<Node, Integer> dijkstra1(Node X) {
		HashMap<Node, Integer> nodeDistance = new HashMap<Node, Integer>();// 记录顶点到各点的距离
		HashSet<Node> selected = new HashSet<Node>();// 标记已经使用过的点
		nodeDistance.put(X, 0);
		Node minDistanceNode = unselectedAndMinDistance(nodeDistance, selected);// 从已解锁的点中选取距离最小的点
		while (minDistanceNode != null) {
			for (Edge edge : minDistanceNode.edges) {// 选到点后
				if (!nodeDistance.containsKey(edge.to)) {// 解锁新点
					nodeDistance.put(edge.to, edge.weight);
				} else {// 更新旧点距离 if (!selected.contains(edge.to)) 没有必要因为新点到已使用过的点距离不会更小
					nodeDistance.put(edge.to,
							Math.min(nodeDistance.get(edge.from) + edge.weight, nodeDistance.get(edge.to)));
				}
			}
			selected.add(minDistanceNode);// 该点使用完毕
			minDistanceNode = unselectedAndMinDistance(nodeDistance, selected);// 选取新的最小点
		}
		return nodeDistance;
	}

	public static Node unselectedAndMinDistance(HashMap<Node, Integer> nodeDistance, HashSet<Node> selected) {
		Node minDistanceNode = null;
		int minDistance = Integer.MAX_VALUE;
		for (Entry<Node, Integer> entry : nodeDistance.entrySet()) {// 从所有解锁并未使用过的点中选择距离最小的点
			if (!selected.contains(entry.getKey()) && entry.getValue() < minDistance) {
				minDistance = entry.getValue();
				minDistanceNode = entry.getKey();
			}
		}
		return minDistanceNode;
	}

	// 7.2 Dijkstra 堆改进 顶点到到其余各点的最短路径问题
	public static class NodeRecord {
		Node node;
		int distance;

		public NodeRecord(Node node, int distance) {
			this.node = node;
			this.distance = distance;
		}

	}

	public static class MyHeap {
		Node[] nodes;
		HashMap<Node, Integer> indexMap;
		HashMap<Node, Integer> distanceMap;
		int size;

		public MyHeap(int size) {
			this.nodes = new Node[size];
			this.indexMap = new HashMap<Node, Integer>();
			this.distanceMap = new HashMap<Node, Integer>();
			this.size = size;
		}

		public void addOrIgnoreOrUpdate(Node x, int distance) {
			if (!isEnterd(x)) {
				nodes[size] = x;
				indexMap.put(x, size);
				distanceMap.put(x, distance);
				heapInsert(size++);
			}
			if (inHeap(x)) {
				if (distanceMap.get(x) > distance) {
					distanceMap.put(x, distance);
					heapInsert(indexMap.get(x));
				}
			}
		}

		public NodeRecord pop() {
			Node cur = nodes[0];
			int distance = distanceMap.get(cur);
			swap(nodes, 0, size - 1);
			distanceMap.remove(cur);// 之后距离不需要了
			nodes[size - 1] = null;
			indexMap.put(cur, -1);
			heapify(0, --size);
			return new NodeRecord(cur, distance);
		}

		private void heapInsert(int index) {
			while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
				swap(nodes, index, (index - 1) / 2);
				index = (index - 1) / 2;
			}
		}

		private void heapify(int index, int end) {
			int left = index * 2 + 1;
			while (left < end) {
				int min = left + 1 < size
						? distanceMap.get(nodes[left]) < distanceMap.get(nodes[left + 1]) ? left : left + 1
						: left;
				min = distanceMap.get(nodes[min]) < distanceMap.get(nodes[index]) ? min : index;
				if (min == index) {
					break;
				}
				swap(nodes, index, min);
				index = min;// 更新下标
				left = index * 2 + 1;
			}
		}

		public boolean isEmpty() {
			return size == 0;
		}

		private boolean isEnterd(Node node) {
			return indexMap.containsKey(node);
		}

		private boolean inHeap(Node node) {
			return isEnterd(node) && indexMap.get(node) != -1;
		}

		private void swap(Node[] nodes, int i, int j) {
			indexMap.put(nodes[i], j);
			indexMap.put(nodes[j], i);
			Node tmp = nodes[i];
			nodes[i] = nodes[j];
			nodes[j] = tmp;
		}
	}

	public static HashMap<Node, Integer> dijkstra2(Node from) {
		HashMap<Node, Integer> res = new HashMap<Node, Integer>();
		return res;
	}

	public static HashMap<Node, Integer> dijkstra2(Node x, int size) {
		MyHeap heap = new MyHeap(size);// 使用堆不用每次比较边的大小
		heap.addOrIgnoreOrUpdate(x, 0);
		HashMap<Node, Integer> res = new HashMap<Node, Integer>();
		while (!heap.isEmpty()) {
			NodeRecord record = heap.pop();
			Node cur = record.node;
			int distance = record.distance;
			res.put(cur, distance);
			for (Edge edge : cur.edges) {
				heap.addOrIgnoreOrUpdate(edge.to, edge.weight + distance);
			}
		}
		return res;
	}
}
