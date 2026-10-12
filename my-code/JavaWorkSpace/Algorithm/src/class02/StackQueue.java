package class02;
// 用栈实现队列
public class StackQueue {
	private MyStack<Integer> pushStack;
	private MyStack<Integer> popStack;
	public StackQueue() {
		this.popStack = new MyStack<Integer>(null);
		this.pushStack = new MyStack<Integer>(null);
	}
	
	public void push(int element) {
		pushStack.push(element);
	}
	
	public int pop() {
		if(popStack.isEmpty()&&pushStack.isEmpty()) {
			throw new RuntimeException("Queue is empty.");
		}
		pushToPop();
		return popStack.pop();
	}
	
	public int peek() {
		if(popStack.isEmpty()&&pushStack.isEmpty()) {
			throw new RuntimeException("Queue is empty.");
		}
		pushToPop();
		return popStack.peek();
	}
	
	public void pushToPop() {
		if(popStack.isEmpty()) {
			while(!pushStack.isEmpty()) {
				popStack.push(pushStack.pop());
			}
		}
	}
}
