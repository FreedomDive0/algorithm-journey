package 基本数据类型专题;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LC295_数据流的中位数 {
	class MedianFinder {// 两个堆实现中位数
		PriorityQueue<Integer> l = new PriorityQueue<Integer>(new MyCom());// 大根堆
		PriorityQueue<Integer> r = new PriorityQueue<Integer>();

		public MedianFinder() {

		}

		public void addNum(int num) {
			int s1 = l.size(), s2 = r.size();
			if (s1 == s2) {
				if (num < r.peek()) {
					l.add(num);
				} else {
					l.add(r.poll());
					r.add(num);
				}
			} else {
				if (num > l.peek()) {
					r.add(num);
				} else {
					r.add(l.poll());
					l.add(num);
				}
			}
		}

		public double findMedian() {
			int s1 = l.size(), s2 = r.size();
			if ((s1 + s2) % 2 == 0) {
				return (l.peek() + r.peek()) / 2.0;
			} else {
				return l.peek();
			}
		}

		class MyCom implements Comparator<Integer> {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}

		}
	}

}
