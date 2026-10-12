package class10;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

//no negative weight
public class Code06_Dijkstra {
	// 出发点到其他所有点的最短距离
	// 每次选出一个距离最近并没有更细过的点 去更新与之相关点的距离
	public static HashMap<Node, Integer> dijkstra1(Node from) {
		HashMap<Node, Integer> disMap = new HashMap<Node, Integer>();
		if (from == null) {
			return disMap;
		}
		disMap.put(from, 0);
		HashSet<Node> selectedNode = new HashSet<Node>();
		Node minNode = getMinNode(disMap, selectedNode);
		while (minNode != null) {
			selectedNode.add(minNode);
			for (Edge edge : minNode.edges) {
				Node toNode = edge.to;
				int curDis = disMap.get(minNode);
				if (!disMap.containsKey(toNode)) {
					disMap.put(toNode, curDis + edge.weight);
				} else {
					disMap.put(toNode, Math.min(curDis + edge.weight, disMap.get(toNode)));
				}
			}
			minNode = getMinNode(disMap, selectedNode);
		}
		return disMap;
	}

	// 遍历找最小
	public static Node getMinNode(HashMap<Node, Integer> disMap, HashSet<Node> selectedNode) {
		int minDis = Integer.MAX_VALUE;
		Node minNode = null;
		for (Entry<Node, Integer> entry : disMap.entrySet()) {
			if (!selectedNode.contains(entry.getKey()) && entry.getValue() < minDis) {
				minDis = entry.getValue();
				minNode = entry.getKey();
			}
		}
		return minNode;
	}

	// 改进版使用堆 代替遍历查找
	public static class NodeRecord {
		public Node node;
		public int dis;

		public NodeRecord(Node node, int dis) {
			this.node = node;
			this.dis = dis;
		}

	}

	public static class NodeRecordHeap {
		Node[] nodes;
		HashMap<Node, Integer> indexMap;
		HashMap<Node, Integer> disMap;
		int size;

		public NodeRecordHeap(int size) {
			nodes = new Node[size];
			indexMap = new HashMap<Node, Integer>();
			disMap = new HashMap<Node, Integer>();
			size = 0;
		}

		public void addOrUpdateOrIgnore(Node node, int dis) {
			if (!indexMap.containsKey(node)) {// 未进入堆
				nodes[size] = node;
				indexMap.put(node, size);
				disMap.put(node, dis);
				heapInsert(size++);
			}
			if (indexMap.containsKey(node) && indexMap.get(node) != -1) {// 进入过堆 但未弹出
				disMap.put(node, Math.min(dis, disMap.get(node)));
				heapInsert(indexMap.get(node));
			}
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public NodeRecord pop() {
			if (isEmpty()) {
				return null;
			}
			NodeRecord res = new NodeRecord(nodes[0], disMap.get(nodes[0]));
			swap(0, --size);
			nodes[size] = null;// 删除记录
			disMap.remove(res.node);
			heapify(0, size);
			indexMap.put(res.node, -1);
			return res;
		}

		private void heapify(int idx, int size) {
			int left = idx * 2 + 1;
			while (left < size) {
				int smallest = left + 1 < size && disMap.get(nodes[left + 1]) < disMap.get(nodes[left]) ? left + 1
						: left;// 小根堆
				smallest = disMap.get(nodes[smallest]) < disMap.get(nodes[idx]) ? smallest : idx;
				if (idx == smallest) {
					break;
				}
				swap(idx, smallest);
				idx = smallest;
				left = idx * 2 + 1;
			}
		}

		private void heapInsert(int idx) {
			while (disMap.get(nodes[idx]) < disMap.get(nodes[(idx - 1) / 2])) {
				swap(idx, (idx - 1) / 2);
				idx = (idx - 1) / 2;
			}
		}

		private void swap(int idx1, int idx2) {
			indexMap.put(nodes[idx1], idx2);
			indexMap.put(nodes[idx2], idx1);
			Node tmp = nodes[idx1];
			nodes[idx1] = nodes[idx2];
			nodes[idx2] = tmp;
		}
	}

	public static HashMap<Node, Integer> dijkstra2(Node from, int size) {
		HashMap<Node, Integer> res = new HashMap<Node, Integer>();
		res.put(from, 0);
		NodeRecordHeap heap = new NodeRecordHeap(size);
		heap.addOrUpdateOrIgnore(from, 0);
		while (!heap.isEmpty()) {
			NodeRecord record = heap.pop();
			Node node = record.node;
			int dis = record.dis;
			res.put(node, dis);// 添加记录
			for (Edge edge : node.edges) {// 更新其他记录
				heap.addOrUpdateOrIgnore(edge.to, dis + edge.weight);
			}
		}
		return res;
	}

	public static int networkDelayTime(int[][] times, int n, int k) {
		if (times == null) {
			return 0;
		}
		HashMap<Integer, Node> nodeMap = new HashMap<Integer, Node>();
		for (int i = 0; i < times.length; i++) {
			if (!nodeMap.containsKey(times[i][0])) {
				nodeMap.put(times[i][0], new Node(times[i][0]));
			}
			if (!nodeMap.containsKey(times[i][1])) {
				nodeMap.put(times[i][1], new Node(times[i][1]));
			}
			Node from = nodeMap.get(times[i][0]);
			Node to = nodeMap.get(times[i][1]);
			from.nexts.add(to);
			from.edges.add(new Edge(times[i][2], from, to));
		}
		HashMap<Node, Integer> disMap = dijkstra1(nodeMap.get(k));
		if (disMap.size() < n) {
			return -1;
		} else {
			int max = -1;
			for (int dis : disMap.values()) {
				max = Math.max(max, dis);
			}
			return max;
		}
	}
}
