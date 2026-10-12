package HW;

public class 进制转换 {
//	1.写出一个程序，接受一个十六进制的数，输出该数值的十进制表示
//	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		while (in.hasNext()) {
//			String str = in.nextLine();
//			System.out.println(Integer.valueOf(str.substring(2), 16).toString());
//		}
//	}
//	2.给出一个整型数组 numbers 和一个目标值 target，请在数组中找出两个加起来等于目标值的数的下标，返回的下标按升序排列。
//	class Solution {
//	    public int[] twoSum(int[] nums, int target) {
//	        int[] res = new int[2];
//			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
//			for (int i = 0; i < nums.length; i++) {
//				if (map.containsKey(target - nums[i])) {
//					res[0] = map.get(target - nums[i]);
//					res[1] = i;
//					return res;
//				} else {
//					map.put(nums[i], i);
//				}
//			}
//	        return null;
//	    }
//	}
//	3.明明生成了NN个1到500之间的随机整数。请你删去其中重复的数字，即相同的数字只保留一个，把其余相同的数去掉，然后再把这些数从小到大排序，按照排好的顺序输出。
//	public static void main(String[] args) {
//		int n;
//		TreeSet<E>
//		Scanner in = new Scanner(System.in);
//		n = in.nextInt();
//		HashSet<Integer> set = new HashSet<Integer>();
//		for (int i = 0; i < n; i++) {
//			set.add(in.nextInt());
//		}
//		Iterator<Integer> iterator = set.iterator();
//		int[] nums = new int[set.size()];
//		int i = 0;
//		while (iterator.hasNext()) {
//			nums[i++] = iterator.next();
//		}
//		QuickSort(nums);
//		for (int j = 0; j < nums.length; j++) {
//			System.out.println(nums[j]);
//		}
//		Scanner sc = new Scanner(System.in);
//		int num = sc.nextInt();
//		TreeSet set = new TreeSet();// TreeSet有序
//		for (int i = 0; i < num; i++) {
//			set.add(sc.nextInt());
//		}
//		Iterator iterator = set.iterator();
//		while (iterator.hasNext()) {
//			System.out.println(iterator.next());
//		}
//	}
//
//	public static void QuickSort(int[] nums) {
//		if (nums == null || nums.length < 2) {
//			return;
//		}
//		process(nums, 0, nums.length - 1);
//	}
//
//	public static void process(int[] nums, int l, int r) {
//		if (l >= r) {
//			return;
//		}
//		swap(nums, l + (int) (Math.random() * (r - l + 1)), r);
//		int[] equalArea = netherLands(nums, l, r);
//		process(nums, l, equalArea[0] - 1);
//		process(nums, equalArea[1] + 1, r);
//	}
//
//	public static int[] netherLands(int[] nums, int L, int R) {
//		if (L > R) {
//			return new int[] { -1, -1 };
//		}
//		if (L == R) {
//			return new int[] { L, R };
//		}
//		int l = L - 1, m = R, i = L;
//		while (i < m) {
//			if (nums[i] < nums[R]) {
//				swap(nums, i++, ++l);
//			} else if (nums[i] == nums[R]) {
//				i++;
//			} else {
//				swap(nums, i, --m);
//			}
//		}
//		swap(nums, m, R);
//		return new int[] { l + 1, m };
//	}
//
//	public static void swap(int[] nums, int i, int j) {
//		int tmp = nums[i];
//		nums[i] = nums[j];
//		nums[j] = tmp;
//	}
//	4.编写一个函数，计算字符串中含有的不同字符的个数。字符在 ASCII 码范围内( 0~127 ，包括 0 和 127 )，换行表示结束符，不算在字符里。不在范围内的不作统计。多个相同的字符只计算一次
//	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		String str = in.nextLine();
//		HashSet set = new HashSet();
//		for (char c : str.toCharArray()) {
//			set.add(c);
//		}
//		System.out.println(set.size());
//	}
//	5.一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
//	public int jumpFloor(int target) {
//        if(target==1){
//            return 1;
//        }
//        if(target==2){
//            return 2;
//        }
//        return jumpFloor(target-1)+jumpFloor(target-2);
//    }
}
