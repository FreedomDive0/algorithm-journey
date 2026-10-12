package 堆;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Problem_0502_IPO {
	public class Stock {
		int capital;
		int profit;

		public Stock(int capital, int profit) {
			this.capital = capital;
			this.profit = profit;
		}

	}

	public class StockCComparator implements Comparator<Stock> {

		@Override
		public int compare(Stock o1, Stock o2) {
			// TODO Auto-generated method stub
			return o1.capital - o2.capital;
		}

	}

	public class StockPComparator implements Comparator<Stock> {

		@Override
		public int compare(Stock o1, Stock o2) {
			// TODO Auto-generated method stub
			return o2.profit - o1.profit;
		}

	}

	public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
		PriorityQueue<Stock> cHeap = new PriorityQueue<Stock>(new StockCComparator());
		PriorityQueue<Stock> pHeap = new PriorityQueue<Stock>(new StockPComparator());
		for (int i = 0; i < capital.length; i++) {
			if (w < capital[i]) {
				cHeap.add(new Stock(capital[i], profits[i]));
			} else {
				pHeap.add(new Stock(capital[i], profits[i]));
			}
		}
		int res = w;
		while (k > 0 && !pHeap.isEmpty()) {
			res += pHeap.poll().profit;
			k--;
			while (!cHeap.isEmpty() && cHeap.peek().capital <= res) {
				pHeap.add(cHeap.poll());
			}
		}
		return res;
	}
}
