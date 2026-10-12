package 回溯法与深度优先搜索专题;

public class LC52_N皇后II {
	public static void main(String[] args) {

	}

	public int totalNQueens(int n) {
		if (n < 1 || n > 32) {
			return 0;
		}
//		负数在机器码中用补码存储 
//		-1
//		真码：1000 0000 0000 0000 0000 0000 0000 0001
//		反码: 1111 1111 1111 1111 1111 1111 1111 1110 真码取反
//		补码：1111 1111 1111 1111 1111 1111 1111 1111 反码加1
		int limit = n == 32 ? -1 : (1 << n) - 1;// n皇后 n个1用于计算可用位置 用0排除越界位置 用1保留可用位置
		return process(limit, 0, 0, 0);
	}

	// 列限制 左对角线限制 有对角线限制
	public int process(int limit, int colLimit, int leftDiaLimit, int rightDiaLimit) {
		if (colLimit == limit) {// 所有的列放满了
			return 1;
		}
		int pos = limit & (~(colLimit | leftDiaLimit | rightDiaLimit));// 求出可用位置
		int res = 0;
		while (pos != 0) {
			int mostRightOne = pos & (~pos + 1);
			pos -= mostRightOne;// 减去用过的位置
			res += process(limit, (colLimit | mostRightOne), (leftDiaLimit | mostRightOne) << 1,
					(rightDiaLimit | mostRightOne) >>> 1);
		}
		return res;
	}

}
