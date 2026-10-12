package class03;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

public class Code01_ChooseWork {
	public static class Job {
		int hard;
		int money;

		public Job(int hard, int money) {
			this.hard = hard;
			this.money = money;
		}

	}

	public static class JobComparator implements Comparator<Job> {

		@Override
		public int compare(Job o1, Job o2) {
			return o1.hard != o2.hard ? o1.hard - o2.hard : o2.money - o1.money;// 难度递增排序 难度相同钱大放在前
		}

	}

	public static int[] chooseWork(Job[] jobs, int[] ability) {
		if (jobs == null || jobs.length < 1 || ability == null || ability.length < 1) {
			return null;
		}
		Arrays.sort(jobs, new JobComparator());// 先排序先按难度排序 然后难度相同钱大排在前
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
}
