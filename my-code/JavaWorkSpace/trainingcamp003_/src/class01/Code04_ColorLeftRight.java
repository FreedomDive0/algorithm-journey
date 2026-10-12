package class01;

public class Code04_ColorLeftRight {
	// 涂房子Red Green两种颜色 所有G都在R右侧 最少涂几个房子
	public static int minRedPlusGreen(String s) {
		if (s == null || s.length() < 1) {
			return 0;
		}
		int N = s.length();
		char[] str = s.toCharArray();
		int red = 0;// 分界线右侧的红色数量
		for (int i = 0; i < N; i++) {
			red += str[i] == 'R' ? 1 : 0;
		}
		int ans = red;
		int green = 0;
		for (int i = 0; i < N; i++) {
			red -= str[i] == 'R' ? 1 : 0;
			green += str[i] == 'G' ? 1 : 0;
			ans = Math.min(ans, red + green);
		}
		return ans;
	}
}
