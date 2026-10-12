package class05;

import java.util.Comparator;

public class Code01_AVLTreeMap {
	public static class AVLNode<K extends Comparator<K>, V> {
		public K key;
		public V value;
		public int height;
		public AVLNode<K, V> left;
		public AVLNode<K, V> right;

		public AVLNode(K key, V value) {
			this.key = key;
			this.value = value;
			height = 1;
		}

	}

	public static class AVLTreeMap<K extends Comparator<K>, V> {
		public AVLTreeMap<K, V> root;
		public int size;

		public AVLTreeMap() {
			root = null;
			size = 0;
		}

		private AVLNode<K, V> rightRotate(AVLNode<K, V> cur) {
			AVLNode<K, V> left = cur.left;
			cur.left = left.right;
			left.right = cur;
			cur.height = Math.max(cur.left != null ? cur.left.height : 0, cur.right != null ? cur.right.height : 0) + 1;
			left.height = Math.max((left.left != null ? left.left.height : 0),
					(left.right != null ? left.right.height : 0)) + 1;
			return left;// left是新头
		}
	}

}
