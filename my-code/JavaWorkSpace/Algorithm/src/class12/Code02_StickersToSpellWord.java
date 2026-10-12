package class12;

import java.util.HashMap;

public class Code02_StickersToSpellWord {
	public static int leastStickers1(String[] stickers, String aim) {
		int N = stickers.length;
		int[][] map = new int[N][26];
//		将贴纸数字化 既排序 又方便操作
		for (int i = 0; i < N; i++) {
			char[] chs = stickers[i].toCharArray();
			for (int j = 0; j < chs.length; j++) {
				map[i][chs[j] - 'a']++;
			}
		}
		HashMap<String, Integer> dp = new HashMap<String, Integer>();
		dp.put("", 0);
		return process1(map, aim, dp);
	}

	public static int process1(int[][] map, String rest, HashMap<String, Integer> dp) {
		if (dp.containsKey(rest)) {
			return dp.get(rest);
		}
		int N = map.length;
		int ans = Integer.MAX_VALUE;
		int[] aims = new int[26];// 当前目标
		char[] chs = rest.toCharArray();
		for (char c : chs) {
			aims[c - 'a']++;
		}
		for (int i = 0; i < N; i++) {
//			以每一张贴纸为开头开始尝试
			if (map[i][chs[0] - 'a'] == 0) {// 因为贴纸使用顺序不影响结果 为了防止该号贴纸不包含any目标字符而进入死循环
				continue;
			}
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < 26; j++) {
				if (aims[j] > 0) {
					// 贴纸与目标字符串中该字符相差多少个
					for (int k = 0; k < Math.max(0, aims[j] - map[i][j]); k++) {
//						注意要转换成字符添加 向更高级转型变为int 所以要转成char
						sb.append((char) ('a' + j));// 组成剩余目标字符串
					}
				}
			}
			String s = sb.toString();
			int tmp = process1(map, s, dp);// 以该号贴纸为开头剩余部分所需最小贴纸数是多少
			if (tmp != -1) {
				ans = Math.min(ans, tmp + 1);
			}
		}
		dp.put(rest, ans == Integer.MAX_VALUE ? -1 : ans);// 第一个-1 全部贴纸continue ans未被赋值
		return dp.get(rest);
	}

	// 复习
	// 3.贴纸问题
	public static int Stickers(String[] stricker, String target) {
		if (stricker == null || target == null) {
			return 0;
		}
		int[][] map = new int[stricker.length][26];
		for (int i = 0; i < map.length; i++) {// 将贴纸变为数组形式方便使用
			for (char c : stricker[i].toCharArray()) {
				map[i][c - 'a']++;
			}
		}
		HashMap<String, Integer> dp = new HashMap<>();// 因为参数为字符串形式数组不好表达
		dp.put("", 0);
		processST(map, target, dp);
		return dp.get(target);
	}

	public static int processST(int[][] map, String rest, HashMap<String, Integer> dp) {
		if (dp.containsKey(rest)) {
			return dp.get(rest);
		}
		int res = Integer.MAX_VALUE;
		int[] target = new int[26];
		for (int c : rest.toCharArray()) {
			target[c - 'a']++;
		}
		for (int[] stricker : map) {
			if (stricker[rest.toCharArray()[0] - 'a'] == 0) {// 防止遇到无效贴纸陷入死循环
				continue;
			}
			StringBuilder sb = new StringBuilder();
			for (int k = 0; k < target.length; k++) {
				if (target[k] > 0) {// 有需求
					for (int i = 0; i < Math.max(target[k] - stricker[k], 0); i++) {// 将剩余字符添加进rest
						sb.append((char) ('a' + k));
					}
				}
			}
			String s = sb.toString();
			int tmp = processST(map, s, dp);
			if (tmp != -1) {
				res = Math.min(tmp + 1, res);
			}
		}
		dp.put(rest, res == Integer.MAX_VALUE ? -1 : res);
		return res;
	}

	public static void main(String[] args) {
		String[] arr = { "aaaa", "bbaa", "ccddd" };
		String str = "abcccccdddddbbbaaaaa";
		System.out.println(leastStickers1(arr, str));
		System.out.println(Stickers(arr, str));

	}
}
