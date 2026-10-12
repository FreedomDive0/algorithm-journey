package class05;

import java.util.HashMap;

public class Code04_LRU {
	// Least Recently Used
	public static class Node<K, V> {
		K key;
		V value;
		Node<K, V> last;
		Node<K, V> next;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			this.last = null;
			this.next = null;
		}

	}

	public static class DoubleLinkedList<K, V> {
		private Node<K, V> head;
		private Node<K, V> tail;
		final int capacity;// 最大容量

		public DoubleLinkedList(int capacity) {
			this.head = null;
			this.tail = null;
			this.capacity = capacity;
		}

		// node一定在链表上
		public void moveToTail(Node<K, V> node) {
			if (node == tail) {
				return;
			}
			if (node == head) {// 移动节点为头节点
				this.head = head.next;
				head.last = null;
			} else {
				node.last.next = node.next;// 处理node左右节点
				node.next.last = node.last;
			}
			// 后续操作
			tail.next = node;
			node.next = null;
			tail = node;
		}

		// 添加新节点
		public void addNode(Node<K, V> node) {
			if (node == null) {
				return;
			}
			if (head == null) {// 链表中没有节点
				head = node;
				tail = node;
			} else {
				tail.next = node;
				node.last = tail;
				tail = node;
			}
		}

		// 当内存满了 弹出头节点
		public Node<K, V> removeHead(Node<K, V> node) {
			if (this.head == null) {
				return null;
			}
			Node<K, V> ans = head;
			if (this.head == this.tail) {// 只存在一个节点
				this.head = null;
				this.tail = null;
			} else {
				this.head = ans.next;
				this.head.last = null;
				ans.next = null;
			}
			return ans;
		}

	}

	public static class LRUCatch<K, V> {
		HashMap<K, Node<K, V>> map;
		DoubleLinkedList<K, V> list;
		int capacity;

		public LRUCatch(int capacity) {
			if (capacity < 1) {
				throw new RuntimeException("should be more than 0.");
			}
			this.capacity = capacity;
			this.map = new HashMap<K, Node<K, V>>();
			this.list = new DoubleLinkedList<>(capacity);
		}

		// key可能存在可能新加入
		public void set(K key, V value) {
			if (map.containsKey(key)) {
				Node<K, V> node = map.get(key);
				node.value = value;// 修改值
				list.moveToTail(node);// 移动到末尾
			} else {
				if (map.size() == capacity) {// 查看内存是否满了
					Node<K, V> node = list.removeHead(list.head);
					map.remove(node.key);
				}
				Node<K, V> node = new Node<K, V>(key, value);
				map.put(key, node);
				list.addNode(node);
			}
		}

		public V get(K key) {
			if (map.containsKey(key)) {
				Node<K, V> node = map.get(key);
				list.moveToTail(node);
				return node.value;
			}
			return null;
		}
	}

	public static void main(String[] args) {
		LRUCatch<String, Integer> testCache = new LRUCatch<String, Integer>(3);
		testCache.set("A", 1);
		testCache.set("B", 2);
		testCache.set("C", 3);
		System.out.println(testCache.get("B"));
		System.out.println(testCache.get("A"));
		testCache.set("D", 4);
		System.out.println(testCache.get("D"));
		System.out.println(testCache.get("C"));

	}
}
