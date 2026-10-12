package class03;

public class Code03_RandToRand {

	public static int g(int a, int b, int c, int d) {
		int range = d - c;// c到d差多少
		int count = 1;
		while ((1 << count) - 1 < range) {// 减掉初始的1
			count++;
		}
		int res = 0;
		do {
			res = 0;
			for (int i = 0; i < count; i++) {
				res += (random01(a, b) << i);
			}
		} while (res > range);
		return res + 1;
	}

	public static int random01(int a, int b) {
		int count = b - a + 1;
		boolean odd = count % 2 != 0;// 偶数even 奇数odd
		int mid = count / 2;
		int res = f(a, b) - a;
		while (odd && res == mid) {
			res = f(a, b) - a;
		}
		return res < mid ? 0 : 1;
	}

	public static int f(int a, int b) {
		return a + (int) (Math.random() * (b - a + 1));
	}

	public static int rand10() {
		int count = 5;
		int res = 0;
		do {
			res = 0;
			for (int i = 0; i < count; i++) {
				int tmp = random011(1, 7) << i;
				System.out.println("i: " + i + " tmp: " + tmp);
				res += tmp;
			}
		} while (res > 10);
		return res + 1;
	}

	public static int random011(int a, int b) {
		System.out.println("---------------Start---------------");
		int count = b - a + 1;
		System.out.println("count: " + count);
		boolean even = count % 2 == 0;// 偶数even 奇数odd
		System.out.println("even: " + even);
		int mid = a + (b - a) >> 1;
		System.out.println("mid: " + mid);
		int res = rand7();
		while (!even && res == b) {
			res = rand7();
			System.out.println("res: " + res);
		}
		System.out.println("---------------End---------------");
		return res + a <= mid ? 0 : 1;
	}

	public static int rand7() {
		return f(1, 7);
	}

	public static void main(String[] args) {
		rand10();
	}
}
