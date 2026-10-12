package class10;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

//no negative weight
public class Code06_Dijkstra {
	public static HashMap<Node, Integer> dijkstra1(Node from) {
		HashMap<Node, Integer> disMap = new HashMap<Node, Integer>();
		HashSet<Node> visited = new HashSet<Node>();
		disMap.put(from, 0);
		Node node = getMinDisWithoutVisited(disMap, visited);
		while (node != null) {
			for (Edge edge : node.edges) {// 更新距离
				if (!disMap.containsKey(edge.to)) {
					disMap.put(edge.to, edge.weight + disMap.get(node));
				} else {
					disMap.put(edge.to, Math.min(disMap.get(edge.to), edge.weight + disMap.get(node)));
				}
			}
			visited.add(node);
			node = getMinDisWithoutVisited(disMap, visited);
		}
		return disMap;
	}

	public static Node getMinDisWithoutVisited(HashMap<Node, Integer> disMap, HashSet<Node> visited) {
		Node min = null;
		int minDis = Integer.MAX_VALUE;
		for (Entry<Node, Integer> entry : disMap.entrySet()) {
			if (!visited.contains(entry.getKey()) && entry.getValue() < minDis) {
				min = entry.getKey();
				minDis = entry.getValue();
			}
		}
		return min;
	}

	public static class NodeRecord {
		public Node node;
		public int dis;

		public NodeRecord(Node node, int dis) {
			this.node = node;
			this.dis = dis;
		}

	}

	public static HashMap<Node, Integer> dijkstra2(Node from, int size) {
		NodeHeap heap = new NodeHeap(size);
		heap.AddOrUpdateOrIgnore(from, 0);
		HashMap<Node, Integer> disMap = new HashMap<Node, Integer>();
		while (!heap.isEmpty()) {
			NodeRecord nodeRecord = heap.pop();
			disMap.put(nodeRecord.node, nodeRecord.dis);
			for (Edge edge : nodeRecord.node.edges) {
				heap.AddOrUpdateOrIgnore(edge.to, disMap.get(nodeRecord.node) + edge.weight);
			}
		}
		return disMap;
	}

	public static class NodeHeap {
		public Node[] nodes;
		public HashMap<Node, Integer> indexMap;
		public HashMap<Node, Integer> disMap;
		public int size;
		public int limit;

		public NodeHeap(int limit) {
			this.limit = limit;
			nodes = new Node[this.limit];
			indexMap = new HashMap<Node, Integer>();
			disMap = new HashMap<Node, Integer>();
			size = 0;
		}

		public void AddOrUpdateOrIgnore(Node node, int dis) {
			if (inHeap(node)) {// 更新
				disMap.put(node, Math.min(disMap.get(node), dis));
				heapInsert(indexMap.get(node));
			}
			if (!isEnter(node)) {// 添加
				nodes[size] = node;
				indexMap.put(node, size);
				disMap.put(node, dis);
				heapInsert(indexMap.get(node));
				size++;
			}
			// 不在堆上 添加过 忽略
		}

		public NodeRecord pop() {
			if (isEmpty()) {
				throw new RuntimeException("heap is empty");
			}
			Node res = nodes[0];
			swap(--size, 0);
			heapify(0);
			indexMap.put(res, -1);
			return new NodeRecord(res, disMap.get(res));
		}

		private void heapify(int idx) {
			int leftSon = idx * 2 + 1;
			while (leftSon < size) {
				int smallest = leftSon + 1 < size && disMap.get(nodes[leftSon + 1]) < disMap.get(nodes[leftSon])
						? leftSon + 1
						: leftSon;
				smallest = disMap.get(nodes[smallest]) < disMap.get(nodes[idx]) ? smallest : idx;
				if (smallest == idx) {
					return;
				}
				swap(idx, smallest);
				idx = smallest;
				leftSon = idx * 2 + 1;
			}
		}

		private void heapInsert(int idx) {
			while (disMap.get(nodes[idx]) < disMap.get(nodes[(idx - 1) / 2])) {
				swap(idx, (idx - 1) / 2);
				idx = (idx - 1) / 2;// 下标需要覆盖
			}
		}

		private void swap(int i, int j) {
			indexMap.put(nodes[i], j);
			indexMap.put(nodes[j], i);
			Node tmp = nodes[i];// 交换对应数组
			nodes[i] = nodes[j];
			nodes[j] = tmp;
		}

		private boolean isEnter(Node node) {
			return indexMap.containsKey(node);
		}

		private boolean inHeap(Node node) {
			return indexMap.containsKey(node) && indexMap.get(node) != -1;
		}

		public boolean isEmpty() {
			return size == 0;
		}
	}
}
