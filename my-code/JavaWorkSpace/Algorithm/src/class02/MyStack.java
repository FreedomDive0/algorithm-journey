package class02;

import class02.LinkedList.DoubleNode;

public class MyStack<T> {
	DoubleNode<T> top;

	public MyStack(DoubleNode<T> top) {
		this.top = top;
	}

	public DoubleNode<T> push(T element) {
		DoubleNode<T> node = new DoubleNode<T>(element);
		LinkedList.addFromHead(top, node);
		return top;
	}

	public T pop() {
		DoubleNode<T> node = LinkedList.deleteFromHead(top);
		return node.value;
	}

	public boolean isEmpty() {
		return top == null;
	}

	public T peek() {
		return top.value;
	}

	public void showStack() {
		DoubleNode<T> testTop = top;
		while (testTop.next != null) {
			testTop = testTop.next;
		}
		do {
			System.out.println(testTop);
			testTop = testTop.last;
		} while (testTop != null);
	}
}
