package class02;

public class LinkedList {
//	单链表
	public static class Node<T> {
		T value;
		Node<T> next;

		public Node(T t) {
			value = t;
			next = null;
		}

		@Override
		public String toString() {
			return "Node [value=" + value + "]";
		}
	}

//	双向链表
	public static class DoubleNode<T> {
		T value;
		DoubleNode next;
		DoubleNode last;

		public DoubleNode(T t) {
			this.value = t;
			this.next = null;
			this.last = null;
		}

		@Override
		public String toString() {
			return "DoubleNode [value=" + value + "]";
		}
	}

//	从前加入
	public static DoubleNode addFromHead(DoubleNode head, DoubleNode node) {
		if (head == null) {
			return node;
		}
		node.last = head.last;
		head.last = node;
		node.next = head;
		return node;
	}

//	从尾部添加
	public static DoubleNode addFromTail(DoubleNode head, DoubleNode node) {
		if (head == null) {
			return node;
		}
		DoubleNode tmp = head;
		while (tmp.next != null) {
			tmp = tmp.next;
		}
		node.last = tmp;
		node.next = tmp.next;
		tmp.next = node;
		return head;
	}

//	从头部删除
	public static DoubleNode deleteFromHead(DoubleNode head) {
		if (head == null) {
			return head;
		}
		DoubleNode tmp = head;
		tmp = head.next;
		head.next = null;
		tmp.last = head.last;
		return tmp;
	}

//	从尾部删除
	public static DoubleNode deleteFromTail(DoubleNode head) {
		if (head == null) {
			return head;
		}
		DoubleNode tmp = head;
		while (tmp.next != null) {
			tmp = tmp.next;
		}
		tmp.last.next = tmp.next;
		tmp.last = null;
		return head;
	}

//	单链表逆转
	public static Node reverseLinkedList(Node head) {
		Node pre = null;
		Node next = null;
		while (head != null) {
			next = head.next;// 保存
			head.next = pre;// 反转
			pre = head;// 移动
			head = next;
		}
		return pre;// 最后head->next==null 所以返回pre
	}

//	双链表反转
	public static DoubleNode reverseLinkedList(DoubleNode head) {
		DoubleNode pre = null;
		DoubleNode next = null;
		while (head != null) {
			next = head.next;// 保存

			head.next = pre;// 反转
			head.last = next;

			pre = head;// 移动
			head = next;
		}
		return pre;// 最后head->next==null 所以返回pre
	}

//	删除指定值
	public static Node<Integer> remove(Node<Integer> head, int element) {
		while (head != null) {
			if (head.value != element) {
				break;
			}
			head = head.next;
		}
		Node<Integer> pre = head;
		Node<Integer> cur = head;
		while (cur != null) {
			if (cur.value == element) {
				pre.next = cur.next;
			} else {
				pre = cur;// 如果出现连续相等可以节省操作
			}
//			pre = cur;
			cur = cur.next;
		}
		return head;
	}

	public static Node<Integer> deleGivenNum(Node<Integer> head, int num) {
		while (head != null) {// 去除含有num的头部
			if (head.value != num) {
				break;
			}
			head = head.next;
		}
		Node<Integer> cur = head.next;
		Node<Integer> pre = head;
		while (cur != null) {
			if (cur.value == num) {
				pre.next = cur.next;
			} else {
				pre = cur;
			}
			cur = cur.next;
		}
		return head;
	}

	public static void main(String[] args) {
		Node node1 = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(4);
		Node node5 = new Node(5);
		Node node6 = new Node(5);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
		Node head = node1;
//		do {
//			System.out.println(head);
//			head = head.next;
//		} while (head != null);
		deleGivenNum(head, 5);
//		head = reverseLinkedList(node1);
		do {
			System.out.println(head);
			head = head.next;
		} while (head != null);

//		DoubleNode doubleNode1 = new DoubleNode(1);
//		DoubleNode doubleNode2 = new DoubleNode(2);
//		DoubleNode doubleNode3 = new DoubleNode(3);
//		DoubleNode doubleNode4 = new DoubleNode(4);
//		DoubleNode doubleNode5 = new DoubleNode(5);
//		doubleNode1.last = null;
//		doubleNode1.next = doubleNode2;
//		doubleNode2.last = doubleNode1;
//		doubleNode2.next = doubleNode3;
//		doubleNode3.last = doubleNode2;
//		doubleNode3.next = doubleNode4;
//		doubleNode4.last = doubleNode3;
//		doubleNode4.next = doubleNode5;
//		doubleNode5.last = doubleNode4;
//		doubleNode5.next = null;
//		DoubleNode head = doubleNode1;
//		DoubleNode tail = doubleNode5;
//		DoubleNode testHead = head;
//		DoubleNode testTail = tail;
//		do{
//			System.out.println(testHead);
//			testHead = testHead.next;
//		}while(testHead!=null);
//		System.out.println("------------测试开始--------------");
//		do{
//			System.out.println(tail);
//			tail = tail.last;
//		}while(tail!=null);
//		head = reverseLinkedList(doubleNode1);
//		head = addFromHead(head, new DoubleNode(6));
//		head = addFromTail(head, new DoubleNode(6));
//		head = deleteFromHead(head);
//		head = deleteFromTail(head);
//		do{
//			System.out.println(head);
//			head = head.next;
//		}while(head!=null);
//		MyStack stack = new MyStack(head);
//		stack.push(new DoubleNode(6));
//		stack.pop();
//		stack.showStack();
//		MyQueue queue = new MyQueue(head);
//		queue.push(new DoubleNode(6));
//		queue.pop();
//		queue.showQueue();
	}
}
