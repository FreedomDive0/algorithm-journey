package class03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Review {
	// 5.1 arr[]一个数出现了超过数组长度一半 打印这个数
	public static void moreThanHalf(int[] arr) {
		if (arr == null || arr.length < 1) {
			return;
		}
		int cand = 0;
		int hp = 0;
		for (int i = 0; i < arr.length; i++) {
			if (hp == 0) {
				cand = arr[i];
				hp = 1;// 给第一个数血量赋值
			} else if (cand == arr[i]) {
				hp++;
			} else {
				hp--;// 每次删除两个不同的数
			}
//				System.out.println("i: " + i + " " + "hp: " + hp + "cand: " + cand);
		}
		if (hp == 0) {
			System.out.println("No Such Number");
			return;
		}
		hp = 0;
		for (int i = 0; i < arr.length; i++) {
			if (cand == arr[i]) {
				hp++;
			}
		}
		if (hp > arr.length / 2) {
			System.out.println(cand);
		} else {
			System.out.println("No Such Number");
		}
	}

	// 5.2 arr[] 给定整数K arr长度为N 如果某些数出现超过了N/K 打印这些数
	public static void FindKMajority(int[] arr, int k) {
		if (arr == null || arr.length < k || arr.length < 1 || k < 2) {
			return;
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < arr.length; i++) {
			if (map.containsKey(arr[i])) {
				map.put(arr[i], map.get(arr[i]) + 1);
			} else {
				if (map.size() == k - 1) {
					allSubOne(map);
				} else {
					map.put(arr[i], 1);
				}
			}
		}
		HashMap<Integer, Integer> real = getReals(map, arr);
		boolean hasNumber = false;
		for (Entry<Integer, Integer> entry : real.entrySet()) {
			if (entry.getValue() > (arr.length / k)) {
				hasNumber = true;
				System.out.print(entry.getKey() + " " + entry.getValue());
				System.out.println();
			}
		}
		System.out.println(hasNumber ? "" : "No Such Number");
	}

	public static void allSubOne(HashMap<Integer, Integer> map) {
		ArrayList<Integer> removeList = new ArrayList<Integer>();
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			int key = entry.getKey();
			int value = entry.getValue();
			if (value == 1) {
				removeList.add(entry.getKey());
			} else {
				map.put(key, value - 1);
			}
		}
		for (Integer key : removeList) {
			map.remove(key);
		}
	}

	public static HashMap<Integer, Integer> getReals(HashMap<Integer, Integer> map, int[] arr) {
		HashMap<Integer, Integer> real = new HashMap<Integer, Integer>();
		for (int i = 0; i < arr.length; i++) {
			int curNum = arr[i];
			if (map.containsKey(curNum)) {
				if (real.containsKey(curNum)) {
					real.put(curNum, real.get(curNum) + 1);
				} else {
					real.put(curNum, 1);
				}
			}
		}
		return real;
	}
//		// 5.2 arr[] 给定整数K arr长度为N 如果某些数出现超过了N/K 打印这些数
//		public static void FindKMajority(int[] arr, int k) {
//			if (arr == null || arr.length < k || arr.length < 1) {
//				return;
//			}
//			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
//			for (int i = 0; i < arr.length; i++) {
//				if (map.containsKey(arr[i])) {
//					map.put(arr[i], map.get(arr[i]) + 1);
//				} else {
//					if (map.size() < k - 1) {
//						map.put(arr[i], 1);
//					} else {
//						allSubOne(map);
//					}
//				}
//			}
//			for (Entry<Integer, Integer> entry : map.entrySet()) {
//				map.put(entry.getKey(), 0);
//			}
//			for (int i = 0; i < arr.length; i++) {
//				if (map.containsKey(arr[i])) {
//					map.put(arr[i], map.get(arr[i]) + 1);
//				}
//			}
//			ArrayList<Integer> ans = new ArrayList<Integer>();
//			for (Entry<Integer, Integer> entry : map.entrySet()) {
//				if (entry.getValue() > (arr.length / k)) {
//					ans.add(entry.getKey());
//				}
//			}
//			if (ans.size() == 0) {
//				System.out.println("No Such Number");
//			} else {
//				for (Integer num : ans) {
//					System.out.println(num);
//				}
//			}
//		}
//
//		public static void allSubOne(HashMap<Integer, Integer> map) {
//			for (Entry<Integer, Integer> entry : map.entrySet()) {
//				if (entry.getValue() - 1 == 0) {
//					map.remove(entry.getKey());
//				} else {
//					map.put(entry.getKey(), entry.getValue() - 1);
//				}
//			}
//		}
}
