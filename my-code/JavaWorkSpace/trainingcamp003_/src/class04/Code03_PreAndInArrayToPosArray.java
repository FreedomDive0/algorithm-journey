package class04;

import java.util.HashMap;

public class Code03_PreAndInArrayToPosArray {
	public static int[] PITP(int[] pre, int[] in) {
		if (pre == null || in == null || pre.length != in.length) {
			return null;
		}
		HashMap<Integer, Integer> inOrderMap = new HashMap<>();// 记录中序遍历位置
		for (int i = 0; i < in.length; i++) {
			inOrderMap.put(in[i], i);
		}
		int[] pos = new int[pre.length];
		int len = pre.length;
		process(pre, in, pos, 0, len - 1, 0, len - 1, 0, len - 1, inOrderMap);
		return pos;
	}

	public static void process(int[] pre, int[] in, int[] pos, int l1, int r1, int l2, int r2, int l3, int r3,
			HashMap<Integer, Integer> inOrderMap) {
		if (l1 > r1) {
			return;
		}
		if (l3 == r3) {
			pos[l3] = pre[l1];
			return;
		}
		pos[r3] = pre[l1];// 先序遍历头左右 后序遍历左右头 先头填后尾 处理左右树
		int headIndex = inOrderMap.get(pre[l1]);
		int leftSize = headIndex - l2;
		// 每个顺序遍历使用 使用对应的计算值填位置
		process(pre, in, pos, l1 + 1, l1 + leftSize, l2, headIndex - 1, l3, l3 + leftSize - 1, inOrderMap);// 左树
		process(pre, in, pos, l1 + leftSize + 1, r1, headIndex + 1, r2, l3 + leftSize, r3 - 1, inOrderMap);// 右树
	}
}
