package class02;

import class02.LinkedList.DoubleNode;

public class QueueStack {
	private MyQueue1 data;
	private MyQueue1 help;
	
	public QueueStack() {
		this.data = new MyQueue1(10);
		this.help = new MyQueue1(10);
	}
	
	public void add(int element) {
		data.push(element);
	}
	
	public int pop() {
		if(data.isEmpty()) {
			throw new RuntimeException("Stack is empty.");
		}
		while(data.Size()>1) {
			help.push(data.pop());
		}
		MyQueue1 tmp = data;
		data = help;
		help = data;
		return help.pop();
	}
	
	public int peek() {
		if(data.isEmpty()) {
			throw new RuntimeException("Stack is empty.");
		}
		while(data.Size()>1) {
			help.push(data.pop());
		}
		int element = help.peek();
		help.push(data.pop());
		MyQueue1 tmp = data;
		data = help;
		help = data;
		return element;
	}
}
