package 数组字符串;

import java.util.LinkedList;

public class Problem_0134_Gas_Station {
	// 1.不存在or只存在1个好出发点
	public int canCompleteCircuit1(int[] gas, int[] cost) {
		int cur = 0;// 当前油量
		int need = 0;// 需要抵消的负油量
		int start = 0;
		for (int i = 0; i < cost.length; i++) {
			cur += gas[i] - cost[i];
			if (cur < 0) {
				need += cur;
				cur = 0;
				start = i + 1;
			}
		}
		return cur + need < 0 ? -1 : start;
	}

	// 2.判断所有点是否为好出发点 时间O(N) 空间O(N)
	public int canCompleteCircuit(int[] gas, int[] cost) {
		boolean[] good = goodArr(gas, cost);
		for (int i = 0; i < good.length; i++) {
			if (good[i]) {
				return i;
			}
		}
		return -1;
	}

	public boolean[] goodArr(int[] gas, int[] cost) {
		int N = gas.length;
		int M = N << 1;
		int[] arr = new int[M];
		boolean[] good = new boolean[N];
		for (int i = 0; i < N; i++) {
			arr[i] = gas[i] - cost[i];
			arr[i + N] = arr[i];
		}
		for (int i = 1; i < M; i++) {
			arr[i] += arr[i - 1];
		}
		LinkedList<Integer> queue = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			while (!queue.isEmpty() && arr[queue.peekLast()] >= arr[i]) {
				queue.pollLast();
			}
			queue.addLast(i);
		}
		for (int i = 0, j = N, preSum = 0; j < M; i++, j++) {
			if (arr[queue.peekFirst()] - preSum >= 0) {
				good[i] = true;
			}
			if (queue.peekFirst() == i) {
				queue.pollFirst();
			}
			while (!queue.isEmpty() && arr[queue.peekLast()] >= arr[j]) {
				queue.pollLast();
			}
			preSum += arr[i];
			queue.addLast(j);
		}
		return good;
	}

	// 3.判断所有点是否为好出发点 时间O(N) 空间O(1)
	public int canCompleteCircuit2(int[] gas, int[] cost) {
		if (gas.length == 1) {
			return gas[0] - cost[0] >= 0 ? 0 : -1;
		}
		boolean[] good = process1(gas, cost);
		for (int i = 0; i < good.length; i++) {
			if (good[i]) {
				return i;
			}
		}
		return -1;
	}

	public boolean[] process1(int[] gas, int[] cost) {
		int init = -1;
		for (int i = 0; i < cost.length; i++) {
			gas[i] -= cost[i];
			if (gas[i] >= 0) {
				init = i;
			}
		}
		return init == -1 ? new boolean[gas.length] : enlargeArea(gas, init);
	}

	public boolean[] enlargeArea(int[] gas, int init) {
		boolean[] good = new boolean[gas.length];
		int start = init;// 连通区域[start,end)
		int end = nextIndex(init, gas.length);
		int need = 0;// 先前连通需要的油量
		int rest = 0;// 目前油量
		do {
			if (start != init && start == lastIndex(end, gas.length)) {// 已经进入连通区域还没有找到 可行点 整个环不存在可行点
				break;
			}
			if (gas[start] < need) {// 无法向前链接
				need -= gas[start];
			} else {// 可以向前链接
				rest += gas[start] - need;
				while (rest >= 0 && end != start) {// 向后拓展
					rest += gas[end];
					end = nextIndex(end, gas.length);
				}
				if (rest >= 0) {// 已经找到可行点
					good[start] = true;
					getGoodConnect(good, gas, lastIndex(start, gas.length), init);
					break;
				}
			}
			start = lastIndex(start, gas.length);
		} while (start != init);
		return good;
	}

	public int nextIndex(int cur, int len) {
		return cur == len - 1 ? 0 : cur + 1;
	}

	public int lastIndex(int cur, int len) {
		return cur == 0 ? len - 1 : cur - 1;
	}

	public void getGoodConnect(boolean[] good, int[] gas, int start, int init) {
		int need = 0;
		while (start != init) {
			if (gas[start] > need) {
				good[start] = true;
				need = 0;
			} else {
				need -= gas[start];
			}
			start = lastIndex(start, gas.length);
		}
	}
}
