package class03;

import java.util.HashMap;

public class Code07_TopKTimesRealTime {

	public static class Node {
		String value;
		int times;

		public Node(String value, int times) {
			this.value = value;
			this.times = times;
		}

	}

	public static class TopKRealTime {
		Node[] heap;
		int heapSize;
		int K;
		HashMap<String, Node> nodeMap;
		HashMap<Node, Integer> indexMap;

		public TopKRealTime(int K) {
			this.K = K;
			this.heap = new Node[K];
			this.heapSize = 0;
			this.nodeMap = new HashMap<>();
			this.indexMap = new HashMap<>();
		}

		// 添加String O(logK)
		public void add(String str) {
			// 先修改表的信息 再修改堆的信息
			int preIndex = -1;// 该str在堆上的位置
			Node cur = null;
			if (!nodeMap.containsKey(str)) {// 是否加入过该str
				cur = new Node(str, 1);
				nodeMap.put(str, cur);
				indexMap.put(cur, -1);// 该str不在堆上
			} else {
				cur = nodeMap.get(str);
				cur.times++;
				preIndex = indexMap.get(cur);// 该str在堆上位置
			}
			if (preIndex == -1) {// str不在堆上
				if (heapSize < K) {// 堆未满
					heap[heapSize] = cur;
					indexMap.put(cur, heapSize);
					heapInsert(heap, heapSize++);
				} else if (heap[0].times < cur.times) {// 堆满了 看是否能进门槛
					indexMap.put(heap[0], -1);
					heap[0] = cur;
					heapify(heap, 0, K);
				}
			} else {
				heapify(heap, preIndex, heapSize);// times++只看能不能向下
			}
		}

		// 返回TopK O(K)
		public void topKRT() {
			System.out.println("TOP:");
			for (int i = 0; i < heap.length; i++) {
				if (heap[i] == null) {
					return;
				}
				System.out.println(heap[i].value + " " + heap[i].times);
			}
		}

		// 优化
		public void heapInsert(Node[] values, int index) {
			while (values[index].times < values[(index - 1) / 2].times) {// ∵(0-1)/2==0所以数组不会越界
				swap(values, index, (index - 1) / 2);
				index = (index - 1) / 2;
			}
		}

		public void heapify(Node[] values, int index, int size) {
			int leftSon = index * 2 + 1;
			while (leftSon < size) {
				int minIndex = leftSon + 1 < size
						? values[leftSon].times < values[leftSon + 1].times ? leftSon : leftSon + 1
						: leftSon;
				minIndex = values[minIndex].times < values[index].times ? minIndex : index;
				if (minIndex == index) {
					return;
				}
				swap(values, index, minIndex);
				index = minIndex;
				leftSon = index * 2 + 1;
			}
		}

		public void swap(Node[] value, int i, int j) {
			if (value == null || i == j) {
				return;
			}
			indexMap.put(value[i], j);// 修改indexMap中的位置
			indexMap.put(value[j], i);
			Node tmp = value[i];
			value[i] = value[j];
			value[j] = tmp;
		}
	}

	public static String[] generateRandomArray(int len, int max) {
		String[] res = new String[len];
		for (int i = 0; i != len; i++) {
			res[i] = String.valueOf((int) (Math.random() * (max + 1)));
		}
		return res;
	}

	public static void printArray(String[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		TopKRealTime record = new TopKRealTime(2);
		record.add("zuo");
		record.topKRT();
		record.add("cheng");
		record.add("cheng");
		record.topKRT();
		record.add("Yun");
		record.add("Yun");
		record.topKRT();
	}
}
