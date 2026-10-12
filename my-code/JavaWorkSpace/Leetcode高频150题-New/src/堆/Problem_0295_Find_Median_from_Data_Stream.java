package 堆;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Problem_0295_Find_Median_from_Data_Stream {
	class MyCom implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			// TODO Auto-generated method stub
			return o2 - o1;
		}

	}

	class MedianFinder {
		PriorityQueue<Integer> l = new PriorityQueue<Integer>(new MyCom());
		PriorityQueue<Integer> r = new PriorityQueue<Integer>();

		public MedianFinder() {

		}

		public void addNum(int num) {
			int ls = l.size();
			int rs = r.size();
			if (ls == rs) {// 进入左侧
				if (rs == 0 || num < r.peek()) {
					l.add(num);
				} else {
					l.add(r.poll());
					r.add(num);
				}
			} else {// 进入右侧
				if (num > l.peek()) {
					r.add(num);
				} else {
					r.add(l.poll());
					l.add(num);
				}
			}
		}

		public double findMedian() {
			int ls = l.size();
			int rs = r.size();
			if (((ls + rs) & 1) == 0) {
				return (double) ((l.peek() + r.peek()) / 2.0);
			} else {
				return l.peek();
			}
		}
	}
}
