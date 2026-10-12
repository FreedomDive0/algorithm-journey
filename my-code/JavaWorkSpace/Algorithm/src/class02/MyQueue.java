package class02;

import class02.LinkedList.DoubleNode;

public class MyQueue <T>{
	DoubleNode<T> head = null;
	
	public MyQueue( ) {
		this.head = new DoubleNode(null);
	}
	
	public DoubleNode push(DoubleNode node) {
		head = LinkedList.addFromTail(head, node);
		return head;
	}
	
	public DoubleNode pop() {
		head = LinkedList.deleteFromHead(head);
		return head;
	}
	
	public boolean isEmpty() {
		return head==null;
	}
	
	public T peek() {
		DoubleNode<T> tmp = this.head;
		if(tmp==null) {
			return null;
		}
		while(tmp.next!=null) {
			tmp = tmp.next;
		}
		return tmp.value;
	}
	
	public void showQueue() {
		DoubleNode testHead = head;
		do{
			System.out.println(testHead);
			testHead = testHead.next;
		}while(testHead!=null);
	}
}
