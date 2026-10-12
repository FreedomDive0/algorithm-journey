package class10;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Code01_UnionFind {
	public static class Node<V> {
		V value;

		public Node(V value) {
			this.value = value;
		}

	}

	public static class UnionFind<V> {
		public HashMap<V, Node<V>> nodeMap;
		public HashMap<Node<V>, Node<V>> parentMap;
		public HashMap<Node<V>, Integer> sizeMap;

		public UnionFind(List<V> list) {
			nodeMap = new HashMap<V, Node<V>>();
			parentMap = new HashMap<Node<V>, Node<V>>();
			sizeMap = new HashMap<Node<V>, Integer>();
			for (V v : list) {
				if (nodeMap.containsKey(v))
					continue;
				Node<V> node = new Node<V>(v);
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
			Node<V> headA = findFather(nodeMap.get(a));
			Node<V> headB = findFather(nodeMap.get(b));
			if (headA == headB)// 不用重复判断是否为1个集合
				return;
			Node<V> larget = sizeMap.get(headA) > sizeMap.get(headB) ? headA : headB;
			Node<V> small = larget == headA ? headB : headA;
			parentMap.put(small, larget);
			sizeMap.put(larget, sizeMap.get(larget) + sizeMap.get(small));
			sizeMap.remove(small);
		}

		private Node<V> findFather(Node<V> node) {
//			if (!nodeMap.containsKey(node.value))//内部调用node一直存在 调用前就检查过了
//				return null;
			Stack<Node<V>> stack = new Stack<Node<V>>();
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
