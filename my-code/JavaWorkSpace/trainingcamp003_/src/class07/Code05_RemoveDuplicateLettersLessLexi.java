package class07;

public class Code05_RemoveDuplicateLettersLessLexi {
	public static String removeToLeast(String s) {
		if (s == null || s.length() < 1) {
			return s;
		}
		return process(s, "");
	}

	public static String process(String s, String cur) {
		if (s.equals("")) {
			return cur;
		}
		int[] times = new int[256];
		char[] chs = s.toCharArray();
		for (int i = 0; i < chs.length; i++) {// 统计词频表
			times[chs[i] - 'a']++;
		}
		int minASCIndex = 0;
		for (int i = 0; i < chs.length; i++) {// 查看最先不足的字符 确定范围
			minASCIndex = chs[minASCIndex] > chs[i] ? i : minASCIndex;// 记录最小ASC码第一次出现的位置
			if (--times[chs[i] - 'a'] == 0) {
				break;
			}
		}
		// 舍去minASCIndex前面的字符串
		return process(s.substring(minASCIndex).replace(String.valueOf(chs[minASCIndex]), ""), cur + chs[minASCIndex]);
	}

	public static void main(String[] args) {
		System.out.println(removeToLeast("abdccaddaaddcccv"));
	}
}
