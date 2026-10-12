package class04;

import java.util.Comparator;

public class Code01_Comparator {
	public static class Student {
		public String name;
		public int id;
		public int age;

		public Student(String name, int id, int age) {
			this.name = name;
			this.id = id;
			this.age = age;
		}
	}

	public static class MyCom implements Comparator<Student> {
		// 负数 o1排前面
		// 正数 o2排前面
		// 0 相等
		@Override
		public int compare(Student o1, Student o2) {
			return o1.age - o2.age;
		}

	}
}
