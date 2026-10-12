package HW;

public class 双指针 {
//	1.给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
//	public int findLengthOfLCIS(int[] nums) {
//		if (nums.length < 2) {
//			return nums.length;
//		}
//		int ans = 1;
//		int cnt = 1;
//		for (int i = 0; i < nums.length - 1; i++) {
//			if (nums[i + 1] > nums[i]) {
//				cnt++;
//			} else {
//				cnt = 1;
//			}
//			ans = cnt > ans ? cnt : ans;
//		}
//		return ans;
//	}
//	2.给你一个字符串 s，找到 s 中最长的回文子串。
//	public String longestPalindrome(String s) {
//		if (s.equals("")) {
//			return s;
//		}
//		String orgin = s;
//		String resver = new StringBuffer(s).reverse().toString();
//		int[][] arr = new int[s.length()][s.length()];
//		int maxLen = 0;
//		int maxEnd = 0;
//		for (int i = 0; i < arr.length; i++) {
//			for (int j = 0; j < arr.length; j++) {
//				if (orgin.charAt(i) == resver.charAt(j)) {
//					if (i == 0 || j == 0) {
//						arr[i][j] = 1;
//					} else {
//						arr[i][j] = arr[i - 1][j - 1] + 1;
//					}
//					if (arr[i][j] > maxLen) {
//						int breIndex = s.length() - 1 - j;
//						if (breIndex + arr[i][j] - 1 == i) {
//							maxLen = arr[i][j];
//							maxEnd = i;
//						}
//					}
//				}
//			}
//		}
//		return orgin.substring(maxEnd - maxLen + 1, maxEnd + 1);
//	}
//	3.
}
