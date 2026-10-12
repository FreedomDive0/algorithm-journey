package class12;

import java.util.Arrays;
import java.util.HashMap;

public class Code02_StickersToSpellWord {

	public static int minStickers1(String[] stickers, String target) {

		int n = stickers.length;

		int[][] map = new int[n][26];// stickers -> [26] [26] [26]
		for (int i = 0; i < n; i++) {
			char[] str = stickers[i].toCharArray();
			for (char c : str) {
				map[i][c - 'a']++;
			}
		}
		HashMap<String, Integer> dp = new HashMap<>();
		dp.put("", 0);
		return process1(dp, map, target);
	}

	// dp 傻缓存，如果t已经算过了，直接返回dp中的值
	// t 剩余的目标
	// 0..N每一个字符串所含字符的词频统计
	// 返回值是-1，map 中的贴纸 怎么都无法rest
	public static int process1(HashMap<String, Integer> dp, int[][] map, String rest) {
		if (dp.containsKey(rest)) {
			return dp.get(rest);
		}
		// 以下就是正式的递归调用过程
		int ans = Integer.MAX_VALUE; // ans -> 搞定rest，使用的最少的贴纸数量
		int n = map.length; // N种贴纸
		int[] tmap = new int[26]; // tmap 去替代 rest
		char[] target = rest.toCharArray();
		for (char c : target) {
			tmap[c - 'a']++;
		}
		for (int i = 0; i < n; i++) {
			// 枚举当前第一张贴纸是谁？
			if (map[i][target[0] - 'a'] == 0) {
				continue;
			}
			StringBuilder sb = new StringBuilder();
			// i 贴纸， j 枚举a~z字符
			for (int j = 0; j < 26; j++) { //
				if (tmap[j] > 0) { // j这个字符是target需要的
					for (int k = 0; k < Math.max(0, tmap[j] - map[i][j]); k++) {
						sb.append((char) ('a' + j));
					}
				}
			}
			// sb -> i
			String s = sb.toString();
			int tmp = process1(dp, map, s);
			if (tmp != -1) {
				ans = Math.min(ans, 1 + tmp);
			}
		}
		// ans 系统最大 rest
		dp.put(rest, ans == Integer.MAX_VALUE ? -1 : ans);
		return dp.get(rest);
	}

	public static int minStickers2(String[] stickers, String target) {
		int n = stickers.length;
		int[][] map = new int[n][26];
		for (int i = 0; i < n; i++) {
			char[] str = stickers[i].toCharArray();
			for (char c : str) {
				map[i][c - 'a']++;
			}
		}
		char[] str = target.toCharArray();
		int[] tmap = new int[26];
		for (char c : str) {
			tmap[c - 'a']++;
		}
		HashMap<String, Integer> dp = new HashMap<>();
		int ans = process2(map, 0, tmap, dp);
		return ans;
	}

//	i为第几张贴纸
	public static int process2(int[][] map, int i, int[] tmap, HashMap<String, Integer> dp) {
		StringBuilder keyBuilder = new StringBuilder();
		keyBuilder.append(i + "_");
		for (int asc = 0; asc < 26; asc++) {// 把目标字符串变为i_'a'_a的个数_...
			if (tmap[asc] != 0) {
				keyBuilder.append((char) (asc + 'a') + "_" + tmap[asc] + "_");
			}
		}
		String key = keyBuilder.toString();
		if (dp.containsKey(key)) {
			return dp.get(key);
		}
		boolean finish = true;
		for (int asc = 0; asc < 26; asc++) {// 检验是否还有剩余
			if (tmap[asc] != 0) {
				finish = false;
				break;
			}
		}
		if (finish) {
			dp.put(key, 0);
			return 0;
		}
		if (i == map.length) {// 到最后都未完成
			dp.put(key, -1);
			return -1;
		}
		int maxZhang = 0;
		for (int asc = 0; asc < 26; asc++) {
//			求出最多可以使用该帖纸多少次 把所有能完成的全部完成
			if (map[i][asc] != 0 && tmap[asc] != 0) {// 目标中字符 i号贴纸含有 全部凑齐需要多少张
//				能整除即 整除张数 不能整除+1
				maxZhang = Math.max(maxZhang, (tmap[asc] / map[i][asc]) + (tmap[asc] % map[i][asc] == 0 ? 0 : 1));
			}
		}
		int[] backup = Arrays.copyOf(tmap, tmap.length);// 保存目标
		int min = Integer.MAX_VALUE;
		int next = process2(map, i + 1, tmap, dp);
		tmap = Arrays.copyOf(backup, backup.length);// 还原目标
		if (next != -1) {
			min = next;
		}
		for (int zhang = 1; zhang <= maxZhang; zhang++) {
			for (int asc = 0; asc < 26; asc++) {
				tmap[asc] = Math.max(0, tmap[asc] - (map[i][asc] * zhang));
			}
			next = process2(map, i + 1, tmap, dp);
			tmap = Arrays.copyOf(backup, backup.length);
			if (next != -1) {
				min = Math.min(min, zhang + next);
			}
		}
		int ans = min == Integer.MAX_VALUE ? -1 : min;
		dp.put(key, ans);
		return ans;
	}

	// 测试
	// 3.贴纸问题
	public static int stickersToWord(String[] stickers, String word) {
		int n = stickers.length;
		int[][] strickerMap = new int[n][26];
		for (int i = 0; i < n; i++) {
			for (char c : stickers[i].toCharArray()) {
				strickerMap[i][c - 'a']++;
			}
		}
		HashMap<String, Integer> dp = new HashMap<String, Integer>();
		dp.put("", 0);
		processSTW(strickerMap, word, dp);
		return dp.get(word);
	}

	public static int processSTW(int[][] strickerMap, String rest, HashMap<String, Integer> dp) {
		if (dp.containsKey(rest)) {
			return dp.get(rest);
		}
		int[] tmap = new int[26];
		char[] target = rest.toCharArray();
		for (char c : target) {
			tmap[c - 'a']++;
		}
		int res = Integer.MAX_VALUE;
		for (int i = 0; i < strickerMap.length; i++) {
			if (strickerMap[i][target[0] - 'a'] == 0) {
				continue;
			}
			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < tmap.length; j++) {
				for (int k = 0; k < Math.max(0, tmap[j] - strickerMap[i][j]); k++) {
					sb.append((char) ('a' + j));
				}
			}
			int next = processSTW(strickerMap, sb.toString(), dp);
			if (next != -1) {
				res = Math.min(res, next + 1);
			}
		}
		dp.put(rest, res == Integer.MAX_VALUE ? -1 : res);
		return dp.get(rest);
	}

	public static void main(String[] args) {
		String[] arr = { "aaaa", "bbaa", "ccddd" };
		String str = "abcccccdddddbbbaaaaa";
		System.out.println(stickersToWord(arr, str));
		System.out.println(minStickers2(arr, str));

	}

}
