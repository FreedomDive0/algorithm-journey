package class06;

import java.util.HashMap;

public class Code05_Split4Parts {
	public static boolean split4Parts(int[] arr) {
		if (arr == null || arr.length < 7) {
			return false;
		}
		HashMap<Integer, Integer> sumIndex = new HashMap<>();// 记录前缀和的下一个位置
		int sum = arr[0];
		for (int i = 1; i < arr.length; i++) {// 从1开始是因为 防止记录到对应位置index+1爆掉 因为最后一刀不可能到达length-1所以不存也可以
			sumIndex.put(sum, i);
			sum += arr[i];
		}
		int part = arr[0];
		for (int index1 = 1; index1 < arr.length - 5; index1++) {// 第一刀位置 1~n-6
			if (sumIndex.containsKey(part * 2 + arr[index1])) {
				int index2 = sumIndex.get(part * 2 + arr[index1]);// 第二刀位置
				if (sumIndex.containsKey(part * 3 + arr[index1] + arr[index2])) {
					int index3 = sumIndex.get(part * 3 + arr[index1] + arr[index2]);// 第三刀位置
					if (sum - part * 3 - arr[index1] - arr[index2] - arr[index3] == part) {
						return true;
					}
				}
			}
			part += arr[index1];
		}
		return false;
	}
}
