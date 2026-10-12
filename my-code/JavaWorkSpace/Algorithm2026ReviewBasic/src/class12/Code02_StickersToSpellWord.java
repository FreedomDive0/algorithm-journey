package class12;

import java.util.HashMap;

public class Code02_StickersToSpellWord {
	// 拼贴纸
	public static int minStickers1(String[] stickers, String target) {
		if (stickers == null || stickers.length < 1 || stickers[0].length() < 1 || target == null) {
			return 0;
		}
		int N = stickers.length;
		int[][] stickersMap = new int[N][26];
		for (int i = 0; i < stickers.length; i++) {
			for (char c : stickers[i].toCharArray()) {
				stickersMap[i][c - 'a']++;
			}
		}
		HashMap<String, Integer> dp = new HashMap<String, Integer>();
		dp.put("", 0);
		return mSProcess(stickersMap, target, dp);
	}

	public static int mSProcess(int[][] stickersMap, String rest, HashMap<String, Integer> dp) {
		if (dp.containsKey(rest)) {
			return dp.get(rest);
		}
		char[] restArr = rest.toCharArray();
		int[] restCount = new int[26];
		for (char c : restArr) {
			restCount[c - 'a']++;
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < stickersMap.length; i++) {
			if (stickersMap[i][restArr[0] - 'a'] == 0) {
				continue;
			}
			StringBuilder sb = new StringBuilder();
			for (int k = 0; k < 26; k++) {
				for (int count = 0; count < restCount[k] - stickersMap[i][k]; count++) {
					sb.append((char) (k + 'a'));// 拼字符为k+'a' 数量为restCount[k] - stickersMap[i][k]
				}
			}
			int next = mSProcess(stickersMap, sb.toString(), dp);
			if (next != -1) {
				min = Math.min(min, next + 1);
			}
		}
		min = min == Integer.MAX_VALUE ? -1 : min;
		dp.put(rest, min);
		return dp.get(rest);
	}

	public static void main(String[] args) {
		String[] stickers = new String[] { "with", "example", "science" };
		String target = "thehat";
		System.out.println(minStickers1(stickers, target));
	}
}
