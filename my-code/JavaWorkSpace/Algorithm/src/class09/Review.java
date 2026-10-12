package class09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Review {
	// 1. 字符串数组最小字典序
	public static String minD(String[] arr) {
		if (arr == null || arr.length < 1) {
			return null;
		}
		ArrayList<String> all = new ArrayList<>();
		process(arr, new HashSet<>(), "", all);
		String min = all.get(0);
		for (String str : all) {
			min = min.compareTo(str) < 0 ? min : str;
		}
		return min;
	}

	public static void process(String[] arr, HashSet<Integer> set, String str, ArrayList<String> all) {
		if (set.size() == arr.length) {
			all.add(str.toString());
		}
		for (int i = 0; i < arr.length; i++) {
			set.add(i);
			process(arr, set, str + arr[i], all);
			set.remove(i);
		}
	}

	// 使用贪心法
	public static class myCompare implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			// TODO Auto-generated method stub
			return (o1 + o2).compareTo(o2 + o1);
		}

	}

	public static String minD1(String[] arr) {
		if (arr == null || arr.length < 1) {
			return null;
		}
		Arrays.sort(arr, new myCompare());
		String str = "";
		for (int i = 0; i < arr.length; i++) {
			str += arr[i];
		}
		return str;
	}

	// 2.安排会议
	public static class Program {
		public int start;
		public int end;

		public Program(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	// 暴力
	public static int arrange(Program[] programs) {
		if (programs == null) {
			return 0;
		}
		return processA(programs, 0, new HashSet<>());
	}

	public static int processA(Program[] programs, int start, HashSet<Integer> set) {
		int res = 0;
		for (int i = 0; i < programs.length; i++) {
			if (start <= programs[i].start && !set.contains(i)) {
				set.add(i);
				res = Math.max(res, processA(programs, programs[i].end, set) + 1);
				set.remove(i);
			}
		}
		return res;
	}

	// 贪心
	public static class myA implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			// TODO Auto-generated method stub
			return o1.end - o2.end;
		}

	}

	public static int arrangeT(Program[] programs) {
		if (programs == null) {
			return 0;
		}
		Arrays.sort(programs, new myA());
		int res = 0;
		int index = 0;
		int start = 0;
		while (index < programs.length) {
			if (start <= programs[index].start) {
				res++;
				start = programs[index].end;
			}
			index++;
		}
		return res;
	}

	// 4.切金条
	// 4.1暴力
	public static int cutGoldB(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		return processG(arr, 0);
	}

	public static int processG(int[] arr, int cost) {
		if (arr.length == 1) {
			return cost;
		}
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				ans = Math.min(ans, processG(copyAndMerge(arr, i, j), cost + arr[i] + arr[j]));
			}
		}
		return ans;
	}

	public static int[] copyAndMerge(int[] arr, int i, int j) {
		int[] arri = new int[arr.length - 1];
		int index = 0;
		for (int k = 0; k < arr.length; k++) {
			if (k != i && k != j) {
				arri[index++] = arr[k];
			}
		}
		arri[index] = arr[i] + arr[j];
		return arri;
	}

	// 3.路灯
	// 暴力
	public static int lightB(String str) {
		if (str == null) {
			return 0;
		}
		return processL(str.toCharArray(), 0, new HashSet<Integer>());
	}

	public static int processL(char[] arr, int index, HashSet<Integer> lights) {
		if (index == arr.length) {
			return isOk(arr, lights) ? lights.size() : arr.length;
		}
		int no = processL(arr, index + 1, lights);
		int yes = arr.length;
		if (arr[index] == '.') {
			lights.add(index);
			yes = processL(arr, index + 1, lights);
			lights.remove(index);
		}
		return Math.min(no, yes);
	}

	public static boolean isOk(char[] arr, HashSet<Integer> lights) {
		boolean ok = true;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == '.' && !lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
				ok = false;
			}
		}
		return ok;
	}

	// 贪心
	public static int lightsT(String str) {
		if (str == null) {
			return 0;
		}
		char[] sArr = str.toCharArray();
		int res = 0;
		int index = 0;
		while (index < str.length()) {// 使用逻辑化简
			if (sArr[index] == '.') {
				res++;
				if (index + 1 == str.length()) {
					break;
				}
				if (sArr[index + 1] == '.') {
					index = index + 3;
				} else {
					index = index + 2;
				}
			} else {
				index++;
			}
		}
		return res;
	}
//	public static int lightsT(String str) {
//		if (str == null) {
//			return 0;
//		}
//		char[] sArr = str.toCharArray();
//		int res = 0;
//		int index = 0;
//		while (index < str.length()) {
//			if (sArr[index] == '.') {
//				if (index + 1 < str.length()) {
//					if (sArr[index + 1] == 'X') {
//						index = index + 2;
//					} else if (sArr[index + 1] == '.') {
//						if (index + 2 < str.length()) {
//							if (sArr[index + 2] == '.') {
//								index = index + 3;
//							} else {
//								index = index + 2;
//							}
//						} else {
//							index = index + 2;
//						}
//					}
//					res++;
//				} else {
//					res++;
//					break;
//				}
//			} else {
//				index++;
//			}
//		}
//		return res;
//	}

	// 4.2贪心法
	public static int cutGoldT(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		PriorityQueue<Integer> queue = new PriorityQueue<>();// 不能使用数组是因为无法动态维持每次取出为最小值
		for (int i = 0; i < arr.length; i++) {
			queue.add(arr[i]);
		}
		int ans = 0;
		while (queue.size() > 1) {
			int cur = queue.poll() + queue.poll();// 每次结合两个最小的 得到成本
			ans += cur;
			queue.add(cur);
		}
		return ans;
	}

	// 5.最大工程收益 k个项目
	public static class Project {
		public int p;
		public int c;

		public Project(int p, int c) {
			this.p = p;
			this.c = c;
		}
	}

	public static class myC implements Comparator<Project> {

		@Override
		public int compare(Project o1, Project o2) {
			// TODO Auto-generated method stub
			return o1.c - o2.c;
		}

	}

	public static class myP implements Comparator<Project> {

		@Override
		public int compare(Project o1, Project o2) {
			// TODO Auto-generated method stub
			return o2.p - o1.p;
		}

	}

	public static int maxPN(int K, int W, Project[] projects, int[] Capital) {
		if (projects == null || projects.length < 1 || K == 0) {
			return 0;
		}
		int res = 0;
		PriorityQueue<Project> queueC = new PriorityQueue<>(new myC());
		PriorityQueue<Project> queueP = new PriorityQueue<>(new myP());
		for (int i = 0; i < projects.length; i++) {// 按成本从小到大加入到队列中
			queueC.add(projects[i]);
		}
		while (K > 0) {
			while (!queueC.isEmpty() && W >= queueC.peek().c) {
				queueP.add(queueC.poll());
			}
			if (queueP.isEmpty()) {
				break;
			} else {
				Project p = queueP.poll();
				W += p.p;// 只加收益
				K++;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 10, 20, 30 };
		System.out.println(cutGoldT(arr));
	}
}
