package HW;

import java.util.ArrayList;
import java.util.Scanner;

public class 其他 {
//	1.正整数A和正整数B 的最小公倍数是指 能被A和B整除的最小的正整数值，设计一个算法，求输入A和B的最小公倍数。
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		String str = br.readLine();
//		String[] s = str.split(" ");
//		int a = Integer.valueOf(s[0]);
//		int b = Integer.valueOf(s[1]);
//		int c = Math.max(a, b);
//		while (!(c % a == 0 && c % b == 0)) {
//			c++;
//		}
//		System.out.println(c);
//	}
//	2.题目描述:若两个正整数的和为素数，则这两个正整数称之为“素数伴侣”，如2和5、6和13，它们能应用于通信加密。现在密码学会请你设计一个程序，从已有的 N （ N 为偶数）个正整数中挑选出若干对组成“素数伴侣”，挑选方案多种多样，例如有4个正整数：2，5，6，13，如果将5和6分为一组中只能得到一组“素数伴侣”，而将2和5、6和13编组将得到两组“素数伴侣”，能组成“素数伴侣”最多的方案称为“最佳方案”，当然密码学会希望你寻找出“最佳方案”。
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int n = in.nextInt();
			int[] tempArr = new int[n];
			for (int i = 0; i < n; i++) {
				tempArr[i] = in.nextInt();
			}
			ArrayList<Integer> evens = new ArrayList<Integer>();
			ArrayList<Integer> odds = new ArrayList<Integer>();
			for (int i = 0; i < tempArr.length; i++) {
				if ((tempArr[i] & 1) == 1) {
					odds.add(tempArr[i]);
				} else {
					evens.add(tempArr[i]);
				}
			}
			int[] matchEven = new int[evens.size()];
			int res = 0;
			for (int i = 0; i < odds.size(); i++) {
				int[] used = new int[evens.size()];
				if (find(odds.get(i), used, matchEven, evens)) {
					res++;
				}
			}
			System.out.println(res);
		}
	}

	public static boolean isPrime(int num) {
		for (int i = 2; i * i <= num; i++) {
			if (num % i == 0) {
				return false;
			}
			if (num == 1) {
				return false;
			}
		}
		return true;
	}

	public static boolean find(int x, int[] used, int[] matchEven, ArrayList<Integer> evens) {
		for (int i = 0; i < matchEven.length; i++) {
			if (isPrime(x + evens.get(i)) && (used[i] == 0)) {
				used[i] = 1;
				if (matchEven[i] == 0 || find(matchEven[i], used, matchEven, evens)) {
					matchEven[i] = x;
					return true;
				}
			}
		}
		return false;
	}
}
