package class04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Code03_Heap02 {
	public static class Heap<T> {
		private ArrayList<T> list;
		private HashMap<T, Integer> indexMap;
		private Comparator<T> comparator;
		private int size;

		public Heap(Comparator<T> comparator) {
			list = new ArrayList<T>();
			indexMap = new HashMap<T, Integer>();
			this.comparator = comparator;
			size = 0;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public void push(T value) {
			list.add(value);
			indexMap.put(value, size);
			heapInsert(size++);
		}

		public T pop() {
			if (isEmpty())
				return null;
			T ans = list.get(0);
			swap(0, --size);
			list.remove(size);
			indexMap.remove(ans);
			heapify(0);
			return ans;
		}

		public void resign(T value) {
			if (indexMap.containsKey(value)) {
				int idx = indexMap.get(value);
				heapInsert(idx);
				heapify(idx);
			}
		}

		private void heapify(int idx) {
			int leftSon = idx * 2 + 1;
			while (leftSon < size) {
				int largest = leftSon + 1 < size && comparator.compare(list.get(leftSon), list.get(leftSon + 1)) < 0
						? leftSon + 1
						: leftSon;
				largest = comparator.compare(list.get(largest), list.get(idx)) < 0 ? largest : idx;
				if (largest == idx)
					return;
				swap(idx, largest);
				idx = largest;
				leftSon = idx * 2 + 1;
			}
		}

		private void heapInsert(int idx) {
			if (comparator.compare(list.get(idx), list.get((idx - 1) / 2)) < 0) {
				swap(idx, (idx - 1) / 2);
				idx = (idx - 1) / 2;
			}
		}

		private void swap(int i, int j) {
			T o1 = list.get(i);
			T o2 = list.get(j);
			list.set(i, o2);
			list.set(j, o1);
			indexMap.put(o1, j);
			indexMap.put(o2, i);
		}
	}
}
