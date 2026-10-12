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

	public static int maxHappy(Employee boss) {
		if (boss == null) {
			return 0;
		}
		MHInfo res = mHProcess(boss);
		return Math.max(res.no, res.yes);
	}

	public static class MHInfo {
		public int no;
		public int yes;

		public MHInfo(int no, int yes) {
			this.no = no;
			this.yes = yes;
		}

	}

	public static MHInfo mHProcess(Employee employee) {
		if (employee.nexts.size() == 0) {
			return new MHInfo(0, employee.happy);
		}
		int no = 0;
		int yes = employee.happy;
		for (Employee e : employee.nexts) {
			yes += mHProcess(e).no;
			no += Math.max(mHProcess(e).no, mHProcess(e).yes);
		}
		return new MHInfo(no, yes);
	}
}
