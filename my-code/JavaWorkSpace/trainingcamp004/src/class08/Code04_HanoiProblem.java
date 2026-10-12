package class08;

public class Code04_HanoiProblem {
	// 汉诺塔问题 给定arr[] 如果是汉诺塔问题最优解的某一步 返回是第几步 如果不是返回-1
	public static int hanoi(int[] arr) {
		if (arr == null || arr.length < 1) {
			return -1;
		}
		return process1(arr, arr.length - 1, 1, 3, 2);
	}

	// 汉诺塔最优步骤2^N-1
	// 三步递归调用
	// (1) 0 ~ i-1 from -> other
	// (2) i from ->to
	// (3) 0 ~ i-1 other -> to
	// arr如果是最优解的一步 一定会把整个流程跑下来
	public static int process1(int[] arr, int i, int from, int to, int other) {
		if (i == -1) {// 汉诺塔移动完毕 所需步骤为0
			return 0;
		}
		if (arr[i] == other) {// 汉诺塔的步骤中不存在将i位置的移动到other这一步 所以出现即为错误
			return -1;
		}
		if (arr[i] == from) {// 分类讨论两种情况看看是哪种 i在from
			return process1(arr, i - 1, from, other, to);// 验证剩下的位置
		} else {// i在to 已经完成了(1) (2)
			int rest = process1(arr, i - 1, other, to, from);
			if (rest == -1) {
				return -1;
			}
			return rest + (1 << i);// 这一步之前移动了多少步 rest第(3)步 (1<<i) (1)(2)步之和 [0~i-1移动到to] (2^i-1) + 1 [i移动到to]
		}
	}
}
