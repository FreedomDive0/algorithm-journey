package class06;

import java.util.HashMap;
import java.util.HashSet;

public class Code05_Split4Parts {

	public static boolean canSplits1(int[] arr) {
		if (arr == null || arr.length < 7) {
			return false;
		}
		HashSet<String> set = new HashSet<String>();
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		int leftSum = arr[0];
		for (int i = 1; i < arr.length - 1; i++) {
			set.add(String.valueOf(leftSum) + "_" + String.valueOf(sum - leftSum - arr[i]));
			leftSum += arr[i];
		}
		int l = 1;
		int lsum = arr[0];
		int r = arr.length - 2;
		int rsum = arr[arr.length - 1];
		while (l < r - 3) {
			if (lsum == rsum) {
				String lkey = String.valueOf(lsum * 2 + arr[l]);
				String rkey = String.valueOf(rsum * 2 + arr[r]);
				if (set.contains(lkey + "_" + rkey)) {
					return true;
				}
				lsum += arr[l++];
			} else if (lsum < rsum) {
				lsum += arr[l++];
			} else {
				rsum += arr[r--];
			}
		}
		return false;
	}

	public static boolean canSplits2(int[] arr) {
		if (arr == null || arr.length < 7) {
			return false;
		}
		// key 某一个累加和， value出现的位置
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int sum = arr[0];
		for (int i = 1; i < arr.length; i++) {
			map.put(sum, i);
			sum += arr[i];
		}
		int lsum = arr[0]; // 第一刀左侧的累加和
		for (int s1 = 1; s1 < arr.length - 5; s1++) { // s1是第一刀的位置
			int checkSum = lsum * 2 + arr[s1]; // 100 x 100 100*2 + x
			if (map.containsKey(checkSum)) {
				int s2 = map.get(checkSum); // j -> y
				checkSum += lsum + arr[s2];
				if (map.containsKey(checkSum)) { // 100 * 3 + x + y
					int s3 = map.get(checkSum); // k -> z
					if (checkSum + arr[s3] + lsum == sum) {
						return true;
					}
				}
			}
			lsum += arr[s1];
		}
		return false;
	}

	public static int[] generateRondomArray() {
		int[] res = new int[(int) (Math.random() * 10) + 7];
		for (int i = 0; i < res.length; i++) {
			res[i] = (int) (Math.random() * 10) + 1;
		}
		return res;
	}

	// 测试
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

	// 测试
	// 5.正数数组能不能分成4个累加和相同部分 分割位数值舍弃
	public static boolean splitforParts(int[] arr) {
		if (arr == null || arr.length < 7) {
			return false;
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int sum = arr[0];
		for (int i = 1; i < arr.length; i++) {// 放置的前缀和为 i-1的前缀和 这样才能得到s1的位置 不需要向后取一位
			map.put(sum, i);
			sum += arr[i];
		}
		int lsum = arr[0];
		for (int s1 = 1; s1 < arr.length - 5; s1++) {// 第一刀
			int checkSum = lsum * 2 + arr[s1];
			if (map.containsKey(checkSum)) {// 第二刀
				int s2 = map.get(checkSum);
				checkSum += arr[s2] + lsum;
				if (map.containsKey(checkSum)) {// 第三刀
					int s3 = map.get(checkSum);
					if (checkSum + arr[s3] + lsum == sum) {// 最后一部分是否相同
						return true;
					}
				}
			}
			lsum += arr[s1];// 更新lsum
		}

		return false;
	}

	public static void main(String[] args) {
		int testTime = 3000000;
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRondomArray();
			if (split4Parts(arr) ^ splitforParts(arr)) {
				System.out.println("Error");
			}
		}
	}
}
