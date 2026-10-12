package class09;

import java.util.PriorityQueue;

public class Code03_LessMoneySplitGold {
	public static int splitGold1(int[] golds) {
		if(golds==null||golds.length==0) {
			return 0;
		}
		return process(golds, 0);
	}
	public static int process(int[] golds,int money) {
		if(golds.length==1) {
			return money;
		}
		int res = Integer.MAX_VALUE;
		for(int i=0;i<golds.length;i++) {
			for(int j=i+1;j<golds.length;j++) {
				res = Math.min(res, process(copyAndMerge(golds, i, j), money+golds[i]+golds[j]));
			}
		}
		return res;
	}
	public static int[] copyAndMerge(int[] golds,int i,int j) {
		int[] copy = new int[golds.length-1];
		int copyIndex = 0;
		for(int index=0;index<golds.length;index++) {
			if(index!=i&&index!=j) {
				copy[copyIndex++] = golds[index];
			}
		}
		copy[copyIndex] = golds[i] + golds[j];//放在末尾不会影响 全排列结果
		return copy;
	}
	
	public static int splitGold2(int[] golds) {
		if(golds==null||golds.length==0) {
			return 0;
		}
		PriorityQueue<Integer> g = new PriorityQueue<Integer>();
		for(int i=0;i<golds.length;i++) {
			g.add(golds[i]);
		}
		int money = 0;
		while(g.size()>1) {
			int cur = g.poll() + g.poll();
			money += cur;
			g.add(cur);
		}
		return money;
	}
	
	// for test
		public static int[] generateRandomArray(int maxSize, int maxValue) {
			int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
			for (int i = 0; i < arr.length; i++) {
				arr[i] = (int) (Math.random() * (maxValue + 1));
			}
			return arr;
		}

		public static void main(String[] args) {
			int testTime = 100000;
			int maxSize = 6;
			int maxValue = 1000;
			for (int i = 0; i < testTime; i++) {
				int[] arr = generateRandomArray(maxSize, maxValue);
				if (splitGold1(arr) != splitGold2(arr)) {
					System.out.println("Oops!");
				}
			}
			System.out.println("finish!");
		}
}
