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

	public static class HappyInfo {
		public int yes;
		public int no;

		public HappyInfo(int yes, int no) {
			this.yes = yes;
			this.no = no;
		}

	}

	public static int maxHappy(Employee boss) {
		if (boss == null) {
			return 0;
		}
		HappyInfo info = processH(boss);
		return Math.max(info.yes, info.no);
	}

	public static HappyInfo processH(Employee employee) {
		if (employee.nexts == null) {
			return new HappyInfo(employee.happy, 0);
		}
		int yes = employee.happy;
		int no = 0;
		for (Employee next : employee.nexts) {
			HappyInfo info = processH(next);
			yes += info.no;
			no += Math.max(info.yes, info.no);
		}
		return new HappyInfo(yes, no);
	}
}
