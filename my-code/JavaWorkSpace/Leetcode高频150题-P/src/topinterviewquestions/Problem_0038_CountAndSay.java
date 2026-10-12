package topinterviewquestions;

public class Problem_0038_CountAndSay {
	public static String countAndSay(int n) {
		if (n < 1) {
			return "";
		}
		if (n == 1) {
			return "1";
		}
		char[] last = countAndSay(n - 1).toCharArray();
		StringBuilder res = new StringBuilder();
		int times = 1;
		for (int i = 1; i < last.length; i++) {
			if (last[i] == last[i - 1]) {
				times++;
			} else {
				res.append(String.valueOf(times));
				res.append(String.valueOf(last[i - 1]));
				times = 1;
			}
		}
		res.append(String.valueOf(times));
		res.append(String.valueOf(last[last.length - 1]));
		return res.toString();
	}
}
