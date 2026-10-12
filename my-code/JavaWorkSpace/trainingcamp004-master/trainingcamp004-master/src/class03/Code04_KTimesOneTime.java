package class03;

public class Code04_KTimesOneTime {

	public static int onceNum(int[] arr, int k) {
		int[] eO = new int[32];
		for (int i = 0; i != arr.length; i++) {
			// 当前数是arr[i], 请把arr[i]变成K进制的形式，每一位累加到eO
			setExclusiveOr(eO, arr[i], k);
		}
		int res = getNumFromKSysNum(eO, k);
		return res;
	}

	public static void setExclusiveOr(int[] eO, int value, int k) {
		int[] curKSysNum = getKSysNumFromNum(value, k);
		for (int i = 0; i != eO.length; i++) {
			eO[i] = (eO[i] + curKSysNum[i]) % k;
		}
	}

	public static int[] getKSysNumFromNum(int value, int k) {
		int[] res = new int[32];
		int index = 0;
		while (value != 0) {
			res[index++] = value % k;
			value = value / k;
		}
		return res;
	}

	public static int getNumFromKSysNum(int[] eO, int k) {
		int res = 0;
		for (int i = eO.length - 1; i != -1; i--) {
			res = res * k + eO[i];
		}
		return res;
	}

	// 测试
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

	public static void main(String[] args) {
		int[] test1 = { 1, 1, 1, 2, 6, 6, 2, 2, 10, 10, 10, 12, 12, 12, 6, 9 };
		System.out.println(onceNum(test1, 3));
		System.out.println(KTOT(test1, 3));
		int[] test2 = { -1, -1, -1, -1, -1, 2, 2, 2, 4, 2, 2 };
		System.out.println(onceNum(test2, 5));
		System.out.println(KTOT(test2, 5));
	}

}