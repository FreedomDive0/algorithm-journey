package 分治;

import java.util.ArrayList;
import java.util.Comparator;

public class Problem_0148_SortList {
	public class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

	// 笔试
	class MyCom implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			// TODO Auto-generated method stub
			return o1 - o2;
		}

	}

	public ListNode sortList1(ListNode head) {
		if (head == null) {
			return head;
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		ListNode cur = head;
		while (cur != null) {
			list.add(cur.val);
			cur = cur.next;
		}
		list.sort(new MyCom());
		cur = head;
		for (Integer num : list) {
			cur.val = num;
			cur = cur.next;
		}
		return head;
	}
	// 面试

}
