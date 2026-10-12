package 链表;

public class Problem_0021_Merge_Two_Sorted_Lists {
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

	public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
		if (list1 == null || list2 == null) {
			return list1 == null ? list2 : list1;
		}
		ListNode head = list1.val < list2.val ? list1 : list2;
		ListNode pre = head;
		ListNode cur1 = head.next;
		ListNode cur2 = head == list1 ? list2 : list1;// 不需要重新创建直接使用
		while (cur1 != null && cur2 != null) {
			if (cur1.val < cur2.val) {
				pre.next = cur1;
				cur1 = cur1.next;
			} else {
				pre.next = cur2;
				cur2 = cur2.next;
			}
			pre = pre.next;
		}
		pre.next = cur1 == null ? cur2 : cur1;
		return head;
	}
}
