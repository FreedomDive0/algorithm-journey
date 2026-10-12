package class08;

import java.util.HashMap;

public class Code01_ReceiveAndPrintOrderLine {
	public static class Node {
		String info;
		Node next;

		public Node(String info) {
			this.info = info;
			this.next = null;
		}

	}

	public static class MessageBox {
		static HashMap<Integer, Node> head;
		static HashMap<Integer, Node> tail;
		static int printIndex = 1;

		public MessageBox() {
			this.head = new HashMap<>();
			this.tail = new HashMap<>();
			printIndex = 1;
		}

		public static void receive(int index, String value) {
			if (index < 0) {
				return;
			}
			Node cur = new Node(value);
			head.put(index, cur);// 头尾范围先放入
			tail.put(index, cur);
			if (head.containsKey(index + 1)) {// cur后面是否有后续
				cur.next = head.get(index + 1);
				tail.remove(index);
				head.remove(index + 1);
			}
			if (tail.containsKey(index - 1)) {// cur前面是否能连
				tail.get(index - 1).next = cur;
				tail.remove(index - 1);
				head.remove(index);
			}

			if (index == printIndex) {
				print();
			}
		}

		private static void print() {
			Node cur = head.get(printIndex);
			head.remove(printIndex);
			while (cur != null) {
				System.out.print(cur.info + " ");
				cur = cur.next;
				printIndex++;
			}
			System.out.println();
			tail.remove(printIndex - 1);
		}
	}

	// 测试
	public static class MessageBox1 {
		static HashMap<Integer, Node> head;
		static HashMap<Integer, Node> tail;
		static int waitPrint = 1;

		public MessageBox1() {
			head = new HashMap<Integer, Node>();
			tail = new HashMap<Integer, Node>();
			waitPrint = 1;
		}

		public static void receive(int index, String value) {
			if (index < 0) {
				return;
			}
			Node cur = new Node(value);
			head.put(index, cur);
			tail.put(index, cur);
			if (tail.containsKey(index - 1)) {// cur头能不能连
				tail.get(index - 1).next = cur;
				tail.remove(index - 1);
				head.remove(index);
			}
			if (head.containsKey(index + 1)) {// cur尾能不能连
				cur.next = head.get(index + 1);
				head.remove(index + 1);
				tail.remove(index);
			}
			if (index == waitPrint) {
				print();
			}
		}

		private static void print() {
			Node cur = head.get(waitPrint);
			head.remove(waitPrint);
			while (cur != null) {
				System.out.print(cur.info + " ");
				waitPrint++;
				cur = cur.next;
			}
			System.out.println();
			tail.remove(waitPrint - 1);
		}
	}

	public static void main(String[] args) {
		// MessageBox only receive 1~N
		MessageBox box = new MessageBox();
		MessageBox1 box1 = new MessageBox1();
		box.receive(2, "B"); // - 2"
		box.receive(1, "A"); // 1 2 -> print, trigger is 1

		box.receive(4, "D"); // - 4
		box.receive(5, "E"); // - 4 5
		box.receive(7, "G"); // - 4 5 - 7
		box.receive(8, "H"); // - 4 5 - 7 8
		box.receive(6, "F"); // - 4 5 6 7 8
		box.receive(3, "C"); // 3 4 5 6 7 8 -> print, trigger is 3

		box.receive(9, "I"); // 9 -> print, trigger is 9

		box.receive(10, "J"); // 10 -> print, trigger is 10

		box.receive(12, "L"); // - 12
		box.receive(13, "M"); // - 12 13
		box.receive(11, "K"); // 11 12 13 -> print, trigger is 11
		System.out.println();

		box1.receive(2, "B"); // - 2"
		box1.receive(1, "A"); // 1 2 -> print, trigger is 1

		box1.receive(4, "D"); // - 4
		box1.receive(5, "E"); // - 4 5
		box1.receive(7, "G"); // - 4 5 - 7
		box1.receive(8, "H"); // - 4 5 - 7 8
		box1.receive(6, "F"); // - 4 5 6 7 8
		box1.receive(3, "C"); // 3 4 5 6 7 8 -> print, trigger is 3

		box1.receive(9, "I"); // 9 -> print, trigger is 9

		box1.receive(10, "J"); // 10 -> print, trigger is 10

		box1.receive(12, "L"); // - 12
		box1.receive(13, "M"); // - 12 13
		box1.receive(11, "K"); // 11 12 13 -> print, trigger is 11
	}
}
