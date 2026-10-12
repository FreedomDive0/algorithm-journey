package 滑动窗口;

public class Problem_0076_Minimum_Window_Substring {
	public String minWindow(String s, String t) {
		if (s.length() < t.length()) {
			return "";
		}
		int[] map = new int[128];
		int l = 0;// [l,r] 一开始r未进入
		int r = 0;
		int minLen = -1;
		int all = t.length();
		int ansl = 0;
		int ansr = 0;
		for (char c : t.toCharArray()) {// 欠债表
			map[c]++;
		}
		while (r < s.length()) {
			map[s.charAt(r)]--;
			if (map[s.charAt(r)] >= 0) {// 有效还账
				all--;
			}
			if (all == 0) {// 记录答案
				while (map[s.charAt(l)] < 0) {// 剔除无效还账
					map[s.charAt(l++)]++;
				}
				if (minLen == -1 || minLen > r - l + 1) {
					minLen = r - l + 1;
					ansl = l;
					ansr = r;
				}
				map[s.charAt(l++)]++;
				all++;
			}
			r++;
		}
		return minLen == -1 ? "" : s.substring(ansl, ansr + 1);
	}
}
