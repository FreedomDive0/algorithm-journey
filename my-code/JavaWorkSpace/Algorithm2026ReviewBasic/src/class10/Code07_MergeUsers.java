package class10;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Code07_MergeUsers {
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
			if (!nodeMap.containsKey(a) || !nodeMap.containsKey(b) || isSameSet(a, b))
				return;
			Node<V> headA = findFather(nodeMap.get(a));
			Node<V> headB = findFather(nodeMap.get(b));
			Node<V> larget = sizeMap.get(headA) > sizeMap.get(headB) ? headA : headB;
			Node<V> small = larget == headA ? headB : headA;
			parentMap.put(small, larget);
			sizeMap.put(larget, sizeMap.get(larget) + sizeMap.get(small));
			sizeMap.remove(small);
		}

		private Node<V> findFather(Node<V> node) {
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

		public int getUnionSize() {
			return sizeMap.size();
		}

	}

	public static class User {
		public String a;
		public String b;
		public String c;

		public User(String a, String b, String c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}

	}

	// 合并用户
	public static int mergeUser(List<User> users) {
		if (users == null || users.size() == 0) {
			return 0;
		}
		UnionFind<User> unionFind = new UnionFind<User>(users);
		HashMap<String, User> mapA = new HashMap<String, User>();
		HashMap<String, User> mapB = new HashMap<String, User>();
		HashMap<String, User> mapC = new HashMap<String, User>();
		for (User user : users) {
			if (!mapA.containsKey(user.a)) {
				mapA.put(user.a, user);
			} else {
				unionFind.union(user, mapA.get(user.a));
			}
			if (!mapB.containsKey(user.b)) {
				mapB.put(user.b, user);
			} else {
				unionFind.union(user, mapB.get(user.b));
			}
			if (!mapC.containsKey(user.c)) {
				mapC.put(user.c, user);
			} else {
				unionFind.union(user, mapC.get(user.c));
			}
		}
		return unionFind.getUnionSize();
	}
}
