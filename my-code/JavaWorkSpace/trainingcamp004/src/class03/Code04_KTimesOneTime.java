package class03;

public class Code04_KTimesOneTime {
	public static int KTOT(int[] arr, int K) {
		int[] res = new int[32];
		for (int i = 0; i < arr.length; i++) {
			set(res, arr[i], K);
		}
		int ans = kToTen(res, K);
		return ans;
	}

	public static void set(int[] res, int value, int K) {
		int[] tmp = tenToK(value, K);
		for (int i = 0; i < tmp.length; i++) {
			res[i] = (res[i] + tmp[i]) % K;// 每一步取余
		}
	}

	public static int[] tenToK(int value, int K) {
		int[] ans = new int[32];
		int index = 0;
		while (value > 0) {
			ans[index++] = value % K;
			value = value / K;
		}
		return ans;
	}

	public static int kToTen(int[] res, int K) {
		int ans = 0;
		for (int i = res.length - 1; i >= 0; i--) {
			ans = ans * K + res[i];
		}
		return ans;
	}
}
