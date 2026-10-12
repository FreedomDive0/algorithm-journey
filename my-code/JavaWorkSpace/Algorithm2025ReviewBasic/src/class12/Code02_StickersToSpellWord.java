package class12;

import java.util.HashMap;

public class Code02_StickersToSpellWord {
	public static int minStickers1(String[] stickers, String target) {
		int[][] stickerMap = new int[stickers.length][26];
		for (int i = 0; i < stickerMap.length; i++) {
			for (char c : stickers[i].toCharArray()) {
				stickerMap[i][c - 'a']++;
			}
		}
		HashMap<String, Integer> dp = new HashMap<String, Integer>();
		dp.put("", 0);
		return processS(stickerMap, dp, target);
	}

	public static int processS(int[][] stickerMap, HashMap<String, Integer> dp, String rest) {
		if (dp.containsKey(rest)) {
			return dp.get(rest);
		}
		char[] target = rest.toCharArray();
		int[] restMap = new int[26];// 将目标字符串转化为数组方便删除字符
		for (char c : target) {
			restMap[c - 'a']++;
		}
		int ways = Integer.MAX_VALUE;
		for (int i = 0; i < stickerMap.length; i++) {
			if (stickerMap[i][target[0] - 'a'] == 0) {// 为了避免无法消除任何字符的停止进入死循环 所以规定先搞定剩余字符串的第一个字符 又因为搞定字符串与顺序无关
				continue;
			}
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < 26; j++) {
				for (int k = 0; k < Math.max(0, restMap[j] - stickerMap[i][j]); k++) {
					sb.append((char) ('a' + j));// 添加字符时要注意添加char
				}
			}
			int next = processS(stickerMap, dp, sb.toString());
			if (next != -1) {
				ways = Math.min(ways, next + 1);
			}
		}
		dp.put(rest, ways == Integer.MAX_VALUE ? -1 : ways);
		return dp.get(rest);
	}

	public static void main(String[] args) {
		String[] arr = { "aaaa", "bbaa", "ccddd" };
		String str = "abcccccdddddbbbaaaaa";
		System.out.println(minStickers1(arr, str));

	}
}
