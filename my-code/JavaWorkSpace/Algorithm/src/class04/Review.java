package class04;

public class Review {
	// 比较器
//	static class teacher {
//		int id;
//		int age;
//		String name;
//
//		public teacher(int id, int age, String name) {
//			this.id = id;
//			this.age = age;
//			this.name = name;
//		}
//
//		public static class myCompare implements Comparator<teacher> {// 比较器类 实现比较方法
//
//			@Override
//			public int compare(teacher o1, teacher o2) {
//				// TODO Auto-generated method stub
//				return o1.age - o2.age;
//			}
//		}
//	}
	// 堆排序
	static class Myheap {
		int limit;
		int size;
		int[] values;

		public Myheap(int limit) {
			this.limit = limit;
			this.size = 0;
			this.values = new int[limit];
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public boolean isFull() {
			return size == limit;
		}

		// 插入
		public void push(int element) {
			if (isFull()) {
				throw new RuntimeException("堆满了");
			}
			values[size] = element;
			heapInsert(values, size++);
		}

//		public void heapInsert() {
//			int index = size - 1;
//			int parent = (index - 1) / 2;
//			while (parent >= 0) {
//				if (values[index] <= values[parent]) {
//					break;
//				}
//				swap(values, index, parent);
//				index = parent;
//				parent = (index - 1) / 2;
//			}
//		}
		// 优化
		public void heapInsert(int[] values, int index) {
			while (values[index] > values[(index - 1) / 2]) {// ∵(0-1)/2==0所以数组不会越界
				swap(values, index, (index - 1) / 2);
				index = (index - 1) / 2;
			}
		}

		// 删除
		public int pop() {
			if (isEmpty()) {
				throw new RuntimeException("堆中没有数据");
			}
			swap(values, --size, 0);
			heapify(values, 0, size);
			return values[size];
		}

		public void heapify(int[] values, int index, int size) {
			int leftSon = index * 2 + 1;
			while (leftSon < size) {
				int maxIndex = leftSon + 1 < size ? values[leftSon] > values[leftSon + 1] ? leftSon : leftSon + 1
						: leftSon;
				maxIndex = values[maxIndex] > values[index] ? maxIndex : index;
				if (maxIndex == index) {
					return;
				}
				swap(values, index, maxIndex);
				index = maxIndex;
				leftSon = index * 2 + 1;
			}
		}
	}

	public static void swap(int[] arr, int i, int j) {
		if (arr == null || i == j) {
			return;
		}
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public static void heapSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		Myheap heap = new Myheap(arr.length);
//		for (int i = 0; i < arr.length; i++) {
//			heap.heapInsert(arr, i);// 从上向下建堆
//		}
		for (int i = arr.length - 1; i >= 0; i--) {
			heap.heapify(arr, i, arr.length);
		}
		int size = arr.length;
		swap(arr, 0, --size);
		while (size > 0) {
			heap.heapify(arr, 0, size);
			swap(arr, 0, --size);
		}
	}
}
