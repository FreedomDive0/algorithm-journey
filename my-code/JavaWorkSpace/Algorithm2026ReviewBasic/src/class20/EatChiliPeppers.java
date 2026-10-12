package class20;

public class EatChiliPeppers {
	// 吃辣椒每次可以吃1~3个吃到最后一个的人获胜
	public static boolean eatChiliPeppers(int peppersCount) {
		if (first(peppersCount))
			return true;
		else
			return false;
	}

	public static boolean first(int peppersCount) {
		if (peppersCount <= 3) {
			return true;
		}
		for (int i = 1; i <= 3; i++) {
			if (second(peppersCount - i))
				return true;
		}
		return false;
	}

	public static boolean second(int peppersCount) {
		if (peppersCount <= 3) {
			return false;
		}
		for (int i = 1; i <= 3; i++) {
			if (!first(peppersCount - i))
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(eatChiliPeppers(20) ? "先手" : "后手");
	}
}
