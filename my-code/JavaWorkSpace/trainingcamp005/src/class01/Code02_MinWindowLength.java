package class01;

public class Code02_MinWindowLength {
	// 字符串str1和str2 求str1子串包含str2所有字符的的最小字串长度
	public static int minSubAll(String str1, String str2) {
		if (str1 == null || str2 == null || str1.length() < str2.length()) {
			return -1;
		}
		int[] map = new int[256];// 数组代替HashMap
		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();
		for (int i = 0; i < str2.length(); i++) {
			map[s2[i]]++;// 欠债表
		}
		int L = 0;// 滑动窗口
		int R = 0;
		int all = str2.length();// 总欠债数 判断是否包含全部字符
		int ans = Integer.MAX_VALUE;
		while (R < str1.length()) {
			map[s1[R]]--;// 还债 可能会有干扰字符 但是在判断条件时 巧妙的消除了
			if (map[s1[R]] >= 0) {// 如果该字符是str2中的字符并且成功偿还了一个(该字符数量为0无法偿还)
				all--;
			}
			if (all == 0) {// 当所有字符全部偿还完 寻找该子串下的最短子串
				while (map[s1[L]] < 0) {// 当前字符还多了或者是干扰字符 但是干扰字符在只有经过了它，它才会变为0 所以不会对结果造成影响 只有在还债表的字符才可能出现>=0的情况
					map[s1[L++]]++;
				}
				ans = Math.min(ans, R - L + 1);
				map[s1[L++]]++;// 向右移动
				all++;// L要向右移动
			}
			R++;
		}
		return ans == Integer.MAX_VALUE ? 0 : ans;
	}
}
