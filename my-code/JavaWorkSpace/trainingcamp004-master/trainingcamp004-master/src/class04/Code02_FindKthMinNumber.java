package class04;

import java.util.Arrays;

public class Code02_FindKthMinNumber {

	public static int findKthNum(int[] arr1, int[] arr2, int kth) {
		if (arr1 == null || arr2 == null) {
			return -1;
		}
		if (kth < 1 || kth > arr1.length + arr2.length) {
			return -1;
		}
		int[] longs = arr1.length >= arr2.length ? arr1 : arr2;
		int[] shorts = arr1.length < arr2.length ? arr1 : arr2;
		int l = longs.length;
		int s = shorts.length;
		if (kth <= s) {
			return getUpMedian(shorts, 0, kth - 1, longs, 0, kth - 1);
		}
		if (kth > l) {
			if (shorts[kth - l - 1] >= longs[l - 1]) {
				return shorts[kth - l - 1];
			}
			if (longs[kth - s - 1] >= shorts[s - 1]) {
				return longs[kth - s - 1];
			}
			return getUpMedian(shorts, kth - l, s - 1, longs, kth - s, l - 1);
		}
		// 短数组长度 < k <= 长数组长度
		if (longs[kth - s - 1] >= shorts[s - 1]) {
			return longs[kth - s - 1];
		}
		return getUpMedian(shorts, 0, s - 1, longs, kth - s, kth - 1);
	}

	// A[s1...e1]
	// B[s2...e2]
	// 这两段一定等长且都有序
	// 求这两段整体的上中位数，上中位数值返回
	public static int getUpMedian(int[] A, int s1, int e1, int[] B, int s2, int e2) {
		int mid1 = 0;
		int mid2 = 0;
		while (s1 < e1) {
			mid1 = (s1 + e1) / 2;
			mid2 = (s2 + e2) / 2;
			if (A[mid1] == B[mid2]) {
				return A[mid1];
			}
			if (((e1 - s1 + 1) & 1) == 1) { // 奇数长度
				if (A[mid1] > B[mid2]) {
					if (B[mid2] >= A[mid1 - 1]) {
						return B[mid2];
					}
					e1 = mid1 - 1;
					s2 = mid2 + 1;
				} else { // A[mid1] < B[mid2]
					if (A[mid1] >= B[mid2 - 1]) {
						return A[mid1];
					}
					e2 = mid2 - 1;
					s1 = mid1 + 1;
				}
			} else { // 偶数长度
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

	// For test, this method is inefficient but absolutely right
	public static int[] getSortedAllArray(int[] arr1, int[] arr2) {
		if (arr1 == null || arr2 == null) {
			throw new RuntimeException("Your arr is invalid!");
		}
		int[] arrAll = new int[arr1.length + arr2.length];
		int index = 0;
		for (int i = 0; i != arr1.length; i++) {
			arrAll[index++] = arr1[i];
		}
		for (int i = 0; i != arr2.length; i++) {
			arrAll[index++] = arr2[i];
		}
		Arrays.sort(arrAll);
		return arrAll;
	}

	public static int[] generateSortedArray(int len, int maxValue) {
		int[] res = new int[len];
		for (int i = 0; i != len; i++) {
			res[i] = (int) (Math.random() * (maxValue + 1));
		}
		Arrays.sort(res);
		return res;
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// 测试
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

	public static void main(String[] args) {
		for (int i = 0; i < 500000; i++) {
			int len1 = 10;
			int len2 = 23;
			int maxValue1 = 20;
			int maxValue2 = 100;
			int[] arr1 = generateSortedArray(len1, maxValue1);
			int[] arr2 = generateSortedArray(len2, maxValue2);
//			printArray(arr1);
//			printArray(arr2);
			int[] sortedAll = getSortedAllArray(arr1, arr2);
//			printArray(sortedAll);
			int kth = 17;
			int r1 = findKthNum(arr1, arr2, kth);
			int r2 = findKthMin(arr1, arr2, kth);
			if (r1 != r2) {
				System.out.println("Shitttttttttttt");
				break;
			}
//			System.out.println();
//			System.out.println(sortedAll[kth - 1]);
//			int r1 = getUpMedian(arr1, 0, arr1.length - 1, arr2, 0, arr2.length - 1);
//			int r2 = FindUpMid(arr1, 0, arr1.length - 1, arr2, 0, arr2.length - 1);

		}

	}

}
