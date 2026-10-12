package class01;

public class Code02_EatGrass {
	// n份青草放在一堆
	// 先手后手都绝顶聪明
	// string "先手" "后手"
	// 1.暴力尝试
	public static String winnerBruteForce(int n) {
		if (n < 5) {
			return n == 2 || n == 0 ? "后手" : "先手";
		}
		int cowEat = 1;
		while (cowEat < n) {
			if (winnerBruteForce(n - cowEat).equals("后手")) {
				return "先手";
			}
			if (cowEat > n / 4) {
				break;
			}
			cowEat <<= 2;
		}
		return "后手";
	}

	// 2.打表法
	public static String winner(int n) {
		return n % 5 == 0 || n % 5 == 2 ? "后手" : "先手";
	}

	public static void main(String[] args) {
		for (int i = 0; i <= 100; i++) {
			String a = winnerBruteForce(i);
			String b = winner(i);
			if (!a.equals(b)) {
				System.out.println("Opps");
			}
		}
	}
}
