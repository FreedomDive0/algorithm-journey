package class10;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Review {
	public static class Node<V> {
		V value;

		public Node(V value) {
			this.value = value;
		}

	}

	public static class Union<V> {
		public HashMap<V, Node> nodes = new HashMap<>();
		public HashMap<Node, Node> parent = new HashMap<>();
		public HashMap<Node, Integer> unionSize = new HashMap<>();

		public Union(List<V> list) {
			for (V v : list) {
				Node n = new Node(v);
				nodes.put(v, n);
				parent.put(n, n);
				unionSize.put(n, 1);
			}
		}

		public boolean isSameSet(Node a, Node b) {
			if (a == null || b == null) {
				return false;
			}
			Node pa = FindParent(a);
			Node pb = FindParent(b);
			return pa == pb;
		}

		public void Union(Node a, Node b) {
			if (a == null || b == null) {
				return;
			}
			Node pa = FindParent(a);
			Node pb = FindParent(b);
			if (pa != pb) {
				Node small = unionSize.get(pa) > unionSize.get(pb) ? pa : pb;
				Node big = small == pa ? pb : pa;
				parent.put(small, big);
				unionSize.put(big, unionSize.get(big) + unionSize.get(small));
				unionSize.remove(small);
			}
		}

		public Node FindParent(Node node) {
			Stack<Node> path = new Stack<>();
			while (node != parent.get(node)) {
				path.add(node);
				node = parent.get(node);
			}
			while (!path.isEmpty()) {// 扁平化
				parent.put(path.pop(), node);
//				unionSize.put(node, unionSize.get(node) + unionSize.get(n));//本来就在同一个集合
//				unionSize.remove(n);
			}
			return node;
		}
	}

}
