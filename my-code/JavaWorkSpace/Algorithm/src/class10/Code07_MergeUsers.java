package class10;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Code07_MergeUsers {
	public static class Node<V>{
		V value;

		public Node(V value) {
			this.value = value;
		}
	}
	
	public static class UnionSet<V>{
		HashMap<V, Node<V>> nodes;
		HashMap<Node<V>,Node<V>> parents;
		HashMap<Node<V>,Integer> nodesSize;
		public UnionSet(List<V> list) {
			for (V value : list) {
				Node<V> node = new Node<V>(value);
				nodes.put(value, node);
				parents.put(node, node);
				nodesSize.put(node, 1);
			}
		}
		public boolean isSameSet(V a,V b) {
			if(!nodes.containsKey(a)||!nodes.containsKey(b)) {
				return false;
			}
			return findFather(a)==findFather(b); 
		}
		public Node<V> findFather(V value){
			Node<V> cur = nodes.get(value);
			Stack<Node<V>> stack = new Stack<Node<V>>();
			while(cur != parents.get(cur)) {
				stack.add(cur);
				cur = parents.get(cur);
			}
			while(!stack.isEmpty()) {//扁平化处理
				Node<V> node = stack.pop();
				parents.put(node, cur);
			}
			return cur;
		}
		public void union(V a,V b) {
			if(!nodes.containsKey(a)||!nodes.containsKey(b)) {
				return;
			}
			Node<V> aHead = findFather(a);
			Node<V> bHead = findFather(b);
			if(aHead!=bHead) {
				Node<V> big = nodesSize.get(aHead)>nodesSize.get(bHead)?aHead:bHead;
				Node<V> small = big == aHead ? bHead : aHead;
				parents.put(small, big);
				nodesSize.put(big,nodesSize.get(big)+nodesSize.get(small));
				nodesSize.remove(small);
			}
		}
		
		public int getSetNum() {
			return nodesSize.size();
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
	public static int mergeUser(List<User> userList) {
		UnionSet<User> unionFind = new UnionSet<User>(userList);
		HashMap<String,User> mapA = new HashMap<String,User>();
		HashMap<String,User> mapB = new HashMap<String,User>();
		HashMap<String,User> mapC = new HashMap<String,User>();
		
		for(User user:userList) {
			if(!mapA.containsKey(user.a)) {
				mapA.put(user.a, user);
			}else {
				unionFind.union(user, mapA.get(user.a));
			}
			if(!mapB.containsKey(user.b)) {
				mapB.put(user.b, user);
			}else {
				unionFind.union(user, mapB.get(user.b));
			}
			if(!mapC.containsKey(user.c)) {
				mapC.put(user.c, user);
			}else {
				unionFind.union(user, mapC.get(user.c));
			}
		}
		return unionFind.getSetNum();
	}
}
