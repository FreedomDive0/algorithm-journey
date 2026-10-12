package class10;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Code07_MergeUsers {
	public static class UnionFind<T> {
		public HashMap<T, T> fatherMap;
		public HashMap<T, Integer> sizeMap;

		public UnionFind(List<T> list) {
			fatherMap = new HashMap<T, T>();
			sizeMap = new HashMap<T, Integer>();
			for (T t : list) {
				fatherMap.put(t, t);
				sizeMap.put(t, 1);
			}
		}

		public boolean isSameSet(T t1, T t2) {
			if (!fatherMap.containsKey(t1) || !fatherMap.containsKey(t2)) {
				return false;
			}
			return findFather(t1) == findFather(t2);
		}

		public void union(T t1, T t2) {
			T f1 = findFather(t1);
			T f2 = findFather(t2);
			if (f1 != f2) {
				int s1 = sizeMap.get(f1);
				int s2 = sizeMap.get(f2);
				T big = s1 > s2 ? f1 : f2;
				T small = big == f1 ? f2 : f1;
				fatherMap.put(small, big);
				sizeMap.put(big, s1 + s2);
				sizeMap.remove(small);
			}
		}

		public int getSize() {
			return sizeMap.size();
		}

		private T findFather(T t) {
			if (!fatherMap.containsKey(t)) {
				return null;
			}
			Stack<T> stack = new Stack<T>();
			while (fatherMap.get(t) != t) {
				stack.push(t);// 注意放入的节点是自身
				t = fatherMap.get(t);
			}
			while (!stack.isEmpty()) {
				fatherMap.put(stack.pop(), t);// 扁平化
			}
			return t;
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

	public static int mergeUsers(List<User> users) {
		if (users == null || users.size() < 1) {
			return 0;
		}
		UnionFind<User> unionFind = new UnionFind<User>(users);
		HashMap<String, User> aMap = new HashMap<String, User>();
		HashMap<String, User> bMap = new HashMap<String, User>();
		HashMap<String, User> cMap = new HashMap<String, User>();
		for (User user : users) {
			if (!aMap.containsKey(user.a)) {
				aMap.put(user.a, user);
			} else {
				unionFind.union(user, aMap.get(user.a));
			}
			if (!bMap.containsKey(user.b)) {
				bMap.put(user.b, user);
			} else {
				unionFind.union(user, bMap.get(user.b));
			}
			if (!cMap.containsKey(user.c)) {
				cMap.put(user.c, user);
			} else {
				unionFind.union(user, cMap.get(user.c));
			}
		}
		return unionFind.getSize();
	}
}
