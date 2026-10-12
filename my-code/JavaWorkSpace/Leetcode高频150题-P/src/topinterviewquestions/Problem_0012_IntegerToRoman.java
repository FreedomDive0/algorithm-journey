package topinterviewquestions;

public class Problem_0012_IntegerToRoman {
	public static String intToRoman(int num) {
		String[][] c = new String[][] { { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" },
				{ "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" },
				{ "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" }, { "", "M", "MM", "MMM" } };// 对应表一定要写对
		StringBuilder res = new StringBuilder();
		res.append(c[3][num / 1000]).append(c[2][num / 100 % 10]).append(c[1][num / 10 % 10]).append(c[0][num % 10]);
		return res.toString();
	}
}
