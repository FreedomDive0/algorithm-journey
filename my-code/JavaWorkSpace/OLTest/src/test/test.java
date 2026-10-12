package test;

import java.util.ArrayList;
import java.util.Scanner;

public class test {
//	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		while (in.hasNext()) {
//			String s = in.nextLine();
//			String[] strs = s.split(" ");
//			int[] arr = new int[strs.length];
//			for (int i = 0; i < strs.length; i++) {
//				arr[i] = Integer.valueOf(strs[i]);
//			}
//			System.out.println(findnew(arr));
//		}
//	}
//
//	public static int findnew(int[] arr) {
//		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
//		int max = 0;
//		for (int i = 0; i < arr.length; i++) {
//			if (map.containsKey(arr[i])) {
//				map.put(arr[i], map.get(arr[i]) + 1);
//			} else {
//				map.put(arr[i], 1);
//			}
//			max = Math.max(max, map.get(arr[i]));
//		}
//		ArrayList<Integer> list = new ArrayList<Integer>();
//		for (Integer n : map.keySet()) {
//			if (map.get(n) < max) {
//				continue;
//			} else {
//				list.add(n);
//			}
//		}
//		int[] Arr = new int[list.size() * max];
//		int i = 0;
//		for (Integer num : list) {
//			for (int j = 0; j < max; j++) {
//				Arr[i++] = num;
//			}
//		}
//		if (Arr.length % 2 == 0) {
//			return (Arr[Arr.length / 2 - 1] + Arr[Arr.length / 2]) / 2;
//		} else {
//			return Arr[Arr.length / 2];
//		}
//	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int n = in.nextInt();
			int m = in.nextInt();
			ArrayList<Integer> treelist = new ArrayList<Integer>();
			int[] tree = new int[n];
			for (int i = 0; i < m; i++) {
				int index = in.nextInt() - 1;
				treelist.add(index);
				tree[index] = 1;
			}
			int k = in.nextInt();
			int max = 0;
			for (int i = 0; i < tree.length; i++) {
				int count = 0;
				int rest = k;
				int index = treelist.get(i) + 1;
				while (rest > 0) {
					if (tree[index++] == 1) {
						rest--;
					}
					count++;
				}
			}

		}
	}

}
