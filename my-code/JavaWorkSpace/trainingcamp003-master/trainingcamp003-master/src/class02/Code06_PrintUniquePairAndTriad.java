package class02;

public class Code06_PrintUniquePairAndTriad {

	public static void printUniquePair(int[] arr, int k) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int left = 0;
		int right = arr.length - 1;
		while (left < right) {
			if (arr[left] + arr[right] < k) {
				left++;
			} else if (arr[left] + arr[right] > k) {
				right--;
			} else { // L + R = aim
				if (left == 0 || arr[left - 1] != arr[left]) {
					System.out.println(arr[left] + "," + arr[right]);
				}
				left++;
				right--;
			}
		}
	}

	public static void printUniqueTriad(int[] arr, int k) {
		if (arr == null || arr.length < 3) {
			return;
		}
		for (int i = 0; i < arr.length - 2; i++) {
			if (i == 0 || arr[i] != arr[i - 1]) {
				printRest(arr, i, i + 1, arr.length - 1, k - arr[i]);
			}
		}
	}

	public static void printRest(int[] arr, int f, int l, int r, int k) {
		while (l < r) {
			if (arr[l] + arr[r] < k) {
				l++;
			} else if (arr[l] + arr[r] > k) {
				r--;
			} else {
				if (l == f + 1 || arr[l - 1] != arr[l]) {
					System.out.println(arr[f] + "," + arr[l] + "," + arr[r]);
				}
				l++;
				r--;
			}
		}
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// 测试
	public static void pair(int[] arr, int aim) {
		if (arr == null || arr.length < 1) {
			return;
		}
		int left = 0;
		int right = arr.length - 1;
		while (left < right) {
			if (arr[left] + arr[right] < aim) {
				left++;
			} else if (arr[left] + arr[right] > aim) {
				right--;
			} else {
				if (left == 0 || (left > 0 && arr[left] != arr[left - 1]))
					System.out.println("(" + arr[left++] + "," + arr[right--] + ")");
			}
		}
	}

	public static void triad(int[] arr, int aim) {
		if (arr == null || arr.length < 1) {
			return;
		}
		for (int i = 0; i < arr.length - 2; i++) {
			int curAim = aim - arr[i];
			int left = i + 1;
			int right = arr.length - 1;
			while (left < right) {
				if (arr[left] + arr[right] < curAim) {
					left++;
				} else if (arr[left] + arr[right] > curAim) {
					right--;
				} else {
					if ((i == 0 || (i > 0 && arr[i] != arr[i - 1]))
							&& (left == 0 || (left > 0 && arr[left] != arr[left - 1])))
						System.out.println("(" + arr[i] + "," + arr[left++] + "," + arr[right--] + ")");
				}
			}
		}
	}

	// 测试
	// 6.有序数组arr[] 正整数aim
	// 6.1 return累加和为aim的所有二元组
	public static void printUniquePair1(int[] arr, int aim) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int left = 0;
		int right = arr.length - 1;
		while (left < right) {
			if (arr[left] + arr[right] < aim) {
				left++;
			} else if (arr[left] + arr[right] > aim) {
				right--;
			} else {
				if (left == 0 || arr[left] != arr[left - 1]) {
					System.out.println(arr[left] + "," + arr[right]);
					left++;
					right--;
				}
			}
		}
	}

	// 6.2 return累加和为aim的所有三元组
	public static void printUniqueTriad1(int[] arr, int aim) {
		if (arr == null || arr.length < 3) {
			return;
		}
		for (int i = 0; i < arr.length - 2; i++) {
			int curAim = aim - arr[i];
			int left = i + 1;
			int right = arr.length - 1;
			while (left < right) {
				if (arr[left] + arr[right] < curAim) {
					left++;
				} else if (arr[left] + arr[right] > curAim) {
					right--;
				} else {
					if (left == 0 || arr[left] != arr[left - 1]) {
						System.out.println(arr[i] + "," + arr[left] + "," + arr[right]);
						left++;
						right--;
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		int sum = 10;
		int[] arr1 = { -8, -4, -3, 0, 1, 2, 4, 5, 8, 9 };
		printArray(arr1);
		System.out.println("====");
		printUniquePair(arr1, sum);
		printUniquePair1(arr1, sum);
		System.out.println("====");
		printUniqueTriad(arr1, sum);
		printUniqueTriad1(arr1, sum);
	}

}
