package class04;

public class Code02_FindKthMinNumber {
	// (1)两个数组A[] B[]长度相等 寻找上中位数
	public static int FindUpMid(int[] A, int aS, int aE, int[] B, int bS, int bE) {
		int s1 = aS;
		int e1 = aE;
		int s2 = bS;
		int e2 = bE;
		while (s1 < e1) {
			int mid1 = (s1 + e1) / 2;
			int mid2 = (s2 + e2) / 2;
			if (A[mid1] == B[mid2]) {
				return A[mid1];
			}
			if (((e1 - s1 + 1) & 1) == 1) {// 个数为奇数
				if (A[mid1] > B[mid2]) {
					if (B[mid2] >= A[mid1 - 1]) {
						return B[mid2];
					}
					e1 = mid1 - 1;
					s2 = mid2 + 1;// 注意下标位置
				} else {
					if (A[mid1] >= B[mid2 - 1]) {
						return A[mid1];
					}
					e2 = mid2 - 1;
					s1 = mid1 + 1;
				}
			} else {// 个数为偶数
				if (A[mid1] > B[mid2]) {
					e1 = mid1;
					s2 = mid2 + 1;
				} else {
					e2 = mid2;
					s1 = mid1 + 1;
				}
			}
		}
		return Math.min(A[s1], B[s2]);
	}

	// (2)两个整数数组 A[]长度m B[]长度n 元素都从小到达排序过 在A，B中找出最大的k个数字
	public static int findKthMin(int[] A, int[] B, int k) {
		if (A == null || B == null) {
			return -1;
		}
		if (k < 1 || A.length + B.length < k) {
			return -1;
		}
		int[] shortArr = A.length < B.length ? A : B;
		int[] longArr = A.length > B.length ? A : B;
		int s = shortArr.length;
		int l = longArr.length;
		if (k <= s) {// k<=短数组长度
			return FindUpMid(shortArr, 0, k - 1, longArr, 0, k - 1);
		}
		if (k > l) {
			if (shortArr[k - l - 1] >= longArr[l - 1]) {
				return shortArr[k - l - 1];
			}
			if (longArr[k - s - 1] >= shortArr[s - 1]) {
				return longArr[k - s - 1];
			}
			return FindUpMid(shortArr, k - l, s - 1, B, k - s, l - 1);
		} else {
			if (longArr[k - s - 1] >= shortArr[s - 1]) {
				return longArr[k - s - 1];
			}
			return FindUpMid(shortArr, 0, s - 1, longArr, k - s, k - 1);
		}
	}
}
