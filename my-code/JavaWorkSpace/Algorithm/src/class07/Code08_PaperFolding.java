package class07;

public class Code08_PaperFolding {
	
	public static void paperFolding(int N) {
		if(N==0) {
			return;
		}
		printFold(1,N,true);
	}
	public static void printFold(int n,int N,boolean down) {
		if(n>N) {
			return;
		}
		printFold(n+1, N, true);
		System.out.print((down?"凹":"突")+" ");
		printFold(n+1, N, false);
	}
	
	public static void main(String[] args) {
		int N = 3;
		paperFolding(N);
	}
}
