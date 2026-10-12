package class04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Code03_Heap02 {
	public static class Heap<T> {
		private List<T> heap;
		private HashMap<T, Integer> idxMap;
		private Comparator<? super T> com;
		private int size;

		public Heap(Comparator<? super T> com) {
			this.com = com;
			heap = new ArrayList<T>();
			idxMap = new HashMap<T, Integer>();
			size = 0;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public boolean contains(T key) {
			return idxMap.containsKey(key);
		}

		public void push(T value) {
			heap.add(value);
			heapInsert(size++);
		}

		public T pop() {
			T ans = heap.get(0);
			swap(0, --size);
			heapify(0, size);
			return ans;
		}

		public void resign(T value) {
			if (contains(value)) {
				int idx = idxMap.get(value);
				heapInsert(idx);
				heapify(idx, size);
			}
		}

		public void heapInsert(int idx) {
			while (com.compare(heap.get(idx), heap.get((idx - 1) / 2)) < 0) {
				swap(idx, (idx - 1) / 2);
				idx = (idx - 1) / 2;
			}
		}

		public void heapify(int idx, int heapSize) {
			int left = idx * 2 + 1;
			while (left < heapSize) {
				int largest = left + 1 < heapSize && com.compare(heap.get(left + 1), heap.get(left)) < 0 ? left + 1
						: left;
				largest = com.compare(heap.get(largest), heap.get(idx)) < 0 ? largest : idx;
				if (largest == idx) {
					break;
				}
				swap(idx, largest);
				idx = largest;
				left = idx * 2 + 1;
			}
		}

		public void swap(int i, int j) {
			T a = heap.get(i);
			T b = heap.get(j);
			heap.set(i, b);
			heap.set(j, a);
			idxMap.put(a, j);
			idxMap.put(b, i);
		}
	}
}
