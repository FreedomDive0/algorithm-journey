package class04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import class04.code_comparator.Student;

public class heapCase1 {
	public static class Myheap<T> {
		private int heapSize;
		private ArrayList<T> heap;
		private HashMap<T, Integer> indexMap;// 记录index
		private Comparator<? super T> comparator;

		public Myheap(Comparator<T> com) {
			this.comparator = com;
			heap = new ArrayList<T>();
			heapSize = 0;
			indexMap = new HashMap<T, Integer>();
		}

		public boolean isEmpty() {
			return heapSize == 0;
		}

		public int size() {
			return heapSize;
		}

		public boolean containsKey(T value) {
			return indexMap.containsKey(value);
		}

		public void push(T value) {
			if (containsKey(value)) {
				throw new RuntimeException("already have this element!");
			}
			heap.add(value);
			indexMap.put(value, heapSize);
			heapInsert(heapSize++);
		}

		public void heapInsert(int index) {
			while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {// compare返回值为int
				swap(index, (index - 1) / 2);
				index = (index - 1) / 2;
			}
		}

		public T pop() {
			T o = heap.get(0);
			swap(0, --heapSize);
			heap.remove(heapSize);
			indexMap.remove(o);
			heapify(0, heapSize);
			return o;
		}

		public void heapify(int index, int heapSize) {
			int leftSon = index * 2 + 1;
			while (leftSon < heapSize) {
				int largest = leftSon + 1 < heapSize && comparator.compare(heap.get(leftSon), heap.get(leftSon + 1)) > 0
						? leftSon + 1
						: leftSon;
				largest = comparator.compare(heap.get(largest), heap.get(index)) < 0 ? largest : index;
				if (largest == index) {
					break;
				}
				swap(index, largest);
				index = largest;
				leftSon = index * 2 + 1;// 记得更新leftSon
			}
		}

		public void resign(T value) {// 先修改后调用方法修正堆
			int valueIndex = indexMap.get(value);
			heapInsert(valueIndex);// 向上向下二选一执行
			heapify(valueIndex, heapSize);
		}

		public void swap(int i, int j) {
			T o1 = heap.get(i);
			T o2 = heap.get(j);
			heap.set(i, o2);
			heap.set(j, o1);
			indexMap.put(o1, j);
			indexMap.put(o2, i);
		}
	}

	public static class AgeAscendingComparator implements Comparator<Student> {// 年龄比较器
		@Override
		public int compare(Student o1, Student o2) {
			return o1.age - o2.age;
		}
	}

	public static void main(String[] args) {
		Student s1 = new Student(9, 20, "L");
		Student s2 = new Student(8, 50, "E");
		Student s3 = new Student(3, 18, "O");
		Student s4 = new Student(0, 14, "G");
		Student s5 = new Student(4, 27, "D");
		Student s6 = new Student(6, 99, "N");

		Myheap<Student> heap = new Myheap<Student>(new AgeAscendingComparator());
		heap.push(s1);
		heap.push(s2);
		heap.push(s3);
		heap.push(s4);
		heap.push(s5);
		heap.push(s6);

		while (!heap.isEmpty()) {
			System.out.println(heap.pop());
		}
	}
}
