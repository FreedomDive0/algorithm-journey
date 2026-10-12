package class03;

import java.util.HashMap;
import java.util.Map.Entry;

public class Code05_FindKMajority {
	// 存在一个数出现次数超过数组长度一半
	public static void FindKMajority1(int[] arr) {
		int hp = 0;
		int cand = 0;
		for (int i = 0; i < arr.length; i++) {
			if (hp == 0) {// 实现同时删除两个数字
				cand = arr[i];
			} else if (cand == arr[i]) {
				hp++;
			} else {
				hp--;
			}
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
			System.out.println("该数字不存在");
		}
	}

	// 打印数组中出现次数大于N/K的数字
	public static void FindKMajority2(int[] arr, int k) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			if (map.size() < k - 1) {// 一次减k个数 未满
				map.put(arr[i], 1);
			} else if (map.containsKey(arr[i])) {// 满了 map中有该数
				map.put(arr[i], map.get(arr[i]) + 1);
			} else {// map中无该数
				for (Entry<Integer, Integer> entry : map.entrySet()) {
					if (entry.getValue() - 1 > 0) {
						map.put(entry.getKey(), entry.getValue() - 1);
					} else {
						map.remove(entry.getKey());
					}
				}
			}
		}
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			map.put(entry.getKey(), 0);
		}
		for (int i = 0; i < arr.length; i++) {
			if (map.containsKey(arr[i])) {
				map.put(arr[i], map.get(arr[i]) + 1);
			}
		}
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() > arr.length / k) {
				System.out.print(entry.getKey() + " ");
			}
		}
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 1, 1, 2, 1 };
		FindKMajority1(arr);
		int K = 4;
		FindKMajority2(arr, K);
	}
}
