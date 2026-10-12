package class02;

public class MinStack {
	private MyStack<Integer> data;
	private MyStack<Integer> min;
	
	public MinStack() {
		this.data = new MyStack(null);
		this.min = new MyStack(null);
	}
	
	public void add(int element) {
		if(this.min.isEmpty()) {//用this代替对象
			this.min.push(element);
		}else if(element<this.getMin()) {
			this.min.push(element);
		}else {
			int newMin = this.min.peek();
			this.min.push(newMin);
		}
		this.data.push(element);
	}
	public Integer pop() {
		if(this.data.isEmpty()) {
			throw new RuntimeException("Your stack is empty.");
		}
		this.min.pop();
		return this.data.pop();
	}
	public Integer getMin() {
		if(this.min.isEmpty()) {
			throw new RuntimeException("Your stack is empty.");
		}
		return min.peek();
	}
}
