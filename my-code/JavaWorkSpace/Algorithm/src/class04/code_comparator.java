package class04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import class04.code_comparator.Student.IdAscendingComparator;
import class04.code_comparator.Student.MyCom;

public class code_comparator {
	public static class Student{
		int id;
		int age;
		String name;
		
	@Override
		public String toString() {
			return "Student [id=" + id + ", age=" + age + ", name=" + name + "]";
		}
	public Student(int id,int age,String name){
		this.id = id;
		this.age = age;
		this.name = name;
	}
	public static class IdAscendingComparator implements Comparator<Student> {//比较器
		@Override
		public int compare(Student arg0, Student arg1) {
			return arg0.id-arg1.id;
		}	
	}
	public static class MyCom implements Comparator<Integer>{
		@Override
		public int compare(Integer o1, Integer o2) {
			return o2-o1;
		}
		
	}
	}
	public static void main(String[] args) {
		Student s1 = new Student(9, 20, "A");
		Student s2 = new Student(8, 24, "B");
		Student s3 = new Student(6, 29, "C");
		Student students[] = {s1,s2,s3};
		Arrays.sort(students,new IdAscendingComparator());
		for(int i=0;i<students.length;i++) {
			System.out.println(students[i]);
		}
		
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>(new MyCom());//改变排序方式 变为大根堆
		heap.add(9);
		heap.add(8);
		heap.add(3);
		heap.add(0);
		heap.add(4);
		heap.add(6);
		heap.add(7);
		while(!heap.isEmpty()) {
			System.out.print(heap.poll()+" ");
		}
	}
}
