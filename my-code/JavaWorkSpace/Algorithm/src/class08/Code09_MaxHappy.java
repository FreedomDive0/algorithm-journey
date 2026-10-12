package class08;

import java.util.ArrayList;
import java.util.List;

public class Code09_MaxHappy {
	public static class Employee {
		public int happy;
		public List<Employee> nexts;

		public Employee(int h) {
			happy = h;
			nexts = new ArrayList<>();
		}
	}
	
	public static class Infor{
		int yes;
		int no;
		
		public Infor(int yes,int no) {
			this.yes = yes;
			this.no = no;
		}
	}
	
	public static int MaxHappy(Employee emp) {
		if(emp==null) {
			return 0;
		}
		Infor last = processH(emp);
		return last.yes>last.no?last.yes:last.no;
	}
	public static Infor processH(Employee emp) {
		if(emp.nexts.isEmpty()) {
			return new Infor(emp.happy, 0);
		}
		int yes = emp.happy;
		int no = 0;
		for(Employee next : emp.nexts) {
			Infor info = processH(next);
			no += Math.max(info.yes, info.no);//上司不参加 手下看参加的大还是不参加的大
			yes += info.no;//上司参与手下就一种情况 不参加的总和
		}
		return new Infor(yes, no);
	}
	
//	for test
	public static int maxHappy1(Employee boss) {
		if (boss == null) {
			return 0;
		}
		return process1(boss, false);
	}

	public static int process1(Employee cur, boolean up) {
		if (up) {
			int ans = 0;
			for (Employee next : cur.nexts) {
				ans += process1(next, false);
			}
			return ans;
		} else {
			int p1 = cur.happy;
			int p2 = 0;
			for (Employee next : cur.nexts) {
				p1 += process1(next, true);
				p2 += process1(next, false);
			}
			return Math.max(p1, p2);
		}
	}
	// for test
		public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
			if (Math.random() < 0.02) {
				return null;
			}
			Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
			genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
			return boss;
		}

		// for test
		public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
			if (level > maxLevel) {
				return;
			}
			int nextsSize = (int) (Math.random() * (maxNexts + 1));
			for (int i = 0; i < nextsSize; i++) {
				Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
				e.nexts.add(next);
				genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
			}
		}
		public static void main(String[] args) {
			int maxLevel = 4;
			int maxNexts = 7;
			int maxHappy = 100;
			int testTimes = 100000;
			for (int i = 0; i < testTimes; i++) {
				Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
				if (maxHappy1(boss) != MaxHappy(boss)) {
					System.out.println("Oops!");
				}
			}
			System.out.println("finish!");
		}
}
