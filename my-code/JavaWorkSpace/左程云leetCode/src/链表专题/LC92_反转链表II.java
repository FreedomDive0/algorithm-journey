
package 链表专题;

public class LC92_反转链表II {
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

	ListNode successor = null;

	public ListNode reverseN(ListNode head, int n) {
		if (n == 1) {
			successor = head.next;
			return head;
		}
		ListNode last = reverseN(head.next, n - 1);// 不要进入 递归 要从字面意思理解递归的结果
		head.next.next = head;// 反转
		head.next = successor;// 连接链表
		return last;
	}

	public ListNode reverseBetween(ListNode head, int left, int right) {
		if (left == 1) {
			return reverseN(head, right);
		}
		head.next = reverseBetween(head.next, left - 1, right - 1);// 连接返回的头节点
		return head;
	}
//	public ListNode reverseBetween(ListNode head, int left, int right) {
//		if (head == null || head.next == null) {
//			return head;
//		}
//		ListNode pre = null, next = null, cur = head;
//		ListNode start = new ListNode(0);
//		start.next = head;
//		int n = 1;
//		while (n <= right) {
//			if (n <= left) {
//				pre = cur;// 反转三件套
//				cur = cur.next;
//				if (n < left) {
//					start = start.next;// 反转下标的前一个
//				}
//			} else if (n == right) {
//				next = cur.next;
//				cur.next = pre;
//				start.next.next = next;
//				start.next = cur;
//			} else {
//				next = cur.next;
//				cur.next = pre;
//				pre = cur;
//				cur = next;
//			}
//			n++;
//		}
//		return left == 1 ? cur : head;
//	}
}
