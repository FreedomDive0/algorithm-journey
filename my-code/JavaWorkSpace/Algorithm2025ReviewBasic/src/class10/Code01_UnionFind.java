package class10;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Code01_UnionFind {
	public static class Node<T> {
		T value;

		public Node(T value) {
			this.value = value;
		}

	}

	public static class UnionFind<T> {
		HashMap<T, Node<T>> nodeMap;
		HashMap<Node<T>, Node<T>> fatherMap;
		HashMap<Node<T>, Integer> sizeMap;

		public UnionFind(List<T> list) {
			nodeMap = new HashMap<T, Node<T>>();
			fatherMap = new HashMap<Node<T>, Node<T>>();
			sizeMap = new HashMap<Node<T>, Integer>();
			for (T t : list) {
				Node<T> node = new Node<T>(t);
				nodeMap.put(t, node);
				fatherMap.put(node, node);
				sizeMap.put(node, 1);
			}
		}

		public boolean isSameSet(T t1, T t2) {
			if (!nodeMap.containsKey(t1) || !nodeMap.containsKey(t2)) {
				return false;
			}
			return findFather(nodeMap.get(t1)) == findFather(nodeMap.get(t2));
		}

		public void Union(T t1, T t2) {
			if (!nodeMap.containsKey(t1) || !nodeMap.containsKey(t2)) {
				return;
			}
			Node<T> f1 = findFather(nodeMap.get(t1));
			Node<T> f2 = findFather(nodeMap.get(t2));
			if (f1 != f2) {
				int s1 = sizeMap.get(f1);
				int s2 = sizeMap.get(f2);
				Node<T> big = s1 > s2 ? f1 : f2;
				Node<T> small = big == f1 ? f2 : f1;
				fatherMap.put(small, big);
				sizeMap.put(big, s1 + s2);
				sizeMap.remove(small);
			}
		}

		private Node<T> findFather(Node<T> node) {
			Stack<Node<T>> stack = new Stack<Node<T>>();
			while (node != fatherMap.get(node)) {
				stack.add(node);
				node = fatherMap.get(node);
			}
			while (!stack.isEmpty()) {
				fatherMap.put(stack.pop(), node);
			}
			return node;
		}

	}
}
