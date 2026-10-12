package class02;
//循环队列
public class MyQueue1 {
	private int[] data;
	private int size;
	private int limit;
	private int pushIndex;
	private int popIndex;
	
	public MyQueue1(int limit) {
		this.limit = limit;
		this.data = new int[limit];
		this.pushIndex = 0;
		this.popIndex = 0;
		this.size = 0;
	}
	
	public void push(int element) {
		if(isFull()) {
			throw new RuntimeException("Your stack is full.");
		}
		data[pushIndex] = element;
		size++;
		pushIndex = nextIndex(pushIndex);
	}
	
	public int pop() {
		if(isEmpty()) {
			throw new RuntimeException("Your stack is empty.");
		}
		int element = data[popIndex];
		size--;
		popIndex = nextIndex(popIndex);
		return element;
	}
	
	public int Size() {
		return size;
	}
	
	public boolean isFull() {
		return limit==size;
	}
	
	public boolean isEmpty() {
		return size==0;
	}
	
	public int peek() {
		return data[popIndex];
	}
	
	public int nextIndex(int index) {
		return index<limit-1?index+1:0;
	}
}
