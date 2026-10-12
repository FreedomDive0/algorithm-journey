package class10;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class Code04_Kruskal {
	// 最小生成树K算法 Edge
	public static Set<Edge> kruskal(Graph graph) {
		UnionFind<Node> unionFind = new UnionFind<Node>(graph.nodes.values());
		PriorityQueue<Edge> heap = new PriorityQueue<Edge>(new EdgeCom());
		for (Edge edge : graph.edges) {
			heap.add(edge);
		}
		HashSet<Edge> res = new HashSet<Edge>();
		while (!heap.isEmpty()) {
			Edge edge = heap.poll();
			if (!unionFind.isSameSet(edge.from, edge.to)) {
				unionFind.union(edge.from, edge.to);
				res.add(edge);
			}
		}
		return res;
	}

	public static class EdgeCom implements Comparator<Edge> {

		@Override
		public int compare(Edge o1, Edge o2) {
			return o1.weight - o2.weight;
		}

	}

	public static class UnionNode<V> {
		V value;

		public UnionNode(V value) {
			this.value = value;
		}

	}

	public static class UnionFind<V> {
		public HashMap<V, UnionNode<V>> nodeMap;
		public HashMap<UnionNode<V>, UnionNode<V>> parentMap;
		public HashMap<UnionNode<V>, Integer> sizeMap;

		public UnionFind(List<V> list) {
			nodeMap = new HashMap<V, UnionNode<V>>();
			parentMap = new HashMap<UnionNode<V>, UnionNode<V>>();
			sizeMap = new HashMap<UnionNode<V>, Integer>();
			for (V v : list) {
				if (nodeMap.containsKey(v))
					continue;
				UnionNode<V> node = new UnionNode<V>(v);
				nodeMap.put(v, node);
				parentMap.put(node, node);
				sizeMap.put(node, 1);
			}
		}

		public UnionFind(Collection<V> set) {
			nodeMap = new HashMap<V, UnionNode<V>>();
			parentMap = new HashMap<UnionNode<V>, UnionNode<V>>();
			sizeMap = new HashMap<UnionNode<V>, Integer>();
			for (V v : set) {
				if (nodeMap.containsKey(v))
					continue;
				UnionNode<V> node = new UnionNode<V>(v);
				nodeMap.put(v, node);
				parentMap.put(node, node);
				sizeMap.put(node, 1);
			}
		}

		public boolean isSameSet(V a, V b) {
			if (!nodeMap.containsKey(a) || !nodeMap.containsKey(b))
				return false;
			return findFather(nodeMap.get(a)) == findFather(nodeMap.get(b));
		}

		public void union(V a, V b) {
			if (!nodeMap.containsKey(a) || !nodeMap.containsKey(b))
				return;
			UnionNode<V> headA = findFather(nodeMap.get(a));
			UnionNode<V> headB = findFather(nodeMap.get(b));
			if (headA == headB)// 不用重复判断是否为1个集合
				return;
			UnionNode<V> larget = sizeMap.get(headA) > sizeMap.get(headB) ? headA : headB;
			UnionNode<V> small = larget == headA ? headB : headA;
			parentMap.put(small, larget);
			sizeMap.put(larget, sizeMap.get(larget) + sizeMap.get(small));
			sizeMap.remove(small);
		}

		private UnionNode<V> findFather(UnionNode<V> node) {
//			if (!nodeMap.containsKey(node.value))//内部调用node一直存在 调用前就检查过了
//				return null;
			Stack<UnionNode<V>> stack = new Stack<UnionNode<V>>();
			while (node != parentMap.get(node)) {
				stack.push(node);
				node = parentMap.get(node);
			}
			while (!stack.isEmpty()) {
				parentMap.put(stack.pop(), node);
			}
			return node;
		}

	}
}
