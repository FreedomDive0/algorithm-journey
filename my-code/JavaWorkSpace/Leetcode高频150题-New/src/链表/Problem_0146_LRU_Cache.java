package 链表;

import java.util.HashMap;

public class Problem_0146_LRU_Cache {
	class LRUCache {
		class Node<K, V> {
			K key;
			V value;
			Node<K, V> last;
			Node<K, V> next;

			public Node(K key, V value) {
				this.key = key;
				this.value = value;
			}

		}

		class NodeDoubleLinkedList<K, V> {
			Node<K, V> head;
			Node<K, V> tail;

			public NodeDoubleLinkedList() {
				head = null;
				tail = null;
			}

			public void addNode(Node<K, V> newNode) {
				if (newNode == null) {
					return;
				}
				if (head == null) {
					head = newNode;
					tail = newNode;
				} else {
					tail.next = newNode;
					newNode.last = tail;
					tail = tail.next;
				}
			}

			public void moveNodeToTail(Node<K, V> node) {
				if (node == tail) {
					return;
				}
				if (head == node) {
					head = node.next;
					head.last = null;
				} else {
					node.last.next = node.next;
					node.next.last = node.last;
				}
				node.last = tail;
				tail.next = node;
				node.next = null;
				tail = node;
			}

			public Node<K, V> removeHead() {
				if (head == null) {
					return null;
				}
				Node<K, V> res = head;
				if (head == tail) {
					head = null;
					tail = null;
				} else {
					head = head.next;
					head.last = null;
					res.next = null;
				}
				return res;
			}

		}

		class MyCache<K, V> {
			HashMap<K, Node<K, V>> map;
			NodeDoubleLinkedList<K, V> nodeList;
			final int capacity;

			public MyCache(int capacity) {
				if (capacity < 1) {
					throw new RuntimeException("should be more than 0.");
				}
				this.map = new HashMap<K, Node<K, V>>();
				this.nodeList = new NodeDoubleLinkedList<K, V>();
				this.capacity = capacity;
			}

			public V get(K key) {
				if (map.containsKey(key)) {
					Node<K, V> node = map.get(key);
					nodeList.moveNodeToTail(node);
					return node.value;
				}
				return null;
			}

			public void set(K key, V value) {
				if (map.containsKey(key)) {
					Node<K, V> node = map.get(key);
					node.value = value;
					nodeList.moveNodeToTail(node);
				} else {
					if (map.size() == capacity) {
						removeMostUnusedCache();
					}
					Node<K, V> node = new Node<K, V>(key, value);
					nodeList.addNode(node);
					map.put(key, node);
				}
			}

			private void removeMostUnusedCache() {
				Node<K, V> node = nodeList.removeHead();
				map.remove(node.key);
			}
		}

		private MyCache<Integer, Integer> myCache;

		public LRUCache(int capacity) {
			myCache = new MyCache<Integer, Integer>(capacity);
		}

		public int get(int key) {
			Integer ans = myCache.get(key);
			return ans == null ? -1 : ans;
		}

		public void put(int key, int value) {
			myCache.set(key, value);
		}
	}
}
