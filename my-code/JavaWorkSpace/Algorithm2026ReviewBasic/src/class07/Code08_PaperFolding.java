package class07;

public class Code08_PaperFolding {
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
}
