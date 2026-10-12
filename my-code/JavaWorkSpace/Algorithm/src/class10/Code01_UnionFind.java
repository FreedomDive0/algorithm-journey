package class10;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Code01_UnionFind {
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
	// 复习
	// 1.并查集
	public static class Node1<V> {
		V value;

		public Node1(V value) {
			this.value = value;
		}

	}

	public static class UnionFind<V> {
		HashMap<V, Node1<V>> nodeMap;
		HashMap<Node1<V>, Node1<V>> parentMap;
		HashMap<Node1<V>, Integer> sizeMap;

		public UnionFind(List<V> list) {
			this.nodeMap = new HashMap<V, Node1<V>>();
			this.parentMap = new HashMap<Node1<V>, Node1<V>>();
			this.sizeMap = new HashMap<Node1<V>, Integer>();
			for (V v : list) {
				Node1<V> cur = new Node1<V>(v);
				nodeMap.put(v, cur);
				parentMap.put(cur, cur);
				sizeMap.put(cur, 1);
			}
		}

		public Node1<V> findFather(V v) {
			if (!nodeMap.containsKey(v)) {
				return null;
			}
			Node1<V> cur = nodeMap.get(v);
			Stack<Node1<V>> stack = new Stack<Node1<V>>();
			while (cur != parentMap.get(cur)) {
				stack.add(cur);
				cur = parentMap.get(cur);
			}
			while (!stack.isEmpty()) {// 扁平化
				parentMap.put(stack.pop(), cur);
			}
			return cur;
		}

		public boolean isSameSet(V v1, V v2) {
			if (!nodeMap.containsKey(v1) || !nodeMap.containsKey(v2)) {
				return false;
			}
			return findFather(v1) == findFather(v2);
		}

		public void Union(V v1, V v2) {
			if (!nodeMap.containsKey(v1) || !nodeMap.containsKey(v2)) {
				return;
			}
			Node1<V> v1F = findFather(v1);
			Node1<V> v2F = findFather(v2);
			if (v1F != v2F) {
				Node1<V> big = sizeMap.get(v1F) > sizeMap.get(v2F) ? v1F : v2F;
				Node1<V> small = big == v1F ? v2F : v1F;
				parentMap.put(small, big);// 小挂大 找得快
				sizeMap.put(big, sizeMap.get(big) + sizeMap.get(small));
				sizeMap.remove(small);
			}
		}
	}

}
