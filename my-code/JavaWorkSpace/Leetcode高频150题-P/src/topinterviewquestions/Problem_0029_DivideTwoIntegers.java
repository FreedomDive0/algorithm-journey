package topinterviewquestions;

public class Problem_0029_DivideTwoIntegers {
	public static int add(int a, int b) {// 加法实现
		int sum = a;
		while (b != 0) {
			sum = a ^ b;
			b = (a & b) << 1;
			a = sum;
		}
		return sum;
	}

	public static int negNum(int n) {// 相反数
		return add(~n, 1);
	}

	public static int minus(int a, int b) {// 减法
		return add(a, negNum(b));
	}

	public static int multi(int a, int b) {// 乘法
		int res = 0;
		while (b != 0) {
			if ((b & 1) != 0) {
				res = add(res, a);
			}
			a <<= 1;
			b >>>= 1;// 无符号位移
		}
		return res;
	}

	public static int divide(int dividend, int divisor) {// 除法
		if (divisor == Integer.MIN_VALUE) {// 因为负数范围比正数大1 需要额外判断
			return dividend == Integer.MIN_VALUE ? 1 : 0;
		}
		if (dividend == Integer.MIN_VALUE) {
			if (divisor == negNum(1)) {
				return Integer.MAX_VALUE;
			}
			int res = div(add(dividend, 1), divisor);// 如果被除数为Min 则将被除数+1缩小范围先计算一部分
			return add(res, div(minus(dividend, multi(divisor, res)), divisor));// 再与剩余部分求和
		}
		return div(dividend, divisor);
	}

	public static int div(int a, int b) {// 除法按都按正数算 最后加符号
		int x = isNeg(a) ? negNum(a) : a;
		int y = isNeg(b) ? negNum(b) : b;
		int res = 0;
		for (int i = 31; i >= 0; i = minus(i, 1)) {// 每一位直接尝试
			if ((x >> i) >= y) {
				res |= 1 << i;
				x = minus(x, y << i);
			}
		}
		return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
	}

	public static boolean isNeg(int n) {
		return n < 0;
	}

	public static void main(String[] args) {
		System.out.println(divide(10, 5));
	}
}
