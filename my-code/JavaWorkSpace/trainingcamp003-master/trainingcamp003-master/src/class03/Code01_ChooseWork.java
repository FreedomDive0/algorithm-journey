package class03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

public class Code01_ChooseWork {

	public static class Job {
		public int money;
		public int hard;

		public Job(int hard, int money) {
			this.money = money;
			this.hard = hard;
		}
	}

	public static class JobComparator implements Comparator<Job> {
		@Override
		public int compare(Job o1, Job o2) {
			return o1.hard != o2.hard ? (o1.hard - o2.hard) : (o2.money - o1.money);
		}
	}

	public static int[] getMoneys(Job[] job, int[] ability) {
		Arrays.sort(job, new JobComparator());
		// 难度为key的工作，最优钱数是多少，有序表
		TreeMap<Integer, Integer> map = new TreeMap<>();
		map.put(job[0].hard, job[0].money);
		Job pre = job[0]; // pre 之前组的组长
		for (int i = 1; i < job.length; i++) {
			if (job[i].hard != pre.hard && job[i].money > pre.money) {
				pre = job[i];
				map.put(pre.hard, pre.money);
			}
		}
		int[] ans = new int[ability.length];
		for (int i = 0; i < ability.length; i++) {
			Integer key = map.floorKey(ability[i]);
			ans[i] = key != null ? map.get(key) : 0;
		}
		return ans;
	}

	public static void printArr(int[] arr) {
		if (arr != null) {
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
		}
	}

	// 测试
	public static int[] chooseWork(Job[] jobs, int[] ability) {
		if (jobs == null || jobs.length < 1 || ability == null || ability.length < 1) {
			return null;
		}
		Arrays.sort(jobs, new JobComparator());
		TreeMap<Integer, Integer> jobList = new TreeMap<>();// 对应难度和金钱
		Job pre = jobs[0];
		jobList.put(pre.hard, pre.money);
		for (int i = 1; i < jobs.length; i++) {
			if (pre.hard != jobs[i].hard && pre.money < jobs[i].money) {// 以为已经排过序 只需将难度更高且收入大于前一个难度的工作加入序列中 为后续使用
				pre = jobs[i];
				jobList.put(pre.hard, pre.money);
			}
		}
		// 已建立好难度上升且收入上升的工作表
		int[] ans = new int[ability.length];
		for (int i = 0; i < ability.length; i++) {
			Integer key = jobList.floorKey(ability[i]);// 得到小于等于最大值
			ans[i] = key == null ? 0 : jobList.get(key);// 不存在为0
		}
		return ans;
	}

	// 测试
	public static class Jobomparator implements Comparator<Job> {

		@Override
		public int compare(Job o1, Job o2) {
			// TODO Auto-generated method stub
			return o1.hard != o2.hard ? o1.hard - o2.hard : o2.money - o1.money;
		}

	}

	public static int[] chooseJob(Job[] jobs, int[] people) {
		if (jobs == null || jobs.length < 1 || people == null || people.length < 1) {
			return null;
		}
		Arrays.sort(jobs, new Jobomparator());
		TreeMap<Integer, Integer> jobList = new TreeMap<Integer, Integer>();
		Job pre = jobs[0];
		int[] ans = new int[people.length];
		jobList.put(pre.hard, pre.money);
		for (int i = 1; i < jobs.length; i++) {
			if (pre.hard != jobs[i].hard && jobs[i].money > pre.money) {
				jobList.put(jobs[i].hard, jobs[i].money);
				pre = jobs[i];
			}
		}
		for (int i = 0; i < people.length; i++) {
			Integer key = jobList.floorKey(people[i]);// 有可能能力太低没有报酬
			ans[i] = key == null ? 0 : jobList.get(key);
		}
		return ans;
	}

	public static void main(String[] args) {
		// java中有序表是红黑树
		// 有序表可以被：红黑树、avl树、跳表、size-balanced-tree(SB树)实现
		// 不同的实现有什么区别：在使用层次上和性能上看，没区别。只有常数时间的区别。
		// 所有接口的性能O(logN)
		// 设计细节：扩展班最后一节
		// 有序表和哈希表的区别：
		// 哈希表的所有功能有序表一定有，哈希表的key（散乱组织，哈希函数）
		// 有序表所有的key有序组织，比哈希表的功能多。
		// 哈希表所有操作，使用时认为时间复杂度O(1)，有序表所有接口的性能O(logN)
//		TreeMap<Integer, String> map = new TreeMap<>();
//		map.put(7, "我是7"); // （key,value） 所有的key按顺序组织
//		map.put(3, "我是3");
//		map.put(9, "我是9");
//		map.put(2, "我是2");
//		map.put(8, "我是8");
//		map.put(5, "我是5");
//
//		map.put(5, "我还是5");// (5, 我还是5)
//
//		System.out.println(map.containsKey(2));
//		System.out.println(map.get(7));
//		map.remove(9);
//
//		System.out.println(map.firstKey());
//		System.out.println(map.lastKey());
//		// <= num 离num最近的key
//		System.out.println(map.floorKey(6));
//		System.out.println(map.floorKey(-2));
//		// >= num 离num最近的东西
//		System.out.println(map.ceilingKey(6));
//		System.out.println(map.ceilingKey(100));

		// 时间复杂度全是O(logN)级别
		ArrayList<Job> jobList = new ArrayList<>();
		jobList.add(new Job(1, 1));
		jobList.add(new Job(1, 2));
		jobList.add(new Job(1, 3));
		jobList.add(new Job(1, 4));
		jobList.add(new Job(2, 1));
		jobList.add(new Job(2, 2));
		jobList.add(new Job(2, 3));
		jobList.add(new Job(2, 4));
		jobList.add(new Job(3, 6));
		jobList.add(new Job(4, 7));
		jobList.add(new Job(5, 8));
		Job[] jobs = new Job[jobList.size()];
		Job[] jobs1 = new Job[jobList.size()];
		for (int i = 0; i < jobList.size(); i++) {
			jobs[i] = jobList.get(i);
			jobs1[i] = jobList.get(i);
		}
		int[] ability = new int[] { 1, 2, 3, 9, 8, 6, 4, 4, 5, 6, 9, 8, 5, 4, 6, 5 };
		printArr(getMoneys(jobs, ability));
		System.out.println();
		printArr(chooseJob(jobs1, ability));
	}

}
