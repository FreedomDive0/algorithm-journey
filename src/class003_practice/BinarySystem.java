package class003_practice;

public class BinarySystem {
	public static void printBinary(int num) {
		for (int i = 31; i >= 0; i--) {
//			System.out.print((num & (1 << i)) == 1 ? "1" : "0"); //这个数不一定是1
			System.out.print((num & (1 << i)) != 0 ? "1" : "0");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		printBinary(2);
		int i = 0b10000000000000000000000000000010;
		printBinary(i >> 2);
	}
}
