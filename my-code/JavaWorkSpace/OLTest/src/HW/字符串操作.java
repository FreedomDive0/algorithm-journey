package HW;

import java.util.Scanner;

public class 字符串操作 {
//	1.开发一个坐标计算工具， A表示向左移动，D表示向右移动，W表示向上移动，S表示向下移动。从（0,0）点开始移动，从输入字符串里面读取一些坐标，并将最终输入结果输出到输出文件里面。
//	public static void main(String[] args) throws IOException {
//		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//		String[] in = reader.readLine().split(";");
//		int x = 0, y = 0;
//		for (String s : in) {
//			if (!s.matches("[WASD][0-9]{1,2}")) {
//				continue;
//			}
//			int val = Integer.valueOf(s.substring(1));
//			switch (s.charAt(0)) {
//			case 'A':
//				x -= val;
//				break;
//			case 'D':
//				x += val;
//				break;
//			case 'W':
//				y += val;
//				break;
//			case 'S':
//				y -= val;
//				break;
//			}
//		}
//		System.out.println(x + "," + y);
//	}
//	2.密码要求：1.长度超过8位2.包括大小写字母.数字.其它符号,以上四种至少三种3.不能有长度大于2的包含公共元素的子串重复 （注：其他符号不含空格或换行）
//	public static void main(String[] args) throws IOException {
//		Scanner in = new Scanner(System.in);
//		while(in.hasNext()) {
//			String input = in.nextLine();
//			char[] chars = in.nextLine().toCharArray();
//			if (isLengthOK(input) && isTypeOK(chars) && isRepeat(input)) {
//				System.out.println("OK");
//			}
//			System.out.println("NG");
//		}
//	}
//
//	public static boolean isLengthOK(String str) {
//		if (str.length() < 9) {
//			return false;
//		}
//		return true;
//	}
//
//	public static boolean isTypeOK(char[] chars) {
//		if (isLowLetter(chars) + isDig(chars) + isUpLetter(chars) + isOther(chars) < 3) {
//			return false;
//		}
//		return true;
//	}
//
//	public static boolean isRepeat(String str) {
//		for (int i = 0; i < str.length() - 4; i++) {
//			String sub = str.substring(i, i + 3);
//			String str1 = str.substring(i + 1);
//			if (str1.contains(sub)) {
//				return false;
//			}
//		}
//		return true;
//	}
//
//	public static int isUpLetter(char[] chars) {
//		for (char c : chars) {
//			if (Character.isUpperCase(c)) {
//				return 1;
//			}
//		}
//		return 0;
//	}
//
//	public static int isLowLetter(char[] chars) {
//		for (char c : chars) {
//			if (Character.isLowerCase(c)) {
//				return 1;
//			}
//		}
//		return 0;
//	}
//
//	public static int isDig(char[] chars) {
//		for (char c : chars) {
//			if (Character.isDigit(c)) {
//				return 1;
//			}
//		}
//		return 0;
//	}
//
//	public static int isOther(char[] chars) {
//		for (char c : chars) {
//			if (!Character.isLetterOrDigit(c)) {
//				return 1;
//			}
//		}
//		return 0;
//	}
//	3.实现删除字符串中出现次数最少的字符，若出现次数最少的字符有多个，则把出现次数最少的字符都删除。输出删除这些单词后的字符串，字符串中其它字符保持原来的顺序。
//	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		String str = in.nextLine();
//		char[] chars = str.toCharArray();
//		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
//		for (char c : chars) {
//			map.put(c, map.getOrDefault(c, 0)+1);
//		}
//		int min = Integer.MAX_VALUE;
//		for (Integer times : map.values()) {
//			min = Math.min(min, times);
//		}
//		String string = "";
//		for (char c : chars) {
//			if(map.get(c)!=min) {
//				string += c;
//			}
//		}
//		System.out.println(string);
//	}
//	4.原理：ip地址的每段可以看成是一个0-255的整数，把每段拆分成一个二进制形式组合起来，然后把这个二进制数转变成一个长整数。
//	public static void main(String[] args) throws IOException {
//		Scanner in = new Scanner(System.in);
//		while (in.hasNext()) {
//			String str = in.nextLine();
//			System.out.println(convert(str));
//		}
//
//	}
//
//	public static String convert(String str) {
//		if (str.contains(".")) {
//			String[] strs = str.split("//.");// //->/ //+.表示.
//			long res = 0;
//			for (int i = 0; i < strs.length; i++) {
//				res = Integer.valueOf(strs[i]) + res * 256;
//			}
//			return res + "";
//		} else {
//			long nums = Long.valueOf(str);
//			String res = "";
//			while (nums != 0) {
//				res = nums % 256 + "." + res;
//				nums /= 256;
//			}
//			return res.substring(0, res.length() - 1);
//		}
//	}
//	5.输入整型数组和排序标识，对其元素按照升序或降序进行排序.第一行输入数组元素个数.第二行输入待排序的数组，每个数用空格隔开.第三行输入一个整数0或1。0代表升序排序，1代表降序排序
//	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		int n = in.nextInt();
//		String str = in.nextLine();
//		String[] strs = str.split(" ");
//		int[] arr = new int[n];
//		for (int i = 0; i < n; i++) {
//			arr[i] = Integer.valueOf(strs[i]);
//		}
//		QuickSort(arr);
//		int flag = in.nextInt();
//		if (flag == 0) {
//			for (int i = 0; i < n; i++) {
//				System.out.print(arr[i] + " ");
//			}
//		} else {
//			for (int i = n - 1; i >= 0; i--) {
//				System.out.println(arr[i] + " ");
//			}
//		}
//	}
//
//	public static void QuickSort(int[] arr) {
//		if (arr == null || arr.length < 2) {
//			return;
//		}
//		process(arr, 0, arr.length - 1);
//	}
//
//	public static void process(int[] arr, int l, int r) {
//		if (l >= r) {
//			return;
//		}
//		swap(arr, l + (int) Math.random() * (r - l + 1), r);
//		int[] equalArea = netherLands(arr, l, r);
//		process(arr, l, equalArea[0] - 1);
//		process(arr, equalArea[1] + 1, r);
//	}
//
//	public static int[] netherLands(int[] arr, int l, int r) {
//		if (l > r) {
//			return new int[] { -1, -1 };
//		}
//		if (l == r) {
//			return new int[] { l, r };
//		}
//		int less = l - 1, more = r, index = l;
//		while (index < more) {
//			if (arr[index] < arr[r]) {
//				swap(arr, index++, ++less);
//			} else if (arr[index] == arr[r]) {
//				index++;
//			} else {
//				swap(arr, index, --more);
//			}
//		}
//		swap(arr, more, r);
//		return new int[] { less + 1, more };
//	}
//
//	public static void swap(int[] arr, int i, int j) {
//		int tmp = arr[i];
//		arr[i] = arr[j];
//		arr[j] = tmp;
//	}
//	6.将一个字符串str的内容颠倒过来，并输出。
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			String str = in.nextLine();
			char[] chars = str.toCharArray();
			for (int i = chars.length - 1; i >= 0; i--) {
				System.out.print(chars[i]);
			}
		}
	}
}
