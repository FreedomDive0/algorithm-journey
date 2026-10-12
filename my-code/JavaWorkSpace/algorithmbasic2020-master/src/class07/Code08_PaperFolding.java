package class07;

public class Code08_PaperFolding {

	public static void printAllFolds(int N) {
		printProcess(1, N, true);
	}

	// 递归过程，来到了某一个节点，
	// i是节点的层数，N一共的层数，down == true 凹 down == false 凸
	public static void printProcess(int i, int N, boolean down) {
		if (i > N) {
			return;
		}
		printProcess(i + 1, N, true);
		System.out.println(down ? "凹 " : "凸 ");
		printProcess(i + 1, N, false);
	}

	// 测试
	// 7.折纸问题
	public static void foldingPaper(int N) {
		fProcess(1, "凹", N);
	}

	public static void fProcess(int idx, String creases, int N) {
		if (idx > N) {
			return;
		}
		fProcess(idx + 1, "凹", N);
		System.out.println(creases);
		fProcess(idx + 1, "凸", N);
	}

	public static void main(String[] args) {
		int N = 5;
		printAllFolds(N);
		System.out.println("=========================");
		foldingPaper(N);
	}

}
