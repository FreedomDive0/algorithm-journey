package class01;

public class Code03_MSumToN {
	// 1.暴力尝试
	public static boolean isMSum1(int num) {
		if (num < 3) {
			return false;
		}
		for (int i = 1; i <= num; i++) {
			int count = i;
			for (int j = i + 1; j < num && count < num; j++) {
				count += j;
				if (count == num) {
					return true;
				}
			}
		}
		return false;
	}

	// 2.打表法
	public static boolean isMSum2(int num) {
		return (num & (num - 1)) != 0;// 表示数是二的某次幂 使用二进制表示只有1位有1 减1除了那一位之后都变为1相&后变为0
	}

	public static void main(String[] args) {
		for (int i = 1; i <= 100; i++) {
			boolean a = isMSum1(i);
			boolean b = isMSum2(i);
			if (a != b) {
				System.out.println("Opps");
			}
		}
	}
}
