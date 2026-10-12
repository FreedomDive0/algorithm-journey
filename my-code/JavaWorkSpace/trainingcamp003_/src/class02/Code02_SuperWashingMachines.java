package class02;

public class Code02_SuperWashingMachines {
	// 机器打包
	public static int findMinMoves(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		int n = arr.length;
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		if (sum % n != 0) {// 不满足返回-1
			return -1;
		}
		int avg = sum / n;// 求出每台机器平均值
		int ans = 0;
		int leftSum = 0;
		for (int i = 0; i < arr.length; i++) {
			int leftRest = leftSum - i * avg;// 左右剩余数量 用剩余数量分情况讨论
			int rightRest = (sum - leftSum - arr[i]) - (n - i - 1) * avg;
			if (leftRest < 0 && rightRest < 0) {
				ans = Math.max(ans, Math.abs(leftRest + rightRest));
			} else {
				ans = Math.max(ans, Math.max(Math.abs(leftRest), Math.abs(rightRest)));
			}
			leftSum += arr[i];
		}
		return ans;
	}
}
