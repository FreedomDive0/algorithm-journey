package class06;

import java.util.HashSet;


public class Code05_FindFirstIntersectNode {
	public static class Node{
		int value;
		Node next;
		public Node(int value) {
			this.value = value;
			next = null;
		}
	}
//	使用HashSet 还需改进另一个head2也为环
//	public static Node findFirstIntersectNode(Node head1,Node head2) {
//		Node cur = head1;
//		HashSet<Node> set = new HashSet<Node>();
//		while(cur!=null) {
//			if(set.contains(cur)) {
//				break;
//			}
//			set.add(cur);
//			cur = cur.next;
//		}
//		cur = head2;
//		while(cur!=null) {
//			if(set.contains(cur)) {
//				return cur;
//			}
//			cur = cur.next;
//		}
//		return null;
//	}
	
	public static Node findFirstIntersectNode(Node head1,Node head2) {
		if(head1==null||head2==null) {
			return null;
		}
		Node loop1 = findLoop(head1);
		Node loop2 = findLoop(head2);
		if(loop1==null&&loop2==null) {
			return noloop(head1, head2);
		}
		if(loop1!=null&&loop2!=null) {
			return bothloop(head1, head2, loop1,loop2);
		}
		return null;
	}
	
//	首先确定两个list有无环
	public static Node findLoop(Node head) {
		if(head==null||head.next==null||head.next.next==null) {
			return null;
		}
		Node slow = head.next;
		Node fast = head.next.next;
		while(slow!=fast) {
			if(fast.next==null||fast.next.next==null) {
				return null;
			}
			slow = slow.next;
			fast = fast.next.next;
		}
		fast = head;
		while(slow!=fast) {
			slow = slow.next;
			fast = fast.next;
		}
		return slow;
	}
//	处理都为空的情况
	public static Node noloop(Node head1,Node head2) {
		Node cur1 = head1;
		Node cur2 = head2;
		int n = 0;
		while(cur1.next!=null) {
			n++;
			cur1 = cur1.next;
		}
		while(cur2.next!=null) {
			n--;
			cur2 = cur2.next;
		}
		if(cur1!=cur2) {//相交的话最后一定相同 上面遍历时不能到最后一个 
			return null;
		}
		cur1 = n>0 ? head1:head2;
		cur2 = cur1==head1 ? head2:head1;
		n = Math.abs(n);
		while(n!=0) {
			n--;
			cur1 = cur1.next;
		}
		while(cur1!=cur2) {
			cur1 = cur1.next;
			cur2 = cur2.next;
		}
		return cur1;
	}
//	处理都不为空的情况
	public static Node bothloop(Node head1,Node head2,Node loop1,Node loop2) {
		Node cur1 = null;
		Node cur2 = null;
		if(loop1==loop2) {
			int n = 0;
			cur1 = head1;
			while(cur1!=loop1) {
				cur1 = cur1.next;
				n++;
			}
			cur2 = head2;
			while(cur2!=loop2) {
				cur2 = cur2.next;
				n--;
			}
			cur1 = n>0?head1:head2;
			cur2 = cur1==head1?head2:head1;
			n = Math.abs(n);
			while(n!=0) {
				cur1 = cur1.next;
				n--;
			}
			while(cur1!=cur2) {
				cur2 = cur2.next;
				cur1 = cur1.next;
			}
			return cur1;
		}else {
			cur1 = loop1.next;
			while(cur1!=loop2) {
				cur1 = cur1.next;
				if(cur1==loop1) {
					return null;
				}
			}
			return loop1;
		}
	}
	
	public static void main(String[] args) {
		// 1->2->3->4->5->6->7->null
		Node head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);

		// 0->9->8->6->7->null
		Node head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(findFirstIntersectNode(head1, head2).value);

		// 1->2->3->4->5->6->7->4...
		head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);
		head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

		// 0->9->8->2...
		head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next; // 8->2
		System.out.println(findFirstIntersectNode(head1, head2).value);

		// 0->9->8->6->4->5->6..
		head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(findFirstIntersectNode(head1, head2).value);

	}
}
