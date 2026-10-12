package class05;

public class Review {
	// 1.前缀树
	public static class Node {
		int pass;
		int end;
		Node[] nexts;

		public Node() {
			this.pass = 0;
			this.end = 0;
			this.nexts = new Node[26];
		}

	}

	public static class TrieTree {
		Node root;

		public TrieTree() {
			this.root = new Node();
		}

		public void add(String word) {
			if (word == null || word.equals("")) {
				return;
			}
			Node cur = root;
			cur.pass++;
			char[] str = word.toCharArray();
			for (int i = 0; i < str.length; i++) {
				int path = str[i] - 'a';
				if (cur.nexts[path] == null) {
					cur.nexts[path] = new Node();
				}
				cur.nexts[path].pass++;
				cur = cur.nexts[path];
			}
			cur.end++;
		}

		public boolean search(String word) {
			if (word == null || word.equals("")) {
				return false;
			}
			Node cur = root;
			char[] str = word.toCharArray();
			for (int i = 0; i < str.length; i++) {
				int path = str[i] - 'a';
				if (cur.nexts[path] == null) {
					return false;
				}
				cur = cur.nexts[path];
			}
			return cur.end > 0;
		}

		public void delete(String word) {
			if (search(word)) {
				Node cur = root;
				cur.pass--;
				char[] str = word.toCharArray();
				for (int i = 0; i < str.length; i++) {
					int path = str[i] - 'a';
					if (--cur.nexts[path].pass == 0) {
						cur.nexts[path] = null;
						return;
					}
					cur = cur.nexts[path];
				}
				cur.end--;
			}
		}

		public int pretrieWord(String word) {
			if (word == null || word.equals("")) {
				return 0;
			}
			Node cur = root;
			char[] str = word.toCharArray();
			for (int i = 0; i < str.length; i++) {
				int path = str[i] - 'a';
				if (cur.nexts[path] == null) {
					return 0;
				}
				cur = cur.nexts[path];
			}
			return cur.pass;
		}
	}

	// 2.计数排序
	public static void countSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
		}
		int[] help = new int[max + 1];
		for (int i = 0; i < arr.length; i++) {
			help[arr[i]]++;
		}
		int index = 0;
		for (int i = 0; i < help.length; i++) {
			while (help[i]-- > 0) {// 需要将有数的位置输出完
				arr[index++] = i;
			}
		}
	}

	// 3.基数排序
	public static void RadixSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int[] help = new int[arr.length];
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
		}
		int radix = getRadix(max);
		for (int i = 1; i <= radix; i++) {
			int[] count = new int[10];// 注意count申请位置 因为后续有累加值无法清零 每个新的位需重新申请
			for (int j = 0; j < help.length; j++) {// 统计每个数第i位的数字
				int num = getRadixNum(arr[j], i);
				count[num]++;
			}
			for (int j = 1; j < count.length; j++) {// 求出<= j的数字有多少
				count[j] = count[j] + count[j - 1];
			}
			for (int j = help.length - 1; j >= 0; j--) {// 倒叙遍历上次排序或未排序数组 count[]为 0~9 <=该位数字的个数 倒叙遍历从大的数开始 所以有几个数就排在第几位
				int num = getRadixNum(arr[j], i);
				help[count[num] - 1] = arr[j];
				count[num]--;
			}
			for (int j = 0; j < help.length; j++) {
				arr[j] = help[j];
			}
		}
	}

	// 得到数字有几位
	public static int getRadix(int num) {
		int res = 0;
		while (num > 0) {
			num /= 10;
			res++;
		}
		return res;
	}

	// 得到数字第i位的数字
	public static int getRadixNum(int num, int i) {
		return (num / (int) (Math.pow(10, i - 1)) % 10);// 先除到个位再取余
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 98, 32, 11, 10, 34, 85, 88, 34, 37, 59, 53 };
		RadixSort(arr);
//		for (int i = 0; i < arr.length; i++) {
//			System.out.println(getRadixNum(arr[i], 1));
//		}
	}
}
