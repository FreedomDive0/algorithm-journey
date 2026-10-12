package class06;

import java.util.HashMap;

public class Code02_MostEOR {
	// 最多异或和为0部分
	public static int mostEOR(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		int N = arr.length;
		int[] dp = new int[N];
		HashMap<Integer, Integer> indexMap = new HashMap<>();// 存放异或和最右位置
		indexMap.put(0, -1);// 初始化
		int eor = 0;// 存放0~i的异或和
		for (int i = 0; i < N; i++) {// 从0开始
			eor ^= arr[i];
			if (indexMap.containsKey(eor)) {// 存在异或和为eor的位置
				dp[i] = indexMap.get(eor) == -1 ? 1 : dp[indexMap.get(eor)] + 1;// index==-1 说明第一次出现eor等于1
			}
			if (i > 0) {
				dp[i] = Math.max(dp[i], dp[i - 1]);
			}
			indexMap.put(eor, i);// 记录或更新该位置值
		}
		return dp[N - 1];
	}
}
