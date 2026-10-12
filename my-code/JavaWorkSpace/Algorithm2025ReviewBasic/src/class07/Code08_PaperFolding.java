package class07;

public class Code08_PaperFolding {
	public static void PaperFolding(int N) {
		process(true, 0, N);
	}

	public static void process(boolean isAo, int i, int N) {
		if (i == N) {
			return;
		}
		process(false, i + 1, N);
		System.out.println(isAo ? "凹" : "凸");
		process(true, i + 1, N);
	}

	public static void main(String[] args) {
		PaperFolding(3);
	}
}
