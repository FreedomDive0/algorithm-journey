package class10;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class Review {
	// 并查集
	public static class User {
		String a;
		String b;
		String c;

		public User(String a, String b, String c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}

	}

//	public static class Node<V> {
//		V value;
//
//		public Node(V value) {
//			this.value = value;
//		}
//	}
//
//	public static class UnionSet<V> {
//		HashMap<V, Node<V>> nodes;// 值对应的点
//		HashMap<Node<V>, Node<V>> parents;// 点对应的父亲节点
//		HashMap<Node<V>, Integer> nodesSize;
//
//		public UnionSet(List<V> list) {
//			for (V value : list) {
//				Node<V> node = new Node<V>(value);
//				nodes.put(value, node);
//				parents.put(node, node);
//				nodesSize.put(node, 1);
//			}
//		}
//
//		public boolean isSameSet(V a, V b) {
//			if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
//				return false;
//			}
//			return findFather(a) == findFather(b);
//		}
//
//		public Node<V> findFather(V value) {// 找到祖宗点
//			Node<V> cur = nodes.get(value);
//			Stack<Node<V>> stack = new Stack<Node<V>>();
//			while (cur != parents.get(cur)) {
//				stack.add(cur);
//				cur = parents.get(cur);
//			}
//			while (!stack.isEmpty()) {// 优化以后的查询 扁平化处理
//				Node<V> node = stack.pop();
//				parents.put(node, cur);
//			}
//			return cur;
//		}
//
//		public void union(V a, V b) {
//			if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
//				return;
//			}
//			Node<V> aHead = findFather(a);
//			Node<V> bHead = findFather(b);
//			if (aHead != bHead) {
//				Node<V> big = nodesSize.get(aHead) > nodesSize.get(bHead) ? aHead : bHead;
//				Node<V> small = big == aHead ? bHead : aHead;
//				parents.put(small, big);
//				nodesSize.put(big, nodesSize.get(big) + nodesSize.get(small));
//				nodesSize.remove(small);
//			}
//		}
//	}
//
//	public static int UserNum(User[] users) {
//		if (users == null || users.length < 1) {
//			return 0;
//		}
//		ArrayList<Node> list = new ArrayList<>();
//		for (int i = 0; i < users.length; i++) {
//			list.add(new Node<User>(new User(users[i].a, users[i].b, users[i].c)));
//		}
//		UnionSet<Node> union = new UnionSet<>(list);
//		HashMap<String, Node> mapA = new HashMap<>();
//		HashMap<String, Node> mapB = new HashMap<>();
//		HashMap<String, Node> mapC = new HashMap<>();
//		for (User user : users) {
//			if (mapA.containsKey(user.a)) {
//				union.union(union.nodes.get(user), mapA.get(user.a));
//			} else {
//				mapA.put(user.a, union.nodes.get(user));
//			}
//			if (mapB.containsKey(user.b)) {
//				union.union(union.nodes.get(user), mapB.get(user.b));
//			} else {
//				mapB.put(user.b, union.nodes.get(user));
//			}
//			if (mapC.containsKey(user.c)) {
//				union.union(union.nodes.get(user), mapC.get(user.a));
//			} else {
//				mapC.put(user.c, union.nodes.get(user));
//			}
//		}
//		return union.nodesSize.size();
//	}

	// 格式转换
//	public Graph generator(int[][] edges) {
//		if (edges == null) {
//			return null;
//		}
//		Graph graph = new Graph();
//		for (int i = 0; i < edges.length; i++) {
//			int weight = edges[i][0];
//			int from = edges[i][1];
//			int to = edges[i][2];
//			if (!graph.nodes.containsKey(from)) {
//				graph.nodes.put(from, new Node(from));
//			}
//			if (!graph.nodes.containsKey(to)) {
//				graph.nodes.put(to, new Node(to));
//			}
//			Node fromNode = graph.nodes.get(from);
//			Node toNode = graph.nodes.get(to);
//			Edge edge = new Edge(weight, fromNode, toNode);
//			fromNode.out++;
//			toNode.in++;
//			fromNode.nexts.add(toNode);
//			fromNode.edges.add(edge);
//			graph.edges.add(edge);
//		}
//		return graph;
//	}
//
//	// 1.宽度遍历
//	public static void BFS(Node node) {
//		if (node == null) {
//			return;
//		}
//		Queue<Node> queue = new LinkedList<Node>();
//		HashSet<Node> set = new HashSet<>();
//		queue.add(node);
//		set.add(node);
//		while (!queue.isEmpty()) {
//			Node cur = queue.poll();
//			System.out.println(cur.value + " ");
//			for (Node n : cur.nexts) {
//				if (!set.contains(n)) {
//					queue.add(n);
//					set.add(n);
//				}
//			}
//		}
//	}
//
//	// 2.深度遍历
//	public static void DFS(Node node) {
//		if (node == null) {
//			return;
//		}
//		Stack<Node> stack = new Stack<>();// 栈中存储深度路径
//		HashSet<Node> set = new HashSet<>();
//		stack.add(node);
//		set.add(node);
//		System.out.println(node.value + " ");
//		while (!stack.isEmpty()) {
//			Node cur = stack.pop();
//			for (Node n : cur.nexts) {
//				if (!set.contains(n)) {
//					stack.add(cur);
//					stack.add(n);
//					set.add(n);
//					System.out.println(n.value + " ");
//					break;
//				}
//			}
//		}
//	}
//
//	// 3.拓扑排序
//	public static List<Node> TopologySort(Graph graph) {
//		if (graph == null) {
//			return null;
//		}
//		ArrayList<Node> res = new ArrayList<>();
//		HashMap<Node, Integer> inMap = new HashMap<>();
//		Queue<Node> queueZero = new LinkedList<Node>();
//		for (Node cur : graph.nodes.values()) {
//			inMap.put(cur, cur.in);
//			if (cur.in == 0) {
//				queueZero.add(cur);
//			}
//		}
//		while (!queueZero.isEmpty()) {
//			Node cur = queueZero.poll();
//			res.add(cur);
//			for (Node next : cur.nexts) {
//				inMap.put(next, inMap.get(cur) - 1);// 已加入的节点 in会变为负数不会影响结果
//				if (inMap.get(next) == 0) {
//					queueZero.add(next);
//				}
//			}
//		}
//		return res;
//	}

	// 最小生成树
	// Kruskal
	public class UnionSet {
		HashMap<Node, Node> parents;// 点对应的父亲节点
		HashMap<Node, Integer> nodesSize;

		public UnionSet(Collection<Node> collection) {
			for (Node node : collection) {
				parents.put(node, node);
				nodesSize.put(node, 1);
			}
		}

		public boolean isSameSet(Node a, Node b) {
			return findFather(a) == findFather(b);
		}

		public Node findFather(Node node) {// 找到祖宗点
			Node cur = node;
			Stack<Node> stack = new Stack<Node>();
			while (cur != parents.get(cur)) {
				stack.add(cur);
				cur = parents.get(cur);
			}
			while (!stack.isEmpty()) {// 优化以后的查询 扁平化处理
				Node n = stack.pop();
				parents.put(n, cur);
			}
			return cur;
		}

		public void union(Node a, Node b) {
			Node aHead = findFather(a);
			Node bHead = findFather(b);
			if (aHead != bHead) {
				Node big = nodesSize.get(aHead) > nodesSize.get(bHead) ? aHead : bHead;
				Node small = big == aHead ? bHead : aHead;
				parents.put(small, big);
				nodesSize.put(big, nodesSize.get(big) + nodesSize.get(small));
				nodesSize.remove(small);
			}
		}
	}

	public class EdgeCom implements Comparator<Edge> {

		@Override
		public int compare(Edge o1, Edge o2) {
			// TODO Auto-generated method stub
			return o1.weight - o2.weight;
		}

	}

	public Set<Edge> Kruskal(Graph graph) {
		if (graph == null) {
			return null;
		}
		HashSet<Edge> res = new HashSet<>();
		UnionSet nodesSet = new UnionSet(graph.nodes.values());
		PriorityQueue<Edge> edgeQueue = new PriorityQueue<>(new EdgeCom());
		for (Edge edge : graph.edges) {
			edgeQueue.add(edge);
		}
		while (!edgeQueue.isEmpty()) {
			Edge edge = edgeQueue.poll();
			if (!nodesSet.isSameSet(edge.from, edge.to)) {
				res.add(edge);
				nodesSet.union(edge.from, edge.to);
			}
		}
		return res;
	}

	// Prim
	public Set<Edge> Prim(Graph graph) {
		if (graph == null) {
			return null;
		}
		PriorityQueue<Edge> edgeQueue = new PriorityQueue<>(new EdgeCom());
		HashSet<Node> nodeSet = new HashSet<>();
		HashSet<Edge> edgeSet = new HashSet<>();
		HashSet<Edge> res = new HashSet<>();
		for (Node node : graph.nodes.values()) {
			for (Edge edge : node.edges) {
				if (!edgeSet.contains(edge)) {
					edgeQueue.add(edge);
					edgeSet.add(edge);
				}
			}
			while (!edgeQueue.isEmpty()) {
				Edge curEdge = edgeQueue.poll();
				if (!nodeSet.contains(curEdge.to)) {
					nodeSet.add(curEdge.to);
					res.add(curEdge);
					for (Edge e : curEdge.to.edges) {
						if (!edgeSet.contains(e)) {
							edgeQueue.add(e);
							edgeSet.add(e);
						}
					}
				}
			}
			break;
		}
		return res;
	}

	// Dijkstra
	// 1.0
	public static HashMap<Node, Integer> Dijkstra1(Graph graph, Node from) {
		if (from == null || graph == null) {
			return null;
		}
		HashMap<Node, Integer> distanceMap = new HashMap<>();
		HashSet<Node> used = new HashSet<>();
		distanceMap.put(from, 0);
		Node cur = null;
		while ((cur = FindUnusedMin(distanceMap, used)) != null) {
			for (Edge edge : cur.edges) {// 更新边
				if (distanceMap.containsKey(edge.to)) {
					distanceMap.put(edge.to, Math.min(distanceMap.get(edge.to), distanceMap.get(cur) + edge.weight));
				} else {
					distanceMap.put(edge.to, distanceMap.get(cur) + edge.weight);
				}
			}
			used.add(cur);
		}
		return distanceMap;
	}

	public static Node FindUnusedMin(HashMap<Node, Integer> disMap, HashSet<Node> used) {
		int min = Integer.MIN_VALUE;
		Node res = null;
		for (Entry<Node, Integer> entry : disMap.entrySet()) {
			if (!used.contains(entry.getKey()) && entry.getValue() < min) {
				min = entry.getValue();
				res = entry.getKey();
			}
		}
		return res;
	}

	// 2.0
	public static class NodeRecord {
		Node node;
		int dis;

		public NodeRecord(Node node, int dis) {
			this.node = node;
			this.dis = dis;
		}

	}

	public static class nodeCom implements Comparator<NodeRecord> {

		@Override
		public int compare(NodeRecord o1, NodeRecord o2) {
			// TODO Auto-generated method stub
			return o1.dis - o2.dis;
		}

	}

	static class Nodeheap {
		int limit;
		int size;
		HashMap<Node, Integer> indexMap;
		NodeRecord[] nodes;

		public Nodeheap(int limit) {
			this.limit = limit;
			this.size = 0;
			this.nodes = new NodeRecord[limit];
			indexMap = new HashMap<>();
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public boolean isFull() {
			return size == limit;
		}

		// 插入
		public void addOrUpdateOrIgnore(NodeRecord record) {
			if (indexMap.get(record.node) == null) {
				nodes[size] = record;
				heapInsert(size++);
			} else if (inHeap(record)) {
				int index = indexMap.get(record.node);
				if (nodes[index].dis > nodes[indexMap.get(record.node)].dis + record.dis) {
					heapInsert(index);// 值只可能变小
				}
			}

		}

		// 是否在堆中
		public boolean inHeap(NodeRecord record) {
			return indexMap.get(record.node) != null && indexMap.get(record.node) != -1;
		}

		// 优化
		public void heapInsert(int index) {
			while (nodes[index].dis > nodes[(index - 1) / 2].dis) {// ∵(0-1)/2==0所以数组不会越界
				swap(nodes, index, (index - 1) / 2, indexMap);
				index = (index - 1) / 2;
			}
		}

		// 删除
		public NodeRecord pop() {
			if (isEmpty()) {
				throw new RuntimeException("堆中没有数据");
			}
			swap(nodes, --size, 0, indexMap);
			indexMap.put(nodes[size].node, -1);
			heapify(0, size);
			return nodes[size];
		}

		public void heapify(int i, int size) {
			int index = i;
			int leftSon = index * 2 + 1;
			while (leftSon < size) {
				int maxIndex = leftSon + 1 < size ? nodes[leftSon].dis > nodes[leftSon + 1].dis ? leftSon : leftSon + 1
						: leftSon;
				maxIndex = nodes[maxIndex].dis > nodes[index].dis ? maxIndex : index;
				if (maxIndex == index) {
					return;
				}
				swap(nodes, index, maxIndex, indexMap);
				index = maxIndex;
				leftSon = index * 2 + 1;
			}
		}
	}

	public static void swap(NodeRecord[] arr, int i, int j, HashMap<Node, Integer> indexMap) {
		if (arr == null || i == j) {
			return;
		}
		indexMap.put(arr[i].node, j);
		indexMap.put(arr[j].node, i);
		NodeRecord tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public static HashMap<Node, Integer> Dijkstra2(Graph graph, Node from) {
		if (from == null || graph == null) {
			return null;
		}
		HashMap<Node, Integer> distanceMap = new HashMap<>();
		Nodeheap heap = new Nodeheap(graph.nodes.size());
		heap.addOrUpdateOrIgnore(new NodeRecord(from, 0));
		distanceMap.put(from, 0);
		while (!heap.isEmpty()) {
			NodeRecord record = heap.pop();
			for (Edge edge : record.node.edges) {
				heap.addOrUpdateOrIgnore(new NodeRecord(edge.to, edge.weight));
			}
			distanceMap.put(record.node, record.dis);
		}
		return distanceMap;
	}
}
